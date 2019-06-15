package view;

import domain.model.Kweet;
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
import domain.dao.KweetDao;
import domain.dao.qualifiers.JPA;

@Named("kweetController")
@SessionScoped
public class KweetController implements Serializable {

    @Inject
    @JPA
    private KweetDao kweetCrud;
    private List<Kweet> items = null;
    private Kweet selected;

    public KweetController() {
    }

    public Kweet getSelected() {
        return selected;
    }

    public void setSelected(Kweet selected) {
        this.selected = selected;
    }

    private KweetDao getDao() {
        return kweetCrud;
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/i18n/text").getString("DeletedItem"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Kweet> getItems() {
        if (items == null) {
            items = kweetCrud.getAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
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

    public Kweet getKweet(java.lang.Long id) {
        return getDao().getById(id);
    }

    public List<Kweet> getItemsAvailableSelectMany() {
        return getDao().getAll();
    }

    public List<Kweet> getItemsAvailableSelectOne() {
        return getDao().getAll();
    }

    @FacesConverter(forClass = Kweet.class)
    public static class KweetControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            KweetController controller = (KweetController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "kweetController");
            return controller.getKweet(getKey(value));
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
            if (object instanceof Kweet) {
                Kweet o = (Kweet) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Kweet.class.getName()});
                return null;
            }
        }

    }

}
