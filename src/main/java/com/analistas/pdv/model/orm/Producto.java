package com.analistas.pdv.model.orm;

import java.math.BigDecimal;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "productos")
@Where(clause = "activo = true")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_bar", unique = true, length = 13)
    private String codigoBarras;

    @NotEmpty(message = "La descripción es requerida...")
    @Size(min = 3, max = 65)
    private String descripcion;

    @NotNull(message = "El precio es requerido...")
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal precio;

    @NotNull(message = "El stock es requerido...")
    private int stock;

    @Column(name = "link_img", length = 500)
    private String linkImagen;

    @Column(name = "activo", columnDefinition = "boolean default 1")
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    private Categoria categoria;

    /*@PrePersist
    public void prePersist() {
        this.setActivo(true);
    }*/

    public Producto() {

        this.activo = true;
    }
}
