package com.newinit.services.security;

import com.newinit.services.LoginAttemptService;
import com.newinit.utilidades.Util;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Einteligent
 */
@Component("authenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);
        String ip = Util.getClientIpAddr(request);
        if (ip != null) {
            loginAttemptService.loginFailed(ip);
        }
        if (loginAttemptService.isBlocked(ip)) {
            setDefaultFailureUrl("/?blocked=true");
        } else {
            setDefaultFailureUrl("/?error-auth=true");
        }
        //request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, "");
    }
}
