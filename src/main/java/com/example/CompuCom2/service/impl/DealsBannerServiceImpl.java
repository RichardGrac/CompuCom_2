package com.example.CompuCom2.service.impl;

import com.example.CompuCom2.converter.DealsBannerConverter;
import com.example.CompuCom2.entity.DealsBanner;
import com.example.CompuCom2.model.DealsBannerModel;
import com.example.CompuCom2.repository.DealsBannerRepository;
import com.example.CompuCom2.service.DealsBannerService;
import com.example.CompuCom2.utils.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DealsBannerServiceImpl implements DealsBannerService {

    private final DealsBannerRepository dealsBannerRepository;
    private final StorageService storageService;
    private final DealsBannerConverter dealsBannerConverter;

    @Autowired
    public DealsBannerServiceImpl(DealsBannerRepository dealsBannerRepository,
                                  DealsBannerConverter dealsBannerConverter,
                                  StorageService storageService){
        this.dealsBannerRepository = dealsBannerRepository;
        this.storageService = storageService;
        this.dealsBannerConverter = dealsBannerConverter;
    }


    @Override
    public DealsBannerModel saveDealBanner(DealsBannerModel dealsBannerModel) {
        DealsBannerModel pop = dealsBannerConverter.entityToModel(dealsBannerRepository.save(dealsBannerConverter.modelToEntity(dealsBannerModel)));
        storageService.store(dealsBannerModel.getImage());
        return pop;
    }

    @Override
    public DealsBannerModel updateDealBanner(DealsBannerModel dealsBannerModel) {
        return null;
    }

    @Override
    public List<DealsBannerModel> findAll() {
        List<DealsBannerModel> result = new ArrayList<>();
        for (DealsBanner dealsBanner : dealsBannerRepository.findAll()){
            result.add(dealsBannerConverter.entityToModel(dealsBanner));
        }
        return result;
    }

    @Override
    public DealsBannerModel findOne(Integer id) {
        return dealsBannerConverter.entityToModel(dealsBannerRepository.findById(id));
    }

    @Override
    public void deleteDealBannerById(Integer id) {
        dealsBannerRepository.deleteById(id);
    }

    @Override
    public String findImageById(Integer id) {
        return dealsBannerRepository.findImageById(id);
    }

    @Override
    public void changeStatusById(Integer id) {
        DealsBanner dealsBanner = dealsBannerRepository.findById(id);
        dealsBanner.setActive(!dealsBanner.getActive());
        dealsBannerRepository.save(dealsBanner);
    }

    @Override
    public List<DealsBanner> findActive() {
        return dealsBannerRepository.findActives();
    }
}
