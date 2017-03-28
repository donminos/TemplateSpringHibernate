package com.newinit.controller.view.usuarios;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author carlos
 */
public class UserViewCreate implements Serializable {

    private Integer idUser;

    @NotEmpty
    @Size(min = 5, max = 45)
    private String user;
    @NotEmpty
    @Size(min = 8, max = 45)
    private String password;

    private List<Integer> roles;

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
