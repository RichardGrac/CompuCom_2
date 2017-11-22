package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.model.BillModel;
import com.example.CompuCom2.service.impl.BillServiceImpl;
import com.example.CompuCom2.service.impl.ShippingServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shippings")
public class ShippingsController {

    public static final Log LOG = LogFactory.getLog(ShippingsController.class);

    @Autowired
    @Qualifier("shippingServiceImpl")
    private ShippingServiceImpl shippingService;

    @Autowired
    @Qualifier("billServiceImpl")
    private BillServiceImpl billService;

    @GetMapping("/finished")
    public ModelAndView shippingsFinished(){
        ModelAndView mav = new ModelAndView(Constants.SFINISHED);
        mav.addObject("bills", shippingService.getBillsByShippingStatus("Finalizado"));
        return mav;
    }

    @GetMapping("/showshippings")
    public ModelAndView allShippings(){
        ModelAndView mav = new ModelAndView(Constants.SALL);
        mav.addObject("bills", billService.getAllBills());
        return mav;
    }
}
