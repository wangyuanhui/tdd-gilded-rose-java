package cn.xpbootcamp.gilded_rose.service;

import cn.xpbootcamp.gilded_rose.domain.ProductType;
import cn.xpbootcamp.gilded_rose.domain.entity.AbstractProduct;
import cn.xpbootcamp.gilded_rose.domain.entity.AgedBrie;
import cn.xpbootcamp.gilded_rose.domain.entity.BackstagePass;
import cn.xpbootcamp.gilded_rose.domain.entity.Product;
import cn.xpbootcamp.gilded_rose.domain.entity.Sulfuras;
import cn.xpbootcamp.gilded_rose.exception.ProductException;
import cn.xpbootcamp.gilded_rose.infrastructure.EntityMapper;
import cn.xpbootcamp.gilded_rose.infrastructure.ProductData;
import cn.xpbootcamp.gilded_rose.infrastructure.ProductRepository;
import cn.xpbootcamp.gilded_rose.representation.request.CreateProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository repository;
    private EntityMapper mapper;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
        mapper = new EntityMapper();
    }

    public long create(CreateProductRequest request) {
        ProductType type = convertType(request.getType());
        AbstractProduct product;
        switch (type) {
            case COMMON: product = Product.create(
                    request.getName(), request.getDefaultQuality(), request.getCreatedAt(), request.getBestBefore());
            case AGEDBRIE: product = AgedBrie.create(
                    request.getName(), request.getDefaultQuality(), request.getCreatedAt(), request.getBestBefore());
            case SULFURAS: product = Sulfuras.create(
                    request.getName(), request.getDefaultQuality(), request.getCreatedAt(), request.getBestBefore());
            case BACKSTAGEPASS: product = BackstagePass.create(
                    request.getName(), request.getDefaultQuality(), request.getCreatedAt(), request.getBestBefore());
            default: product = Product.create(
                    request.getName(), request.getDefaultQuality(), request.getCreatedAt(), request.getBestBefore());
        }
        ProductData data = mapper.toProductData(product);
        data = repository.save(data);
        return data.getId();
    }

    public ProductData get(long id) {
        return repository.findById(id).orElseThrow(() -> new ProductException(404, "Data not found by id" + id));
    }

    protected ProductType convertType(String type) {
        for (ProductType value : ProductType.values()) {
            if (value.toString().equalsIgnoreCase(type)) {
                return value;
            }
        }
        throw new ProductException(400, "Invalid type.");
    }
}
