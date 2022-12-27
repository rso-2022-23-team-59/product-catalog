package si.fri.rso.productcatalog.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "productstores")
@NamedQueries(
        value = {
                @NamedQuery(name = "ProductStoreEntity.getAll", query = "SELECT productstores FROM ProductStoreEntity productstores"),
                @NamedQuery(
                        name = "ProductStoreEntity.getLatest",
                        query = "SELECT t1.id, t1.product, t1.storeId, t1.price, t1.timestamp FROM ProductStoreEntity t1 LEFT JOIN ProductStoreEntity t2 ON (t1.storeId = t2.storeId AND t1.product.id = t2.product.id AND t1.timestamp < t2.timestamp) WHERE t1.product.id = :productId AND t2.timestamp IS NULL"
                )
        }
)
public class ProductStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private ProductEntity product;

    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "timestamp")
    private Instant timestamp;

    @Column(name = "price")
    private Double price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}