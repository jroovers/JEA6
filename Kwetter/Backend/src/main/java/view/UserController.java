package view;

import domain.model.User;
import view.utility.ViewUtilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
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
    private UserDao userDao;
    
    @Inject
    @JPA
    private RoleDao roleCrud;
    
    private List<User> items = null;
    private User selected;
    
    private List<String> selectedUserRoles;
    private List<String> allRoles;
    
    public UserController() {
    }
    
    public List<String> getSelectedUserRoles() {
        if (this.selectedUserRoles == null) {
            this.selectedUserRoles = new ArrayList<>();
        }
        // here be dragons :(
        int size = this.selected.getRoles().size();
        if (this.selectedUserRoles.size() != size) {
            this.selectedUserRoles.clear();
            for (Role r : this.selected.getRoles()) {
                this.selected.getRoles();
                this.selectedUserRoles.add(r.getName());
            }
        }
        return selectedUserRoles;
    }
    
    public void setSelectedUserRoles(List<String> selectedUserRoles) {
        this.selectedUserRoles = selectedUserRoles;
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
            if (selectedUserRoles.contains(r.getName())) {
                newroles.add(r);
            }
        }
        this.selected.setRoles(newroles);
    }
    
    public User prepareCreate() {
        selected = new User();
        return selected;
    }
    
    public void update() {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                userDao.update(selected);
                ViewUtilities.addSuccessMessage(getText("UpdatedItem"));
                
            } catch (Exception ex) {
                ViewUtilities.addErrorMessage(ex, getText("PersistenceErrorOccured"));
            }
        }
    }
    
    public List<User> getItems() {
        if (items == null) {
            items = userDao.getAll();
        }
        return items;
    }
    
    public String getText(String key) {
        return ResourceBundle.getBundle("/i18n/text").getString(key);
    }
}
