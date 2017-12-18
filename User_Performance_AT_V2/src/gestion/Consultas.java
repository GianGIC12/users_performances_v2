/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import beans.ApostadorBean;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Consultas {

    String sql;

    ApostadorBean[] apostadores;
    int contApostadores;

    public Consultas() {

        apostadores = new ApostadorBean[10000];

        contApostadores = 0;

    }

    public void iniciar() {

    }

    public void completarIds() throws SQLException {

        Conexion objCon = new Conexion();

        objCon.conectar();

        sql = "select id,email, created_at, token_email from db_apuestatotal_prod.user_user where token_email is not null and  created_at>='2017-09-01'"
                + " limit 100";

        PreparedStatement stm = objCon.getCon().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {

            int id = rs.getInt("id");
            String email = rs.getString("email");
            String token_email = rs.getString("token_email");
            String fecha_registro = rs.getString("created_at").substring(0, 10);

            ApostadorBean apostador = new ApostadorBean();
            apostador.iniciar();

            apostador.setId_apostador(id);
            apostador.setEmail(email);
            apostador.setFecha_registro(fecha_registro);

            apostadores[contApostadores] = apostador;

            contApostadores++;

        }

        objCon.desconectar();

    }

    public void completarIdWallet() throws SQLException {

        Conexion objCon = new Conexion();

        objCon.conectar();

        for (int i = 0; i < contApostadores; i++) {

            sql = "select id,iduser from db_apuestatotal_prod.wallet where iduser=" + apostadores[i].getId_apostador();

            PreparedStatement stm = objCon.getCon().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");

                apostadores[i].setId_wallet(id);

            }
            System.out.println(apostadores[i].getId_apostador() + "   " + apostadores[i].getId_wallet());

        }

        objCon.desconectar();

    }

    public void completarApuestas() throws SQLException {

        Conexion objCon = new Conexion();

        objCon.conectar();

        for (int i = 0; i < contApostadores; i++) {

            int contApuestas = 0;

            sql = "select created_at,amount from db_apuestatotal_prod.user_bet where idUser="
                    + apostadores[i].getId_apostador();

          //   sql="select created_at from db_apuestatotal_prod.user_bet where idUser="
            //        +15;
            PreparedStatement stm = objCon.getCon().prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            String[] apuesta_real = new String[10000];
            float[] monto_apuesta= new float[10000];
            
            for (int j = 0; j < apuesta_real.length; j++) {
                apuesta_real[j]="";
                monto_apuesta[j]=0;
            }
            

            while (rs.next()) {

                String fecha_apuesta = rs.getString("created_at").substring(0, 10);
                float apuesta= rs.getFloat("amount");

                apuesta_real[contApuestas] = fecha_apuesta;
                monto_apuesta[contApuestas] = apuesta;

                contApuestas++;
            }

            apostadores[i].setFecha_apuesta(apuesta_real);
            apostadores[i].setContApuesta(contApuestas);
            apostadores[i].setMonto_apuesta(monto_apuesta);

            System.out.println(contApuestas);
            for (int j = 0; j < contApuestas; j++) {

                System.out.println(apostadores[i].getFecha_apuesta()[j]+"...."+apostadores[i].getMonto_apuesta()[j]);

            }

        }

        objCon.desconectar();

    }

    public void completarRecargas() throws SQLException {

        Conexion objCon = new Conexion();

        objCon.conectar();

        for (int i = 0; i < contApostadores; i++) {

            int contRecargas = 0;

            sql = "select created_at,updated_at, amount from db_apuestatotal_prod.wallet_transaction where idWallet="
                    + apostadores[i].getId_wallet() + " and status='2'";

          //   sql="select created_at from db_apuestatotal_prod.user_bet where idUser="
            //        +15;
            PreparedStatement stm = objCon.getCon().prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            String[] recarga_real = new String[10000];
            float[] monto_recarga= new float[10000];
            
            for (int j = 0; j < recarga_real.length; j++) {
                
                recarga_real[j]="";
                monto_recarga[j]=0;
                
            }
            

            while (rs.next()) {

                String fecha_recarga = rs.getString("updated_at").substring(0, 10);
                float recarga= rs.getFloat("amount");
                
                recarga_real[contRecargas] = fecha_recarga;
                 monto_recarga[contRecargas] = recarga;
                
                 

                contRecargas++;
            }

            apostadores[i].setFecha_recarga(recarga_real);
            apostadores[i].setContRecarga(contRecargas);
            apostadores[i].setMonto_recarga(monto_recarga);
            
            System.out.println(apostadores[i].getId_apostador() + "********************" 
                    + apostadores[i].getContRecarga()+"Monto:  ");

        }

        objCon.desconectar();

    }

    public void completarRetiros() throws SQLException {

        Conexion objCon = new Conexion();

        objCon.conectar();

        for (int i = 0; i < contApostadores; i++) {

            int contRetiros = 0;

            sql = "select idUser,response,created_at, amount from db_apuestatotal_prod.transaction_withdraw where idUser="
                    + apostadores[i].getId_apostador();

            PreparedStatement stm = objCon.getCon().prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            String[] retiro_real = new String[10000];
            float[] monto_retiro= new float[10000];
            
            
            for (int j = 0; j < retiro_real.length; j++) {
                
                retiro_real[j]="";
                monto_retiro[j]=0;
                
            }
            

            while (rs.next()) {

                String response = rs.getString("response");
                String palabra = "error";
                boolean encontrado = response.contains(palabra);
                
                float retiro;

                if (encontrado) {

                    System.out.println("" + response);

                } else {

                    System.out.println("No encontrado" + response);

                    retiro_real[contRetiros] = rs.getString("created_at").substring(0, 10);
                    monto_retiro[contRetiros]= rs.getFloat("amount");

                    contRetiros++;
                }

            }

            apostadores[i].setFecha_retiro(retiro_real);
            apostadores[i].setContRetiro(contRetiros);
            apostadores[i].setMonto_retiro(monto_retiro);

            System.out.println(contRetiros);
            for (int j = 0; j < contRetiros; j++) {

                System.out.println(apostadores[i].getFecha_retiro()[j]);
                System.out.println(apostadores[i].getMonto_retiro()[j]);
                
                
                
            }

        }

        objCon.desconectar();

    }

    public void recorreApuestas() {

        for (int i = 0; i < contApostadores; i++) {

            for (int j = 0; j < apostadores[i].getContApuesta(); j++) {

                System.out.println(apostadores[i].getId_apostador() + " Fecha apuesta"
                        + apostadores[i].getFecha_apuesta()[j] + " id_wallet" + apostadores[i].getId_wallet());

            }

        }

    }

    public void recorreRecargas() {

        for (int i = 0; i < contApostadores; i++) {
            System.out.println("probando**" + apostadores[i].getContRecarga());
            for (int j = 0; j < apostadores[i].getContRecarga(); j++) {

                System.out.println(apostadores[i].getId_apostador() + " Fecha recarga"
                        + apostadores[i].getFecha_recarga()[j] + " id_wallet" + apostadores[i].getId_wallet());

            }

        }

    }

    public void recorreRetiros() {

        for (int i = 0; i < contApostadores; i++) {

            for (int j = 0; j < apostadores[i].getContRetiro(); j++) {

                System.out.println(apostadores[i].getId_apostador() + " Fecha retiro"
                        + apostadores[i].getFecha_retiro()[j] + " id_wallet" + apostadores[i].getId_wallet()
                + " Monto Retirado: " + apostadores[i].getMonto_retiro()[j]
                );

            }

        }

    }
    
    
    public void completarMaximoContador(){
        
        for (int i = 0; i < contApostadores; i++) {
            
            apostadores[i].encontrarMayor();
            
            System.out.println("id_apostador: "+apostadores[i].getId_apostador()
                    +" id_wallet: "+ apostadores[i].getId_wallet()
                    
                   + " Apuestas: "+apostadores[i].getContApuesta()
            +" Recargas: "+apostadores[i].getContRecarga()
            +" Retiros"+apostadores[i].getContRetiro()
            +" Mayor Contador: "+apostadores[i].getContadorMaximo());
            
            
        }
        
        
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public ApostadorBean[] getApostadores() {
        return apostadores;
    }

    public void setApostadores(ApostadorBean[] apostadores) {
        this.apostadores = apostadores;
    }

    public int getContApostadores() {
        return contApostadores;
    }

    public void setContApostadores(int contApostadores) {
        this.contApostadores = contApostadores;
    }

    
 
    
    
}
