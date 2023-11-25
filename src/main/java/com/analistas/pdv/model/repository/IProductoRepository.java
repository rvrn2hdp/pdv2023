package com.analistas.pdv.model.repository;

import com.analistas.pdv.model.orm.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IProductoRepository extends CrudRepository<Producto, Long> {
    
}
