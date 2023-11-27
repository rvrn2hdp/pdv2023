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
        }
    });
});