package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.repository.Estadisticas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Estadisticas estadisticas;

    @Autowired
    AdminController(Estadisticas estadisticas){
        this.estadisticas= estadisticas;
    }

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView(Constants.ADMIN_INDEX);
        modelAndView.addObject("month", LocalDate.now().getMonth().name());
        modelAndView.addObject("topProduct", estadisticas.topProductHistory());
        modelAndView.addObject("saleProductCurrentMonth", estadisticas.salesPerProductInThCurrentMonth());
        modelAndView.addObject("productLowAvailability" , estadisticas.productsWithLittleAvailability());
        return modelAndView;
    }
}
