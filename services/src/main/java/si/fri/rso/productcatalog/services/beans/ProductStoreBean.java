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
import si.fri.rso.productcatalog.lib.ProductStore;
import si.fri.rso.productcatalog.models.converters.ProductConverter;
import si.fri.rso.productcatalog.models.converters.ProductStoreConverter;
import si.fri.rso.productcatalog.models.entities.ProductEntity;
import si.fri.rso.productcatalog.models.entities.ProductStoreEntity;


@RequestScoped
public class ProductStoreBean {

    private Logger log = Logger.getLogger(si.fri.rso.productcatalog.services.beans.ProductStoreBean.class.getName());

    @Inject
    private EntityManager em;

    public List<ProductStore> getProducts() {
        TypedQuery<ProductStoreEntity> query = em.createNamedQuery("ProductStoreEntity.getAll", ProductStoreEntity.class);
        List<ProductStoreEntity> resultList = query.getResultList();
        return resultList.stream().map(ProductStoreConverter::toDto).collect(Collectors.toList());
    }

    public List<ProductStore> getProductFilter(UriInfo uriInfo) {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();
        return JPAUtils.queryEntities(em, ProductStoreEntity.class, queryParameters).stream()
                .map(ProductStoreConverter::toDto).collect(Collectors.toList());
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
