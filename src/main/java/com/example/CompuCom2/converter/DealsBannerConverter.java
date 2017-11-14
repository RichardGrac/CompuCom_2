package com.example.CompuCom2.converter;

import com.example.CompuCom2.entity.DealsBanner;
import com.example.CompuCom2.model.DealsBannerModel;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DealsBannerConverter {

    public DealsBanner modelToEntity(DealsBannerModel dealsBannerModel){
        return DealsBanner.builder()
                .id(dealsBannerModel.getId())
                .title(dealsBannerModel.getTitle())
                .content(dealsBannerModel.getContent())
                .active(dealsBannerModel.getActive())
                .image(StringUtils.cleanPath(dealsBannerModel.getImage().getOriginalFilename()))
                .build();
    }

    public DealsBannerModel entityToModel(DealsBanner dealsBanner){
        return DealsBannerModel.builder()
                .id(dealsBanner.getId())
                .title(dealsBanner.getTitle())
                .content(dealsBanner.getContent())
                .active(dealsBanner.getActive())
                .build();
    }
}
