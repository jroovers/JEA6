package domain.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "kweet.getByUserId", query = "SELECT k from Kweet k WHERE k.author.id = :userId ORDER BY k.id")
})
public class Kweet implements Serializable {

    public Kweet(User author, String body) {
        this.author = author;
        this.body = body;
        this.createdTime = ZonedDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User author;
    private String body;
    private ZonedDateTime createdTime;
}
