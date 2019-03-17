package model;

/**
 *
 * @author Jeroen Roovers
 */
public class Role {

    private int id;
    private boolean allowedToViewUsers;
    private boolean allowedToDeleteKweets;
    private boolean allowedToManageRoles;
    private boolean allowedToAssignRoles;

    public Role() {

    }

    public Role(boolean viewUsers, boolean deleteKweets, boolean manageRoles, boolean assignRoles) {
        this.allowedToViewUsers = viewUsers;
        this.allowedToDeleteKweets = deleteKweets;
        this.allowedToManageRoles = manageRoles;
        this.allowedToAssignRoles = assignRoles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAllowedToViewUsers() {
        return allowedToViewUsers;
    }

    public void setAllowedToViewUsers(boolean allowedToViewUsers) {
        this.allowedToViewUsers = allowedToViewUsers;
    }

    public boolean isAllowedToDeleteKweets() {
        return allowedToDeleteKweets;
    }

    public void setAllowedToDeleteKweets(boolean allowedToDeleteKweets) {
        this.allowedToDeleteKweets = allowedToDeleteKweets;
    }

    public boolean isAllowedToManageRoles() {
        return allowedToManageRoles;
    }

    public void setAllowedToManageRoles(boolean allowedToManageRoles) {
        this.allowedToManageRoles = allowedToManageRoles;
    }

    public boolean isAllowedToAssignRoles() {
        return allowedToAssignRoles;
    }

    public void setAllowedToAssignRoles(boolean allowedToAssignRoles) {
        this.allowedToAssignRoles = allowedToAssignRoles;
    }

}
