package com.example.CompuCom2.listener;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.annotation.WebListener;

@Configuration
@WebListener
public class CompuComRequestContextListener extends RequestContextListener {
}
