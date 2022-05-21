package jersey.model;

public class ProductAuthorInfo {

    private String id;

    private String authorId;
    private String authorName;

    public ProductAuthorInfo() {

    }

    // Sử dụng cho JPA/Hibernate Query.
    public ProductAuthorInfo(String id, String authorId, String authorName) {
        this.id = id;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

}