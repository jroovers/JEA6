package model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
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
        this.author.getKweets().add(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User author;
    private String body;
    private ZonedDateTime createdTime;
    private List<User> mentions;
    private List<User> likedBy;
    private List<String> tags;
}
