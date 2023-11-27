package com.analistas.pdv.model.service;

import com.analistas.pdv.model.orm.Producto;
import com.analistas.pdv.model.repository.IProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements IProductoService {

    /* @Inject Injection of dependency (Hollywood principle ) */
    @Autowired
    IProductoRepository productoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Producto> buscarTodo() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Producto> buscarPor(String criterio) {
        return productoRepository.buscarPor(criterio);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardar(Producto producto) {
        this.productoRepository.save(producto);
    }

}
