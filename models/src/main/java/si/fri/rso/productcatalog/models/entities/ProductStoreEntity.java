package si.fri.rso.productcatalog.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "productstores")
@NamedQueries(
        value = {
                @NamedQuery(name = "ProductStoreEntity.getAll", query = "SELECT productstores FROM ProductStoreEntity productstores"),
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

    public void setStoreIdId(Integer storeId) {
        this.storeId = storeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}