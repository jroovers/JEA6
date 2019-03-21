package model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
import javax.persistence.OneToMany;
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
public class Person implements Serializable {

    // non optionals
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String passwordHash;
    @ManyToMany
    private Set<PersonGroup> roles;

    // optionals
    @Lob
    private Byte[] image;
    private String name;
    private String location;
    private String website;
    private String biography;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "followers",
            joinColumns = @JoinColumn(name = "follower"),
            inverseJoinColumns = @JoinColumn(name = "following")
    )
    private List<Person> followers;

    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    private List<Person> following;

    @OneToMany(mappedBy = "author")
    private List<Kweet> kweets;

    @java.lang.SuppressWarnings(value = "all")
    @XmlTransient
    public Set<PersonGroup> getRoles() {
        return roles;
    }

    @java.lang.SuppressWarnings(value = "all")
    @XmlTransient
    public List<Person> getFollowers() {
        return followers;
    }

    @java.lang.SuppressWarnings(value = "all")
    @XmlTransient
    public List<Person> getFollowing() {
        return following;
    }

    @java.lang.SuppressWarnings(value = "all")
    @XmlTransient
    public List<Kweet> getKweets() {
        return kweets;
    }
}
