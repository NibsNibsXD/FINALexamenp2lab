import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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


public class Main extends JFrame     {

        public static void main( String[] args )     {
            new Main( );
        }

        private ArrayList< RentItem > items = new ArrayList< > ( );

        public Main( ) {
        setTitle( "Rent Item System" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

        JLabel background = new JLabel( new ImageIcon( "src/Foto2.png" ) );
        setContentPane( background );
        background.setLayout( new FlowLayout( FlowLayout.CENTER, 50, 50 ) );

        
        
        JButton addButton = new JButton( "Agregar Item" );
        JButton rentButton = new JButton( "Rentar Item" );
        JButton submenuButton = new JButton( "Menu Config Juego" );
        JButton printAllButton = new JButton( "Imprimir Lista" );

        
        
        
        Font buttonFont = new Font( "Arial", Font.BOLD, 16 );
        Color buttonColor = new Color( 0, 0, 0 );
        addButton.setFont( buttonFont );
        addButton.setBackground( Color.LIGHT_GRAY );
        addButton.setForeground( buttonColor );
        rentButton.setFont( buttonFont );
        rentButton.setBackground( Color.LIGHT_GRAY );
        rentButton.setForeground( buttonColor );
        submenuButton.setFont( buttonFont );
        submenuButton.setBackground( Color.LIGHT_GRAY );
        submenuButton.setForeground( buttonColor );
        printAllButton.setFont( buttonFont );
        printAllButton.setBackground( Color.LIGHT_GRAY );
        printAllButton.setForeground( buttonColor );

        background.add( addButton );
        background.add( rentButton );
        background.add( submenuButton );
        background.add( printAllButton );
        
        

        JTextArea displayArea = new JTextArea( 10, 50 );
        displayArea.setEditable( false );
        JScrollPane scrollPane = new JScrollPane( displayArea );
        background.add( scrollPane );

        
        
        addButton.addActionListener( e -> {
        String tipo;
        tipo = JOptionPane.showInputDialog( this, "Ingrese el tipo de item (MOVIE/GAME):" );
        if ( tipo == null ) return;

        String codigo;
        codigo = JOptionPane.showInputDialog( this, "Ingrese el codigo del item:" );
        if ( codigo == null ) return;
        if ( buscarItemPorCodigo( codigo ) != null ) {
            JOptionPane.showMessageDialog( this, "El codigo ya existe. Por favor, ingrese un codigo unico." );
            return;
        }

        String nombre = JOptionPane.showInputDialog( this, "Ingrese el nombre del item:" );
        if ( nombre == null ) return;

        if ( tipo.equalsIgnoreCase( "MOVIE" ) ) {
            String precioStr = JOptionPane.showInputDialog( this, "Ingrese el precio de renta de la Movie:" );
            if ( precioStr == null ) return;
            double precio = 0.0;
            try {
                precio = Double.parseDouble( precioStr );
            } catch ( NumberFormatException ex ) {
                JOptionPane.showMessageDialog( this, "Precio invalido." );
                return;
            }
            
            items.add( new Movie( codigo, nombre, precio ) );
            JOptionPane.showMessageDialog( this, "Movie agregada exitosamente." );
        } else if ( tipo.equalsIgnoreCase( "GAME" ) ) {
            items.add( new Game( codigo, nombre ) );
            JOptionPane.showMessageDialog( this, "Game agregado exitosamente." );
        } else {
            JOptionPane.showMessageDialog( this, "TIPO INCORRECTO" );
        }
        } );

        
        rentButton.addActionListener( e -> {
        String codigo;
        codigo = JOptionPane.showInputDialog( this, "Ingrese el codigo del item a rentar:" );
        
        if ( codigo == null ) return;
        RentItem item = buscarItemPorCodigo( codigo );
        if ( item != null ) {
            String diasStr = JOptionPane.showInputDialog( this, "Ingrese los dias a rentar:" );
            if ( diasStr == null ) return;
            int dias = 0;
            
            try {
                dias = Integer.parseInt( diasStr );
            } catch ( NumberFormatException ex ) {
                JOptionPane.showMessageDialog( this, "Numero de dias invalido." );
                return;
            }
            double montoPagar = item.pagoRenta( dias );
            JOptionPane.showMessageDialog( this, item.toString( ) + "\nMonto a pagar por " + dias + " dias: Lps. " + montoPagar );
        } else {
            JOptionPane.showMessageDialog( this, "Item No Existe" );
        }
        } );

        submenuButton.addActionListener( e -> {
        String codigo;
        codigo = JOptionPane.showInputDialog( this, "Ingrese el codigo del item:" );
        if ( codigo == null ) return;
        RentItem item = buscarItemPorCodigo( codigo );
        if ( item != null && item instanceof MenuActions ) {
            String[] opciones = { "1. Actualizar Fecha de Publicacion", "2. Agregar Especificacion", "3. Ver Especificaciones" };
            
            String opcionStr = ( String ) JOptionPane.showInputDialog( this, "Escoja Opcion:", "Sub Menu",
            JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[ 0 ] );
            
            
            if ( opcionStr == null ) return;
            int opcion = Integer.parseInt( opcionStr.substring( 0, 1 ) );

            switch ( opcion ) {
                case 1:
                    String anioStr = JOptionPane.showInputDialog( this, "Ingrese anio de publicacion:" );
                    String mesStr = JOptionPane.showInputDialog( this, "Ingrese mes de publicacion (1-12):" );
                    String diaStr = JOptionPane.showInputDialog( this, "Ingrese dia de publicacion:" );
                    if ( anioStr == null || mesStr == null || diaStr == null ) {
                        return;
                    }
                    
                    int anio = Integer.parseInt( anioStr );
                    int mes = Integer.parseInt( mesStr );
                    int dia = Integer.parseInt( diaStr );
                    ( ( Game ) item ).actualizarFechaPublicacion( anio, mes, dia );
                    JOptionPane.showMessageDialog( this, "Fecha de publicacion actualizada." );
                    break;
                    
                    
                case 2:
                    String espec = JOptionPane.showInputDialog( this, "Ingrese una especificacion:" );
                    if ( espec == null ){
                        return;
                    } 
                    ( ( Game ) item ).agregarEspecificacionOpcion( espec );
                    JOptionPane.showMessageDialog( this, "Especificacion agregada." );
                    break;
                    
                    
                case 3:
                    ArrayList< String > especificaciones = ( ( Game ) item ).obtenerEspecificaciones( );
                    StringBuilder especText = new StringBuilder( "Especificaciones:\n" );
                    for ( String s : especificaciones ) {
                        especText.append( "- " ).append( s ).append( "\n" );
                    }
                    JOptionPane.showMessageDialog( this, especText.toString( ) );
                    break;
                default:
                    JOptionPane.showMessageDialog( this, "Opcion invalida." );
                    break;
            }
        } else {
            JOptionPane.showMessageDialog( this, "Item no existe o no implementa MenuActions." );
        }
        } );

        printAllButton.addActionListener( e -> {
        StringBuilder allItems = new StringBuilder( );
        for ( RentItem item : items ) {
            allItems.append( item.toString( ) ).append( "\n\n" );
        }
        
        displayArea.setText( allItems.toString( ) );
        } );

        pack( );
        setVisible( true );
    }

    private RentItem buscarItemPorCodigo( String codigo ) {
        for ( RentItem item : items ) {
            if ( item.getCodigoItem( ).equals( codigo ) ) {
                return item;
            }
        }
        return null;
    }


}
