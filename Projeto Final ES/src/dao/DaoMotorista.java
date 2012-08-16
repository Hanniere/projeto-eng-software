package dao;

import util.BancoDados;
import java.sql.*;
import java.util.ArrayList;

import entidades.Motorista;

public class DaoMotorista {

    public int cadastrarMotorista(Motorista motorista) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Motorista";
            ResultSet rsV = stmt.executeQuery(sql);
            while (rsV.next()) {
                if (rsV.getString("motoristaRg").equals(motorista.getRg())) {
                    return 1;
                } else if (rsV.getString("motoristaCpf").equals(motorista.getCpf())) {
                    return 2;
                }
            }
            sql = "INSERT INTO Motorista VALUES (0, '" + motorista.getNome() + "', '" + motorista.getRg() + "', '" + motorista.getCpf() + "', '" + motorista.getEndereco() + "', '" + motorista.getTelefone() + "', '" + motorista.getEmail() + "')";
            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return 0;
    }

    public int alterarMotorista(Motorista motorista) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Motorista";
            ResultSet rsV = stmt.executeQuery(sql);
            while (rsV.next()) {
                if (rsV.getString("motoristaRg").equals(motorista.getRg()) && rsV.getInt("motoristaId") != motorista.getId()) {
                    return 1;
                } else if (rsV.getString("motoristaCpf").equals(motorista.getCpf()) && rsV.getInt("motoristaId") != motorista.getId()) {
                    return 2;
                }
            }
            sql = "UPDATE Motorista set motoristaNome = '" + motorista.getNome() + "', motoristaRg = '" + motorista.getRg() + "', "
                    + "motoristaCpf = '" + motorista.getCpf() + "', motoristaTel = '" + motorista.getTelefone() + "', motoristaEnd = '" + motorista.getEndereco() + "',"
                    + "motoristaEmail = '" + motorista.getEmail() + "' where motoristaId = " + motorista.getId();
            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean removerMotorista(Motorista motorista) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Horario where horario_motoristaId in (Select motoristaId from Motorista where motoristaId = "+motorista.getId()+")";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                return false;
            }
            sql = "DELETE FROM Motorista where motoristaId = " + motorista.getId();
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

    public Motorista consultarMotorista(Motorista motorista) {
        Motorista dadosMotorista = new Motorista();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Motorista where motoristaId = " + motorista.getId();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            dadosMotorista.setRg(rs.getString("motoristaRg"));
            dadosMotorista.setCpf(rs.getString("motoristaCpf"));
            dadosMotorista.setTelefone(rs.getString("motoristaTel"));
            dadosMotorista.setEndereco(rs.getString("motoristaEnd"));
            dadosMotorista.setEmail(rs.getString("motoristaEmail"));
            dadosMotorista.setNome(rs.getString("motoristaNome"));
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return dadosMotorista;
    }

    public ArrayList<Motorista> consultarTodosMotoristas() {
        ArrayList<Motorista> arrayList = new ArrayList<Motorista>();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Motorista order by motoristaNome asc";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Motorista motorista = new Motorista();
                motorista.setId(rs.getInt("motoristaId"));
                motorista.setNome(rs.getString("motoristaNome") + " - " + rs.getString("motoristaCpf"));
                arrayList.add(motorista);
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
    
    public ArrayList<Motorista> consultarMotoristasLivres(String horarioSaida, String horarioChegada, String dias) {
        ArrayList<Motorista> arrayList = new ArrayList<Motorista>();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select motorista.* from motorista where NOT EXISTS (select 1 from horario where motorista.MotoristaId = horario.Horario_MotoristaId and HorarioDiaId IN ("+dias+") and (('"+horarioSaida+"' between horario.HorarioSaida and horario.HorarioChegada) or ('"+horarioChegada+"' between horario.HorarioSaida and horario.HorarioChegada)) and Horario_usado = 1)";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Motorista motorista = new Motorista();
                motorista.setId(rs.getInt("motoristaId"));
                motorista.setNome(rs.getString("motoristaNome") + " - " + rs.getString("motoristaCpf"));
                arrayList.add(motorista);
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
