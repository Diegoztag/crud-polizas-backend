package com.coppel.crud_polizas.dto.Poliza;

import com.coppel.crud_polizas.entity.Empleado;
import com.coppel.crud_polizas.entity.Inventario;
import com.coppel.crud_polizas.entity.Poliza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PolizaDTO {
    private Long idPoliza;
    private int cantidad;
}

