package com.analistas.pdv.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.analistas.pdv.model.orm.Producto;
import com.analistas.pdv.model.orm.Venta;
import com.analistas.pdv.model.service.IProductoService;

@Controller
@RequestMapping("/ventas")
@SessionAttributes("venta")
public class VentaController {

    @Autowired
    IProductoService productoService;
    
    @GetMapping("/listado")
    public String verVentas(Model model){
        
        model.addAttribute("titulo", "Listado de ventas");

        return "ventas/list";
    }

    @GetMapping("/nueva")
    public String nuevaVenta(Model model){

        model.addAttribute("titulo", "Nueva venta");
        model.addAttribute("venta", new Venta());

        return "ventas/form";
    }

    
    /**
     * Retrieves a list of products based on the specified search criteria.
     *
     * @param  criterio  the search criteria
     * @return           a list of products matching the search criteria
     */
    @GetMapping(value = "/buscar-productos/{criterio}", produces = "application/json")
    public @ResponseBody List<Producto> buscarProductos(@PathVariable("criterio") String criterio){

        

        return productoService.buscarPor(criterio);
    }
}
