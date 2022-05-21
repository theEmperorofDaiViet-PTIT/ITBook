package jersey.model;

import java.util.Date;
import java.util.List;

public class RegisteredOrderInfo {

    private String id;
    private Date createdAt;
    private int orderNum;
    private double total;
    
    private String userName;
    private String name;
    private String email;
    private String phone;
    private String address;
    
    private List<RegisteredOrderDetailInfo> details;
    
    public RegisteredOrderInfo() {

    }
    
    // Sử dụng cho Hibernate Query.
    public RegisteredOrderInfo(String id, Date createdAt, int orderNum,
            double total, String userName, String name, String email,
            String phone, String address) {
        this.id = id;
        this.createdAt = createdAt;
        this.orderNum = orderNum;
        this.total = total;
        
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public List<RegisteredOrderDetailInfo> getDetails() {
        return details;
    }

    public void setDetails(List<RegisteredOrderDetailInfo> details) {
        this.details = details;
    }
    
    public String getUserName() {
    	return userName;
    }
    
    public void setUserName(String userName) {
    	this.userName = userName;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    
    public String getAddress() {
    	return address;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
}
