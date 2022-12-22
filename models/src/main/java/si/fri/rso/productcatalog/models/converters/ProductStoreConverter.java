package si.fri.rso.productcatalog.models.converters;

import si.fri.rso.productcatalog.lib.ProductStore;
import si.fri.rso.productcatalog.models.entities.ProductStoreEntity;

public class ProductStoreConverter {

    public static ProductStore toDto(ProductStoreEntity entity) {

        ProductStore dto = new ProductStore();
        dto.setId(entity.getId());
        dto.setProduct(ProductConverter.toDto(entity.getProduct()));
        dto.setStoreIdId(entity.getStoreId());
        dto.setPrice(entity.getPrice());
        return dto;

    }

    public static ProductStoreEntity toEntity(ProductStore dto) {

        ProductStoreEntity entity = new ProductStoreEntity();
        entity.setStoreIdId(dto.getStoreId());
        entity.setPrice(dto.getPrice());
        entity.setProduct(ProductConverter.toEntity(dto.getProduct()));
        return entity;

    }

}
