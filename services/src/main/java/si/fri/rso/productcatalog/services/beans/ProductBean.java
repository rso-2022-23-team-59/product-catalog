package si.fri.rso.productcatalog.services.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import si.fri.rso.productcatalog.lib.Product;
import si.fri.rso.productcatalog.models.converters.ProductConverter;
import si.fri.rso.productcatalog.models.entities.ProductEntity;


@RequestScoped
public class ProductBean {

    private Logger log = Logger.getLogger(si.fri.rso.productcatalog.services.beans.ProductBean.class.getName());

    @Inject
    private EntityManager em;

    public List<Product> getProducts() {
        TypedQuery<ProductEntity> query = em.createNamedQuery("ProductEntity.getAll", ProductEntity.class);
        List<ProductEntity> resultList = query.getResultList();
        return resultList.stream().map(ProductConverter::toDto).collect(Collectors.toList());
    }

    public List<Product> getProductFilter(UriInfo uriInfo) {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();
        return JPAUtils.queryEntities(em, ProductEntity.class, queryParameters).stream()
                .map(ProductConverter::toDto).collect(Collectors.toList());
    }

    public List<Product> findByName(String productName) {
        // If query parameter "q" is not set, return an empty array.
        if (productName == null) return new ArrayList<>();

        TypedQuery<ProductEntity> query = em.createNamedQuery("ProductEntity.findByName", ProductEntity.class);
        query.setParameter("name", productName.toLowerCase());
        List<ProductEntity> matches = query.getResultList();
        return matches.stream().map(ProductConverter::toDto).collect(Collectors.toList());
    }

    public Product getProduct(Integer id) {

        ProductEntity productEntity = em.find(ProductEntity.class, id);

        if (productEntity == null) {
            throw new NotFoundException();
        }

        Product product = ProductConverter.toDto(productEntity);
        return product;
    }

    public Product createProduct(Product product) {

        ProductEntity productEntity = ProductConverter.toEntity(product);

        try {
            beginTx();
            em.persist(productEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (productEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return ProductConverter.toDto(productEntity);
    }

    public Product putProduct(Integer id, Product product) {

        ProductEntity c = em.find(ProductEntity.class, id);

        if (c == null) {
            return null;
        }

        ProductEntity updatedProductEntity = ProductConverter.toEntity(product);

        try {
            beginTx();
            updatedProductEntity.setId(c.getId());
            updatedProductEntity = em.merge(updatedProductEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return ProductConverter.toDto(updatedProductEntity);
    }

    public boolean deleteProduct(Integer id) {

        ProductEntity productEntity = em.find(ProductEntity.class, id);

        if (productEntity != null) {
            try {
                beginTx();
                em.remove(productEntity);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
