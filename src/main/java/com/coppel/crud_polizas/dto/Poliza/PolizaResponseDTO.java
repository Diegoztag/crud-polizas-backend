package com.coppel.crud_polizas.dto.Poliza;

import com.coppel.crud_polizas.dto.EmpleadoDTO;
import com.coppel.crud_polizas.dto.InventarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PolizaResponseDTO {
    private PolizaDTO poliza;
    private EmpleadoDTO empleado;
    private InventarioDTO inventario;
}
