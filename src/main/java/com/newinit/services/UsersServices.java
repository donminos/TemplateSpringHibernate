package com.newinit.services;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.newinit.controller.view.usuarios.UserViewCreate;
import com.newinit.controller.view.usuarios.UserViewListItem;
import com.newinit.dao.UsersDao;
import com.newinit.entity.Users;
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

    public UserViewListItem findName(String username) {
        Users user = userdao.findName(username);
        ModelMapper modelMapper = new ModelMapper();
        UserViewListItem userview = modelMapper.map(user, UserViewListItem.class);
        return userview;
    }

    public List<UserViewListItem> findAll() {
        List<Users> users = userdao.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<UserViewListItem> usersview = modelMapper.map(users, List.class);
        return usersview;
    }

    public void merge(UserViewCreate userview) {
        ModelMapper modelMapper = new ModelMapper();
        Users user = modelMapper.map(userview, Users.class);
        user.setPassword((Hashing.sha256().hashString(user.getPassword(), Charsets.UTF_8).toString()));
        userdao.merge(user);
    }
}
