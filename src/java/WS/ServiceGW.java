/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import Correos.SendEmail;
import Correos.SendEmail2;
import DB.OperacionesDB;
import com.sun.xml.ws.security.opt.impl.util.SOAPUtil;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Diexo
 */
@WebService(serviceName = "ServiceGW")
public class ServiceGW {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
   //EXAMEN FINAL PROGRAIV
    @WebMethod(operationName = "EnviarCorreo2")
    public boolean EnviarCorreo2(@WebParam(name = "TOKEN") String TOKEN,
        @WebParam(name = "receptor") String receptor) 
    {
        
        boolean valido = false; 
        SendEmail2 obj = new SendEmail2();
        
       try 
       {     
            valido = obj.enviarCorreo(TOKEN, receptor);
            
            System.out.println("==================================");
            System.out.println(" Resultado de Correo enviado Listo");
            System.out.println("==================================");
            
            
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
            
        } 
        return valido;
       
    }//end Enviar Correo    
    
    
    
    
    
    
    
    
    
  /*  @WebMethod(operationName = "IngresarUsuario")
    public String IngresarUsuario(@WebParam(name = "Nombre") String Nombre ,
                                  @WebParam(name = "Correo") String Correo , 
                                  @WebParam(name = "user") String user , 
                                  @WebParam(name = "pass") String pass) {
        String result;
        try 
        {
            OperacionesDB obj = new OperacionesDB();

            result =  obj.IngresarUsuario(Nombre , Correo, user , pass);

            if(result.equals("correcto"))
            {
                return "correcto";
            }
            else 
            {
                return "incorrecto";
            }        
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
            return "error";
        }            

    }//end IngresarUsuario
    
@WebMethod(operationName = "ValidarUsuario")
    public String ValidarUsuario(@WebParam(name = "user") String user , 
                                  @WebParam(name = "pass") String pass) {
        String result;
        OperacionesDB obj = new OperacionesDB();
       try 
       {
            result =  obj.ValidarUsuario(user , pass) ;
            
            System.out.println("==================================");
            System.out.println(" Resultado de validacion Listo");
            System.out.println("==================================");
            
            
            if(result.equals("correcto"))
            {
                return "correcto";
            }
            else
            {        
                return "incorrecto";
            }
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
            return "error"; 
        } 
    }//end ValidarUsuario    
 @WebMethod(operationName = "AgregarSucursal")
    public String AgregarSucursal(@WebParam(name = "nombreP") String nombreP ,
                                  @WebParam(name = "tipocambio") double tipocambio , 
                                  @WebParam(name = "comision") int comision , 
                                  @WebParam(name = "moneda") String moneda ) {
        String result;
        OperacionesDB obj = new OperacionesDB();
       try 
       {
            result = obj.IngresarSucursal(nombreP, tipocambio, comision, moneda);
            
            System.out.println("==================================");
            System.out.println(" Resultado de Sucursal Nueva Listo");
            System.out.println("==================================");
            
            
            if(result.equals("correcto"))
            {
                return "correcto";
            }
            else
            {        
                return "incorrecto";
            }
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
            return "error"; 
        } 
    }//end AgregarSucursal    
  @WebMethod(operationName = "ModificarSucursal")
    public String ModificarSucursal(@WebParam(name = "nombreP") String nombreP ,
                                  @WebParam(name = "tipocambio") double tipocambio , 
                                  @WebParam(name = "comision") int comision , 
                                  @WebParam(name = "moneda") String moneda ) {
        String result;
        OperacionesDB obj = new OperacionesDB();
       try 
       {
            result = obj.ModificarSucursal(nombreP, tipocambio, comision, moneda);
            
            System.out.println("==================================");
            System.out.println(" Resultado de Sucursal Modificada Listo");
            System.out.println("==================================");
            
            
            if(result.equals("correcto"))
            {
                return "correcto";
            }
            else
            {        
                return "incorrecto";
            }
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
            return "error"; 
        } 
    }//end ModificarSucursal
    
 @WebMethod(operationName = "EliminarSucursal")
    public String EliminarSucursal(@WebParam(name = "nombreP") String nombreP) {
        String result;
        OperacionesDB obj = new OperacionesDB();
       try 
       {
            result = obj.EliminarSucursal(nombreP);
            
            System.out.println("==================================");
            System.out.println(" Resultado de Sucursal Eliminada Listo");
            System.out.println("==================================");
            
            
            if(result.equals("correcto"))
            {
                return "correcto";
            }
            else
            {        
                return "incorrecto";
            }
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
            return "error"; 
        } 
    }//end EliminarSucursal   
    
   @WebMethod(operationName = "TipoCambio_PaisCompro")
    public float TipoCambio_PaisCompro(@WebParam(name = "nombreP") String nombreP) {
        float result = 0;
        OperacionesDB obj = new OperacionesDB();
       try 
       {
            result = obj.TipoCambio_PaisCompro(nombreP);
            
            System.out.println("==================================");
            System.out.println(" Resultado de Tipo cambio PAIS COMPRO Listo= " +result);
            System.out.println("==================================");
            
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
             
        } 
       if(result == 0)
       {
           System.out.println("==================================");
           System.out.println("       Tipo cambio ES 0!!!");
           System.out.println("==================================");
       }
       
       return result; 
    }//end TipoCambio_PaisCompro     
    
    @WebMethod(operationName = "TipoCambio_MonedaPago")
    public float TipoCambio_MonedaPago(@WebParam(name = "moneda") String moneda) {
        float result = 0;
        OperacionesDB obj = new OperacionesDB();
       try 
       {
            result = obj.TipoCambio_MonedaPago(moneda);
            
            System.out.println("==================================");
            System.out.println(" Resultado de Tipo cambio Moneda PAGO Listo= " +result);
            System.out.println("==================================");
            
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
             
        } 
       if(result == 0)
       {
           System.out.println("==================================");
           System.out.println("       tipo cambio ES 0!!!");
           System.out.println("==================================");
       }
       
       return result; 
    }//end TipoCambio_PaisCompro      
   @WebMethod(operationName = "GetPaises")
    public ArrayList<String> GetPaises() {
        ArrayList<String> ListaPaises = new ArrayList();
        OperacionesDB obj = new OperacionesDB();
       try 
       {
            ListaPaises = obj.ObtenerPaises();
            
            System.out.println("==================================");
            System.out.println(" LISTA PAISES LISTA ;)");
            System.out.println("==================================");
          
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
         
        } 
       
       return ListaPaises;
       
    }//end Getpaises     
    
   @WebMethod(operationName = "GetMonedas")
    public ArrayList<String> GetMonedas() {
        ArrayList<String> ListaMonedas= new ArrayList();
        OperacionesDB obj = new OperacionesDB();
       try 
       {
            ListaMonedas = obj.CargarMonedas();
            
            System.out.println("==================================");
            System.out.println(" LISTA monedas LISTA ;)");
            System.out.println("==================================");
          
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
         
        } 
       
       return ListaMonedas;
       
    }//end GetMonedas  

    
  @WebMethod(operationName = "Reservar")
    public String Reservar(@WebParam(name = "nombreP") String nombreP) {
        String result;
        OperacionesDB obj = new OperacionesDB();
       try 
       {
            result = obj.EliminarSucursal(nombreP);
            
            System.out.println("==================================");
            System.out.println(" Resultado de Sucursal Eliminada Listo");
            System.out.println("==================================");
            
            
            if(result.equals("correcto"))
            {
                return "correcto";
            }
            else
            {        
                return "incorrecto";
            }
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
            return "error"; 
        } 
    }//end Reservar     
   @WebMethod(operationName = "Conversion")
    public float Conversion(@WebParam(name = "cantidad") int Cantidad, 
            @WebParam(name = "tipocambioCompro") float tipocambioCompro,
            @WebParam(name = "tipocambioPago") float tipocambioPago) {
        float result = 0;
        BLL.Calculos i = new BLL.Calculos();
       try 
       {
            result = i.Conversion(Cantidad ,tipocambioCompro ,tipocambioPago);
            
            System.out.println("==================================");
            System.out.println(" Resultado de Conversion  Listo");
            System.out.println("==================================");
            
            
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
            
        } 
       
       return result;
    }//end Conversion 
    
  @WebMethod(operationName = "Comision")
    public float Comision(@WebParam(name = "moneda") String moneda , 
            @WebParam(name = "montobruto") float montoBruto) {
        BLL.Calculos i = new BLL.Calculos();
        OperacionesDB obj = new OperacionesDB();
        float comision = 0;
       try 
       {     
            comision = i.Comision(montoBruto, obj.Comision_MonedaPago(moneda)); 
            
            
            
            System.out.println("==================================");
            System.out.println(" Resultado de Comision  Listo");
            System.out.println("==================================");
            
            
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
            
        } 
       
       return comision;
    }//end Comision   
    
  @WebMethod(operationName = "GuardarReserva")
    public boolean GuardarReserva(@WebParam(name = "IdCliente") int idCliente, 
        @WebParam(name = "Nombre") String Nombre ,
        @WebParam(name = "Correo") String Correo, 
        @WebParam(name = "Telefono") int Telefono,
        @WebParam(name = "Ciudad") String Ciudad,
        @WebParam(name = "Cantidad") int Cantidad,
        @WebParam(name = "Pais") String Pais,
        @WebParam(name = "Moneda") String Moneda,
        @WebParam(name = "MontoBruto") double MontoBruto,
        @WebParam(name = "Comision") double Comision, 
        @WebParam(name = "Total") double Total) 
    {
        
        boolean valido = false; 
        OperacionesDB obj = new OperacionesDB();
        
       try 
       {     
            String resultado = obj.GuardarReserva(idCliente, Nombre, Correo, 
                    Telefono,Ciudad, Cantidad , Pais , Moneda, 
                    MontoBruto, Comision, Total);
            
            if(resultado.equals("correcto"))
            {
                valido = true;
            }
            
            System.out.println("==================================");
            System.out.println(" Resultado de Ingreso Reserva  Listo");
            System.out.println("==================================");
            
            
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
            
        } 
        return valido;
       
    }//end Guardad Reserva
    
@WebMethod(operationName = "EnviarCorreo")
    public boolean EnviarCorreo(@WebParam(name = "CorreoDestino") String CorreoDestino,
        @WebParam(name = "IdCliente") String idCliente, 
        @WebParam(name = "Nombre") String Nombre ,
        @WebParam(name = "Correo") String Correo, 
        @WebParam(name = "Telefono") String Telefono,
        @WebParam(name = "Ciudad") String Ciudad,
        @WebParam(name = "Cantidad") String Cantidad,
        @WebParam(name = "Pais") String Pais,
        @WebParam(name = "Moneda") String Moneda,
        @WebParam(name = "MontoBruto") String MontoBruto,
        @WebParam(name = "Comision") String Comision, 
        @WebParam(name = "Total") String Total) 
    {
        
        boolean valido = false; 
        SendEmail obj = new SendEmail();
        
       try 
       {     
           // valido = obj.EnviarCorreo(CorreoDestino , idCliente, Nombre, Correo, 
             //       Telefono,Ciudad, Cantidad , Pais , Moneda, 
               //     MontoBruto, Comision, Total);
            
            System.out.println("==================================");
            System.out.println(" Resultado de Correo enviado Listo");
            System.out.println("==================================");
            
            
        } catch (Exception e) {
            System.out.println("!!!!!!!!ERROR: "+ e);
            
        } 
        return valido;
       
    }//end Enviar Correo     
    */
}
