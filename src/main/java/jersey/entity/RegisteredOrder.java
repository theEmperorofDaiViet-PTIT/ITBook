package jersey.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "RegisteredOrders", //
        uniqueConstraints = { @UniqueConstraint(columnNames = "Order_Num") })
public class RegisteredOrder implements Serializable{

	private static final long serialVersionUID = -2576670215015463100L;
	
    @Id
    @Column(name = "ID", length = 50)
    private String id;

    @Column(name = "Created_At", nullable = false)
    private Date createdAt;

    @Column(name = "Order_Num", nullable = false)
    private int orderNum;

    @Column(name = "Total", nullable = false)
    private double total;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_USERNAME", nullable = false, 
            foreignKey = @ForeignKey(name = "REGISTERED_ORDER_ACC_FK"))
    private Account account;
    
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
    
    public Account getAccount() {
    	return account;
    }
    
    public void setAccount(Account account) {
    	this.account = account;
    }
}
