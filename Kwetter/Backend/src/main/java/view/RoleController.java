package view;

import domain.dao.PermissionDao;
import domain.model.Role;
import view.utility.ViewUtilities;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import domain.dao.RoleDao;
import domain.dao.qualifiers.JPA;
import domain.model.Permission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Named("roleController")
@SessionScoped
public class RoleController implements Serializable {

    @Inject
    @JPA
    private RoleDao roleDao;

    @Inject
    @JPA
    private PermissionDao permDao;

    private List<Permission> selectedPermissions;
    private List<Role> items = null;
    private Role selected;

    public RoleController() {
    }

    public List<Permission> getSelectedPermissions() {
        if (selected != null && selected.getPermissions() != null) {
            List<Permission> activePerms = new ArrayList<>(selected.getPermissions());
            String listString = activePerms.stream().map(Permission::toString)
                    .collect(Collectors.joining(", "));
            System.out.println(listString);
            this.selectedPermissions = activePerms;
            return this.selectedPermissions;
        } else {
            return new ArrayList<>();
        }
    }

    public void setSelectedPermissions(List<Permission> selectedPerms) {
        String listString = selectedPerms.stream().map(Permission::toString)
                .collect(Collectors.joining(", "));
        System.out.println(listString);
        this.selectedPermissions = selectedPerms;
    }

    public Role getSelected() {
        return selected;
    }

    public void setSelected(Role selected) {
        this.selected = selected;
    }

    public List<Permission> getAllPermissions() {
        return permDao.getAll();
    }

    public List<Permission> getPermissionsOfSelectedRole() {
        if (this.selected != null && this.selected.getPermissions() != null) {
            return new ArrayList<>(this.selected.getPermissions());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * When updating or creating roles sets any complex fields such as
     * permissions.
     */
    protected void setEmbeddableKeys() {
        HashSet<Permission> newPermissions = new HashSet<>();
        if (selectedPermissions != null) {
            if (selectedPermissions.size() > 0) {
                for (Permission p : permDao.getAll()) {
                    if (selectedPermissions.contains(p)) {
                        newPermissions.add(p);
                    }
                }
            }
        } else {
            this.selectedPermissions = new ArrayList<>();
        }
        this.selected.setPermissions(newPermissions);
    }

    /**
     * Clears current selections in preperation for creation of entity.
     *
     * @return
     */
    public Role prepareCreate() {
        selected = new Role();
        this.selectedPermissions = null;
        return selected;
    }

    public void update() {
        if (selected != null) {
            try {
                setEmbeddableKeys();
                roleDao.update(selected);
                ViewUtilities.addSuccessMessage(getText("UpdatedItem"));
            } catch (Exception ex) {
                ViewUtilities.addErrorMessage(ex, getText("PersistenceErrorOccured"));
            }
        }
        if (!ViewUtilities.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void destroy() {
        if (selected != null) {
            try {
                setEmbeddableKeys();
                roleDao.delete(selected);
                ViewUtilities.addSuccessMessage(getText("UpdatedItem"));
            } catch (Exception ex) {
                ViewUtilities.addErrorMessage(ex, getText("PersistenceErrorOccured"));
            }
        }
        if (!ViewUtilities.isValidationFailed()) {
            selected = null; // destroyed item no longer exists.
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Role> getItems() {
        if (items == null) {
            items = roleDao.getAll();
        }
        return items;
    }

    public String getText(String key) {
        return ResourceBundle.getBundle("/i18n/text").getString(key);
    }

    private Permission getPermission(Long id) {
        return permDao.getById(id);
    }

    @FacesConverter(forClass = Permission.class, value = "permissionConverter")
    public static class PermissionConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RoleController controller = (RoleController) context.getApplication().getELResolver().
                    getValue(context.getELContext(), null, "roleController");
            return controller.getPermission(Long.valueOf(value));
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Permission) {
                Permission o = (Permission) object;
                return o.getId().toString();
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Permission.class.getName()});
                return null;
            }
        }

    }
}
