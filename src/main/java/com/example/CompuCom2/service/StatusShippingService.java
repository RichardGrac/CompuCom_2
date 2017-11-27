package com.example.CompuCom2.service;

import org.springframework.stereotype.Service;

@Service
public interface StatusShippingService {

    boolean updateStatusShipping(int id_ss, String state);
}
