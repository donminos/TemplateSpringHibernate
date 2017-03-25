
package com.newinit.dao;

import com.newinit.entity.UserRol;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ceasar
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class UserRolDao extends AbstractDAO<UserRol> {   
    
    public UserRolDao() {
        super(UserRol.class);
    }
    
}
