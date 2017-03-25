package com.newinit.dao;

import com.newinit.entity.Roles;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ceasar
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class RolesDao extends AbstractDAO<Roles> {

    public RolesDao() {
        super(Roles.class);
    }
    public List<Roles> findForUser(int idUser) {
        Query query = em.createQuery("SELECT r FROM Roles r INNER JOIN r.userRolList ur INNER JOIN ur.idUser u WHERE u.idUser=:userSelect", Roles.class);
        query.setParameter("userSelect", idUser);
        return query.getResultList();
    }
}
