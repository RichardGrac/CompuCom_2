package com.example.CompuCom2.service;

import com.example.CompuCom2.entity.DealsBanner;
import com.example.CompuCom2.model.DealsBannerModel;

import java.util.List;

public interface DealsBannerService {
    DealsBannerModel saveDealBanner(DealsBannerModel dealsBannerModel);
    DealsBannerModel updateDealBanner(DealsBannerModel dealsBannerModel);
    List<DealsBannerModel> findAll();
    DealsBannerModel findOne(Integer id);
    void deleteDealBannerById(Integer id);
    String findImageById(Integer id);
    void changeStatusById(Integer id);
    List<DealsBanner> findActive();
}
