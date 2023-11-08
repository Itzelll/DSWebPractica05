/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable; 
import java.util.List;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author yacruz
 */
@Entity
@Table(name="cliente")
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(generator = "cliente_idcliente_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cliente_idcliente_seq", sequenceName = "cliente_idcliente_seq", 
            initialValue = 1, allocationSize = 1)
    @Column(name = "idcliente")
    private Long idCliente;
    
    @Column(name = "nombre")
    private String nombre;
    @Column (name = "rfc")
    private String rfc;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Venta> ventas;    

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
    
    
}
