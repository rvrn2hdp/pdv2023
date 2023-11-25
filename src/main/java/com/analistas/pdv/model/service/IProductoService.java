package com.analistas.pdv.model.service;

import com.analistas.pdv.model.orm.Producto;
import java.util.List;

public interface IProductoService {
    
    public List<Producto> buscarTodo();
    
    public List<Producto> buscarPor(String criterio);
    
    public Producto buscarPorId(Long id);
    
    public void guardar(Producto producto);
}
