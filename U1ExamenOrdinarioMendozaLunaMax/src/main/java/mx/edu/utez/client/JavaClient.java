package mx.edu.utez.client;

import mx.edu.utez.server.Handler;
import mx.edu.utez.server.User;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JavaClient{
    private String name;
    private String lastName;
    private String email;
    private String date;
    private int estado;

    public JavaClient(String name, String lastName, String email, String date, int estado) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.date = date;
        this.estado = estado;
    }


    public static void main(String[] args )
            throws MalformedURLException, XmlRpcException {
        Scanner leer = new Scanner(System.in);
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL( new URL( "http://localhost:1200"  ) );

        XmlRpcClient client = new XmlRpcClient();
        client.setConfig( config );
        int o, i=1;
        do {
            System.out.println("¿Que quieres hacer? \n 1. Registrar \n 2. Borrar \n 3. Actualizar \n 4. Ver todos los registros");
            o = leer.nextInt();
            switch (o){
                case 1:
                    System.out.println("Ingresa los datos que se requieran:");
                    System.out.println("Nombre");
                    String name = leer.next();
                    System.out.println("Apellido");
                    String apellido = leer.next();
                    System.out.println("Correo");
                    String correo = leer.next();
                    System.out.println("Password");
                    String pass = leer.next();
                    System.out.println("Fecha : **-**-***");
                    String date = leer.next();
                    System.out.println("Estado (0 para inactivo, 1 para activo");
                    int state = leer.nextInt();
                    Object[] params = {name,apellido,correo,pass,date,state};
                    String result = (String) client.execute( "Handler.registroUser",  params);

                    System.out.println( result );
                    break;
                case 2:
                    System.out.println("Cual es el id del alumno que quieres eliminar?");
                    int id = leer.nextInt();
                    Object[] params2 = {id};
                    String result2 = (String) client.execute( "Handler.borrarUser",  params2);
                    System.out.println(result2);
                    break;
                case 3:

                    System.out.println("Cual es el id del alumno que quieres actualizar?");
                    id = leer.nextInt();
                    System.out.println("Cual es el nombre?");
                    name = leer.next();
                    System.out.println("Cual es el apellido");
                    apellido = leer.next();
                    System.out.println("Cual es la contraseña nueva");
                    pass = leer.next();
                    System.out.println("Que estado tendra?");
                    state = leer.nextInt();
                    Object[] params3 = {name,apellido,pass,state,id};
                    String result3 = (String) client.execute( "Handler.actualizarUser",  params3);
                    System.out.println(result3);
                    break;
                case 4:
                    System.out.println("Lista\n");
                    System.out.println("Consultar todos");
                    Handler Handler = new Handler();
                    for(User user : Handler.findAll() ){

                        System.out.println("Id: "+user.getId() + "  Nombre : " +user.getName() + " " + user.getLastname());
                        System.out.println("E-mail: "+user.getEmail());
                        if(user.getStatus()==1){
                            System.out.println("Status: Activo");
                        }else{
                            System.out.println("Status: Inactivo");
                        }
                        System.out.println("\n ");
                    }


                    break;
                default:  break;
            }
            System.out.println("Quieres volver al menú principal? \n 1.- Si \n 2.- No");
            i= leer.nextInt();
        }while (i==1);
        System.out.println("Gracias, disfrute su salida del sistema (:");

    }


    }

