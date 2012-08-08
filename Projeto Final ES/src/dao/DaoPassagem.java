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
        
    public ArrayList<ArrayList<String>> consultarTodasPassagensDoDia(String data) {
        BancoDados banco = new BancoDados();
        ArrayList<ArrayList<String>> arraylist = new ArrayList<ArrayList<String>>();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getStr_conn(), banco.getUsuario(), banco.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT P.PassagemData, P.PassagemClienteCpf, P.PassagemClienteNome,"
                    + "P.PassagemNumAssentoComprado, H.horarioSaida, H.horarioChegada, H.horarioPreco,"
                    + "Co.cidadeNome, Eo.estadoUF, Cd.cidadeNome, Ed.estadoUF, O.onibusPlaca, M.motoristaNome,"
                    + " I.ItinerarioId"
                    + " FROM Passagem P, Horario H, Onibus O, RotaItinerario RI, Itinerario I,  Rota R, Cidade Co, Cidade Cd,"
                    + "Estado Eo, Estado Ed, Motorista M"
                    + " WHERE PassagemData = '"+data+"'"
                    + " AND P.passagem_HorarioId = H.horarioId"
                    + " AND H.horario_rotaItinerarioId = RI.rotaItinerarioId"
                    + " AND RI.rotaItinerario_rotaId = R.rotaId"
                    + " AND RI.rotaItinerario_ItinerarioId = I.ItinerarioId"
                    + " AND R.rota_cidadeOrigem = Co.cidadeId"
                    + " AND Co.cidade_estadoId = Eo.estadoId"
                    + " AND R.rota_cidadeDestino = Cd.cidadeId"
                    + " AND Cd.cidade_estadoId = Ed.estadoId"
                    + " AND H.horario_motoristaId = M.motoristaId"
                    + " AND H.horario_onibusId = O.onibusId"
                    + " ORDER BY I.ItinerarioId, P.PassagemClienteCpf, H.horarioSaida";

            //String sql = "select * from Passagem where PassagemData = '" + data + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ArrayList<String> passagem = new ArrayList<String>();
                passagem.add(rs.getString("P.PassagemData"));
                passagem.add(rs.getString("P.PassagemClienteCpf"));
                passagem.add(rs.getString("P.PassagemClienteNome"));
                passagem.add(rs.getString("P.PassagemNumAssentoComprado"));
                passagem.add(rs.getString("H.horarioSaida"));
                passagem.add(rs.getString("H.horarioChegada"));
                passagem.add(rs.getString("H.horarioPreco"));
                passagem.add(rs.getString("Co.cidadeNome"));
                passagem.add(rs.getString("Eo.estadoUF"));
                passagem.add(rs.getString("Cd.cidadeNome"));
                passagem.add(rs.getString("Ed.estadoUF"));
                passagem.add(rs.getString("O.onibusPlaca"));
                passagem.add(rs.getString("M.motoristaNome"));
                passagem.add(rs.getString("I.ItinerarioId"));
                arraylist.add(passagem);
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
