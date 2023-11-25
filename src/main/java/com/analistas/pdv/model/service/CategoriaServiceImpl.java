package com.analistas.pdv.model.service;

import com.analistas.pdv.model.orm.Categoria;
import com.analistas.pdv.model.repository.ICategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    ICategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> buscarTodo() {
        return (List<Categoria>) this.categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> buscarPor(String criterio) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria buscarPorId(Long id) {
        return this.categoriaRepository.findById(id).orElse(null);    
    }

    @Override
    @Transactional
    public void guardar(Categoria categoria) {
        this.categoriaRepository.save(categoria);
    }

}
