package cn.xpbootcamp.gilded_rose.domain.entity;

import cn.xpbootcamp.gilded_rose.domain.ProductType;

import java.time.Instant;

public class Product {
    protected long id;
    protected String name;
    protected ProductType type;
    protected int quality;
    protected Instant createdAt;
    protected Instant bestBefore;
}
