package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.entity.ProductCategory;
import com.example.CompuCom2.model.ProductCategoryModel;
import com.example.CompuCom2.service.ProductCategoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;


@Controller
@RequestMapping("/category")
public class ProductCategoryController {

    private static final Log LOG = LogFactory.getLog(ProductCategoryController.class);

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/show-categories")
    public ModelAndView showCategories(@RequestParam(name = "result", required = false) Integer result,
                                       @RequestParam(name = "delete", required = false) Boolean delete,
                                       @RequestParam(name = "nuevo", required = false) Boolean newTrue,
                                       @RequestParam(name = "update", required = false) Boolean update){
        ModelAndView modelAndView = new ModelAndView(Constants.CATEGORY_VIEW);
        modelAndView.addObject("result", result);
        modelAndView.addObject("delete", delete);
        modelAndView.addObject("nuevo", newTrue);
        modelAndView.addObject("update", update);

        modelAndView.addObject("categories", productCategoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/category-form")
    public ModelAndView categoryForm(@RequestParam(name = "id", required = false) Integer id){
        ModelAndView modelAndView = new ModelAndView(Constants.CATEGORY_FORM);
        if (id != 0 ){
            modelAndView.addObject("category", productCategoryService.findOneById(id));
        }else {
            modelAndView.addObject("category", new ProductCategoryModel());
        }
        return modelAndView;
    }

    @PostMapping("/add-category")
    public ModelAndView addCategory(@ModelAttribute(name = "category") ProductCategoryModel productCategoryModel){
        ModelAndView modelAndView = new ModelAndView("redirect:/category/show-categories");
        LOG.info("METHOD: addCategory() --Params: " + productCategoryModel.toString());
        ProductCategoryModel pcm = productCategoryService.save(productCategoryModel);
        if (pcm != null){
            modelAndView.addObject("result", 1);
            if(productCategoryModel.getId() != null){
                modelAndView.addObject("update",true);
            }else {
                modelAndView.addObject("nuevo",true);
            }
        }else {
            modelAndView.addObject("result", 0);
        }
        return modelAndView;
    }

    @GetMapping("/remove-category")
    public ModelAndView deleteCategory(@RequestParam(name = "id", required = true) Integer id){
        ModelAndView modelAndView = new ModelAndView("redirect:/category/show-categories");
        productCategoryService.delete(id);
        modelAndView.addObject("delete", true);
        return modelAndView;
    }
}
