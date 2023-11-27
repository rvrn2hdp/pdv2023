package com.analistas.pdv.model.repository;

import com.analistas.pdv.model.orm.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IProductoRepository extends CrudRepository<Producto, Long> {
    
    // JPQL: Java Persistence Query Language - es una version de SQL
    // SQL: Structured Query Language - Dialecto de JPQL para hibernate
    // Haremos la consulta equivalente en SQL a:
    // SELECT * FROM PRODUCTO WHERE cod_bar like ? or descripcion like ? and activo = 1;
    @Query(value = "select p from Producto p where p.codigoBarras like %:criterio% or p.descripcion like %:criterio% and (p.stock > 0 and p.activo = true)")
    List<Producto> buscarPor(@Param("criterio") String criterio);
    
}
