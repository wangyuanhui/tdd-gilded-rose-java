package cn.xpbootcamp.gilded_rose.domain.entity;

import cn.xpbootcamp.gilded_rose.domain.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
public class Product {
    protected long id;
    protected String name;
    protected ProductType type;
    protected int quality;
    protected Instant createdAt;
    protected Instant bestBefore;
}
