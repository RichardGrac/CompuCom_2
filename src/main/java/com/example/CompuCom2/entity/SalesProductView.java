package com.example.CompuCom2.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sales_product")
public class SalesProductView {

    @Id
    @Column(name = "id_product")
    private Integer id_product;
    @Column(name = "quantity")
    private Long quantity;
}
