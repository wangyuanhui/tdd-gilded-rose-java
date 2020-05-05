package cn.xpbootcamp.gilded_rose.domain.entity;

import cn.xpbootcamp.gilded_rose.domain.ProductType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Product extends AbstractProduct {

    public static Product create(String name,
                                 int defaultQuality,
                                 Instant createdAt,
                                 Instant bestBefore) {
        Product instance = new Product();
        instance.checkName(name);
        instance.checkDefaultQuality(defaultQuality);
        instance.checkTime(createdAt, bestBefore);
        instance.name = name;
        instance.type = ProductType.COMMON;
        instance.defaultQuality = defaultQuality;
        instance.createdAt = createdAt;
        instance.bestBefore = bestBefore;
        return instance;
    }

    @Override
    public int getCurrentQuality() {
        int defaultSellIn = getDefaultSellIn();
        int currentSellIn = getCurrentSellIn();
        int daysPassed = defaultSellIn - currentSellIn;
        int quality = defaultQuality - daysPassed;
        if (currentSellIn < 0) {
            quality -= Math.abs(currentSellIn);
        }
        if (quality < 0) {
            quality = 0;
        }
        return quality;
    }
}
