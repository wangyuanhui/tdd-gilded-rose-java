package cn.xpbootcamp.gilded_rose.service;

import cn.xpbootcamp.gilded_rose.infrastructure.EntityMapper;
import cn.xpbootcamp.gilded_rose.infrastructure.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository repository;
    private EntityMapper mapper;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
        mapper = new EntityMapper();
    }
}
