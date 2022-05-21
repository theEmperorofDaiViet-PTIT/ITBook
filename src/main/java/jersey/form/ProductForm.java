package jersey.form;

import jersey.entity.Product;
import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private String id;
    private String title;
    private double price;
    private String categoryId;
    private String description;
    private String language;
    private int numPages;

    private boolean newProduct = false;

    // Upload file.
    private MultipartFile fileData;

    public ProductForm() {
        this.newProduct= true;
    }

    public ProductForm(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryId = product.getCategoryId();
        this.description = product.getDescription();
        this.language = product.getLanguage();
        this.numPages = product.getNumPages();
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

    public MultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

}