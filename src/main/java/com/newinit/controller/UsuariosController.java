package com.newinit.controller;

import com.newinit.controller.view.roles.RolesViewListItem;
import com.newinit.controller.view.usuarios.UserViewListItem;
import com.newinit.services.RolesServices;
import com.newinit.services.UsersServices;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ceasar
 */
@Controller
public class UsuariosController {

    private final static Logger log = Logger.getLogger(PrincipalController.class);

    @Autowired
    UsersServices userservices;

    @Autowired
    RolesServices rolesservices;

    @Secured("ROLE_SUPERADMIN")
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public String principal(ModelMap model, HttpServletRequest request) {
        List<UserViewListItem> users = userservices.findAll();
        List<RolesViewListItem> roles = rolesservices.findAll();
        model.addAttribute("users", users);
        model.addAttribute("Roles", roles);
        return "usuarios";
    }
}
