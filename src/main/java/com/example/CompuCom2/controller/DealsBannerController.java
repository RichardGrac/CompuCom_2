package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.model.DealsBannerModel;
import com.example.CompuCom2.service.DealsBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/banner")
public class DealsBannerController {

    private final DealsBannerService dealsBannerService;

    @Autowired
    public DealsBannerController(DealsBannerService dealsBannerService){
        this.dealsBannerService = dealsBannerService;
    }

    @GetMapping("/show-deals")
    public ModelAndView showDeals(@RequestParam(name = "save", required = false) boolean save){
        ModelAndView modelAndView = new ModelAndView(Constants.DEALS_BANNER);
        modelAndView.addObject("deals", dealsBannerService.findAll());
        modelAndView.addObject("save" , save);
        return modelAndView;
    }

    @PostMapping("/save-deals")
    public ModelAndView addDeals(@ModelAttribute("deal") DealsBannerModel dealsBannerModel){
        ModelAndView modelAndView = new ModelAndView("redirect:/banner/show-deals");
        if (dealsBannerService.saveDealBanner(dealsBannerModel) != null){
            modelAndView.addObject("save", true);
        }else {
            modelAndView.addObject("save", false);
        }

        return modelAndView;
    }
}
