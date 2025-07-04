package com.seti.webflux_test.infraestructure.entrypoint.web.in;

import com.seti.webflux_test.domain.model.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductCreateRequestDTO {

    @NotNull(message = "El campo name es obligatorio.")
    @NotEmpty(message = "El campo name es obligatorio.")
    @NotBlank(message = "El campo name es obligatorio.")
    @Size(min = 3, max = 100, message = "El campo name debe tener entre 3 a 10 caracteres.")
    String name;

    @NotNull(message = "El campo branchId es obligatorio.")
    @Min(value = 0, message = "Error en el formulario.")
    Long branchId;

    @NotNull(message = "El campo stock es obligatorio.")
    @Min(value = 0, message = "El stock no puede ser negativo")
    Long stock;

    public Product toDomain() {
        return Product.builder().name(name).branchId(branchId).stock(stock).build();
    }
}