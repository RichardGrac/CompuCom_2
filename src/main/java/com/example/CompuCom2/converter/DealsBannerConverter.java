package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.DealsBanner;
import com.example.CompuCom2.model.DealsBannerModel;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DealsBannerConverter {

    public DealsBanner modelToEntity(DealsBannerModel dealsBannerModel){
        DealsBanner dealsBanner = new DealsBanner();
        dealsBanner.setId(dealsBannerModel.getId());
        dealsBanner.setActive(dealsBannerModel.getActive());
        dealsBanner.setContent(dealsBannerModel.getContent());
        dealsBanner.setTitle(dealsBannerModel.getTitle());
        dealsBanner.setImage(StringUtils.cleanPath(dealsBannerModel.getImage().getOriginalFilename()));
        return dealsBanner;
    }

    public DealsBannerModel entityToModel(DealsBanner dealsBanner){
        DealsBannerModel dealsBannerModel = new DealsBannerModel();
        dealsBannerModel.setId(dealsBanner.getId());
        dealsBannerModel.setActive(dealsBanner.getActive());
        dealsBannerModel.setContent(dealsBanner.getContent());
        dealsBannerModel.setTitle(dealsBanner.getTitle());
        return dealsBannerModel;
    }
}
