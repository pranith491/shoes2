package com.pranith.store.service;
import com.pranith.store.dto.*;
import com.pranith.store.entity.User;
import com.pranith.store.repository.UserRepository;
import com.pranith.store.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;
@Service
public class AuthService {
 private final UserRepository users;private final PasswordEncoder encoder;private final JwtService jwt;
 public AuthService(UserRepository u,PasswordEncoder e,JwtService j){users=u;encoder=e;jwt=j;}
 public Map<String,Object> register(RegisterRequest r){
  String email=r.email().trim().toLowerCase();
  if(users.existsByEmailIgnoreCase(email))throw new ResponseStatusException(HttpStatus.CONFLICT,"Email is already registered");
  return response(users.save(new User(r.name().trim(),email,encoder.encode(r.password()))));
 }
 public Map<String,Object> login(AuthRequest r){
  User u=users.findByEmailIgnoreCase(r.email().trim()).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password"));
  if(!encoder.matches(r.password(),u.getPasswordHash()))throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password");
  return response(u);
 }
 private Map<String,Object> response(User u){return Map.of("token",jwt.generate(u.getEmail(),u.getRole()),"name",u.getName(),"email",u.getEmail(),"role",u.getRole());}
}
