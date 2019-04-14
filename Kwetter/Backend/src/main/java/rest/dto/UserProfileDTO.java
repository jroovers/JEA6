package rest.dto;

import domain.model.Kweet;
import domain.model.User;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Jeroen Roovers
 */
@Getter
@Setter
@NoArgsConstructor
public class UserProfileDTO {

    private User user;
    private List<Kweet> kweets;
    private List<User> following;
    private List<User> followers;

    public UserProfileDTO(User u, List<Kweet> kweets, List<User> following, List<User> followers) {
        this.user = u;
        this.kweets = kweets;
        this.following = following;
        this.followers = followers;
    }
}
