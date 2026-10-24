package com.pranith.store.service;
import com.pranith.store.entity.Product;
import com.pranith.store.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.*;
@Service
public class ProductService {
 private final ProductRepository products;public ProductService(ProductRepository p){products=p;}
 public List<Product> allActive(){return products.findByActiveTrueOrderByIdDesc();}
 public Product create(Map<String,Object>b){return products.save(new Product(text(b,"name",140),text(b,"category",50),price(b.get("price")),image(b.get("imageUrl"))));}
 public Product update(Long id,Map<String,Object>b){Product p=products.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
  p.setName(text(b,"name",140));p.setCategory(text(b,"category",50));p.setPrice(price(b.get("price")));p.setImageUrl(image(b.get("imageUrl")));return products.save(p);}
 public void delete(Long id){Product p=products.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));p.setActive(false);products.save(p);}
 private String text(Map<String,Object>b,String k,int max){Object v=b.get(k);if(!(v instanceof String s)||s.isBlank()||s.length()>max)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid "+k);return s.trim();}
 private BigDecimal price(Object v){try{BigDecimal n=new BigDecimal(String.valueOf(v));if(n.signum()<=0||n.scale()>2||n.precision()>12)throw new Exception();return n;}catch(Exception e){throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Price must be positive with at most 2 decimals");}}
 private String image(Object v){if(v==null||String.valueOf(v).isBlank())return null;String s=String.valueOf(v).trim();if(s.length()>1000||!s.startsWith("https://"))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Image URL must start with https://");return s;}
}
