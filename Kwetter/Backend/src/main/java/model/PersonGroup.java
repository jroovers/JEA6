package model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
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
@XmlRootElement
public class PersonGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<Person> users;
    @ManyToMany
    private Set<Privilege> privileges;

    @java.lang.SuppressWarnings(value = "all")
    @XmlTransient
    public Set<Person> getUsers() {
        return users;
    }

    @java.lang.SuppressWarnings(value = "all")
    @XmlTransient
    public Set<Privilege> getPrivileges() {
        return privileges;
    }
}
