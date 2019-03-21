/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.jpa;

import javax.ejb.Stateless;
import persistence.JPA;

@Stateless
@JPA
public class PrivilegeDaoJpaImpl<Privilege> extends BaseDaoJpa<Privilege> {
    
}
