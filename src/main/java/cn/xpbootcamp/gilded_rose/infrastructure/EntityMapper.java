package cn.xpbootcamp.gilded_rose.infrastructure;

import cn.xpbootcamp.gilded_rose.domain.entity.AbstractProduct;

public class EntityMapper {

    public ProductData toProductData(AbstractProduct domain) {
        ProductData data = new ProductData();
        data.setName(domain.getName());
        data.setType(domain.getType().toString());
        data.setDefaultQuality(domain.getDefaultQuality());
        data.setCreatedAt(domain.getCreatedAt());
        data.setBestBefore(domain.getBestBefore());
        return data;
    }
}
