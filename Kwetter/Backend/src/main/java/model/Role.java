package model;

import java.util.Set;

/**
 *
 * @author Jeroen Roovers
 */
public class Role {

    private Long id;
    private String name;
    private Set<User> users;
    private Set<Privilege> privileges;

    public Role() {

    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
}
