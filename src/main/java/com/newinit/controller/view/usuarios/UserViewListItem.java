package com.newinit.controller.view.usuarios;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ceasar
 */
public class UserViewListItem implements Serializable {

    private Integer idUser;
    private String user;
    private List<String> roles;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
