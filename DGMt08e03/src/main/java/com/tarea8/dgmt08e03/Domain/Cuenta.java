package com.tarea8.dgmt08e03.Domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "IBAN")
@Entity
public class Cuenta {
    @Id
    private String IBAN;

    @Size(min = 3, message = "El alias debe tener al menos 3 caracteres.")
    @NotEmpty(message = "El alias no puede estar vacío.")
    @NotNull(message = "El alias es obligatorio.")
    private String alias;

    @Min(value = 0, message = "El saldo no puede ser negativo")
    @NotNull(message = "El saldo no puede estar vacio.")
    private Double saldo;

    @ToString.Exclude
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos = new ArrayList<>();
}
