package com.pranith.store.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity @Table(name="order_items")
public class OrderItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) private CustomerOrder order;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) private Product product;
 @Column(nullable=false,length=140) private String productName;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal unitPrice;
 @Column(nullable=false) private int quantity;
 protected OrderItem(){}
 public OrderItem(Product p,String n,BigDecimal price,int q){product=p;productName=n;unitPrice=price;quantity=q;}
 public void setOrder(CustomerOrder order){this.order=order;}
 public Long getId(){return id;} public Product getProduct(){return product;} public String getProductName(){return productName;}
 public BigDecimal getUnitPrice(){return unitPrice;} public int getQuantity(){return quantity;}
}
