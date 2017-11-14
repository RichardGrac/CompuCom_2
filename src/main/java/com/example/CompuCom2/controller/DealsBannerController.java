package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.model.DealsBannerModel;
import com.example.CompuCom2.service.DealsBannerService;
import com.example.CompuCom2.utils.storage.StorageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/banner")
public class DealsBannerController {

    private final DealsBannerService dealsBannerService;
    private final StorageService storageService;
    private final Log LOG = LogFactory.getLog(DealsBannerController.class);


    @Autowired
    public DealsBannerController(DealsBannerService dealsBannerService,
                                 StorageService storageService){
        this.dealsBannerService = dealsBannerService;
        this.storageService = storageService;
    }

    @GetMapping("/show-deals")
    public ModelAndView showDeals(@RequestParam(name = "save", required = false) boolean save,
                                  @RequestParam(name = "delete", required = false) boolean delete){
        LOG.info("METHOD: showDeals(), --PARAMS:");
        ModelAndView modelAndView = new ModelAndView(Constants.DEALS_BANNER);
        modelAndView.addObject("deal", new DealsBannerModel());
        modelAndView.addObject("deals", dealsBannerService.findAll());
        modelAndView.addObject("save" , save);
        modelAndView.addObject("delete" , delete);
        return modelAndView;
    }

    @PostMapping("/save-deals")
    public ModelAndView addDeals(@ModelAttribute(name = "deal") DealsBannerModel dealsBannerModel){
        LOG.info("METHOD: addDeals(), --PARAMS: " + dealsBannerModel.toString());
        ModelAndView modelAndView = new ModelAndView("redirect:/banner/show-deals");
        if (dealsBannerService.saveDealBanner(dealsBannerModel) != null){
            modelAndView.addObject("save", true);
        }else {
            modelAndView.addObject("save", false);
        }
        return modelAndView;
    }

    @GetMapping("/files")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@RequestParam Integer id) {
        String filename = dealsBannerService.findImageById(id);
        if (filename != null){
            Resource file = storageService.loadAsResource(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + file.getFilename() + "\"")
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(file);
        }
        return null;
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam(name = "id") Integer id){
        String filename = dealsBannerService.findImageById(id);
        if (filename != null) {
            try {
                byte [] file1 = Files.readAllBytes(storageService.load(filename));
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @GetMapping("/change-status")
    @ResponseBody
    public void statusBanner(@RequestParam(name = "id") Integer id){
        LOG.info("METHOD: statusBanner(), --PARAMS: id = " + id);
        dealsBannerService.changeStatusById(id);
    }

    @GetMapping("/delete-banner")
    public ModelAndView deleteBanner(@RequestParam(name = "id") Integer id){
        ModelAndView modelAndView = new ModelAndView("redirect:/banner/show-deals");
        dealsBannerService.deleteDealBannerById(id);
        modelAndView.addObject("delete", true);
        return modelAndView;
    }
}
