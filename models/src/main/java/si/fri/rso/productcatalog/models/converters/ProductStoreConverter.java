package si.fri.rso.productcatalog.models.converters;

import si.fri.rso.productcatalog.lib.ProductStore;
import si.fri.rso.productcatalog.models.entities.ProductStoreEntity;

public class ProductStoreConverter {

    public static ProductStore toDto(ProductStoreEntity entity, String currency) {

        ProductStore dto = new ProductStore();
        dto.setId(entity.getId());
        dto.setProduct(ProductConverter.toDto(entity.getProduct()));
        dto.setStoreId(entity.getStoreId());
        dto.setTimestamp(entity.getTimestamp());
        dto.setPrice(entity.getPrice());
        dto.setCurrency(currency);
        return dto;

    }

    public static ProductStoreEntity toEntity(ProductStore dto) {

        ProductStoreEntity entity = new ProductStoreEntity();
        entity.setStoreId(dto.getStoreId());
        entity.setPrice(dto.getPrice());
        entity.setProduct(ProductConverter.toEntity(dto.getProduct()));
        entity.setTimestamp(dto.getTimestamp());
        return entity;

    }

}
