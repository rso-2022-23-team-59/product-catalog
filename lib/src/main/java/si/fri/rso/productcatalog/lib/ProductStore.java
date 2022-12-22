package si.fri.rso.productcatalog.lib;

public class ProductStore {

    private Integer id;
    private Product product;
    private Integer storeId;
    private Integer price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreIdId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
