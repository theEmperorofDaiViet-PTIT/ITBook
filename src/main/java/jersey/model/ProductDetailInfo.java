package jersey.model;

import jersey.entity.Product;

public class ProductDetailInfo {
    private String id;
    private String title;
    private double price;

    public ProductDetailInfo() {
    }

    public ProductDetailInfo(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }

    // Sử dụng trong JPA/Hibernate query
    public ProductDetailInfo(String id, String title, double price) {
        this.id = id;
        this.title= title;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
