package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.ProductException;
import cn.xpbootcamp.gilded_rose.infrastructure.ProductData;
import cn.xpbootcamp.gilded_rose.infrastructure.ProductRepository;
import cn.xpbootcamp.gilded_rose.representation.request.CreateProductRequest;
import cn.xpbootcamp.gilded_rose.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    @Test
    void test_create() {
        CreateProductRequest request = new CreateProductRequest();
        request.setName("name");
        request.setType("COMMON");
        request.setDefaultQuality(10);
        request.setCreatedAt(Instant.now().minus(1, HOURS));
        request.setBestBefore(Instant.now().plus(25, HOURS));

        ArgumentCaptor<ProductData> captor = ArgumentCaptor.forClass(ProductData.class);

        ProductData res = new ProductData();
        res.setId(1L);

        when(repository.save(any(ProductData.class))).thenReturn(res);

        long id = service.create(request);

        verify(repository).save(captor.capture());
        assertEquals("name", captor.getValue().getName());
        assertEquals("COMMON", captor.getValue().getType());
        assertEquals(1L, id);
    }

    @Test
    void test_create_fail_when_type_bad() {
        CreateProductRequest request = new CreateProductRequest();
        request.setType("BAD");

        assertThrows(ProductException.class, () -> service.create(request));

        verify(repository, never()).save(any(ProductData.class));
    }

    @Test
    void test_get() {
        ProductData data = new ProductData();
        data.setId(1L);
        data.setName("name");

        when(repository.findById(1L)).thenReturn(Optional.of(data));

        ProductData res = service.get(1L);

        assertEquals(1L, res.getId());
        assertEquals("name", res.getName());
    }

    @Test
    void test_get_fail_when_id_not_found() {
        when(repository.findById(0L)).thenReturn(Optional.empty());

        assertThrows(ProductException.class, () -> service.get(0L));
    }
}
