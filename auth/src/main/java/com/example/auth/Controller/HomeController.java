package com.example.auth.Controller;

import com.example.auth.service.AuthencationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("home")
@Slf4j
public class HomeController {
    private final AuthencationFacade authencationFacade;

    public HomeController(AuthencationFacade authencationFacade) {
        this.authencationFacade = authencationFacade;
    }

    @GetMapping
    public String home(Authentication principal){
        try{
           // log.info("connected user {}",principal.getName());
            log.info("connected user {}",authencationFacade.getUserName());
        }catch (NullPointerException e){
            log.info("no user");
        }
        return "index";
    }
}
