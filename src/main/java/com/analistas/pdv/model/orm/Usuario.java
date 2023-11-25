package com.analistas.pdv.model.orm;

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

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "El nombre es requerido...")
    @Size(min = 3, max = 50)
    private String nombre;

    @NotEmpty(message = "La clave es requerida...")
    @Size(min = 6, max = 80, message = "La clave debe tener entre 6 y 80 caracteres")
    private String clave;

    @Column(name = "activo", columnDefinition = "boolean default 1")
    private Boolean activo;

    @NotNull(message = "El permiso es requerido...")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permiso", referencedColumnName = "id")
    private Permiso permiso;

    @Override
    public String toString() {
        return this.getNombre() + " - " + this.getPermiso();
    }
}
