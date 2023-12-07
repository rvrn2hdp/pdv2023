package com.analistas.pdv.web.controller;

import com.analistas.pdv.model.orm.Categoria;
import com.analistas.pdv.model.orm.Producto;
import com.analistas.pdv.model.service.ICategoriaService;
import com.analistas.pdv.model.service.IProductoService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
@SessionAttributes("producto")
public class ProductoController {

    @Autowired
    IProductoService productoService;

    @Autowired
    ICategoriaService categoriaService;

    @GetMapping("/listado")
    public String listar(Model model) {

        model.addAttribute("titulo", "Listado de productos");
        model.addAttribute("productos", productoService.buscarTodo());

        return "productos/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("titulo", "Nuevo producto");
        model.addAttribute("producto", new Producto());

        return "productos/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {

        Producto producto = this.productoService.buscarPorId(id);

        model.addAttribute("titulo", "Edición de producto");
        model.addAttribute("producto", producto);

        return "productos/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Producto producto,
            BindingResult result,
            @RequestParam("cat") Long idCat,
            Model model, SessionStatus status,
            RedirectAttributes redirect) {

        // Validación de los datos:
        if (result.hasErrors()) {

            model.addAttribute("titulo", "Error en el formulario.");
            model.addAttribute("danger", "Corrija los errores...");

            return "productos/form";
        }

        String mensaje = producto.getId() != null ? "Se ha actualizado el producto " + producto.getDescripcion() + "."
                : "Se ha creado el producto " + producto.getDescripcion() + ".";

        producto.setCategoria(this.categoriaService.buscarPorId(idCat));
        productoService.guardar(producto);
        redirect.addFlashAttribute(producto.getId() == null ? "success" : "warning", mensaje);
        status.setComplete();

        return "redirect:/productos/listado";
    }

    // Eliminación Lógica de un producto:
    @GetMapping("/borrar/{id}")
    public String deshabilitarOrHabilitarProducto(@PathVariable("id") Long id, Model model,
            RedirectAttributes redirect) {

        // this.productoService.buscarPorId(id).setActivo(false);
        Producto producto = productoService.buscarPorId(id);
        producto.setActivo(false);

        this.productoService.guardar(producto);

        redirect.addFlashAttribute("danger", "Se ha deshabilitado el producto " + producto.getDescripcion() + ".");

        return "redirect:/productos/listado";
    }

    @ModelAttribute("categorias")
    public List<Categoria> getCategorias() {

        return this.categoriaService.buscarTodo();
    }
}
