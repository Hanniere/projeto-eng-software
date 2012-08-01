package dao;

import entidades.Horario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import util.BancoDados;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoHorario {

public boolean cadastrarNovoHorario(Horario horario, int itinerarioId){
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql2 = "select * from horario " +
            		"where HorarioDiaId IN ("+horario.getHorarioDiaId()+") " +
            		"and HorarioSaida = '"+horario.getHorarioSaida()+"' " +
            		"and Horario_RotaItinerarioId IN " +
            		"(select RotaItinerarioId from rotaitinerario where RotaItinerario_ItinerarioId = "+itinerarioId+")"; 
            ResultSet rs = stmt.executeQuery(sql2);
            if(rs.first()) {//Se existir first, ent�o j� existe no BD
            	String sq13 ="update horario set HorarioPreco = "+horario.getHorarioPreco()+"" +
            			" , Horario_MotoristaId = "+horario.getHorario_MotoristaId()+"" +
            			" , Horario_OnibusId = "+horario.getHorario_OnibusId()+"" +
            			" , Horario_usado = 1" +
            			" where HorarioDiaId = "+horario.getHorarioDiaId()+"" +
            			" and HorarioSaida = '"+horario.getHorarioSaida()+"'" +
            			" and Horario_RotaItinerarioId = "+horario.getHorario_RotaItinerarioId()+"";
            	stmt.executeUpdate(sq13);
            } else {
            	System.out.println("INSERINDO HORARIO");
                String sql = "insert into Horario (HorarioDiaId, Horario_RotaItinerarioId, HorarioSaida, HorarioChegada, HorarioPreco," +
                		" Horario_MotoristaId, Horario_OnibusId, Horario_usado)" +
                		" VALUES ( '"+horario.getHorarioDiaId()+"','"+horario.getHorario_RotaItinerarioId()+
                		"','"+horario.getHorarioSaida()+"','"+horario.getHorarioChegada()+"','"+horario.getHorarioPreco()+
                		"','"+horario.getHorario_MotoristaId()+"','"+horario.getHorario_OnibusId()+"', 1)";
                stmt.executeUpdate(sql);
            }
            rs.close();
            stmt.close();
   
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

public boolean atualizarHorario(Horario horario){
    BancoDados banco = new BancoDados();
    try {
        Class.forName(banco.getDriver());
        Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
        Statement stmt = conn.createStatement();
        
    	String sq13 ="update horario set HorarioPreco = "+horario.getHorarioPreco()+"" +
    			" , Horario_MotoristaId = "+horario.getHorario_MotoristaId()+"" +
    			" , Horario_OnibusId = "+horario.getHorario_OnibusId()+"" +
    			" , Horario_usado = "+horario.getHorario_usado()+"" +
    			" where HorarioDiaId = "+horario.getHorarioDiaId()+"" +
    			" and HorarioSaida = '"+horario.getHorarioSaida()+"'" +
    			" and Horario_RotaItinerarioId = "+horario.getHorario_RotaItinerarioId()+"";
        	stmt.executeUpdate(sq13);

        stmt.close();
  
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

    public ArrayList<ArrayList<String>> consultarTodosHorarios(int id) {
        ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();        
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT origem.cidadeNome, destino.cidadeNome, HorarioPreco, HorarioSaida, HorarioChegada "
                    + "FROM Horario "
                    + "INNER JOIN RotaItinerario ON (Horario_RotaItinerarioId = rotaItinerario_rotaId) "
                    + "INNER JOIN Rota ON (rotaId = RotaItinerario_RotaId) "
                    + "INNER JOIN Cidade origem ON (origem.cidadeId = Rota_CidadeOrigem) "
                    + "INNER JOIN Cidade destino ON (destino.cidadeId = Rota_CidadeDestino) "
                    + "WHERE RotaItinerario_ItinerarioId = " + id + " "
                    + "GROUP BY origem.CidadeNome,destino.CidadeNome "
                    + "ORDER BY RotaItinerarioOrdem";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ArrayList<String> array = new ArrayList<String>();
                array.add(rs.getString("origem.cidadeNome"));
                array.add(rs.getString("destino.cidadeNome"));
                array.add(rs.getString("HorarioPreco"));
                array.add(rs.getString("HorarioSaida"));
                array.add(rs.getString("HorarioChegada"));
                matriz.add(array);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();

        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();

        }
        return matriz;
    }
    public ArrayList<Horario> consultarTodosHorariosItinerario(int id) {
        ArrayList<Horario> arrayHorario = new ArrayList<Horario>();        
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT horario_RotaItinerarioId, horarioId, horarioDiaId, horarioSaida, horarioChegada, horarioPreco, horario_MotoristaId, horario_OnibusId, horario_usado "
                    + "FROM Horario H, RotaItinerario Ri, Itinerario I "
                    + "WHERE H.Horario_RotaItinerarioId = Ri.RotaItinerarioId "
                    + "AND Ri.RotaItinerario_ItinerarioId = I.ItinerarioId "
                    + "AND I.ItinerarioId = " + id +" "
                    + "ORDER BY Ri.RotaItinerarioOrdem";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Horario horario = new Horario();
                horario.setHorario_RotaItinerarioId(Integer.parseInt(rs.getString("horario_RotaItinerarioId")));
                horario.setHorarioId(Integer.parseInt(rs.getString("horarioId")));
                horario.setHorarioDia(Integer.parseInt(rs.getString("horarioDiaId")));
                horario.setHorarioSaida(rs.getString("horarioSaida"));
                horario.setHorarioChegada(rs.getString("horarioChegada"));
                horario.setHorarioPreco(Double.parseDouble(rs.getString("horarioPreco")));
                horario.setHorario_MotoristaId(Integer.parseInt(rs.getString("horario_MotoristaId")));
                horario.setHorario_OnibusId(Integer.parseInt(rs.getString("horario_OnibusId")));
                horario.setHorario_usado(Integer.parseInt(rs.getString("horario_usado")));
                arrayHorario.add(horario);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();

        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();

        }
        return arrayHorario;
    }
    public ArrayList<Horario> verificaHorarioItinerario(int idItinerario, int horarioUsado, String dias, String horarioSaida) {
        ArrayList<Horario> arrayHorario = new ArrayList<Horario>();        
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "select * from horario where HorarioDiaId IN ("+dias+") " +
            		"and HorarioSaida = '"+horarioSaida+"' " +
            		"and Horario_RotaItinerarioId = (select RotaItinerarioId " +
            		"from rotaitinerario " +
            		"where RotaItinerario_ItinerarioId = "+idItinerario+" " +
            		"and RotaItinerarioOrdem = 1) " +
            		"and Horario_usado = "+horarioUsado+"";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Horario horario = new Horario();
                horario.setHorarioId(Integer.parseInt(rs.getString("horarioId")));
                horario.setHorarioDia(Integer.parseInt(rs.getString("horarioDiaId")));
                horario.setHorario_RotaItinerarioId(Integer.parseInt(rs.getString("horario_RotaItinerarioId")));
                horario.setHorarioSaida(rs.getString("horarioSaida"));
                horario.setHorarioChegada(rs.getString("horarioChegada"));
                horario.setHorarioPreco(Double.parseDouble(rs.getString("horarioPreco")));
                horario.setHorario_MotoristaId(Integer.parseInt(rs.getString("horario_MotoristaId")));
                horario.setHorario_OnibusId(Integer.parseInt(rs.getString("horario_OnibusId")));
                horario.setHorario_usado(Integer.parseInt(rs.getString("horario_usado")));
                arrayHorario.add(horario);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();

        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();

        }
        return arrayHorario;
    }
    
    public ArrayList<Horario> consultaHorarioDeRotas(int id) {
        ArrayList<Horario> arrayHorario = new ArrayList<Horario>();        
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT horario_RotaItinerarioId, horarioId, horarioDiaId, horarioSaida, horarioChegada, horarioPreco, horario_MotoristaId, horario_OnibusId, horario_usado " +
            		"FROM Horario H, RotaItinerario Ri " +
            		"WHERE Ri.RotaItinerarioId = H.Horario_RotaItinerarioId " +
            		"AND Ri.RotaItinerario_ItinerarioId = "+id+" " +
            		"GROUP BY H.Horario_RotaItinerarioId";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Horario horario = new Horario();
                horario.setHorario_RotaItinerarioId(Integer.parseInt(rs.getString("horario_RotaItinerarioId")));
                horario.setHorarioId(Integer.parseInt(rs.getString("horarioId")));
                horario.setHorarioDia(Integer.parseInt(rs.getString("horarioDiaId")));
                horario.setHorarioSaida(rs.getString("horarioSaida"));
                horario.setHorarioChegada(rs.getString("horarioChegada"));
                horario.setHorarioPreco(Double.parseDouble(rs.getString("horarioPreco")));
                horario.setHorario_MotoristaId(Integer.parseInt(rs.getString("horario_MotoristaId")));
                horario.setHorario_OnibusId(Integer.parseInt(rs.getString("horario_OnibusId")));
                horario.setHorario_usado(Integer.parseInt(rs.getString("horario_usado")));
                arrayHorario.add(horario);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();

        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();

        }
        return arrayHorario;
    }
    
}
