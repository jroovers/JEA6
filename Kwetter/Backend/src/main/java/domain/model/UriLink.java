package domain.model;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UriLink {
    String rel;
    String url;
}
