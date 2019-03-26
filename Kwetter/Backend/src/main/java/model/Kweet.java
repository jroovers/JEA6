package model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Kweet implements Serializable {

    public Kweet(User author, String body) {
        this.author = author;
        this.body = body;
        this.createdTime = ZonedDateTime.now();
        // keep at end of constructor
        // okay this isn't actually needed because item is the owning side of the relationship...
        // this.author.getKweets().add(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User author;
    private String body;
    private ZonedDateTime createdTime;
}
