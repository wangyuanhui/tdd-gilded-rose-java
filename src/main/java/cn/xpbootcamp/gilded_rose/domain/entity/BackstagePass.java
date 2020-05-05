package cn.xpbootcamp.gilded_rose.domain.entity;

import cn.xpbootcamp.gilded_rose.domain.ProductType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BackstagePass extends AbstractProduct {

    public static BackstagePass create(String name,
                                         int defaultQuality,
                                         Instant createdAt,
                                         Instant bestBefore) {
        BackstagePass instance = new BackstagePass();
        instance.checkName(name);
        instance.checkDefaultQuality(defaultQuality);
        instance.checkTime(createdAt, bestBefore);
        instance.name = name;
        instance.type = ProductType.BACKSTAGEPASS;
        instance.defaultQuality = defaultQuality;
        instance.createdAt = createdAt;
        instance.bestBefore = bestBefore;
        return instance;
    }

    @Override
    public int getCurrentQuality() {
        int defaultSellIn = getDefaultSellIn();
        System.out.println(defaultSellIn);
        int currentSellIn = getCurrentSellIn();
        System.out.println(currentSellIn);
        if (currentSellIn <= 0) {
            return 0;
        }
        int quality = defaultQuality;
        for (int i = currentSellIn; i < defaultSellIn; i++) {
            if (i < 5) {
                quality += 3;
            } else if (i < 10) {
                quality += 2;
            } else {
                quality += 1;
            }
        }
        if (quality > 50) {
            quality = 50;
        }
        return quality;
    }
}
