import java.util.ArrayList;
import java.util.Calendar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorge Aguirre
 */


public class Game extends RentItem implements MenuActions {
    
    private Calendar fechaPublicacion; 
    private ArrayList<String> especificaciones; 

    
    public Game(String codigoItem, String nombreItem) {
        super(codigoItem, nombreItem, 20.0); // fijo es Lps. 20
        this.fechaPublicacion = Calendar.getInstance(); 
        this.especificaciones = new ArrayList<>(); 
    }
    
    @Override
    public String toString() {
        
        int dia = fechaPublicacion.get(Calendar.DAY_OF_MONTH);
        int mes = fechaPublicacion.get(Calendar.MONTH) + 1; //pq comienza desde 0
        int año = fechaPublicacion.get(Calendar.YEAR);
        String fechaStr = dia +"/" + mes +  "/" +   año;

        return super.toString() + ", Fecha de PubliCacion: " + fechaStr + " – PS3 Game";
    }
    
    
    public void setFechaPublicacion(int año, int mes, int dia) {
        fechaPublicacion.set(Calendar.YEAR, año);
        fechaPublicacion.set(Calendar.MONTH, mes - 1); //pq enero es 0
        fechaPublicacion.set(Calendar.DAY_OF_MONTH, dia);
    }
    
    
    public String getFechaPublicacion() {
        int dia = fechaPublicacion.get(Calendar.DAY_OF_MONTH);
        int mes = fechaPublicacion.get(Calendar.MONTH) + 1;
        int año = fechaPublicacion.get(Calendar.YEAR);
        return dia + "/" + mes + "/" + año;
    }

    
    public void agregarEspecificacion(String especificacion) {
        especificaciones.add(especificacion);
    }

    
    
    
    public ArrayList<String> getEspecificaciones() {
        return especificaciones;
    }

    
    
    
    
    public ArrayList<String> listEspecificaciones() {
        ArrayList<String> listaEspecificaciones = new ArrayList<>();
        listEspecificacionesRecursivo(0, listaEspecificaciones);
        return listaEspecificaciones;
    }

    private void listEspecificacionesRecursivo(int indice, ArrayList<String> lista) {
        if (indice < especificaciones.size()) {
            lista.add(especificaciones.get(indice));
            listEspecificacionesRecursivo(indice + 1, lista);
        }
    }

    
    
    @Override
    public double pagoRenta(int dias) {
        return getPrecioRentaItem() * dias;
    }

    
    
    public void actualizarFechaPublicacion(int año, int mes, int dia) {
        setFechaPublicacion(año, mes, dia);
    }

    
    
    public void agregarEspecificacionOpcion(String especificacion) {
        agregarEspecificacion(especificacion);
    }

    
    
    
    public ArrayList<String> obtenerEspecificaciones() {
        return listEspecificaciones();
    }

   

    @Override
    public void ejecutarOpcion(int opcion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void submenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
