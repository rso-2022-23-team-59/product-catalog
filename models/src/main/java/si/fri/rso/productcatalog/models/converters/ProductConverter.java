package si.fri.rso.productcatalog.models.converters;

import si.fri.rso.productcatalog.lib.Product;
import si.fri.rso.productcatalog.models.entities.ProductEntity;

public class ProductConverter {

    public static Product toDto(ProductEntity entity) {

        Product dto = new Product();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        return dto;

    }

    public static ProductEntity toEntity(Product dto) {

        ProductEntity entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());

        return entity;

    }

}
