$(document).ready(function () {

    $("#buscar_productos").autocomplete({

        source: (request, response) => {
            $.ajax({
                url: `/ventas/buscar-productos/${request.term}`,
                dataType: "json",
                data: {
                    term: request.term
                },
                success: (data) => {
                    response($.map(data, (item) => {
                        return {
                            value: item.id,
                            label: `${item.descripcion} - ${item.precio}`,
                        }
                    }));
                }
            });
        },
        select: (event, ui) => {
            //console.log(ui.item);
            // Crear una linea para la tabla:
            let linea = $("#lineas").html();

            // Asignar valores a las celdas de la linea:
            let producto = ui.item.label;
            let descripcion = producto.split("-")[0];
            let precio = producto.split("-")[1].replace("$", "");
            let id = ui.item.value;

            // verificar si el producto ya fue agregado:
            if (LineasUtil.esRepetido(id)) {
                LineasUtil.incrementarCantidad(id, precio);
                return false;
            }

            // Asignar valores de la linea con RE:
            linea = linea.replace(new RegExp("{ID}", "g"), id);
            linea = linea.replace(new RegExp("{DESCRIPCION}", "g"), descripcion);
            linea = linea.replace(new RegExp("{PRECIO}", "g"), precio);

            $("#tabla_productos").append(linea);

            // Calcular el subtotal de la linea:
            LineasUtil.calcularSubtotal(id, precio, 1);
        }

    });
});

// Clase Helper de linea de Ventas:
const LineasUtil = {

    // Incrementar Cantidad:
    incrementarCantidad: function (id, precio) {
        let cantidad = parseInt($(`#cantidad_${id}`).val());
        $(`#cantidad_${id}`).val(++cantidad);
        this.calcularSubtotal(id, precio, cantidad);
    },

    // Calcular el subtotal de una linea:
    calcularSubtotal: function (id, precio, cantidad) {
        $("#subtotal_" + id).html(parseFloat(precio) * parseInt(cantidad));
        this.calcularTotal();
    },

    // Determine if a line has already been added
    esRepetido: function (id) {
        return $('input[name="item_id[]"]').toArray().some(function (input) {
            return parseInt(id) === parseInt(input.value);
        });
    },

    // Calcular Total:
    calcularTotal: function () {
        let total = 0;

        $(`span[id^="subtotal_"]`).each(function () {
            total += parseFloat($(this).html());
        });

        $("#total").html(`$${parseFloat(total)}`)
        //console.log(total);
    },

    // Eliminar una linea de la tabla:
    eliminarLinea: function (id) {
        $(`#fila_${id}`).remove();
        this.calcularTotal();
    }
}