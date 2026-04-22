package com.taller.bookstore.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequest {

    @Valid
    @NotEmpty
    private List<OrderItemRequest> items = new ArrayList<>();
}
