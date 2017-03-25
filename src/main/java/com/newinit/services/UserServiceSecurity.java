
package com.newinit.services;

import com.newinit.dao.RolesDao;
import com.newinit.dao.UsersDao;
import com.newinit.entity.Roles;
import com.newinit.entity.Users;
import com.newinit.utilidades.Util;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Cesar Rosas<face_less@hotmail.com>
 */
@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserServiceSecurity implements UserDetailsService{
    
    @Autowired
    UsersDao userservices;
    
    @Autowired
    RolesDao roleservices;
    
    @Autowired
    LoginAttemptService loginAttemptService;
    
    @Autowired
    private HttpServletRequest request;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        String ip = Util.getClientIpAddr(request);
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
        Users user=userservices.findName(username);
        List<Roles> roles=roleservices.findForUser(user.getIdUser());
        List<String> rolesSelect=new ArrayList();
        for(Roles ur:roles){
            rolesSelect.add(ur.getRol());
        }
        List<GrantedAuthority> authList = getGrantedAuthorities(rolesSelect);
        UserDetails userdetail = new User(user.getUser(), user.getPassword(), true, true, true, true, authList);
        return userdetail;
    }
    
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
