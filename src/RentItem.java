/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorge Aguirre
 */


public abstract class RentItem {
    
    private String codigoItem;
    private String nombreItem;
    private double precioRentaItem;
    private int cantidadCopias;
    
    
    public RentItem(String codigoItem, String nombreItem, double precioRentaItem) {
        this.codigoItem = codigoItem;
        this.nombreItem = nombreItem;
        this.precioRentaItem = precioRentaItem;
        this.cantidadCopias = 0; 
    }

    
    @Override
    public String toString() {
        return "Código de Item: " + codigoItem + ", Nombre de Item: " + nombreItem + ", Precio de Renta: " + precioRentaItem;
    }

    
    public abstract double pagoRenta(int dias);
    public String getCodigoItem() {
        return codigoItem;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public double getPrecioRentaItem() {
        return precioRentaItem;
    }
}


//testing