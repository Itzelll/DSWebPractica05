/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.uv;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author chel
 */
@Named(value = "beanIndex")
@SessionScoped
public class BeanIndex implements Serializable {

//    private Usuario usr = new Usuario();
    private Venta venta = new Venta();
    private VentaDetalle detalle = new VentaDetalle();

    public VentaDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(VentaDetalle detalle) {
        this.detalle = detalle;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public BeanIndex() {
    }
       
//    public Usuario getUsr() {
//        return usr;
//    }
//
//    public void setUsr(Usuario usr) {
//        this.usr = usr;
//    }
    public void mostrar() {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction t = session.beginTransaction();
//        
//        Long idCliente = Long.valueOf(usr.getUser());
//        
//        Cliente cliente = session.get(Cliente.class, 1L);
//        if(cliente!=null){
//            List<Venta> lstventasclientes = cliente.getVentas();            
//            
//            addMessage(FacesMessage.SEVERITY_INFO, "Atencion", "Cliente: No V:" 
//                    + lstventasclientes.size());
//            Logger.getLogger(BeanIndex.class.getName()).log(Level.INFO, "Cliente si existe");
//        }else{
//            addMessage(FacesMessage.SEVERITY_INFO, "Atencion", "No existe");
//            Logger.getLogger(BeanIndex.class.getName()).log(Level.INFO, "No existe");
//            
//        }
    }

    public void guardar() {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();

            Venta v = new Venta();
            java.util.Date fechaT = new java.util.Date();
            v.setFecha(new Date(fechaT.getTime()));
            Cliente c = new Cliente();
            c.setIdCliente(1L);
            //v.setCliente(1L);
            v.setCliente(c);
            v.setTotal(new BigDecimal(20));            
            
            session.save(v);            

            VentaDetalle ventaDetalle = new VentaDetalle();
            
            
            ventaDetalle.setVenta(v); // Asociar el detalle con la venta creada
            ventaDetalle.setIdProducto(detalle.getIdProducto());
            ventaDetalle.setDescripcion(detalle.getDescripcion());
            ventaDetalle.setPrecio(detalle.getPrecio());
            ventaDetalle.setCantidad(detalle.getCantidad());
            //             
            session.save(ventaDetalle);            

            addMessage(FacesMessage.SEVERITY_INFO, "Atencion", "Se guardó");
            Logger.getLogger(BeanIndex.class.getName()).log(Level.INFO, "Se guardó");
            
            t.commit();
            session.close();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la venta: " + e.getMessage());
            Logger.getLogger(BeanIndex.class.getName()).log(Level.SEVERE, "Error al guardar la venta", e);
        }
    }
    
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void addRow() {
        VentaDetalle d1 = new VentaDetalle();
        d1.setIdProducto(detalle.getIdProducto());
        d1.setCantidad(detalle.getCantidad());
        d1.setDescripcion(detalle.getDescripcion());
        d1.setPrecio(detalle.getPrecio());
        d1.setIdDetalleVenta(1L);

        venta.getVentasDetalle().add(d1);
//        listaVentasDetalle.add(d1);
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto agregado", "El producto se ha agregado a la lista.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
