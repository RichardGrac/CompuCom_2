package com.example.CompuCom2.listener;

import com.example.CompuCom2.model.UserModel;
import com.example.CompuCom2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
        UserModel userModel = userService.findUserModelByEmail(userDetails.getUsername());
        httpSession.setAttribute("userGlobal", userModel);
    }
}
