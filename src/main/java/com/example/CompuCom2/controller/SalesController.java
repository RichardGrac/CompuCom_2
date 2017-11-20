package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.model.BillModel;
import com.example.CompuCom2.service.impl.BillServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/sales")
public class SalesController {

    public static final Log LOG = LogFactory.getLog(SalesController.class);

    @Autowired
    @Qualifier("billServiceImpl")
    private BillServiceImpl billService;

    @GetMapping("/showsales")
    public ModelAndView showSales(){
        LOG.info("METHOD: showSales() --PARAMS: ");
        ModelAndView mav = new ModelAndView(Constants.SALES);
        ArrayList<BillModel> billModels = billService.getAllBills();
        mav.addObject("bills", billModels);
        return mav;
    }

    @RequestMapping("/searchsale")
    public ModelAndView search(@RequestParam(name = "id", required = false) Integer id){
        LOG.info("METHOD: search() --PARAMS: id=" + id);
        ModelAndView mav = new ModelAndView(Constants.SEARCHSALE);
        if (id != null){
            mav.addObject("bill", billService.findBill(id));
            mav.addObject("search", id);
        }else{
            mav.addObject("search", null);
        }
        return mav;
    }
}
