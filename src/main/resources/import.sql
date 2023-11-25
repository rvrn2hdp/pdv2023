

/* Data for the categorias table */

INSERT INTO categorias (nombre) VALUES ('Alimentos');
INSERT INTO categorias (nombre) VALUES ('Bebidas');
INSERT INTO categorias (nombre) VALUES ('Bazar');
INSERT INTO categorias (nombre) VALUES ('Perfumeria');
INSERT INTO categorias (nombre) VALUES ('Fiambreria');
INSERT INTO categorias (nombre) VALUES ('Otros');

/* Data for the productos table */

INSERT INTO productos (activo, id_categoria, precio, stock, cod_bar, descripcion, link_img) VALUES (1, '2', '750', '50', '111111111111', 'Coca Cola', 'https://licoreschullavida.com/wp-content/uploads/2020/07/Coca-cola-1-litro.jpg');
INSERT INTO productos (activo, id_categoria, precio, stock, cod_bar, descripcion, link_img) VALUES (1, '6', '1500', '25', '222222222222', 'Detergente', 'https://th.bing.com/th/id/OIP.UqpQV686CcZ6_dKRUCoWUwHaHa?rs=1&pid=ImgDetMain');
INSERT INTO productos (activo, id_categoria, precio, stock, cod_bar, descripcion, link_img) VALUES (1, '2', '650', '150', '333333333333', 'Cerveza Artesanal', 'https://th.bing.com/th/id/OIP.5zxRjTufx28H5Z2pvKbEOAHaFe?rs=1&pid=ImgDetMain');

