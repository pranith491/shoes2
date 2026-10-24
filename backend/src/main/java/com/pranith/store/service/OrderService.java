package com.pranith.store.service;
import com.pranith.store.dto.OrderRequest;
import com.pranith.store.entity.*;
import com.pranith.store.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.*;
@Service
public class OrderService {
 private final UserRepository users;private final ProductRepository products;private final OrderRepository orders;
 public OrderService(UserRepository u,ProductRepository p,OrderRepository o){users=u;products=p;orders=o;}
 @Transactional public CustomerOrder place(String email,OrderRequest request){
  User user=users.findByEmailIgnoreCase(email).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User not found"));
  if(request.items().size()>50)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Too many products");
  BigDecimal total=BigDecimal.ZERO;CustomerOrder order=new CustomerOrder(user,BigDecimal.ZERO);
  for(OrderRequest.Line line:request.items()){
   if(line.quantity()<1||line.quantity()>99)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Quantity must be 1 to 99");
   Product p=products.findById(line.productId()).filter(Product::isActive).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product unavailable"));
   total=total.add(p.getPrice().multiply(BigDecimal.valueOf(line.quantity())));
   order.addItem(new OrderItem(p,p.getName(),p.getPrice(),line.quantity()));
  }
  CustomerOrder saved=new CustomerOrder(user,total);for(OrderItem item:order.getItems())saved.addItem(item);return orders.save(saved);
 }
 @Transactional(readOnly=true) public List<Map<String,Object>> myOrders(String email){return orders.findByCustomerEmailOrderByCreatedAtDesc(email).stream().map(this::view).toList();}
 @Transactional(readOnly=true) public List<Map<String,Object>> allOrders(){return orders.findAllByOrderByCreatedAtDesc().stream().map(this::view).toList();}
 @Transactional public Map<String,Object> updateStatus(Long id,String status){
  List<String> allowed=List.of("PLACED","PROCESSING","SHIPPED","DELIVERED","CANCELLED");
  if(status==null||!allowed.contains(status.toUpperCase()))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid status");
  CustomerOrder o=orders.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found"));o.setStatus(status.toUpperCase());orders.save(o);return view(o);
 }
 private Map<String,Object> view(CustomerOrder o){
  List<Map<String,Object>> items=o.getItems().stream().map(i->Map.<String,Object>of("productId",i.getProduct().getId(),"name",i.getProductName(),"unitPrice",i.getUnitPrice(),"quantity",i.getQuantity())).toList();
  return Map.of("id",o.getId(),"customerName",o.getCustomer().getName(),"customerEmail",o.getCustomer().getEmail(),"total",o.getTotal(),"status",o.getStatus(),"createdAt",o.getCreatedAt(),"items",items);
 }
}
