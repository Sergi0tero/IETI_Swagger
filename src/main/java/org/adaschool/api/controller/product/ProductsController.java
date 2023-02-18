package org.adaschool.api.controller.product;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.adaschool.api.service.product.ProductsService;
import org.adaschool.api.service.product.ProductsServiceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products/")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(@Autowired ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto product) {
        //TODO implement this method
        Product p = new Product(product);
        productsService.save(p);
        URI createdProductUri = URI.create("");
        return ResponseEntity.created(createdProductUri).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productsService.all());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(productsService.findById(id).orElseThrow(() -> new ProductNotFoundException(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody ProductDto productDto) {
        //TODO implement this method
        productsService.findById(id).orElseThrow(() -> new ProductNotFoundException(id)).update(productDto);
        return ResponseEntity.ok(productsService.save(productsService.findById(id).get()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        productsService.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
