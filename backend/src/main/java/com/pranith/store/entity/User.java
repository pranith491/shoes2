package com.pranith.store.entity;
import jakarta.persistence.*;
import java.time.Instant;
@Entity
@Table(name="users", uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String name;
 @Column(nullable=false,unique=true,length=190) private String email;
 @Column(nullable=false) private String passwordHash;
 @Column(nullable=false,length=20) private String role="CUSTOMER";
 @Column(nullable=false) private Instant createdAt=Instant.now();
 protected User(){}
 public User(String name,String email,String passwordHash){this.name=name;this.email=email;this.passwordHash=passwordHash;this.role="CUSTOMER";}
 public Long getId(){return id;} public String getName(){return name;} public String getEmail(){return email;}
 public String getPasswordHash(){return passwordHash;} public String getRole(){return role;} public Instant getCreatedAt(){return createdAt;}
 public void setRole(String role){this.role=role;}
}
