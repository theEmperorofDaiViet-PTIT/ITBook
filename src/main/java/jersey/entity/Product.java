package jersey.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Products")
public class Product implements Serializable {

    private static final long serialVersionUID = -1000119078147252957L;

    @Id
    @Column(name = "ID", length = 20, nullable = false)
    private String id;

    @Column(name = "Title", length = 255, nullable = false)
    private String title;

    @Column(name = "Price", nullable = false)
    private double price;

    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
    
    @Column(name = "Category_Id", length = 255, nullable = false)
    private String categoryId;
    
    @Column(name = "Description", nullable = false)
    private String description;
    
    @Column(name = "Language", length = 255, nullable = false)
    private String language;
    
    @Column(name = "Num_Pages", nullable = false)
    private int numPages;
    
//    @OneToMany(mappedBy="ord")
//    private Set<String> authors;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Created_At", nullable = false)
    private Date createdAt;

    public Product() {
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
    
    public String getCategoryId() {
    	return categoryId;
    }
    
    public void setCategoryId(String categoryId) {
    	this.categoryId = categoryId;
    }
    
    public String getDescription() {
    	return description;
    }
       
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public String getLanguage() {
    	return language;
    }
    
    public void setLanguage(String language) {
    	this.language = language;
    }
    
    public int getNumPages() {
    	return numPages;
    }
    
    public void setNumPages(int numPages) {
    	this.numPages = numPages;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
