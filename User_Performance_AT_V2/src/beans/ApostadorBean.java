/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.List;

/**
 *
 * @author user
 */
public class ApostadorBean {
    
    int id_apostador,id_wallet;
    String email,fecha_registro;
     String[] fecha_recarga,fecha_apuesta,fecha_retiro;
     float[]  monto_recarga,monto_apuesta,monto_retiro;
     int contRecarga, contApuesta,contRetiro;
         
     int contadorMaximo;
     
    public ApostadorBean() {
        
    fecha_apuesta= new String[10000];
    fecha_recarga= new String[10000];
    fecha_retiro= new String[10000]; 
    
    
    monto_recarga= new float[10000];
    monto_apuesta= new float[10000];
    monto_retiro= new float[10000];
    
    contApuesta=0;
    contRecarga=0;
    contRetiro=0;
    
    }
    
    
    
    public void iniciar(){
        
        
        for (int i = 0; i < 10000; i++) {
            
            
            fecha_apuesta[i]="";
            fecha_recarga[i]="";
            fecha_retiro[i]="";
            
            
            monto_apuesta[i]=0;
            monto_recarga[i]=0;
            monto_retiro[i]=0;
            
        }
 
        
        
        
        
    }
    
    
    
    
    public void encontrarMayor(){
        
        
        if (contApuesta>=contRecarga && contApuesta>=contRetiro) {
            
            
            contadorMaximo=contApuesta;
            
        }else if (contRecarga>=contApuesta && contRecarga>=contRetiro) {
            
            
         contadorMaximo=   contRecarga;
            
        }else   {
            
            contadorMaximo=contRetiro;
            
        }
 
        
        
        
        
    }

    public int getId_apostador() {
        return id_apostador;
    }

    public void setId_apostador(int id_apostador) {
        this.id_apostador = id_apostador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String[] getFecha_recarga() {
        return fecha_recarga;
    }

    public void setFecha_recarga(String[] fecha_recarga) {
        this.fecha_recarga = fecha_recarga;
    }

    public String[] getFecha_apuesta() {
        return fecha_apuesta;
    }

    public void setFecha_apuesta(String[] fecha_apuesta) {
        this.fecha_apuesta = fecha_apuesta;
    }

    public String[] getFecha_retiro() {
        return fecha_retiro;
    }

    public void setFecha_retiro(String[] fecha_retiro) {
        this.fecha_retiro = fecha_retiro;
    }

    public int getContRecarga() {
        return contRecarga;
    }

    public void setContRecarga(int contRecarga) {
        this.contRecarga = contRecarga;
    }

    public int getContApuesta() {
        return contApuesta;
    }

    public void setContApuesta(int contApuesta) {
        this.contApuesta = contApuesta;
    }

    public int getContRetiro() {
        return contRetiro;
    }

    public void setContRetiro(int contRetiro) {
        this.contRetiro = contRetiro;
    }

    public int getContadorMaximo() {
        return contadorMaximo;
    }

    public void setContadorMaximo(int contadorMaximo) {
        this.contadorMaximo = contadorMaximo;
    }

    public int getId_wallet() {
        return id_wallet;
    }

    public void setId_wallet(int id_wallet) {
        this.id_wallet = id_wallet;
    }

    public float[] getMonto_recarga() {
        return monto_recarga;
    }

    public void setMonto_recarga(float[] monto_recarga) {
        this.monto_recarga = monto_recarga;
    }

    public float[] getMonto_apuesta() {
        return monto_apuesta;
    }

    public void setMonto_apuesta(float[] monto_apuesta) {
        this.monto_apuesta = monto_apuesta;
    }

    public float[] getMonto_retiro() {
        return monto_retiro;
    }

    public void setMonto_retiro(float[] monto_retiro) {
        this.monto_retiro = monto_retiro;
    }
    

    
    
    
    
    
    
}
