package com.pranith.store.controller;
import com.pranith.store.entity.Product;
import com.pranith.store.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/products")
public class ProductController {
 private final ProductService service;public ProductController(ProductService s){service=s;}
 @GetMapping public List<Product> all(){return service.allActive();}
 @PostMapping public Product create(@RequestBody Map<String,Object>b){return service.create(b);}
 @PutMapping("/{id}") public Product update(@PathVariable Long id,@RequestBody Map<String,Object>b){return service.update(id,b);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}
