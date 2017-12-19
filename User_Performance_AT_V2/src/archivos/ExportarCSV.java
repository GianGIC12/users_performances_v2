/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivos;

import beans.ApostadorBean;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author user
 */
public class ExportarCSV {

    public ExportarCSV() {
    }
    
    
    public void exportarResultados(ApostadorBean[] apostadores,int contApostadores) throws IOException{
        
          String outputFile = "C:/Users/user/Google Drive/Reporting_AT/user_date_performance.csv";
        
        
        boolean alreadyExists = new File(outputFile).exists();
        
        
        if (alreadyExists) {
            File bd_date_performance = new File(outputFile);
            bd_date_performance.delete();
        }
        
      CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ';');
        
        csvOutput.write("id_reporte");
        csvOutput.write("id_apostador");
        csvOutput.write("id_wallet");
        csvOutput.write("email");
        csvOutput.write("fecha_registro");
        csvOutput.write("fecha_apuesta");
        csvOutput.write("monto_apuesta");
        csvOutput.write("fecha_recarga");
        csvOutput.write("monto_recarga");
        csvOutput.write("fecha_retiro");
        csvOutput.write("monto_retiro");
        csvOutput.write("num_apuestas");
        csvOutput.write("num_recargas");
        csvOutput.write("num_retiros");
        
        csvOutput.endRecord();
        
        
        for (int i = 0; i < contApostadores; i++) {
            
            
            System.out.println(apostadores[i].getContadorMaximo()+"posicion"+i);
            int maximoContador=apostadores[i].getContadorMaximo();
            
            
            
            if (maximoContador==0) {
                
            csvOutput.write(i+"");
            csvOutput.write(apostadores[i].getId_apostador()+"");
            csvOutput.write(apostadores[i].getId_wallet()+"");
            csvOutput.write(apostadores[i].getEmail()+"");
            csvOutput.write(apostadores[i].getFecha_registro()+"");
                
                
            csvOutput.write(apostadores[i].getFecha_apuesta()[0]+"");
            csvOutput.write(0+"");
            csvOutput.write(apostadores[i].getFecha_recarga()[0]+"");
            csvOutput.write(0+"");
            csvOutput.write(apostadores[i].getFecha_retiro()[0]+"");
            csvOutput.write(0+"");
                
            
            csvOutput.write(0+"");
            csvOutput.write(0+"");
            csvOutput.write(0+"");
            
            csvOutput.endRecord();
            
            }else{
                
                
                for (int j = 0; j < maximoContador; j++) {
                    
                    
                    csvOutput.write(i+"");
            csvOutput.write(apostadores[i].getId_apostador()+"");
            csvOutput.write(apostadores[i].getId_wallet()+"");
            csvOutput.write(apostadores[i].getEmail()+"");
            csvOutput.write(apostadores[i].getFecha_registro()+"");
                
                
            csvOutput.write(apostadores[i].getFecha_apuesta()[j]+"");
            csvOutput.write(apostadores[i].getMonto_apuesta()[j]+"");
            csvOutput.write(apostadores[i].getFecha_recarga()[j]+"");
            csvOutput.write(apostadores[i].getMonto_recarga()[j]+"");
            csvOutput.write(apostadores[i].getFecha_retiro()[j]+"");
            csvOutput.write(apostadores[i].getMonto_retiro()[j]+"");
                
            
                    if ((apostadores[i].getFecha_apuesta()[j]).equalsIgnoreCase("")) {
                     csvOutput.write("0");   
                    }else{
                        csvOutput.write("1"); 
                    }
            
            if ((apostadores[i].getFecha_recarga()[j]).equalsIgnoreCase("")) {
                     csvOutput.write("0");   
                    }else{
                        csvOutput.write("1"); 
                    }
                    
            if ((apostadores[i].getFecha_retiro()[j]).equalsIgnoreCase("")) {
                     csvOutput.write("0");   
                    }else{
                        csvOutput.write("1"); 
                    }
            
            
            
            csvOutput.endRecord();
                    
                    
                    
                    
                }
                
                
                
                
                
            }
            
            
            
            
            
            
            
        }
        
        
        
     csvOutput.close();   
        
    } 
    
    
    
}
