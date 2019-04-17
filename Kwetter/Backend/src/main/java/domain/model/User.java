package domain.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
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
import javax.persistence.UniqueConstraint;
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

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // non optionals
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    @JsonbTransient
    private String passwordHash;

    @ManyToMany
    @JsonbTransient
    private Set<Role> roles;

    // optionals
    @Lob
    private Byte[] image;
    private String name;
    private String location;
    private String website;
    private String biography;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "followers",
            joinColumns = @JoinColumn(name = "follower"),
            inverseJoinColumns = @JoinColumn(name = "following")
    )

    @JsonbTransient
    private List<User> followers;

    @JsonbTransient
    @ManyToMany(mappedBy = "followers", fetch = FetchType.EAGER)
    private List<User> following;

    @JsonbTransient
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Kweet> kweets;

    @Override
    public String toString() {
        return username;
    }
}
