package dao;

import java.sql.*;

import classe.Funcionario;

public class DaoLogin {
    
        public int fazerLogin(Funcionario funcionario){
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Funcionario";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if(rs.getString("funcionarioEmail").equals(funcionario.getEmail())){
                    if(rs.getString("funcionarioSenha").equals(funcionario.getSenha())){
                        if(rs.getInt("funcionarioAcesso")==1) {
                            return 0;
                        } else {
                            return 3;
                        }
                    }else{
                        return 1;
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        } 
        return 2;
    }
        
}
