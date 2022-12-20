package si.fri.rso.productcatalog.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NamedQueries(
    value = {
            @NamedQuery(name = "ProductEntity.getAll", query = "SELECT products FROM ProductEntity products"),
            @NamedQuery(name = "ProductEntity.findByName", query = "SELECT products FROM ProductEntity products WHERE LOWER(products.name) LIKE CONCAT('%', :name, '%')"),
    }
)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    // String columns are normally converted into VARCHAR(255) fields, but this is too short for our description.
    // Set column to TEXT field in SQL using columnDefinition property.
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image")
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}