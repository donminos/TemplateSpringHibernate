package com.newinit.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
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
public class PrincipalController {

    private final static Logger log = Logger.getLogger(PrincipalController.class);
    
    @Secured("ROLE_SUPERADMIN")
    @RequestMapping(value = "/principal", method = RequestMethod.GET)
    public String principal(ModelMap model, HttpServletRequest request) {
        return "principal";
    }
}
