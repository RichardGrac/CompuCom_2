package com.example.CompuCom2.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

    public static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);

    // Se ejecuta antes de entrar al método del Controllador
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis()); // Guarda el tiempo actual en millisegundos
        return true;
    }

    // Se ejecuta justo antes de lanzar la vista al navegador
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long) request.getAttribute("startTime");
        LOG.info("--Url to: '" + request.getRequestURL().toString() + "' --in: '" +
                (System.currentTimeMillis() - startTime) + "' ms.");
    }
}
