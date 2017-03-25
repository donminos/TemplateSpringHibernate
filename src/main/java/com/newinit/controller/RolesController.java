package com.newinit.controller;

import javax.servlet.http.HttpServletRequest;
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
public class RolesController {

    @Secured("ROLE_SUPERADMIN")
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String principal(ModelMap model, HttpServletRequest request) {
        return "roles";
    }
}
