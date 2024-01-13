/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DB.OperacionesDB;

/**
 *
 * @author Diexo
 */
public class Calculos {
    
    OperacionesDB obj = new OperacionesDB();
    
    public float Conversion(int cantidad , float TipoCambioCompro , float TipoCambioPago)
    {
        float montobruto = cantidad * 1 * TipoCambioPago / TipoCambioCompro ; 
        
        System.out.println(montobruto);
        System.out.println(cantidad);
        System.out.println(TipoCambioPago);
        System.out.println(TipoCambioCompro);
        
        
        return montobruto;
    }
    
    public float Comision( float montobruto , int comision)
    {
        float comi = montobruto * comision / 100 ; 
        
        System.out.println("Comision ES DE : " +comi+" ");
        return comi; 
    }
    
    
        
    
}
