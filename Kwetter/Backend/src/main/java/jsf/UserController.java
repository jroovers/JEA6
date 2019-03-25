package jsf;

import model.User;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import model.Role;
import org.primefaces.model.DualListModel;
import persistence.RoleDao;
import persistence.UserDao;
import persistence.qualifiers.JPA;

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

    //roles viewer
    private DualListModel<Role> notusedRoles;

    public DualListModel<Role> getRoles() {
        if (selected != null) {
            List<Role> allRoles = roleCrud.getAll();
            List<Role> setRoles = new ArrayList<>(userCrud.getById(selected.getId()).getRoles());
            for(Role r : setRoles){
                allRoles.remove(r);
            }
            return new DualListModel<>(allRoles, setRoles);
        } else {
            return new DualListModel<>(new ArrayList<>(), new ArrayList<>());

        }
    }

    public void setRoles(DualListModel<Role> roles) {
        // dont do aynthing dont care lol
    }

    public UserController() {
    }

    public User getSelected() {
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserDao getDao() {
        return userCrud;
    }

    public User prepareCreate() {
        selected = new User();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/i18n/text").getString("UserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/i18n/text").getString("UserUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/i18n/text").getString("UserDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
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
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/i18n/text").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/i18n/text").getString("PersistenceErrorOccured"));
            }
        }
    }

    public User getUser(java.lang.Long id) {
        return getDao().getById(id);
    }

    public List<User> getItemsAvailableSelectMany() {
        return getDao().getAll();
    }

    public List<User> getItemsAvailableSelectOne() {
        return getDao().getAll();
    }

    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.getUser(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), User.class.getName()});
                return null;
            }
        }

    }
}
