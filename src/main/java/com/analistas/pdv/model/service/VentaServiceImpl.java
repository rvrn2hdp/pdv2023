package com.analistas.pdv.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analistas.pdv.model.orm.Venta;
import com.analistas.pdv.model.repository.IVentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired
    IVentaRepository ventaRepository;

    @Override
    public List<Venta> buscarTodo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodo'");
    }

    @Override
    public Venta buscarPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    @Transactional
    public void guardar(Venta venta) {
        ventaRepository.save(venta);
    }

    @Override
    public void borrar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrar'");
    }

}
