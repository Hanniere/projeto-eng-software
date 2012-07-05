package dao;

import entidades.Passagem;
import entidades.Horario;
import entidades.RotaItinerario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.BancoDados;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import entidades.Cidade;

public class DaoPassagem {

    public boolean cadastrarPassagemComprada(Passagem passagem) {
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            /*String sql = "select estadoId from Estado where estadoUF = '" + cidade.getEstado() + "'";
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
            stmt.executeUpdate(sql);*/
        } catch (ClassNotFoundException ex) {
            System.out.println("Nao foi possivel carregar o driver.");
            ex.printStackTrace();
            return false;
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Cidade> carregaCidadesOrigem() {
        ArrayList<Cidade> arraylist = new ArrayList<Cidade>();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT distinct CidadeId , CidadeNome FROM Horario"
                            + " INNER JOIN RotaItinerario ON ( Horario_RotaItinerarioId = RotaItinerarioId )"
                            + " INNER JOIN Rota ON ( RotaItinerario_RotaId = RotaId )"
                            + " INNER JOIN Cidade ON ( Rota_CidadeOrigem = CidadeId )";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(rs.getInt("CidadeId"));
                cidade.setNome(rs.getString("CidadeNome"));
                arraylist.add(cidade);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Nao foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return arraylist;
    }

    public ArrayList<Cidade> carregaCidadesDestino(Cidade origem) {
        ArrayList<RotaItinerario> arraylist = new ArrayList<RotaItinerario>();
        ArrayList<Cidade> cidades = new ArrayList<Cidade>();
        BancoDados banco = new BancoDados();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT ItinerarioId, RotaItinerarioOrdem, RotaItinerarioId from Itinerario Join RotaItinerario ON (ItinerarioId = RotaItinerario_ItinerarioId) Join Rota ON (RotaItinerario_RotaId = RotaId) Join Cidade ON (Rota_CidadeOrigem = CidadeId) and cidadeId =" + origem.getId();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                RotaItinerario RI = new RotaItinerario();
                RI.setRotaitinerarioOrdem(rs.getInt("RotaItinerarioOrdem"));
                RI.setRotaitinerario_itinerarioId(rs.getInt("ItinerarioId"));
                RI.setRotaItinerarioId(rs.getInt("RotaItinerarioId"));
                arraylist.add(RI);
            }
            for (int i = 0; i < arraylist.size(); i++) {
                sql = "SELECT cidadeId, cidadeNome FROM Cidade JOIN Rota ON (cidadeId = Rota_CidadeDestino) JOIN RotaItinerario ON(rotaItinerario_rotaId = RotaId) AND RotaItinerario_ItinerarioId = " + arraylist.get(i).getRotaitinerario_itinerarioId() + " AND RotaItinerarioOrdem >=" + arraylist.get(i).getRotaitinerarioOrdem() + " ORDER BY cidadeNome";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Cidade cidade = new Cidade();
                    cidade.setId(rs.getInt("CidadeId"));
                    cidade.setNome(rs.getString("CidadeNome"));
                    cidade.setEstado(String.valueOf(arraylist.get(i).getRotaItinerarioId()));
                    cidades.add(cidade);
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Nao foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return cidades;
    }

    public ArrayList<Horario> carregaHorarios(int id, int dia, int mes, int ano) {
        ArrayList<Horario> horarios = new ArrayList<Horario>();
        BancoDados banco = new BancoDados();
        Calendar calendarPegarDiaSemana = Calendar.getInstance();
        calendarPegarDiaSemana.set(Calendar.DAY_OF_MONTH, dia);
        calendarPegarDiaSemana.set(Calendar.MONTH, mes - 1);
        calendarPegarDiaSemana.set(Calendar.YEAR, ano);
        //System.out.println(calendarPegarDiaSemana.get(Calendar.DAY_OF_WEEK));
        int diaSemana = calendarPegarDiaSemana.get(Calendar.DAY_OF_WEEK);
        boolean existeItinerarioNoDia = false;
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT HorarioDiaId, HorarioId from Horario WHERE Horario_RotaItinerarioId = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (diaSemana == rs.getInt("HorarioDiaId")) {
                    existeItinerarioNoDia = true;
                }
            }
            if (existeItinerarioNoDia) {
                sql = "SELECT HorarioSaida, HorarioChegada, HorarioPreco, HorarioId FROM Horario WHERE Horario_RotaItinerarioId = " + id + " AND HorarioDiaId = " + diaSemana;
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Horario horario = new Horario();
                    horario.setHorarioId(rs.getInt("HorarioId"));
                    horario.setHorarioSaida(rs.getString("HorarioSaida"));
                    horario.setHorarioPreco(rs.getDouble("HorarioPreco"));
                    horario.setHorarioChegada(rs.getString("HorarioChegada"));
                    horarios.add(horario);
                }
                return horarios;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Nao foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> consultaPoltronasCompradas(int horarioId, String dataCompra) {
        ArrayList<Integer> arraylist = new ArrayList<Integer>();
        BancoDados banco = new BancoDados();
        System.out.println("HORARIO ID: " + horarioId + "      DataCompra: " + dataCompra);
   
        System.out.println("Comparacao com 2/1/2012: " + dataCompra.compareTo("2/1/2012") );
       
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SET @data =" + dataCompra + "";
            sql = "SELECT P.PassagemNumAssentoComprado FROM Passagem P WHERE ( Passagem_HorarioId = '" + horarioId + "' ) AND ( STRCMP(P.passagemData , '" + dataCompra + "' ) = 0 )" ;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                arraylist.add(rs.getInt("PassagemNumAssentoComprado"));
            }
            sql = "SELECT horario_onibusId, horarioChegada, horarioSaida FROM Horario WHERE horarioId = " + horarioId;
            rs = stmt.executeQuery(sql);
            rs.next();
            int IdOnibus = rs.getInt("horario_onibusId");
            String horarioSaida = rs.getString("horarioSaida");
            String horarioChegada = rs.getString("horarioChegada");
            //DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
            Time horarioSaidaTime = Time.valueOf(rs.getString("horarioSaida")); //pegando horario de saida do TXT na primeira vez
            Time horarioChegadaTime = Time.valueOf(rs.getString("horarioChegada"));
            Calendar calAux = Calendar.getInstance();
            Calendar calAux1 = Calendar.getInstance();
            sql = "SET @hS =" + horarioSaida + "";
            sql = "SET @hC =" + horarioChegada + "";
            System.out.println("Horario Saida: " + horarioSaida + "     Horario Chegada: " + horarioChegada);
            calAux.setTime(horarioSaidaTime);
            calAux1.setTime(horarioChegadaTime);
            //se calAux é maior retorna 1, se nao retorna -1 e se for igual retorna 0
            System.out.println("Maior retorna 1, menor retorna -1: " + calAux.compareTo(calAux1));
            calAux.add(Calendar.MINUTE, 30); //somando a duração (em minutos)para obter horario de chegada
            sql = "SELECT P.PassagemNumAssentoComprado FROM Passagem P, Horario H "
                    + " WHERE ( STRCMP(P.passagemData, '"+ dataCompra + "') = 0 ) "
                    + " AND ( H.horario_onibusId = " + IdOnibus + " ) "
                    //+ " AND ( P.Passagem_HorarioId = '" + horarioId + "' ) "
                    + " AND("
                    + "      ( ( STRCMP(H.horarioChegada,'"+horarioSaida+"') = 1 ) AND  ( (STRCMP(H.horarioSaida,'"+horarioSaida+"')= -1) OR (STRCMP(H.horarioSaida,'"+horarioSaida+"')= 0) )  "
                    + "         ) OR ( "
                    + "         ( ( STRCMP(H.horarioChegada,'"+horarioChegada+"') = -1) OR (STRCMP(H.horarioChegada,'"+horarioChegada+"') = 0) ) AND ( (STRCMP('"+horarioSaida+"',H.horarioSaida)= -1) OR (STRCMP('"+horarioSaida+"',H.horarioSaida)= 0) )  "
                    + "         )"
                    + "    )"; 
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                arraylist.add(rs.getInt("PassagemNumAssentoComprado"));
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Nao foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return arraylist;
    }
}
