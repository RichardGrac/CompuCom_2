package com.example.CompuCom2.utils.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}

//http://www.mdoninger.de/2015/05/16/completion-for-custom-properties-in-spring-boot.html
//https://docs.spring.io/spring-boot/docs/1.5.7.RELEASE/reference/html/configuration-metadata.html#configuration-metadata-annotation-processor
