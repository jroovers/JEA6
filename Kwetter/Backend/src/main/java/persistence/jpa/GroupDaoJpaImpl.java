/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.jpa;

import javax.ejb.Stateless;
import model.PersonGroup;
import persistence.JPA;
import persistence.RoleDao;

@Stateless
@JPA
public class GroupDaoJpaImpl extends BaseDaoJpa<PersonGroup> implements RoleDao {

}
