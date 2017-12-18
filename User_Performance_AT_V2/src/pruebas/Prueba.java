/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebas;

import archivos.ExportarCSV;
import gestion.Consultas;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        // TODO code application logic here
        
        Consultas c= new Consultas();
        c.iniciar();
        
        c.completarIds();
        c.completarIdWallet();
       
        c.completarApuestas();
        c.completarRecargas();
        c.completarRetiros();
        
        
        
        c.recorreApuestas();
        c.recorreRecargas();
        c.recorreRetiros();
        
        System.out.println("RESUMEN ******");
        
        c.completarMaximoContador();
        
        System.out.println("terminado");
        
        
        System.out.println("Exportando CSV");
        
        
        ExportarCSV e= new ExportarCSV();
        
        e.exportarResultados(c.getApostadores(),c.getContApostadores());
        
        
        
        
    }
    
}
