package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.model.BillModel;
import com.example.CompuCom2.service.impl.BillServiceImpl;
import com.example.CompuCom2.service.impl.ShippingServiceImpl;
import com.example.CompuCom2.service.impl.StatusShippingServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    @Qualifier("statusShippingServiceImpl")
    private StatusShippingServiceImpl statusShippingService;

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

    @GetMapping("/pendings")
    public ModelAndView pendingShippings(){
        ModelAndView mav = new ModelAndView(Constants.SPENDING);
        mav.addObject("billsPending", shippingService.getBillsByShippingStatus("Pendiente"));
        mav.addObject("billsInProgress", shippingService.getBillsByShippingStatus("En camino"));
        return mav;
    }

    @RequestMapping("/searchshipping")
    public ModelAndView search(@RequestParam(name = "id", required = false) Integer id){
        LOG.info("METHOD: search() --PARAMS: id=" + id);
        ModelAndView mav = new ModelAndView(Constants.SEARCHSHIPPING);
        if (id != null){
            BillModel bill;
            bill = billService.findBill(id);
            if (bill != null) {
                mav.addObject("bill", bill);
            }else{
                mav.addObject("notFound", 1);
            }
        }else{
            mav.addObject("bill", null);
        }

        mav.addObject("search", id);
        return mav;
    }

    @RequestMapping("/updatestatus")
    public ModelAndView updateStatus(@RequestParam(name = "id_ss", required = true) Integer id_ss,
                                     @RequestParam(name = "status", required = true) String status){
        LOG.info("METHOD: updateStatus() --PARAMS: id_ss="+id_ss + ", status="+status);
        ModelAndView mav = new ModelAndView("redirect:/shippings/pendings");
        statusShippingService.updateStatusShipping(id_ss, status);
        return mav;
    }
}
