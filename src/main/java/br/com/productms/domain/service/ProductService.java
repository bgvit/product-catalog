package br.com.productms.domain.service;

import br.com.productms.domain.model.Product;
import br.com.productms.infrastructure.repository.ProductRepository;
import br.com.productms.infrastructure.repository.exception.InvalidPriceException;
import br.com.productms.infrastructure.repository.exception.MissingArgumentsException;
import br.com.productms.infrastructure.repository.exception.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    public ResponseEntity<Product> getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty())
            throw new NotFoundException();

        return ResponseEntity.ok(productOptional.get());
    }

    public ResponseEntity<Product> putProduct(Product productRequest, Long id) {

        validateProduct(productRequest);

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty())
            throw new NotFoundException();
        Product productRetrieved = productOptional.get();

        Product newProduct = Product.builder()
                .id(productRetrieved.getId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        return ResponseEntity.ok(newProduct);
    }

    public ResponseEntity<Product> deleteProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty())
            throw new NotFoundException();

        productRepository.delete(productOptional.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Product> postProduct(@RequestBody Product productRequest) {

        validateProduct(productRequest);

        Product savedProduct = productRepository.save(productRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedProduct);
    }
//
    private void validateProduct(Product product) {

        if (areMissingProductRequirements(product)){
            throw new MissingArgumentsException(HttpStatus.BAD_REQUEST, "Algum campo requerido está faltando. Por favor, verifique se o nome do produto, sua descrição e o preço estão presentes em seu pedido.");
        }

        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPriceException(HttpStatus.BAD_REQUEST, "O preço está com um valor negativo. Por favor, coloque um valor positivo");
        }

    }

    private Boolean areMissingProductRequirements(Product product){
        if (Objects.isNull(product.getPrice()) && StringUtils.isEmpty(product.getName()) && StringUtils.isEmpty(product.getDescription())) {
            return true;
        }
        return false;
    }
}

