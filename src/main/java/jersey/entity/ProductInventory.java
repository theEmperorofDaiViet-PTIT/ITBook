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
@Table(name = "Product_Inventories")
public class ProductInventory implements Serializable{

    private static final long serialVersionUID = 7550745928843183535L;

    @Id
    @Column(name = "ID", length = 50, nullable = false)
    private String id;
    
    @Column(name = "Quantity", nullable = false)
    private int quantity;
    
    public ProductInventory() {
    	
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
