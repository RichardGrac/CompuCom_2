package com.example.CompuCom2.model;

import com.example.CompuCom2.entity.Product;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesProductViewModel {

    private Product product;
    private Long quantity;
}
