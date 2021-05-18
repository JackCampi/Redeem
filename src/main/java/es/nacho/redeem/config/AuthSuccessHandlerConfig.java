package es.nacho.redeem.config;

import es.nacho.redeem.service.CompanyService;
import es.nacho.redeem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@Configuration
public class AuthSuccessHandlerConfig implements AuthenticationSuccessHandler {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        String email;
        if(authentication.getPrincipal() instanceof Principal){
            email = ((Principal) authentication.getPrincipal()).getName();
        }else {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            email = user.getUsername();
        }

        long id;
        long nit;
        try{
            nit = companyService.getCompanyNitByUser(email);
            id = userService.getIdByEmail(email);
        }catch (Exception e){
            e.printStackTrace();
            nit = 0L;
            id = 0L;
        }

        httpServletRequest.getSession().setAttribute("nit",  nit);
        httpServletRequest.getSession().setAttribute("id", id);

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(roles.contains("administrador")) httpServletResponse.sendRedirect("/admin");
        else httpServletResponse.sendRedirect("/emp");

    }
}
