package dao;

import util.BancoDados;
import java.sql.*;
import java.util.ArrayList;

import entidades.Cidade;

public class DaoCidade {

    public boolean cadastrarCidade(Cidade cidade) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select estadoId from Estado where estadoUF = '" + cidade.getEstado() + "'";
            ResultSet rsE = stmt.executeQuery(sql);
            rsE.next();
            cidade.setEstado(rsE.getString("EstadoId"));
            sql = "select * from Cidade";
            ResultSet rsV = stmt.executeQuery(sql);
            while (rsV.next()) {
                if (rsV.getString("cidadeNome").equals(cidade.getNome()) && rsV.getString("cidade_EstadoId").equals(cidade.getEstado())) {
                    return false;
                }
            }
            sql = "INSERT INTO Cidade VALUES (0, '" + cidade.getNome() + "', '" + cidade.getEstado() + "')";
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

    public boolean alterarCidade(Cidade cidade) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select estadoId from Estado where estadoUF = '" + cidade.getEstado() + "'";
            ResultSet rsE = stmt.executeQuery(sql);
            rsE.next();
            cidade.setEstado(rsE.getString("EstadoId"));
            sql = "select * from Cidade";
            ResultSet rsV = stmt.executeQuery(sql);
            while (rsV.next()) {
                if (rsV.getString("cidadeNome").equals(cidade.getNome()) && rsV.getString("cidade_EstadoId").equals(cidade.getEstado()) && rsV.getInt("cidadeId") != cidade.getId()) {
                    return false;
                }
            }
            sql = "UPDATE Cidade set cidadeNome = '" + cidade.getNome() + "', cidade_EstadoId = '" + cidade.getEstado() + "' where cidadeId = " + cidade.getId();
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

    public boolean removerCidade(Cidade cidade) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from Rota where rota_cidadeOrigem in (Select cidadeId from Cidade where cidadeId = "+cidade.getId()+") OR rota_cidadeDestino in (Select cidadeId from Cidade where cidadeId = "+cidade.getId()+")";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                return false;
            }
            sql = "select * from Itinerario where itinerario_cidadeOrigem in (Select cidadeId from Cidade where cidadeId = "+cidade.getId()+") OR itinerario_cidadeDestino in (Select cidadeId from Cidade where cidadeId = "+cidade.getId()+")";
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                return false;
            }
            sql = "DELETE FROM Cidade where cidadeId = " + cidade.getId();
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

    public Cidade consultaCidade(Cidade cidade) {
        Cidade dadosCidade = new Cidade();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select C.cidadeNome, E.estadoUF from Cidade C, Estado E where cidadeId = " + cidade.getId() + " and E.estadoId = C.cidade_EstadoId";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            dadosCidade.setNome(rs.getString("cidadeNome"));
            dadosCidade.setEstado(rs.getString("estadoUF"));
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return dadosCidade;
    }

    public ArrayList<Cidade> consultarTodasCidades() {
        ArrayList<Cidade> arrayList = new ArrayList<Cidade>();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select c.cidadeNome, c.cidadeId, e.estadoUF from cidade c, estado e where c.cidade_EstadoId = e.estadoId order by cidadeNome asc";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(rs.getInt("cidadeId"));
                cidade.setNome(rs.getString("cidadeNome") + " - " + rs.getString("estadoUF"));
                arrayList.add(cidade);
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
