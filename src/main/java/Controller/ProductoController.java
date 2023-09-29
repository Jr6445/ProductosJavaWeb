/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import EJB.ProductoFacadeLocal;
import Entity.Producto;
import java.io.Serializable;
import static java.lang.System.console;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 *
 * @author Riveraa
 */

@Named("productoController")
@SessionScoped
public class ProductoController implements Serializable{
     @EJB
     private ProductoFacadeLocal productoFecade;
     private List<Producto> listaProducto;
     private Producto producto;
     
     
    public ProductoFacadeLocal getProductoFecade() {
        return productoFecade;
    }

    public void setProductoFecade(ProductoFacadeLocal productoFecade) {
        this.productoFecade = productoFecade;
    }

    public List<Producto> getListaProducto() {
    this.listaProducto = this.productoFecade.findAll();
    return listaProducto;
}

    

    
    
    public void agregarProducto() {
    try {
        System.out.println(producto);
        productoFecade.create(producto);
        FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto agregado con éxito", ""));
        producto = new Producto();  // Reset the product
        System.out.println("se envio");
    } catch (Exception e) {
        e.printStackTrace();  // This will print the exception to the console
        FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al agregar el producto", ""));
        System.out.println("error" + e.getMessage());
    }
}


        public void testConnection() {
        try {
            productoFecade.testConnection();
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Conexión exitosa", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conexión: " + e.getMessage(), ""));
        }
    }


    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
     
     
     @PostConstruct
     public void init(){
         this.producto = new Producto();
     }  
}
