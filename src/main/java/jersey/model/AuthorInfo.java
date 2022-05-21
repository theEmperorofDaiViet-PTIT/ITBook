package jersey.model;

import jersey.entity.Author;

public class AuthorInfo {
    private String id;
    private String name;

    public AuthorInfo() {
    }

    public AuthorInfo(Author author) {
        this.id = author.getId();
        this.name = author.getName();
    }

    // Sử dụng trong JPA/Hibernate query
    public AuthorInfo(String id, String name) {
        this.id = id;
        this.name = name;
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
    
}