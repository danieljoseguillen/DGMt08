package com.tarea8.dgmt08e01.Domain;

import com.tarea8.dgmt08e01.Modelos.Genero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class sortDTO {
    private String sortname;
    private Genero sortgender;
}
