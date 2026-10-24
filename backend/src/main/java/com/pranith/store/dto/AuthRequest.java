package com.pranith.store.dto;
import jakarta.validation.constraints.*;
public record AuthRequest(@NotBlank @Email String email,@NotBlank String password){}
