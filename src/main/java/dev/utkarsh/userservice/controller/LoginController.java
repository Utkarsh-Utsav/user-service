package dev.utkarsh.userservice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
public class LoginController {
    @GetMapping("/user/")
    public String currentUserName( Principal principal) {
        return principal.getName();
    }

    @RequestMapping("/logout/")
    public String bye(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "Bye" + name;
    }
}

