package view;

import domain.model.Kweet;
import view.utility.ViewUtilities;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
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
        if (selected != null) {
            try {
                kweetDao.delete(selected);
                ViewUtilities.addSuccessMessage(getText("UpdatedItem"));
            } catch (Exception ex) {
                ViewUtilities.addErrorMessage(ex, getText("PersistenceErrorOccured"));
            }
        }
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

    private String getText(String key) {
        return ResourceBundle.getBundle("/i18n/text").getString(key);
    }
}
