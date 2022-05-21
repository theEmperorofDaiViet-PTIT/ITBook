package jersey.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import jersey.entity.Order;
import jersey.entity.OrderDetail;
import jersey.entity.Product;
import jersey.form.ProductForm;
import jersey.model.OrderDetailInfo;
import jersey.model.OrderInfo;
import jersey.model.ProductAuthorInfo;
import jersey.model.ProductInfo;
import jersey.pagination.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Product findProduct(String id) {
        try {
            String sql = "Select e from " + Product.class.getName() + " e Where e.id =:id ";

            Session session = this.sessionFactory.getCurrentSession();
            Query<Product> query = session.createQuery(sql, Product.class);
            query.setParameter("id", id);
            return (Product) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public ProductInfo findProductInfo(String id) {
        Product product = this.findProduct(id);
        if (product == null) {
            return null;
        }
        return new ProductInfo(product.getId(), product.getTitle(), product.getPrice());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(ProductForm productForm) {

        Session session = this.sessionFactory.getCurrentSession();
        String id = productForm.getId();

        Product product = null;

        boolean isNew = false;
        if (id != null) {
            product = this.findProduct(id);
        }
        if (product == null) {
            isNew = true;
            product = new Product();
            product.setCreatedAt(new Date());
        }
        product.setId(id);
        product.setTitle(productForm.getTitle());
        product.setPrice(productForm.getPrice());
        product.setCategoryId(productForm.getCategoryId());
        product.setDescription(productForm.getDescription());
        product.setLanguage(productForm.getLanguage());
        product.setNumPages(productForm.getNumPages());

        if (productForm.getFileData() != null) {
            byte[] image = null;
            try {
                image = productForm.getFileData().getBytes();
            } catch (IOException e) {
            }
            if (image != null && image.length > 0) {
                product.setImage(image);
            }
        }
        if (isNew) {
            session.persist(product);
        }

        session.flush();
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(String productID) {

        Session session = this.sessionFactory.getCurrentSession();
        
        Product product = this.findProduct(productID);
        session.delete(product);
        
 
        session.flush();
    }
    
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
            String likeName) {
        String sql = "Select new " + ProductInfo.class.getName() //
                + "(p.id, p.title, p.price) " + " from "//
                + Product.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.title) like :likeName ";
        }
        sql += " order by p.createdAt desc ";
        // 
        Session session = this.sessionFactory.getCurrentSession();
        Query<ProductInfo> query = session.createQuery(sql, ProductInfo.class);

        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
    }

    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
        return queryProducts(page, maxResult, maxNavigationPage, null);
    }
  ////////////////////////////////////////////////////////////////////////  

    
}
