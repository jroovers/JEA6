package view;

import domain.dao.PermissionDao;
import domain.model.Role;
import view.utility.JsfUtil;
import view.utility.JsfUtil.PersistAction;

import java.io.Serializable;
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
import org.primefaces.model.DualListModel;
import domain.dao.RoleDao;
import domain.dao.qualifiers.JPA;
import domain.model.Permission;
import java.util.ArrayList;
import java.util.HashSet;

@Named("roleController")
@SessionScoped
public class RoleController implements Serializable {

    @Inject
    @JPA
    private RoleDao roleCrud;

    @Inject
    @JPA
    private PermissionDao permCrud;

    private DualListModel<Role> roles;

    private List<String> selectedPermissions;
    private List<String> allPermissions;

    private List<Role> items = null;
    private Role selected;

    public RoleController() {
    }

    public List<String> getSelectedPermissions() {
        if (this.selectedPermissions == null) {
            this.selectedPermissions = new ArrayList<>();
        }
        // here be dragons :(
        int size = this.selected.getPermissions().size();
        if (this.selectedPermissions.size() != size) {
            this.selectedPermissions.clear();
            for (Permission p : this.selected.getPermissions()) {
                this.selected.getPermissions();
                this.selectedPermissions.add(p.getName());
            }
        }
        return selectedPermissions;
    }

    public void setSelectedPermissions(List<String> selectedRoles) {
        this.selectedPermissions = selectedRoles;
    }

    public List<String> getAllPermissions() {
        if (this.allPermissions == null) {
            this.allPermissions = new ArrayList<>();
            for (Permission p : permCrud.getAll()) {
                this.allPermissions.add(p.getName());
            }
        }
        return this.allPermissions;
    }

    public Role getSelected() {
        return selected;
    }

    public void setSelected(Role selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        HashSet<Permission> newPermissions = new HashSet<>();
        for (Permission p : permCrud.getAll()) {
            if (selectedPermissions.contains(p.getName())) {
                newPermissions.add(p);
            }
        }
        this.selected.setPermissions(newPermissions);
    }

    protected void initializeEmbeddableKey() {
    }

    private RoleDao getDao() {
        return roleCrud;
    }

    public Role prepareCreate() {
        selected = new Role();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/i18n/text").getString("UpdatedItem"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/i18n/text").getString("UpdatedItem"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/i18n/text").getString("UpdatedItem"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Role> getItems() {
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

    public Role getRole(java.lang.Long id) {
        return getDao().getById(id);
    }

    public List<Role> getItemsAvailableSelectMany() {
        return getDao().getAll();
    }

    public List<Role> getItemsAvailableSelectOne() {
        return getDao().getAll();
    }

    @FacesConverter(forClass = Role.class)
    public static class RoleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RoleController controller = (RoleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "roleController");
            return controller.getRole(getKey(value));
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
            if (object instanceof Role) {
                Role o = (Role) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Role.class.getName()});
                return null;
            }
        }

    }

}
