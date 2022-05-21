package jersey.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categories")
public class Category implements Serializable{

    private static final long serialVersionUID = -1000119078147252957L;

    @Id
    @Column(name = "ID", length = 20, nullable = false)
    private String id;
    
    @Column(name = "Name", length = 255, nullable = false)
    private String name;
    
    @Column(name = "Description", nullable = false)
    private String description;
    
    public Category(){
    	
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
}
