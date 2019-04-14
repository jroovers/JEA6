package domain.dao;

import domain.model.Kweet;
import java.util.List;

/**
 *
 * @author Jeroen Roovers
 */
public interface KweetDao extends IBaseDao<Kweet> {
    
    public List<Kweet> getKweetsByUserId(Long id);
    
}
