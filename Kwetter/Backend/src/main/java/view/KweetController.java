package view;

import domain.model.Kweet;
import view.utility.ViewUtilities;
import view.utility.ViewUtilities.PersistAction;

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
    private KweetDao kweetDao;
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

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/i18n/text").getString("DeletedItem"));
        if (!ViewUtilities.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Kweet> getItems() {
        if (items == null) {
            items = kweetDao.getAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    kweetDao.update(selected);
                } else {
                    kweetDao.delete(selected);
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

    public Kweet getKweet(Long id) {
        return kweetDao.getById(id);
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
            return controller.getKweet(Long.valueOf(value));
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Kweet) {
                Kweet o = (Kweet) object;
                return o.getId().toString();
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Kweet.class.getName()});
                return null;
            }
        }
    }
}
