import java.util.Calendar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorge Aguirre
 */


public class Movie extends RentItem {
    private Calendar fecha; // Fecha de lanzamiento de la película

    public Movie(String codigoItem, String nombreItem, double precioRentaItem) {
        super(codigoItem, nombreItem, precioRentaItem);
        this.fecha = Calendar.getInstance(); // Se inicializa con la fecha actual
    }
    
    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Calendar getFecha() {
        return fecha;
    }
    
    public String getEstado() {
        
        
        Calendar fechaActual = Calendar.getInstance();

        int diffYear = fechaActual.get(Calendar.YEAR) - fecha.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + fechaActual.get(Calendar.MONTH) - fecha.get(Calendar.MONTH);

        if (diffMonth <= 3) {
            return "ESTRENO";
        } else {
            return "NORMAL";
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Estado: " + getEstado() + " – Movie";
    }
    
    @Override
    public double pagoRenta(int dias) {
        double pago = getPrecioRentaItem();
        String estado = getEstado();

        if (estado.equals("ESTRENO") && dias > 2) {
            pago += 50 * (dias - 2);
            
        } else if (estado.equals("NORMAL") && dias > 5) {
            pago += 30 * (dias - 5);
        }

        return pago;
    }
}
