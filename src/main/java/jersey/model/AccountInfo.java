package jersey.model;

import java.util.Date;
import java.util.List;

public class AccountInfo {
	
	private String userName;
	private String userRole;
	private String name;
	private String email;
	private String phone;
	private String address;
	private Date createdAt;
	
	private List<RegisteredOrderInfo> registeredOrders;
	
    public AccountInfo(String userName, String userRole, String name,
    				   String email, String phone, String address, Date createdAt) {
    	this.userName = userName;
    	this.userRole = userRole;
    	this.name = name;
    	this.email = email;
    	this.phone = phone;
    	this.address = address;
    	this.createdAt = createdAt;
    }
    
    public AccountInfo() {
    	
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<RegisteredOrderInfo> getRegisteredOrders() {
        return registeredOrders;
    }

    public void setRegisteredOrders(List<RegisteredOrderInfo> registeredOrders) {
        this.registeredOrders = registeredOrders;
    }
}
