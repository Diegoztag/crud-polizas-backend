package com.coppel.crud_polizas.controller;

import com.coppel.crud_polizas.entity.Poliza;
import com.coppel.crud_polizas.service.PolizaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polizas")
public class PolizaController {

    @Autowired
    private PolizaService polizaService;

    @GetMapping
    public List<Poliza> obtenerPolizas() {
        return polizaService.obtenerPolizas();
    }

    @PostMapping
    public Poliza crearPoliza(@RequestParam Long empleadoId, @RequestParam String sku, @RequestParam int cantidad) {
        return polizaService.crearPoliza(empleadoId, sku, cantidad);
    }

    @DeleteMapping("/{id}")
    public void eliminarPoliza(@PathVariable Long id) {
        polizaService.eliminarPoliza(id);
    }

    @PutMapping("/{id}/empleado")
    public Poliza actualizarEmpleadoEnPoliza(@PathVariable Long id, @RequestParam Long nuevoEmpleadoId) {
        return polizaService.actualizarEmpleadoEnPoliza(id, nuevoEmpleadoId);
    }
}
