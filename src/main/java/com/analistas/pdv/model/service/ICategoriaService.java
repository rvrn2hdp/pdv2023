package com.analistas.pdv.model.service;

import com.analistas.pdv.model.orm.Categoria;
import java.util.List;

public interface ICategoriaService {

    public List<Categoria> buscarTodo();

    public List<Categoria> buscarPor(String criterio);

    public Categoria buscarPorId(Long id);

    public void guardar(Categoria categoria);

}
