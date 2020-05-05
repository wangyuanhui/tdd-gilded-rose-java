package cn.xpbootcamp.gilded_rose.domain.entity;

import cn.xpbootcamp.gilded_rose.domain.ProductType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Getter
@NoArgsConstructor
public class Product {
    protected long id;
    protected String name;
    protected ProductType type;
    protected int quality;
    protected Instant createdAt;
    protected Instant bestBefore;

    public static Product create() {
        // TODO
        return null;
    }
}
