package cn.xpbootcamp.gilded_rose.representation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class GetProductResponse {
    private long id;
    private String name;
    private String type;
    private int defaultQuality;
    private Instant createdAt;
    private Instant bestBefore;
}
