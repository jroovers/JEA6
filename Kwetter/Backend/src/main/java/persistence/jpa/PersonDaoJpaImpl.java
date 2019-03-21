/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.jpa;

import javax.ejb.Stateless;
import model.Person;
import persistence.JPA;
import persistence.UserDao;

@Stateless
@JPA
public class PersonDaoJpaImpl extends BaseDaoJpa<Person> implements UserDao {

    @Override
    public Person getByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
