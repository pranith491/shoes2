package com.pranith.store.controller;
import com.pranith.store.dto.OrderRequest;
import com.pranith.store.entity.CustomerOrder;
import com.pranith.store.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/orders")
public class OrderController {
 private final OrderService service;public OrderController(OrderService s){service=s;}
 @PostMapping public Map<String,Object> place(@Valid @RequestBody OrderRequest r,Authentication a){CustomerOrder o=service.place(a.getName(),r);return Map.of("id",o.getId(),"total",o.getTotal(),"status",o.getStatus());}
 @GetMapping("/mine") public List<Map<String,Object>> mine(Authentication a){return service.myOrders(a.getName());}
 @GetMapping("/admin/all") public List<Map<String,Object>> all(){return service.allOrders();}
 @PutMapping("/admin/{id}/status") public Map<String,Object> status(@PathVariable Long id,@RequestBody Map<String,String>b){return service.updateStatus(id,b.get("status"));}
}
