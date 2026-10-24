package com.pranith.store.controller;
import com.pranith.store.dto.*;
import com.pranith.store.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AuthService service;public AuthController(AuthService s){service=s;}
 @PostMapping("/register") public Map<String,Object> register(@Valid @RequestBody RegisterRequest r){return service.register(r);}
 @PostMapping("/login") public Map<String,Object> login(@Valid @RequestBody AuthRequest r){return service.login(r);}
}
