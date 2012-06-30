package dao;

import util.BancoDados;
import java.sql.*;
import java.util.ArrayList;

import entidades.Funcionario;

public class DaoFuncionario {

    public boolean cadastrarFuncionario(Funcionario funcionario) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Funcionario";
            ResultSet rsV = stmt.executeQuery(sql);
            while (rsV.next()) {
                if (rsV.getString("funcionarioCpf").equals(funcionario.getCpf())) {
                    return false;
                }
            }
            sql = "INSERT INTO Funcionario VALUES (0, '" + funcionario.getNome() + "', '" + funcionario.getCpf() + "', '" + funcionario.getEmail() + "', '" + funcionario.getSenha() + "', " + funcionario.getAcesso() + ")";
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

    public boolean alterarFuncionario(Funcionario funcionario) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Funcionario";
            ResultSet rsV = stmt.executeQuery(sql);
            while (rsV.next()) {
                if (rsV.getString("funcionarioCpf").equals(funcionario.getCpf()) && rsV.getInt("funcionarioId") != funcionario.getId()) {
                    return false;
                }
            }
            sql = "UPDATE Funcionario set funcionarioNome = '" + funcionario.getNome() + "', "
                    + "funcionarioCpf = '" + funcionario.getCpf() + "', funcionarioSenha = '" + funcionario.getSenha() + "',"
                    + "funcionarioEmail = '" + funcionario.getEmail() + "' ,"
                    + "funcionarioAcesso = " + funcionario.getAcesso() + " where funcionarioId = " + funcionario.getId();
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

    public boolean removerFuncionario(Funcionario funcionario) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM Funcionario where funcionarioId = " + funcionario.getId();
            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
            return false;
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            //ex.printStackTrace();
            return false;
        }
        return true;
    }

    public Funcionario consultarFuncionario(Funcionario funcionario) {
        Funcionario dadosFuncionario = new Funcionario();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Funcionario where funcionarioId = " + funcionario.getId();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            dadosFuncionario.setCpf(rs.getString("funcionarioCpf"));
            dadosFuncionario.setSenha(rs.getString("funcionarioSenha"));
            dadosFuncionario.setEmail(rs.getString("funcionarioEmail"));
            dadosFuncionario.setNome(rs.getString("funcionarioNome"));
            dadosFuncionario.setAcesso(rs.getInt("funcionarioAcesso"));
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return dadosFuncionario;
    }

    public ArrayList<Funcionario> consultarTodosFuncionarios() {
        ArrayList<Funcionario> arrayList = new ArrayList<Funcionario>();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Funcionario order by funcionarioNome asc";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("funcionarioId"));
                funcionario.setNome(rs.getString("funcionarioNome") + " - " + rs.getString("funcionarioCpf"));
                arrayList.add(funcionario);
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
