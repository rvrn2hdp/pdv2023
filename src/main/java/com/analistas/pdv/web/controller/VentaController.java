package com.analistas.pdv.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.analistas.pdv.model.orm.LineaVenta;
import com.analistas.pdv.model.orm.Producto;
import com.analistas.pdv.model.orm.Venta;
import com.analistas.pdv.model.service.IProductoService;
import com.analistas.pdv.model.service.IVentaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ventas")
@SessionAttributes("venta")
public class VentaController {

    @Autowired
    IVentaService ventaService;

    @Autowired
    IProductoService productoService;

    @GetMapping("/listado")
    public String verVentas(Model model) {

        model.addAttribute("titulo", "Listado de ventas");

        return "ventas/list";
    }

    @GetMapping("/nueva")
    public String nuevaVenta(Model model) {

        model.addAttribute("titulo", "Nueva venta");
        model.addAttribute("venta", new Venta());

        return "ventas/form";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@Valid Venta venta, Model model, BindingResult result,
            @RequestParam("item_id[]") List<String> itemsIds,
            @RequestParam("cantidad[]") List<String> cantidades,
            RedirectAttributes redirect, SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Error en el formulario");
            model.addAttribute("danger", "Corrija los errores...");

            return "ventas/form";
        }

        // Verificar si hay Productos(Lineas):
        if (itemsIds == null || itemsIds.isEmpty()) {

            model.addAttribute("titulo", "Error en el formulario");
            model.addAttribute("danger", "Debe agregar al menos un producto...");

            return "ventas/form";
        }

        // si no hay errores:

        for (int i = 0; i < itemsIds.size() - 1; i++) {
            String itemId = itemsIds.get(i);
            String cantidad = cantidades.get(i);

            // Crear una nueva linea de venta
            LineaVenta lineaVenta = new LineaVenta();
            lineaVenta.setProducto(productoService.buscarPorId(Long.parseLong(itemId)));
            lineaVenta.setCantidad(Integer.parseInt(cantidad));

            // Verificar si hay stock suficiente para la venta:
            if (Integer.parseInt(cantidad) > (lineaVenta.getProducto().getStock())) {
                model.addAttribute("titulo", "Error en el formulario");
                model.addAttribute("warning", "No hay suficiente stock para la venta...");

                return "ventas/form";
            }

            // Agregar la linea de venta a la venta
            venta.agregarLinea(lineaVenta);

        }

        // Guardar la venta:
        ventaService.guardar(venta);

        // Limpiar la sesión
        status.setComplete();

        // Mostrar un mensaje de éxito
        redirect.addFlashAttribute("success", "La venta se ha guardado correctamente.");

        return "redirect:/ventas/listado";
    }

    /**
     * Retrieves a list of products based on the specified search criteria.
     *
     * @param criterio the search criteria
     * @return a list of products matching the search criteria
     */
    @GetMapping(value = "/buscar-productos/{criterio}", produces = "application/json")
    public @ResponseBody List<Producto> buscarProductos(@PathVariable("criterio") String criterio) {

        return productoService.buscarPor(criterio);
    }
}
