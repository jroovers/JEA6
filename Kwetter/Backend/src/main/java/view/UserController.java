package view;

import domain.model.User;
import view.utility.ViewUtilities;
import view.utility.ViewUtilities.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import domain.model.Role;
import domain.dao.RoleDao;
import domain.dao.UserDao;
import domain.dao.qualifiers.JPA;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    @Inject
    @JPA
    private UserDao userCrud;

    @Inject
    @JPA
    private RoleDao roleCrud;

    private List<User> items = null;
    private User selected;

    private List<String> selectedRoles;
    private List<String> allRoles;

    public UserController() {
    }

    public List<String> getSelectedRoles() {
        if (this.selectedRoles == null) {
            this.selectedRoles = new ArrayList<>();
        }
        // here be dragons :(
        int size = this.selected.getRoles().size();
        if (this.selectedRoles.size() != size) {
            this.selectedRoles.clear();
            for (Role r : this.selected.getRoles()) {
                this.selected.getRoles();
                this.selectedRoles.add(r.getName());
            }
        }
        return selectedRoles;
    }

    public void setSelectedRoles(List<String> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public List<String> getAllRoles() {
        if (this.allRoles == null) {
            this.allRoles = new ArrayList<>();
            for (Role r : roleCrud.getAll()) {
                this.allRoles.add(r.getName());
            }
        }
        return this.allRoles;
    }

    public User getSelected() {
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        HashSet<Role> newroles = new HashSet<>();
        for (Role r : roleCrud.getAll()) {
            if (selectedRoles.contains(r.getName())) {
                newroles.add(r);
            }
        }
        this.selected.setRoles(newroles);
    }

    private UserDao getDao() {
        return userCrud;
    }

    public User prepareCreate() {
        selected = new User();
        return selected;
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/i18n/text").getString("UpdatedItem"));
    }

    public List<User> getItems() {
        if (items == null) {
            items = getDao().getAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getDao().update(selected);
                } else {
                    getDao().delete(selected);
                }
                ViewUtilities.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    ViewUtilities.addErrorMessage(msg);
                } else {
                    ViewUtilities.addErrorMessage(ex, ResourceBundle.getBundle("/i18n/text").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                ViewUtilities.addErrorMessage(ex, ResourceBundle.getBundle("/i18n/text").getString("PersistenceErrorOccured"));
            }
        }
    }
    
}
