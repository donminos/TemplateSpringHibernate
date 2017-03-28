package com.newinit.dao;

import com.newinit.entity.Roles;
import com.newinit.entity.Users;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ceasar
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class UsersDao extends AbstractDAO<Users> {

    public UsersDao() {
        super(Users.class);
    }
    private final static Logger log = Logger.getLogger(UsersDao.class);

    public Users findName(String username) throws NoResultException {
        log.debug("Accediendo a UserDao findName");
        Query query = em.createQuery("SELECT u FROM Users u WHERE u.user=:username", Users.class);
        query.setParameter("username", username);
        return (Users) query.getSingleResult();
    }

    public void mergeCascade(Users user) {
        Users newuser=em.merge(user);
        for(Roles rol:user.getRolesList()){
            Query query=em.createNativeQuery("INSERT INTO Permissions (idUser, idRol) VALUES (?, ?)");
            query.setParameter(1,newuser.getIdUser());
            query.setParameter(2,rol.getIdRol());
            query.executeUpdate();
        }
    }
}
