package mx.edu.utez.server;

import mx.edu.utez.database.ConnectionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Handler {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    Statement state;




    public String registroUser(String name, String lastName, String email, String password, String date, int state){
        String status = null;

    try{
        
        con = ConnectionMySQL.getConnection();
        String query = "INSERT INTO usuario.user (`name`,`lastName`, `correo`,`password`,`date`, `status`) VALUES (?,?,?,?,?,?);";
        pstm= con.prepareStatement(query);
        pstm.setString(1, name );
        pstm.setString(2,lastName);
        pstm.setString(3,email);
        pstm.setString(4,password);
        pstm.setString(5,date);
        pstm.setInt(6,state);

        if (pstm.executeUpdate() == 1){
            status = "Se ha creado " +name +" su estado actual es " +state;
        }else {
            status = "No se creó";
        }



    }catch (SQLException ex){
        ex.printStackTrace();

    }finally {

    }
    
        return status;
    }

    public String borrarUser(int id){
            String status = null;
            try{
                con = ConnectionMySQL.getConnection();
                String query = "DELETE FROM usuario.user WHERE user .id = ?;";
                pstm = con.prepareStatement(query);
                pstm.setInt(1,id);
                if (pstm.executeUpdate() == 1){
                    status = "Se ha borrado el usuario con el id:  "+ id;
                }else {
                    status = "No se borró";
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }finally {

            }
            return status;
    }
    public String actualizarUser ( String name, String lastName, String password, int state, int id){
            String status = null;
            try {
                con = ConnectionMySQL.getConnection();
                String query ="UPDATE usuario.user SET user.name = ?,\n" +
                        "user.lastName = ?,\n" +
                        "user.password = ?,\n" +
                        "user.status = ?\n" +
                        "WHERE user.id = ?; ";
                pstm = con.prepareStatement(query);
                pstm.setString(1,name);
                pstm.setString(2,lastName);
                pstm.setString(3,password);
                pstm.setInt(4,state);
                pstm.setInt(5,id);
                if (pstm.executeUpdate() == 1){
                    status = "Se ha actualizado el usuario con el id:  "+ id;
                }else {
                    status = "No se actualizó";
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
            return status;
    }

    public List<User> findAll(){
        List<User> listUser = new ArrayList<>();
        try{
            con = ConnectionMySQL.getConnection();
            String query = "  SELECT user.id, user.name, user.lastName, user.correo, user.status FROM usuario.user;";
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("correo"));
                user.setStatus(rs.getInt("status"));
                listUser.add(user);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {

        }
        return listUser;
    }

}

