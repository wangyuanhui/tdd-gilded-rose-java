package cn.xpbootcamp.gilded_rose.infrastructure;

import cn.xpbootcamp.gilded_rose.domain.ProductType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity
@Table(name = "products")
public class ProductData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    private String name;

    private String type;

    private int quality;

    private Instant createdAt;

    private Instant bestBefore;
}
