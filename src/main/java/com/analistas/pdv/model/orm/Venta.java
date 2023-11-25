package com.analistas.pdv.model.orm;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long nroFactura;

    @NotNull(message = "La fecha es requerida...")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm z")
    private LocalDateTime fechaHora;

    @Size(min = 3, max = 65)
    private String descripcion;
    
    @NotNull(message = "El usuario es requerido...")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_venta", referencedColumnName = "id")
    private List<LineaVenta> lineas;

    @Column(name = "activo", columnDefinition = "boolean default 1")
    private boolean activo;

    public Venta() {
        this.lineas = new ArrayList<>();
        this.fechaHora = LocalDateTime.now();
        this.activo = true;
        this.descripcion = "Ninguna";
    }

    public void agregarLinea(LineaVenta linea) {
        this.lineas.add(linea);
    }

    public Double calcularTotal() {
        Double total = 0.0;
        for (LineaVenta linea : lineas) {
            total += linea.calcularSubTotal();
        }
        return total;
    }
}
