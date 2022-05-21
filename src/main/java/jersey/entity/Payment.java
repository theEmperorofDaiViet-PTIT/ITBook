package jersey.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Payments")
public class Payment implements Serializable{
	
	@Id
    private static final long serialVersionUID = -1000119078147252957L;

    @Id
    @Column(name = "ID", length = 50)
    private String id = "1";
    
    @Column(name = "Type", length = 20, nullable = false)
    private String type;
    
    @Column(name = "Provider", length = 20, nullable = true)
    private String provider;
    
    @Column(name = "Account_No", nullable = true)
    private int accountNo;
    
    @Column(name = "Total", nullable = false)
    private double total;
    
    @Column(name = "Created_At", nullable = false)
    private Date createdAt;
    
    public Payment(){
    	setId(UUID.randomUUID().toString());
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvider() {
        return provider;
    }
    
    public void setProvider(String provider) {
        this.provider = provider;
    }
    
    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }
    
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
