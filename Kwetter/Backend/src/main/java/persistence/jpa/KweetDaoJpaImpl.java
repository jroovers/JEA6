/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.jpa;

import javax.ejb.Stateless;
import model.Kweet;
import persistence.JPA;
import persistence.KweetDao;

@Stateless
@JPA
public class KweetDaoJpaImpl extends BaseDaoJpa<Kweet> implements KweetDao {

}
