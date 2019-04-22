package domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jeroen Roovers
 */
@Getter
@Setter
public class MutualFriendDTO {
    
    private Long id;
    private String biography;
    private String location;
    private String name;
    private String username;
    private String website;
    private Long nr;

    public MutualFriendDTO(Long id, String biography, String location, String name, String username, String website, Long nr) {
        this.id = id;
        this.biography = biography;
        this.location = location;
        this.name = name;
        this.username = username;
        this.website = website;
        this.nr = nr;
    }
    
}
