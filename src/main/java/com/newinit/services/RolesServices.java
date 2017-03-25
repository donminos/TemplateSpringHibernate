
package com.newinit.services;

import com.newinit.controller.view.roles.RolesViewListItem;
import com.newinit.dao.RolesDao;
import com.newinit.entity.Roles;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class RolesServices {
    @Autowired
    RolesDao rolesdao;
    
    public List<RolesViewListItem> findAll(){
        List<Roles> roles=rolesdao.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<RolesViewListItem> rolesview = modelMapper.map(roles, List.class);
        return rolesview;
    }
}
