package service;

import java.util.List;
import model.Permission;

/**
 *
 * @author Jeroen Roovers
 */
public interface PermissionService {

    /**
     * Gets all possible permissions that are supported.
     * @return list of permissions
     */
    public List<Permission> getAllPermissions();

}
