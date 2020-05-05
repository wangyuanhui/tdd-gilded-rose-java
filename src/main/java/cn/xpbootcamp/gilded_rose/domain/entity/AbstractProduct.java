package cn.xpbootcamp.gilded_rose.domain.entity;

import cn.xpbootcamp.gilded_rose.domain.ProductType;
import cn.xpbootcamp.gilded_rose.exception.ProductException;
import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class AbstractProduct {
    protected long id;
    protected String name;
    protected ProductType type;
    protected int defaultQuality;
    protected Instant createdAt;
    protected Instant bestBefore;

    public int getCurrentSellIn() {
        return (int) ((bestBefore.getEpochSecond() - Instant.now().getEpochSecond()) / (24 * 3600));
    }

    public int getDefaultSellIn() {
        return (int) ((bestBefore.getEpochSecond() - createdAt.getEpochSecond()) / (24 * 3600));
    }

    public abstract int getCurrentQuality();

    protected void checkName(String name) {
        if (name.length() == 0 || name.length() > 20) {
            throw new ProductException(400, "Invalid name.");
        }
    }

    protected void checkDefaultQuality(int defaultQuality) {
        if (defaultQuality < 0|| defaultQuality > 50) {
            throw new ProductException(400, "Invalid value for defaultQuality.");
        }
    }

    protected void checkTime(Instant createdAt, Instant bestBefore) {
        if (createdAt.isAfter(Instant.now())) {
            throw new ProductException(400, "CreatedAt time can not be future.");
        }
        if (createdAt.isAfter(bestBefore)) {
            throw new ProductException(400, "BestBefore time should be after createdAt time.");
        }
    }
}
