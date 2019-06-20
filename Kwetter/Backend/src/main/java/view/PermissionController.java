package view;

import domain.model.Permission;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import domain.dao.PermissionDao;
import domain.dao.qualifiers.JPA;

@Named("permissionController")
@SessionScoped
public class PermissionController implements Serializable {

    @Inject
    @JPA
    private PermissionDao permDao;
    private List<Permission> items = null;
    
    public PermissionController() {
    }

    public List<Permission> getItems() {
        if (items == null) {
            items = permDao.getAll();
        }
        return items;
    }
}
