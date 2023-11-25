package com.analistas.pdv.model.repository;

import com.analistas.pdv.model.orm.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaRepository extends CrudRepository<Categoria, Long> {

}
