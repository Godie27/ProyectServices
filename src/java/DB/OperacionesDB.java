/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import com.sun.xml.ws.security.opt.impl.util.SOAPUtil;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Diexo
 */
public class OperacionesDB {
    
    String filePath = "C:\\Users\\usuario\\Documents\\NetBeansProjects\\ProyectServices\\src\\java\\BLL\\StringDeConexion.xml" ; 
    public String ObtenerStringConexion() {
        String url = "" ; 
        
        try {
            File archivoXML = new File(filePath);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document documento = saxBuilder.build(archivoXML);

            Element raiz = documento.getRootElement();
            
            List<Element> nodoStringC = raiz.getChildren("StringConexion");
            
            
            //RECORRE EL UNICO NODO
            for (Element elemento : nodoStringC) {
                
                //String fisrtname = elemento.getAttributeValue("fisrtname");
                String host = elemento.getChildText("HOST");
                String database = elemento.getChildText("Database");
                String user = elemento.getChildText("User");
                String password = elemento.getChildText("Password");               
            
            
                url = host +";"+ database +";"+ user +";"+ password ; 
            }
                                               
        } catch (Exception e) {
        }

        return url;
    }//end archivo 
    
    
public Connection Conexion()
    {
            Connection con = null;
            ///////Lee el XML y devuelve el string de conexion/////       
            String conec = ObtenerStringConexion();
            //////////////////////////////////////////////////////
            if(conec.equals(""))
            {
                System.out.println("ERROR!!!!!!!!!!!!");
            }
            else
            {           
                try {
                    
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    con = DriverManager.getConnection(conec); 
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            
            return con; 
     
    }
    
    //////////VARIABLES GLOBALES///////////
        Connection cn = Conexion();
        ResultSet rs;
        CallableStatement sta;
        Statement st;
        PreparedStatement ps;
    ///////////////////////////////////////
    
    
    
    public String IngresarUsuario(String nombre , String correo ,
            String user , String pass) 
    { 
        String resultado = "";
        try {
            
            sta =  cn.prepareCall("{call sp_RegistrarU (?,?,?,?)}");
            sta.setString(1 , nombre );
            sta.setString(2, correo);            
            sta.setString(3 , user );
            sta.setString(4, pass);
                     
            sta.executeUpdate(); 
            resultado = "correcto";       
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("=======================");
        }finally {
            
            //CODIGO PARA CERRAR LA CONEXION Y LAS LLAMADAS, PARA EVITA CONSUMO
            //INNESESARIO DE MEMORIA
            try {
                if (rs != null) {
                    rs.close();
                }

                if (sta != null) {
                    sta.close();
                }

                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
                System.out.println("¡¡¡Error!!!");
            }
        }
    
        return resultado;
    }
    
    public String IngresarSucursal(String nombre , double tipoCambio , 
        int comision , String moneda)
    { 
        String resultado = "";
        try {
            
            sta =  cn.prepareCall("{call sp_AgregarS (?,? ,?,?)}");
            sta.setString(1 , nombre );
            sta.setDouble(2, tipoCambio);
            sta.setInt(3, comision);
            sta.setString(4, moneda);
                     
            sta.executeUpdate(); 
            resultado = "correcto";       
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("=======================");
        }finally {
            
            //CODIGO PARA CERRAR LA CONEXION Y LAS LLAMADAS, PARA EVITA CONSUMO
            //INNESESARIO DE MEMORIA
            try {
                if (rs != null) {
                    rs.close();
                }

                if (sta != null) {
                    sta.close();
                }

                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
                System.out.println("¡¡¡Error!!!");
            }
        }
    
        return resultado;
    } 
    public String ModificarSucursal(String nombre , double tipoCambio , 
        int comision , String moneda)
    { 
        
        System.out.println("ENTRA Modificar");
        String resultado = "";
        try {
            //sta =  cn.prepareCall("{call sp_Eliminar(?)}");
            String update = "UPDATE Paises "
                    + "SET TipoCambio = ? , "
                    + "    Comision = ? ,"
                    + "    Moneda = ? "
                    + "WHERE Nombre = ?";
            
            ps = cn.prepareStatement(update);
            
            ps.setDouble(1 ,tipoCambio);
            ps.setInt(2, comision);
            ps.setString(3, moneda);
            ps.setString(4, nombre);
            
            int rowsAffected = ps.executeUpdate(); 
            
            System.out.println("Rows affecter: " + rowsAffected);
            
            if(rowsAffected > 0)
            {
                resultado = "correcto";
            }
            else 
            {
                resultado = "";
            }
              
        } catch (Exception e) {
            System.out.println(e.toString());           
        }finally {
            
            //CODIGO PARA CERRAR LA CONEXION Y LAS LLAMADAS, PARA EVITA CONSUMO
            //INNESESARIO DE MEMORIA
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }

                if (sta != null) {
                    sta.close();
                }

                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    
        return resultado;
    }    
    public String EliminarSucursal(String nombre)
    { 
        
        System.out.println("ENTRA Eliminar");
        String resultado = "";
        try {
            //sta =  cn.prepareCall("{call sp_Eliminar(?)}");
            String update = "Delete FROM Paises "
                    + "WHERE Nombre = ?";
            
            ps = cn.prepareStatement(update);

            ps.setString(1, nombre);
            
            int rowsAffected = ps.executeUpdate(); 
            
            System.out.println("Rows affecter: " + rowsAffected);
            
            if(rowsAffected > 0)
            {
                resultado = "correcto";
            }
            else 
            {
                resultado = "";
            }
              
        } catch (Exception e) {
            System.out.println(e.toString());           
        }finally {
            
            //CODIGO PARA CERRAR LA CONEXION Y LAS LLAMADAS, PARA EVITA CONSUMO
            //INNESESARIO DE MEMORIA
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }

                if (sta != null) {
                    sta.close();
                }

                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    
        return resultado;
    }    
    
    
    
    
    
    
    
    
public String ValidarUsuario(String user, String pass) {
    String resultado = "";

    try {
        sta = cn.prepareCall("{call dbo.sp_ValidarU (?, ? , ?)}");
        sta.setString(1, user);
        sta.setString(2, pass);
        sta.registerOutParameter(3, java.sql.Types.INTEGER);

        sta.execute();
        
        System.out.println("Bien");
        System.out.println(user);
        System.out.println(pass);
        
        int UNO = sta.getInt(3);
        
        System.out.println(UNO);
        
        if (UNO == 1) {
            resultado = "correcto";
        } else {
            resultado = "incorrecto";
        }

    } catch (SQLException e) {
        System.out.println(e.toString());
        System.out.println("=======ERROR AL VALIDAR USUARIO================");
    } finally {
        // CÓDIGO PARA CERRAR LA CONEXIÓN Y LAS LLAMADAS, PARA EVITAR CONSUMO INNECESARIO DE MEMORIA
        try {
            if (rs != null) {
                rs.close();
            }

            if (sta != null) {
                sta.close();
            }

            if (cn != null) {
                cn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            System.out.println("¡¡¡Error!!!");
        }
    }
    
    return resultado;
}

public ArrayList<String> ObtenerPaises()
{
    ArrayList<String> listaPaises = new ArrayList();
    try {
            sta = cn.prepareCall("{call sp_MostrarPaises}");
            rs = sta.executeQuery(); 
            while(rs.next())
            {
                listaPaises.add(rs.getString("Nombre"));
               // System.out.println(rs.getString("Nombre"));
            }
        
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally {
            
            //CODIGO PARA CERRAR LA CONEXION Y LAS LLAMADAS, PARA EVITA CONSUMO
            //INNESESARIO DE MEMORIA
            try {
                if (rs != null) {
                    rs.close();
                }

                if (sta != null) {
                    sta.close();
                }

               /* if (cn != null) {
                    cn.close();
                }*/
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    return listaPaises;
}



public ArrayList<String> CargarMonedas() throws SQLException
{
  
    ArrayList<String> listaMonedas = new ArrayList();
      try {
          String sql = "SELECT Moneda FROM Paises" ;
           
            st = cn.createStatement(); 
            rs = st.executeQuery(sql); 
            while(rs.next())
            {
                listaMonedas.add(rs.getString("Moneda"));
               // System.out.println(rs.getString("Moneda"));
            }
        
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally {
            
            //CODIGO PARA CERRAR LA CONEXION Y LAS LLAMADAS, PARA EVITA CONSUMO
            //INNESESARIO DE MEMORIA
            try {
                if (rs != null) {
                    rs.close();
                }

                if (st != null) {
                    st.close();
                }

                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }  
    return listaMonedas;
}

//ESTE CODIGO TIENE QUE SER CONSUMIDO DESDE UN WEB SERVICE EN C# 
public float TipoCambio_PaisCompro(String paisCompro)
{
    
        System.out.println(paisCompro);
        float tipocambio = 0; 
      try {
          String sql = "SELECT TipoCambio FROM Paises "
                + "WHERE Nombre = '"+paisCompro+"'" ;
           
            st = cn.createStatement(); 
            rs = st.executeQuery(sql); 
            while(rs.next())
            {
                tipocambio = rs.getFloat("TipoCambio");
                
                System.out.println(rs.getFloat("TipoCambio"));
            }
            
            
            System.out.println("tipo de cambioPaisCompro EXITOSO");
        
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error en TipoCambioPaisCompro");
        }finally {
            
            //CODIGO PARA CERRAR LA CONEXION Y LAS LLAMADAS, PARA EVITA CONSUMO
            //INNESESARIO DE MEMORIA
            try {
                if (rs != null) {
                    rs.close();
                }

                if (st != null) {
                    st.close();
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
                
            }
        }  
      
    
      return tipocambio; 


}

public float TipoCambio_MonedaPago(String MonedaPago)
{
        float tipocambio = 0; 
      try {
          String sql = "SELECT TipoCambio FROM Paises "
                + "WHERE Moneda = '"+MonedaPago+"'" ;
           
            st = cn.createStatement(); 
            rs = st.executeQuery(sql); 
            while(rs.next())
            {
                tipocambio = rs.getFloat("TipoCambio");
                
                System.out.println(rs.getFloat("TipoCambio"));
            }
            
            System.out.println("tipo de cambio  monedapago EXITOSO");
        
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally {
            
            //CODIGO PARA CERRAR LA CONEXION Y LAS LLAMADAS, PARA EVITA CONSUMO
            //INNESESARIO DE MEMORIA
            try {
                if (rs != null) {
                    rs.close();
                }

                if (st != null) {
                    st.close();
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
                System.out.println("Erorr en TipoCambio MonedaPago");
            }
        }      
      return tipocambio; 
}  

public int Comision_MonedaPago(String MonedaPago)
{
        int Comision = 0; 
      try {
          String sql = "SELECT Comision FROM Paises "
                + "WHERE Moneda = '"+MonedaPago+"'" ;
           
            st = cn.createStatement(); 
            rs = st.executeQuery(sql); 
            while(rs.next())
            {
                Comision = rs.getInt("Comision");
                
                System.out.println(rs.getInt("Comision"));
            }
            
            System.out.println("comision exitosa!!!!!!1");
            System.out.println(rs.getInt("Comision"));
        
        } catch (Exception e) {
            System.out.println(e.toString());
            
        }finally {
            
            //CODIGO PARA CERRAR LA CONEXION Y LAS LLAMADAS, PARA EVITA CONSUMO
            //INNESESARIO DE MEMORIA
            try {
                if (rs != null) {
                    rs.close();
                }

                if (st != null) {
                    st.close();
                }

                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
                System.out.println("Error en COMISION");
            }
        }      
      return Comision; 
    }

 public String GuardarReserva( int idCliente ,String NombreC , String Correo ,
      int Telefono ,String Ciudad, int Cantidad, String Pais , String Moneda,
      double MontoBruto , double Comision , double Total)
    { 
        Connection cn2 = Conexion();
        String resultado = "";
        try {
            
            sta =  cn2.prepareCall("{ call sp_AgregarHistorialRevervas"
                                    + "(? , ? ,? , ? ,? , ? , ? , ? , ? , ? , ?) }");
            
            System.out.println("ENTRO LA PREPARE CALL");
            sta.setInt(1 , idCliente );
            System.out.println("1");
            sta.setString(2, NombreC);
            System.out.println("2");
            sta.setString(3, Correo);
            System.out.println("3");
            sta.setInt(4, Telefono);
            System.out.println("4");
            sta.setString(5, Ciudad);
            System.out.println("5");
            sta.setInt(6, Cantidad);
            System.out.println("6");
            sta.setString(7, Pais);
            System.out.println("7");
            sta.setString(8, Moneda);
            System.out.println("8");
            sta.setDouble(9 , MontoBruto );
            System.out.println("9");
            sta.setDouble(10, Comision);
            System.out.println("10");
            sta.setDouble(11, Total);
            System.out.println("11");
            
            sta.executeUpdate(); 
            
            
            resultado = "correcto";       
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("=======================");
        }finally {
            
            //CODIGO PARA CERRAR LA CONEXION Y LAS LLAMADAS, PARA EVITA CONSUMO
            //INNESESARIO DE MEMORIA
            try {
                if (rs != null) {
                    rs.close();
                }

                if (sta != null) {
                    sta.close();
                }

                if (cn2 != null) {
                    cn2.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
                System.out.println("¡¡¡Error!!!");
            }
        }
    
        return resultado;
    } 
 
 
 public String Moneda_PaisCompro(String paisCompro)
{
    
        System.out.println(paisCompro);
        String moneda = ""; 
      try {
          String sql = "SELECT Moneda FROM Paises "
                + "WHERE Nombre = '"+paisCompro+"'" ;
           
            st = cn.createStatement(); 
            rs = st.executeQuery(sql); 
            while(rs.next())
            {
                moneda = rs.getString("Moneda");
                
                //System.out.println(rs.getFloat("TipoCambio"));
            }
            
            
            System.out.println("Moneda de pais compro EXITOSO");
        
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error en MonedaPaisCompro");
        }finally {
            
            //CODIGO PARA CERRAR LA CONEXION Y LAS LLAMADAS, PARA EVITA CONSUMO
            //INNESESARIO DE MEMORIA
            try {
                if (rs != null) {
                    rs.close();
                }

                if (st != null) {
                    st.close();
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
                
            }
        }  
      
    
      return moneda; 


}
 
 
}//final end
