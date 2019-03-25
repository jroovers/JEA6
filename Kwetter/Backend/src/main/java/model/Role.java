package model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Jeroen Roovers
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "role.FindByName", query = "Select r From Role r WHERE r.name = :name")
})
public class Role implements Serializable, Comparable<Role> {

    public Role(String name) {
        this.name = name;
        this.permissions = new TreeSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    @ManyToMany
    private Set<Permission> permissions;

    @Override
    public int compareTo(Role o) {
       return this.name.compareTo(o.name);
    }
    
    
}
