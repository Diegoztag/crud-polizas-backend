package com.coppel.crud_polizas.service;

import com.coppel.crud_polizas.domain.dto.InventarioDTO;
import com.coppel.crud_polizas.domain.entity.Inventario;
import com.coppel.crud_polizas.exceptions.ResourceNotFoundException;
import com.coppel.crud_polizas.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<InventarioDTO> obtenerArticulos() {
        List<Inventario> articulos = inventarioRepository.findAll();

        return articulos.stream().map(articulo -> new InventarioDTO(
                        articulo.getSku(),
                        articulo.getNombre(),
                        articulo.getCantidad())
        ).toList();
    }

    public InventarioDTO obtenerArticuloPorSku(String sku) {
        Inventario articulos = inventarioRepository.findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Artículo no encontrado en inventario"));

        return new InventarioDTO(
                articulos.getSku(),
                articulos.getNombre(),
                articulos.getCantidad()
        );
    }
}
