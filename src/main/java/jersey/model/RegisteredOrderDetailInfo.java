package jersey.model;

public class RegisteredOrderDetailInfo {

    private String id;

    private String productId;
    private String productTitle;

    private int quantity;
    private double price;
    private double subTotal;
    
    public RegisteredOrderDetailInfo() {

    }
    
    // Sử dụng cho JPA/Hibernate Query.
    public RegisteredOrderDetailInfo(String id, String productId, //
            String productTitle, int quantity, double price, double subTotal) {
        this.id = id;
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    } 
}
