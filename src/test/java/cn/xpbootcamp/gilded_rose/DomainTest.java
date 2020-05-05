package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.domain.entity.AgedBrie;
import cn.xpbootcamp.gilded_rose.domain.entity.BackstagePass;
import cn.xpbootcamp.gilded_rose.domain.entity.Product;
import cn.xpbootcamp.gilded_rose.domain.entity.Sulfuras;
import cn.xpbootcamp.gilded_rose.exception.ProductException;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DomainTest {

    private static String NAME = "name";
    private static int DEFAULT_QUEALITY = 50;
    private static Instant NOW = Instant.now();
    private static Instant CREATED_AT = NOW.minus(1, HOURS);
    private static Instant BEST_BEFORE = CREATED_AT.plus(24, HOURS);

    @Test
    void test_create() {
        assertNotNull(Product.create(NAME, DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> Product.create("", DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> Product.create("ThisStringLengthIs 21", DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> Product.create(NAME, -1, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> Product.create(NAME, 51, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> Product.create(NAME, 1, CREATED_AT, CREATED_AT.minus(1, HOURS)));
        assertThrows(ProductException.class,
                () -> Product.create(NAME, 1, NOW.plus(1, HOURS), BEST_BEFORE));
    }

    @Test
    void test_create_agedBrie() {
        assertNotNull(AgedBrie.create(NAME, DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> AgedBrie.create("", DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> AgedBrie.create("ThisStringLengthIs 21", DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> AgedBrie.create(NAME, -1, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> AgedBrie.create(NAME, 51, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> AgedBrie.create(NAME, 1, CREATED_AT, CREATED_AT.minus(1, HOURS)));
        assertThrows(ProductException.class,
                () -> AgedBrie.create(NAME, 1, NOW.plus(1, HOURS), BEST_BEFORE));
    }

    @Test
    void test_create_sulfuras() {
        assertNotNull(Sulfuras.create(NAME, DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE));
        assertNull(Sulfuras.create(NAME, DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE).getBestBefore());
        assertThrows(ProductException.class,
                () -> Sulfuras.create("", DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> Sulfuras.create("ThisStringLengthIs 21", DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> Sulfuras.create(NAME, -1, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> Sulfuras.create(NAME, 51, CREATED_AT, BEST_BEFORE));
        assertNotNull(Sulfuras.create(NAME, 1, CREATED_AT, CREATED_AT.minus(1, HOURS)));
        assertNotNull(Sulfuras.create(NAME, 1, NOW.plus(1, HOURS), BEST_BEFORE));
    }

    @Test
    void test_create_backstagePass() {
        assertNotNull(BackstagePass.create(NAME, DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> BackstagePass.create("", DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> BackstagePass.create("ThisStringLengthIs 21", DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> BackstagePass.create(NAME, -1, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> BackstagePass.create(NAME, 51, CREATED_AT, BEST_BEFORE));
        assertThrows(ProductException.class,
                () -> BackstagePass.create(NAME, 1, CREATED_AT, CREATED_AT.minus(1, HOURS)));
        assertThrows(ProductException.class,
                () -> BackstagePass.create(NAME, 1, NOW.plus(1, HOURS), BEST_BEFORE));
    }

    @Test
    void test_getCurrentSellIn() {
        Product product;
        Instant createAt = NOW.minus(240, HOURS);
        Instant bestBefore;

        // 1 hour before bestBefore time
        bestBefore = NOW.plus(1, HOURS);
        product = Product.create(NAME, DEFAULT_QUEALITY, createAt, bestBefore);
        assertEquals(0, product.getCurrentSellIn());

        // 16 hours before bestBefore time
        bestBefore = NOW.plus(16, HOURS);
        product = Product.create(NAME, DEFAULT_QUEALITY, createAt, bestBefore);
        assertEquals(0, product.getCurrentSellIn());

        // 24 hours before bestBefore time
        bestBefore = NOW.plus(24, HOURS);
        product = Product.create(NAME, DEFAULT_QUEALITY, createAt, bestBefore);
        assertEquals(1, product.getCurrentSellIn());

        // 32 hours before bestBefore time
        bestBefore = NOW.plus(32, HOURS);
        product = Product.create(NAME, DEFAULT_QUEALITY, createAt, bestBefore);
        assertEquals(1, product.getCurrentSellIn());

        // 48 hours before bestBefore time
        bestBefore = NOW.plus(48, HOURS);
        product = Product.create(NAME, DEFAULT_QUEALITY, createAt, bestBefore);
        assertEquals(2, product.getCurrentSellIn());

        // 1 hour after bestBefore time
        bestBefore = NOW.minus(1, HOURS);
        product = Product.create(NAME, DEFAULT_QUEALITY, createAt, bestBefore);
        assertEquals(0, product.getCurrentSellIn());

        // 24 hours after bestBefore time
        bestBefore = NOW.minus(24, HOURS);
        product = Product.create(NAME, DEFAULT_QUEALITY, createAt, bestBefore);
        assertEquals(-1, product.getCurrentSellIn());
    }

    @Test
    void test_getCurrentSellIn_sulfuras() {
        Sulfuras sulfuras = Sulfuras.create(NAME, DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE);
        assertEquals(0, sulfuras.getCurrentSellIn());
    }

    @Test
    void test_getDefaultSellIn() {
        Product product;
        Instant createAt = NOW.minus(1, HOURS);
        Instant bestBefore;

        // 1 hour good time
        bestBefore = createAt.plus(1, HOURS);
        product = Product.create(NAME, DEFAULT_QUEALITY, createAt, bestBefore);
        assertEquals(0, product.getDefaultSellIn());

        // 16 hours good time
        bestBefore = createAt.plus(16, HOURS);
        product = Product.create(NAME, DEFAULT_QUEALITY, createAt, bestBefore);
        assertEquals(0, product.getDefaultSellIn());

        // 24 hours good time
        bestBefore = createAt.plus(24, HOURS);
        product = Product.create(NAME, DEFAULT_QUEALITY, createAt, bestBefore);
        assertEquals(1, product.getDefaultSellIn());

        // 32 hours good time
        bestBefore = createAt.plus(32, HOURS);
        product = Product.create(NAME, DEFAULT_QUEALITY, createAt, bestBefore);
        assertEquals(1, product.getDefaultSellIn());
    }

    @Test
    void test_getDefaultSellIn_sulfuras() {
        Sulfuras sulfuras = Sulfuras.create(NAME, DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE);
        assertEquals(0, sulfuras.getDefaultSellIn());
    }

    @Test
    void test_getCurrentQuality() {
        Product product;
        Instant createAt;
        Instant bestBefore;

        // today -> today
        createAt = NOW.minus(1, HOURS);
        bestBefore = NOW.plus(1, HOURS);
        product = Product.create(NAME, 10, createAt, bestBefore);
        assertEquals(10, product.getCurrentQuality());

        // yesterday -> today
        createAt = NOW.minus(25, HOURS);
        bestBefore = NOW.plus(1, HOURS);
        product = Product.create(NAME, 10, createAt, bestBefore);
        assertEquals(9, product.getCurrentQuality());

        // yesterday -> tomorrow
        createAt = NOW.minus(25, HOURS);
        bestBefore = NOW.plus(25, HOURS);
        product = Product.create(NAME, 10, createAt, bestBefore);
        assertEquals(9, product.getCurrentQuality());

        // yesterday -> tomorrow
        createAt = NOW.minus(25, HOURS);
        bestBefore = NOW.plus(25, HOURS);
        product = Product.create(NAME, 0, createAt, bestBefore);
        assertEquals(0, product.getCurrentQuality());

        // day before yesterday -> yesterday
        createAt = NOW.minus(49, HOURS);
        bestBefore = NOW.minus(25, HOURS);
        product = Product.create(NAME, 4, createAt, bestBefore);
        assertEquals(1, product.getCurrentQuality());

        // day before yesterday -> yesterday
        createAt = NOW.minus(49, HOURS);
        bestBefore = NOW.minus(25, HOURS);
        product = Product.create(NAME, 2, createAt, bestBefore);
        assertEquals(0, product.getCurrentQuality());
    }

    @Test
    void test_getCurrentQuality_agedBrie() {
        AgedBrie agedBrie;
        Instant createAt;
        Instant bestBefore;

        // today -> today
        createAt = NOW.minus(1, HOURS);
        bestBefore = NOW.plus(1, HOURS);
        agedBrie = AgedBrie.create(NAME, 10, createAt, bestBefore);
        assertEquals(10, agedBrie.getCurrentQuality());

        // yesterday -> today
        createAt = NOW.minus(25, HOURS);
        bestBefore = NOW.plus(1, HOURS);
        agedBrie = AgedBrie.create(NAME, 10, createAt, bestBefore);
        assertEquals(11, agedBrie.getCurrentQuality());

        // day before yesterday -> today
        createAt = NOW.minus(49, HOURS);
        bestBefore = NOW.plus(1, HOURS);
        agedBrie = AgedBrie.create(NAME, 49, createAt, bestBefore);
        assertEquals(50, agedBrie.getCurrentQuality());
    }

    @Test
    void test_getCurrentQuality_sulfuras() {
        Sulfuras sulfuras = Sulfuras.create(NAME, DEFAULT_QUEALITY, CREATED_AT, BEST_BEFORE);
        assertEquals(DEFAULT_QUEALITY, sulfuras.getCurrentQuality());
    }

    @Test
    void test_getCurrentQuality_backstagePass() {
        BackstagePass pass;
        Instant createAt;
        Instant bestBefore;

        // [0] => to 0
        createAt = NOW.minus(1, HOURS);
        bestBefore = NOW.plus(1, HOURS);
        pass = BackstagePass.create(NAME, 1, createAt, bestBefore);
        assertEquals(0, pass.getCurrentQuality());

        // 0 [1]
        createAt = NOW.minus(1, HOURS);
        bestBefore = NOW.plus(25, HOURS);
        pass = BackstagePass.create(NAME, 1, createAt, bestBefore);
        assertEquals(1, pass.getCurrentQuality());

        // 0 [1] 2 3 4 5
        createAt = NOW.minus(1 + 24 * 4, HOURS);
        bestBefore = NOW.plus(25, HOURS);
        pass = BackstagePass.create(NAME, 1, createAt, bestBefore);
        assertEquals(1 + 3 * 4, pass.getCurrentQuality());

        // 0 [1] 2 3 4 5, 6 7 8 9 10
        createAt = NOW.minus(1 + 24 * 9, HOURS);
        bestBefore = NOW.plus(25, HOURS);
        pass = BackstagePass.create(NAME, 1, createAt, bestBefore);
        assertEquals(1 + 3 * 4 + 2 * 5, pass.getCurrentQuality());

        // 0 [1] 2 3 4 5, 6 7 8 9 10, 11 12 13
        createAt = NOW.minus(1 + 24 * 12, HOURS);
        bestBefore = NOW.plus(25, HOURS);
        pass = BackstagePass.create(NAME, 1, createAt, bestBefore);
        assertEquals(1 + 3 * 4 + 2 * 5 + 3, pass.getCurrentQuality());

        // 0 [1] 2 3 4 5, 6 7 8 9 10, 11 12 13 => max 50
        createAt = NOW.minus(1 + 24 * 12, HOURS);
        bestBefore = NOW.plus(25, HOURS);
        pass = BackstagePass.create(NAME, 49, createAt, bestBefore);
        assertEquals(50, pass.getCurrentQuality());

        // [0] 1 2 3 4 5, 6 7 8 9 10, 11 12 13 => to 0
        createAt = NOW.minus(1 + 24 * 13, HOURS);
        bestBefore = NOW.plus(1, HOURS);
        pass = BackstagePass.create(NAME, 49, createAt, bestBefore);
        assertEquals(0, pass.getCurrentQuality());

        // 0 1 2 3 4 5, [6] 7 8 9 10, 11 12 13
        createAt = NOW.minus(1 + 24 * 7, HOURS);
        bestBefore = NOW.plus(1 + 24 * 6, HOURS);
        pass = BackstagePass.create(NAME, 1, createAt, bestBefore);
        assertEquals(1 + 2 * 4 + 3, pass.getCurrentQuality());

        // 0 1 2 3 4 5, 6 7 8 9 [10], 11 12 13
        createAt = NOW.minus(1 + 24 * 3, HOURS);
        bestBefore = NOW.plus(1 + 24 * 10, HOURS);
        pass = BackstagePass.create(NAME, 1, createAt, bestBefore);
        assertEquals(1 + 3, pass.getCurrentQuality());
    }
}
