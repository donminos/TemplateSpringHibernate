package com.newinit.controller.rest;

import com.newinit.controller.view.JsonResponseView;
import com.newinit.controller.view.usuarios.UserViewCreate;
import com.newinit.services.UsersServices;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author carlos
 */
@RestController
@RequestMapping("/usuarios/")
public class UsuariosRestController {

    private final static Logger log = Logger.getLogger(UsuariosRestController.class);

    @Autowired
    UsersServices userservices;

    @RequestMapping(value = "CreateUpdate.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponseView findName(HttpServletRequest request,@Validated @RequestBody UserViewCreate usercreate) {
        JsonResponseView json = new JsonResponseView();
        try {
            userservices.merge(usercreate);
        } catch (NoResultException ex) {
            log.error("Error-Rest-Testing:");
            json.getResponse().put("success", false);
        }
        return json;
    }
}
