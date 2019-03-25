package model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
@NamedQueries({
    @NamedQuery(name = "user.FindByUsername", query = "Select u From User u WHERE u.username = :username")
})
public class User implements Serializable {

    // non optionals
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String passwordHash;
    @ManyToMany
    private Set<Role> roles;

    // optionals
    @Lob
    private Byte[] image;
    private String name;
    private String location;
    private String website;
    private String biography;

    @ManyToMany
    @JoinTable(
            name = "followers",
            joinColumns = @JoinColumn(name = "follower"),
            inverseJoinColumns = @JoinColumn(name = "following")
    )
    private List<User> followers;

    @ManyToMany(mappedBy = "followers")
    private List<User> following;

    @OneToMany(mappedBy = "author")
    private List<Kweet> kweets;

    @Override
    public String toString() {
        return username;
    }
}
