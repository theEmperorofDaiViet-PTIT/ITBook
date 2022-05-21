package jersey.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Discounts")
public class Discount implements Serializable{

    private static final long serialVersionUID = 7550745928843183535L;

    @Id
    @Column(name = "ID", length = 50, nullable = false)
    private String id;
    
    @Column(name = "Name", length = 255, nullable = false)
    private String name;
    
    @Column(name = "Description", nullable = false)
    private String description;
    
    @Column(name = "Discount_Percent", nullable = false)
    private int discountPercent;
    
    public Discount() {
    	
    }
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id) {
    	this.id = id;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public int getDiscountPercent() {
    	return discountPercent;
    }
    
    public void setDiscountPercent(int discountPercent) {
    	this.discountPercent = discountPercent;
    }
}
