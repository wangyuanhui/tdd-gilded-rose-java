package cn.xpbootcamp.gilded_rose.representation;

import cn.xpbootcamp.gilded_rose.representation.request.CreateProductRequest;
import cn.xpbootcamp.gilded_rose.representation.response.CreateProductResponse;
import cn.xpbootcamp.gilded_rose.representation.response.GetProductResponse;
import cn.xpbootcamp.gilded_rose.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public CreateProductResponse create(@RequestBody @Valid CreateProductRequest request) {
        // TODO
        return null;
    }

    @GetMapping("/{id}")
    public GetProductResponse get(@PathVariable("id") long id) {
        // TODO
        return null;
    }
}
