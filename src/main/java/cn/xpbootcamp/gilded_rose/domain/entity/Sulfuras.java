package cn.xpbootcamp.gilded_rose.domain.entity;

import cn.xpbootcamp.gilded_rose.domain.ProductType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Sulfuras extends AbstractProduct {

    public static Sulfuras create(String name,
                                         int defaultQuality,
                                         Instant createdAt,
                                         Instant bestBefore) {
        Sulfuras instance = new Sulfuras();
        instance.checkName(name);
        instance.checkDefaultQuality(defaultQuality);
        instance.name = name;
        instance.type = ProductType.SULFURAS;
        instance.defaultQuality = defaultQuality;
        instance.createdAt = createdAt;
        instance.bestBefore = null;
        return instance;
    }

    @Override
    public int getCurrentQuality() {
        return defaultQuality;
    }

    @Override
    public int getCurrentSellIn() {
        return 0;
    }

    @Override
    public int getDefaultSellIn() {
        return 0;
    }
}
