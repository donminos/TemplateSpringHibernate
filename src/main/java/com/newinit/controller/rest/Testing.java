package com.newinit.controller.rest;

import com.newinit.controller.view.JsonResponseView;
import com.newinit.services.UsersServices;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Carlos Cesar Rosas<face_less@hotmail.com>
 */
@RestController
public class Testing {

    private final static Logger log = Logger.getLogger(Testing.class);

    @Autowired
    UsersServices userservices;

    @RequestMapping(value = "/User/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponseView findName(HttpServletRequest request, @PathVariable(value = "username") String username) {
        JsonResponseView json = new JsonResponseView();
        try {
            json.getResponse().put("id", userservices.findName(username));
        } catch (NoResultException ex) {
            log.error("Error-Rest-Testing:");
            json.getResponse().put("success", false);
        }
        return json;
    }
}
