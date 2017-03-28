package com.newinit.services;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.newinit.controller.view.usuarios.UserViewCreate;
import com.newinit.controller.view.usuarios.UserViewListItem;
import com.newinit.dao.RolesDao;
import com.newinit.dao.UsersDao;
import com.newinit.entity.Roles;
import com.newinit.entity.Users;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ceasar
 */
@Service
public class UsersServices {

    @Autowired
    UsersDao userdao;

    @Autowired
    RolesDao rolesdao;

    public Users findId(Integer idUser){
        Users user=userdao.findId(idUser);
        return user;
    }
    
    public UserViewListItem findName(String username) {
        Users user = userdao.findName(username);
        ModelMapper modelMapper = new ModelMapper();
        UserViewListItem userview = modelMapper.map(user, UserViewListItem.class);
        return userview;
    }

    public List<UserViewListItem> findAll() {
        List<Users> users = userdao.findAll();
        List<UserViewListItem> usersview = new ArrayList();
        for (Users user : users) {
            user.setRolesList(null);
            ModelMapper modelMapper = new ModelMapper();
            UserViewListItem view = modelMapper.map(user, UserViewListItem.class);
            List<String> roles = new ArrayList();
            for (Roles rol : rolesdao.findForUser(user.getIdUser())) {
                roles.add(rol.getDescription());
            }
            view.setRoles(roles);
            usersview.add(view);
        }
        return usersview;
    }

    public void merge(UserViewCreate userview) {
        ModelMapper modelMapper = new ModelMapper();
        Users user = modelMapper.map(userview, Users.class);
        List<Roles> permission = new ArrayList();
        for (Integer rol : userview.getRoles()) {
            Roles perm = rolesdao.findId(rol);
            permission.add(perm);
        }
        user.setRolesList(permission);
        user.setPassword((Hashing.sha256().hashString(user.getPassword(), Charsets.UTF_8).toString()));
        userdao.mergeCascade(user);
    }
}
