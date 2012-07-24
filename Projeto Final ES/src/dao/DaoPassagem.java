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

    public int DescobrirCidadeDestino (int horarioId){
        BancoDados banco = new BancoDados();
        int cidadeDestino = 0;
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT Rota_CidadeDestino FROM Rota, RotaItinerario, Horario WHERE RotaId = RotaItinerario_RotaId AND RotaItinerarioId = Horario_RotaItinerarioId AND HorarioId = "+ horarioId+"" ;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                cidadeDestino = rs.getInt("Rota_CidadeDestino");
                System.out.println("Destino: "+cidadeDestino);
            }
        }catch (ClassNotFoundException ex) {
            System.out.println("Nao foi possivel carregar o driver.");
            ex.printStackTrace();
        }catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return cidadeDestino;
    }
    
    public ArrayList<Integer> consultaPoltronasCompradas(int horarioId, String dataCompra, int cidadeDestino) {
        ArrayList<Integer> arraylist = new ArrayList<Integer>();
        BancoDados banco = new BancoDados();
                
        int horarioIdAux = horarioId;
        int cidadeIntermediaria = DescobrirCidadeDestino(horarioId);
        System.out.println("Cidade Intermediaria: "+cidadeIntermediaria);
        
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            if( cidadeIntermediaria == cidadeDestino){
                String sql = "SELECT PassagemNumAssentoComprado FROM Horario INNER JOIN Passagem ON (Passagem_HorarioId = HorarioId) WHERE ( STRCMP(PassagemData, '"+ dataCompra + "') = 0 ) AND ( HorarioId = " + horarioIdAux + " )";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    arraylist.add(rs.getInt("PassagemNumAssentoComprado"));
                }
            }else{
                System.out.println("Entrou no ELSE");
                while(cidadeIntermediaria != cidadeDestino){
                    System.out.println("Entrou no WHILE");
                    String sql = "SELECT PassagemNumAssentoComprado FROM Horario INNER JOIN Passagem ON (Passagem_HorarioId = HorarioId)  WHERE ( STRCMP(PassagemData, '"+ dataCompra + "') = 0 ) AND ( HorarioId = " + horarioIdAux + " )";
                    ResultSet rs = stmt.executeQuery(sql);
                    while(rs.next()){
                        arraylist.add(rs.getInt("PassagemNumAssentoComprado"));
                    }
                    for(int i =0; i<arraylist.size();i++){
                        System.out.println("Array: "+arraylist.get(i));
                    }
                    System.out.println("ID Horario: "+horarioIdAux);
                    horarioIdAux++;
                    cidadeIntermediaria = DescobrirCidadeDestino(horarioIdAux);
                    System.out.println("Nova cidade Intermediaria: "+cidadeIntermediaria );
                }
                System.out.println("Horario:" +horarioIdAux);
                String sql = "SELECT PassagemNumAssentoComprado FROM Horario INNER JOIN Passagem ON (Passagem_HorarioId = HorarioId) WHERE ( STRCMP(PassagemData, '"+ dataCompra + "') = 0 ) AND ( HorarioId = " + horarioIdAux + " )";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    arraylist.add(rs.getInt("PassagemNumAssentoComprado"));
                }
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
        
        
        
        /*ArrayList<Integer> arraylist = new ArrayList<Integer>();
        BancoDados banco = new BancoDados();
        System.out.println("HORARIO ID: " + horarioId + "      DataCompra: " + dataCompra);
   
        System.out.println("Comparacao com 2/1/2012: " + dataCompra.compareTo("2/1/2012") );
       
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Nao foi possivel carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com SQL.");
            ex.printStackTrace();
        }
        return arraylist;*/
}
