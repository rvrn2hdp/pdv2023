package com.analistas.pdv.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.analistas.pdv.model.orm.Venta;

public interface IVentaRepository extends CrudRepository<Venta, Long> {

}
