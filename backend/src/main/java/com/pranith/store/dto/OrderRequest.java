package com.pranith.store.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
public record OrderRequest(@NotEmpty List<@Valid Line> items){
 public record Line(@NotNull Long productId,@Min(1) int quantity){}
}
