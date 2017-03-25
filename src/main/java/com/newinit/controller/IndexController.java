package com.newinit.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ceasar
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request) {
        model.addAttribute("Error", "none");
        if (Boolean.valueOf(request.getParameter("error-auth"))) {
            model.addAttribute("Error", "101");
        } else if (Boolean.valueOf(request.getParameter("blocked"))) {
            model.addAttribute("Error", "102");
        }
        return "index";
    }
}
