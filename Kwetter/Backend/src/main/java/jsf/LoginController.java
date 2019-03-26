package jsf;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import persistence.UserDao;
import persistence.qualifiers.JPA;
import utility.PasswordStorage;

/**
 *
 * @author Jeroen Roovers
 */
@Named("loginController")
@RequestScoped
public class LoginController {

    @Inject
    @JPA
    UserDao dao;

    private String username;
    private String password;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request.getRemoteUser() != null) {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            try {
                // Already logged in, so redirect to some main page.
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Welkom terug, " + this.username, username));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                response.sendRedirect("admin/admin_index.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(this.username, checkPassword(this.username, this.password));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Welkom terug, " + this.username, username));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "admin/admin_index.xhtml?faces-redirect=true";
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login failed.", username));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "error";
        }
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Logout failed.", username));
        }
    }

    private String checkPassword(String username, String password) {
        User u = dao.getByUsername(username);
        if (u != null) {
            try {
                if (PasswordStorage.verifyPassword(password, u.getPasswordHash())) {
                    // good pass :)
                    return u.getPasswordHash();
                } else {
                    return password;
                }
            } catch (PasswordStorage.CannotPerformOperationException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

            } catch (PasswordStorage.InvalidHashException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return password;
    }
}
