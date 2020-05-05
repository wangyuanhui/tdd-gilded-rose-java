package cn.xpbootcamp.gilded_rose.infrastructure;

import cn.xpbootcamp.gilded_rose.domain.ProductType;

import java.time.Instant;

public class ProductData {
    private long id;
    private String name;
    private ProductType type;
    private int quality;
    private Instant createdAt;
    private Instant bestBefore;
}
