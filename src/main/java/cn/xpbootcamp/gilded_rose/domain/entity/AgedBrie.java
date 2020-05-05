package cn.xpbootcamp.gilded_rose.domain.entity;

import cn.xpbootcamp.gilded_rose.domain.ProductType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AgedBrie extends AbstractProduct {

    public static AgedBrie create(String name,
                                 int defaultQuality,
                                 Instant createdAt,
                                 Instant bestBefore) {
        AgedBrie instance = new AgedBrie();
        instance.checkName(name);
        instance.checkDefaultQuality(defaultQuality);
        instance.checkTime(createdAt, bestBefore);
        instance.name = name;
        instance.type = ProductType.AGEDBRIE;
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
        int quality = defaultQuality + daysPassed;
        if (quality > 50) {
            quality = 50;
        }
        return quality;
    }
}
