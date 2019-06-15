package view;

import domain.model.Permission;
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
import domain.dao.PermissionDao;
import domain.dao.qualifiers.JPA;

@Named("permissionController")
@SessionScoped
public class PermissionController implements Serializable {

    @Inject
    @JPA
    private PermissionDao permissionCrud;
    private List<Permission> items = null;
    private Permission selected;

    public PermissionController() {
    }

    public Permission getSelected() {
        return selected;
    }

    public void setSelected(Permission selected) {
        this.selected = selected;
    }

    private PermissionDao getDao() {
        return permissionCrud;
    }

    public List<Permission> getItems() {
        if (items == null) {
            items = getDao().getAll();
        }
        return items;
    }

    public Permission getPermission(java.lang.Long id) {
        return getDao().getById(id);
    }

    public List<Permission> getItemsAvailableSelectMany() {
        return getDao().getAll();
    }

    public List<Permission> getItemsAvailableSelectOne() {
        return getDao().getAll();
    }

    @FacesConverter(forClass = Permission.class)
    public static class PermissionControllerConverter implements Converter {
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PermissionController controller = (PermissionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "permissionController");
            return controller.getPermission(getKey(value));
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
            if (object instanceof Permission) {
                Permission o = (Permission) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Permission.class.getName()});
                return null;
            }
        }

    }

}
