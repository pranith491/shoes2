package com.pranith.store.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Entity @Table(name="customer_orders")
public class CustomerOrder {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) private User customer;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal total;
 @Column(nullable=false,length=30) private String status="PLACED";
 @Column(nullable=false) private Instant createdAt=Instant.now();
 @OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true) private List<OrderItem> items=new ArrayList<>();
 protected CustomerOrder(){}
 public CustomerOrder(User customer,BigDecimal total){this.customer=customer;this.total=total;}
 public void addItem(OrderItem item){items.add(item);item.setOrder(this);}
 public Long getId(){return id;} public User getCustomer(){return customer;} public BigDecimal getTotal(){return total;}
 public String getStatus(){return status;} public Instant getCreatedAt(){return createdAt;} public List<OrderItem> getItems(){return items;}
 public void setStatus(String status){this.status=status;}
}
