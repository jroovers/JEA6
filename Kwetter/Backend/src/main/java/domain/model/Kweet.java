package domain.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
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
    @NamedQuery(name = "kweet.getByUserId", query = "SELECT k from Kweet k WHERE k.author.id = :userId ORDER BY k.createdTime DESC")
    ,
     @NamedQuery(name = "kweet.getAll", query = "SELECT k from Kweet k ORDER BY k.createdTime DESC")
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

    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime createdTime;

    @Transient
    private List<UriLink> links;
    
    public String getHumanReadableTime(){
        return createdTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
   }
}
