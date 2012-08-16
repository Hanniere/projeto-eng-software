package dao;

import util.BancoDados;
import java.sql.*;
import java.util.ArrayList;

import entidades.Onibus;

public class DaoOnibus {
    
    public boolean cadastrarOnibus(Onibus onibus){
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Onibus";
            ResultSet rsV = stmt.executeQuery(sql);
            while(rsV.next()){
                if(rsV.getString("onibusPlaca").equals(onibus.getPlaca())){
                    return false;
                }
            }
            sql = "INSERT INTO Onibus VALUES (0, '"+onibus.getPlaca()+"', '"+onibus.getModelo()+"', '"+onibus.getMarca()+"', "+onibus.getAno()+", "+onibus.getQtdeAssentos()+")";
            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return true;
    }
    
    public boolean alterarOnibus(Onibus onibus){
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Onibus";
            ResultSet rsV = stmt.executeQuery(sql);
            while(rsV.next()){
                if(rsV.getString("onibusPlaca").equals(onibus.getPlaca()) && rsV.getInt("onibusId") != onibus.getId()){
                    return false;
                }
            }
            sql = "UPDATE Onibus set onibusPlaca = '"+onibus.getPlaca()+"', "
                    + "onibusMarca = '"+onibus.getMarca()+"', onibusModelo = '"+onibus.getModelo()+"', onibusAno = '"+onibus.getAno()+"',"
                    + "onibusQtdeAssentos = '"+onibus.getQtdeAssentos()+"' where onibusId = "+onibus.getId();
            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return true;
    }
    
    public boolean removerOnibus(Onibus onibus){
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Horario where horario_onibusId in (Select onibusId from Onibus where onibusId = "+onibus.getId()+")";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                return false;
            }
            sql = "DELETE FROM Onibus where onibusId = "+ onibus.getId();
            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
            return false;
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public Onibus consultaOnibus(Onibus onibus){
        Onibus dadosOnibus = new Onibus();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Onibus where OnibusId = " + onibus.getId();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            dadosOnibus.setPlaca(rs.getString("OnibusPlaca"));
            dadosOnibus.setModelo(rs.getString("OnibusModelo"));
            dadosOnibus.setMarca(rs.getString("OnibusMarca"));
            dadosOnibus.setAno(rs.getInt("OnibusAno"));
            dadosOnibus.setQtdeAssentos(rs.getInt("OnibusQtdeAssentos"));
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        } 
        return dadosOnibus;
    }
    
    public ArrayList<Onibus> consultarTodosOnibus(){
        ArrayList<Onibus> arrayList = new ArrayList<Onibus>();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Onibus order by onibusPlaca asc";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Onibus onibus = new Onibus();
                onibus.setId(rs.getInt("onibusId"));
                onibus.setPlaca(rs.getString("onibusPlaca"));
                arrayList.add(onibus);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        } 
        return arrayList;
    }
    public ArrayList<Onibus> consultarOnibusLivres(String horarioSaida, String horarioChegada, String dias){
        ArrayList<Onibus> arrayList = new ArrayList<Onibus>();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select onibus.* from onibus" +
            		" where NOT EXISTS (select 1 from horario where onibus.OnibusId = horario.Horario_OnibusId and HorarioDiaId IN ("+dias+")" +
            		" and (('"+horarioSaida+"' between horario.HorarioSaida and horario.HorarioChegada)" +
            		" or ('"+horarioChegada+"' between horario.HorarioSaida and horario.HorarioChegada))" +
            		" and Horario_usado = 1)";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Onibus onibus = new Onibus();
                onibus.setId(rs.getInt("onibusId"));
                onibus.setPlaca(rs.getString("onibusPlaca"));
                arrayList.add(onibus);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        } 
        return arrayList;
    }
}
