package com.DDN.login.dto;

import com.DDN.login.model.Product;
import com.DDN.login.model.Reviews;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {


    private Integer id;
    private @NotNull String name;

    private @NotNull Integer price;
    private @NotNull String description;

    private Integer ratings;
    private String color;
    private String deliveryTime;
    private Integer stock;
    private String created_at;
    private String apparelName;
    private String genderName;
    private String brandName;
    private Date updateDate;
    MultipartFile[] images;


    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getApparelName() {
        return apparelName;
    }

    public void setApparelName(String apparelName) {
        this.apparelName = apparelName;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }



    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }



//    public ProductDto(@NotNull String name, @NotNull String imageURL, @NotNull Integer price, @NotNull String description, @NotNull Integer categoryId,
//                      String ratings, String color, String deliveryTime, Integer reviewId, Integer stock){
//        this.name = name;
//        this.imageUrl = imageURL;
//        this.price = price;
//        this.description = description;
//        this.categoryId = categoryId;
//    }

    public ProductDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
