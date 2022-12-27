package si.fri.rso.productcatalog.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "productstores")
@NamedQueries(
        value = {
                @NamedQuery(name = "ProductStoreEntity.getAll", query = "SELECT productstores FROM ProductStoreEntity productstores"),
        }
)
@NamedNativeQueries(
        value = {
                @NamedNativeQuery (
                        name = "ProductStoreEntity.getLatest",
                        query = "SELECT a.* FROM productstores a LEFT JOIN productstores b ON (a.store_id = b.store_id AND a.product_id = b.product_id AND a.timestamp < b.timestamp) WHERE a.product_id = ? AND b.timestamp IS NULL",
                        resultClass = ProductStoreEntity.class
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