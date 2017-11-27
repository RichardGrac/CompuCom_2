package com.example.CompuCom2.service.impl;

import com.example.CompuCom2.entity.StatusShipping;
import com.example.CompuCom2.repository.StatusShippingRepository;
import com.example.CompuCom2.service.StatusShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("statusShippingServiceImpl")
public class StatusShippingServiceImpl implements StatusShippingService {

    @Autowired
    @Qualifier("statusShippingRepository")
    private StatusShippingRepository statusShippingRepository;

    @Override
    public boolean updateStatusShipping(int id_ss, String state) {
        StatusShipping status = statusShippingRepository.findById(id_ss);
        status.setStatus(state);
        StatusShipping stat = statusShippingRepository.save(status);
        return (stat != null);
    }
}
