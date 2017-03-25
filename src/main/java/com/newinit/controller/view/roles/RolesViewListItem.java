package com.newinit.controller.view.roles;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class RolesViewListItem implements Serializable {

    private Integer idRol;
    private String rol;
    private String description;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
