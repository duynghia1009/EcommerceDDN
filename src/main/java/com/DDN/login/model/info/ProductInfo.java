package com.DDN.login.model.info;

import com.DDN.login.model.OrderItem;
import com.DDN.login.model.Reviews;
import com.DDN.login.model.categories.ApparelCategory;
import com.DDN.login.model.categories.GenderCategory;
import com.DDN.login.model.categories.PriceRangeCategory;
import com.DDN.login.model.categories.ProductBrandCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(indexes={@Index(columnList = "gender_id, apparel_id, brand_id, price")})
public class ProductInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int sellerId;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name="brand_id")
    @JsonIgnore
    private ProductBrandCategory productBrandCategory;

    @ManyToOne
    @JoinColumn(name="gender_id")
    @JsonIgnore
    private GenderCategory genderCategory;

    @ManyToOne
    @JoinColumn(name="price_id")
    @JsonIgnore
    private PriceRangeCategory priceRangeCategory;

    @ManyToOne
    @JoinColumn(name="apparel_id")
    @JsonIgnore
    private ApparelCategory apparelCategory;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "productInfo")
    @JsonIgnore
    private List<Reviews> reviews;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "productInfo")
    @JsonIgnore
    private List<OrderItem> orderItems;


    private double price;
    private int availableQuantity;
    private int deliveryTime;
    private float ratings;
    private boolean verificationStatus;
    private String imageLocalPath;

    @OneToMany(mappedBy = "orderInfo")
    @JsonIgnore
    private List<OrderInfo> orders;

    private String imageURL;

    public ProductInfo() {

    }
    public ProductInfo(int sellerId, String name, Date publicationDate, ProductBrandCategory productBrandCategory,
                       GenderCategory genderCategory, ApparelCategory apparelCategory,
                       PriceRangeCategory priceRangeCategory,
                       double price, int availableQuantity, int deliveryTime, float ratings,
                       boolean verificationStatus, String imageLocalPath, String imageURL) {
        this.sellerId = sellerId;
        this.name = name;
        this.publicationDate = publicationDate;
        this.productBrandCategory = productBrandCategory;
        this.genderCategory = genderCategory;
        this.apparelCategory = apparelCategory;
        this.priceRangeCategory = priceRangeCategory;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.deliveryTime = deliveryTime;
        this.ratings = ratings;
        this.verificationStatus = verificationStatus;
        this.imageLocalPath = imageLocalPath;
        this.imageURL = imageURL;

    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public ProductBrandCategory getProductBrandCategory() {
        return productBrandCategory;
    }

    public void setProductBrandCategory(ProductBrandCategory productBrandCategory) {
        this.productBrandCategory = productBrandCategory;
    }

    public GenderCategory getGenderCategory() {
        return genderCategory;
    }

    public void setGenderCategory(GenderCategory genderCategory) {
        this.genderCategory = genderCategory;
    }

    public PriceRangeCategory getPriceRangeCategory() {
        return priceRangeCategory;
    }

    public void setPriceRangeCategory(PriceRangeCategory priceRangeCategory) {
        this.priceRangeCategory = priceRangeCategory;
    }

    public ApparelCategory getApparelCategory() {
        return apparelCategory;
    }

    public void setApparelCategory(ApparelCategory apparelCategory) {
        this.apparelCategory = apparelCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public boolean isVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(boolean verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getImageLocalPath() {
        return imageLocalPath;
    }

    public void setImageLocalPath(String imageLocalPath) {
        this.imageLocalPath = imageLocalPath;
    }

    public List<OrderInfo> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderInfo> orders) {
        this.orders = orders;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
