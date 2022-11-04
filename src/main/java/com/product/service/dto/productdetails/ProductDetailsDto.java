package com.product.service.dto.productdetails;

import com.product.service.entity.Image;
import com.product.service.entity.constants.Currency;
import com.product.service.entity.constants.HomeMaterial;
import com.product.service.entity.constants.HomeType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDetailsDto {

    private Long id;
    private Long userId;
    private List<Image> images;
    private Double price;
    private Currency currency;
    private Integer roomCount;
    //Kvadrat metr umumiy ??
    private Double totalArea;
    //Nechinchi qavat
    private Integer floorNumber;
    //Jami necha qavatli
    private Integer floorCount;
    private String address;
    //favorite ga qoshish izbranniy
    private Boolean isFavorite;
    private Double lat;
    private Double lan;
    private Double longitude;
    private String phoneNumber;
    private Boolean enablePhone;
    private Boolean enableChat;
    private String createdAt;
    private String updatedAt;
    //yashash maydoni
    private Double livingArea;
    //balkon
    private Boolean balcony;
    //Mebellari bn birga sotiladimi
    private Boolean withFurniture;
    private Integer builtYear;
    //ENUM
    private HomeType type;
    //ENUM uy materiali
    private HomeMaterial material;
    private Boolean elevator;
    private Boolean parking;
    private String description;
    // prosmotr
    private Integer viewCount;

    public ProductDetailsDto(Long userId, List<Image> images, Double price, Currency currency, Integer roomCount, Double totalArea, Integer floorNumber, Integer floorCount, String address, Boolean isFavorite, Double lat, Double lan, Double longitude, String phoneNumber, Boolean enablePhone, Boolean enableChat, String createdAt, String updatedAt, Double livingArea, Boolean balcony, Boolean withFurniture, Integer builtYear, HomeType type, HomeMaterial material, Boolean elevator, Boolean parking, String description, Integer viewCount) {
        this.userId = userId;
        this.images = images;
        this.price = price;
        this.currency = currency;
        this.roomCount = roomCount;
        this.totalArea = totalArea;
        this.floorNumber = floorNumber;
        this.floorCount = floorCount;
        this.address = address;
        this.isFavorite = isFavorite;
        this.lat = lat;
        this.lan = lan;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
        this.enablePhone = enablePhone;
        this.enableChat = enableChat;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.livingArea = livingArea;
        this.balcony = balcony;
        this.withFurniture = withFurniture;
        this.builtYear = builtYear;
        this.type = type;
        this.material = material;
        this.elevator = elevator;
        this.parking = parking;
        this.description = description;
        this.viewCount = viewCount;
    }
}
