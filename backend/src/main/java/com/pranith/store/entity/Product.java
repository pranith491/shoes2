package com.pranith.store.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity @Table(name="products")
public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=140) private String name;
 @Column(nullable=false,length=50) private String category;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal price;
 @Column(length=1000) private String imageUrl;
 @Column(nullable=false) private boolean active=true;
 protected Product(){}
 public Product(String name,String category,BigDecimal price,String imageUrl){this.name=name;this.category=category;this.price=price;this.imageUrl=imageUrl;this.active=true;}
 public Long getId(){return id;} public String getName(){return name;} public String getCategory(){return category;}
 public BigDecimal getPrice(){return price;} public String getImageUrl(){return imageUrl;} public boolean isActive(){return active;}
 public void setName(String v){name=v;} public void setCategory(String v){category=v;} public void setPrice(BigDecimal v){price=v;}
 public void setImageUrl(String v){imageUrl=v;} public void setActive(boolean v){active=v;}
}
