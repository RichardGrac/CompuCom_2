package com.example.CompuCom2.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class DealsBannerModel {
    private Integer id;
    private String title;
    private String content;
    private MultipartFile image;
    private Boolean active;
}
