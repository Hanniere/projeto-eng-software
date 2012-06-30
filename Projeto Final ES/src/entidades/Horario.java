package entidades;

public class Horario implements Cloneable {

    private int HorarioId;
    private int HorarioDiaId;
    private int Horario_RotaItinerarioId;
    private String HorarioSaida;
    private String HorarioChegada;
    private double HorarioPreco; //DECIMAL NOT NULL,
    private int Horario_MotoristaId;
    private int Horario_OnibusId;

    public Horario(int horarioId, int horarioDiaId,
            int horario_RotaItinerarioId, String horarioSaida,
            String horarioChegada, double horarioPreco,
            int horario_MotoristaId, int horario_OnibusId) {

        HorarioId = horarioId;
        HorarioDiaId = horarioDiaId;
        Horario_RotaItinerarioId = horario_RotaItinerarioId;
        HorarioSaida = horarioSaida;
        HorarioChegada = horarioChegada;
        HorarioPreco = horarioPreco;
        Horario_MotoristaId = horario_MotoristaId;
        Horario_OnibusId = horario_OnibusId;
    }

    public Horario() {
    }

    public int getHorarioId() {
        return HorarioId;
    }

    public void setHorarioId(int horarioId) {
        HorarioId = horarioId;
    }

    public int getHorarioDiaId() {
        return HorarioDiaId;
    }

    public void setHorarioDia(int horarioDia) {
        HorarioDiaId = horarioDia;
    }

    public int getHorario_RotaItinerarioId() {
        return Horario_RotaItinerarioId;
    }

    public void setHorario_RotaItinerarioId(int horario_RotaItinerarioId) {
        Horario_RotaItinerarioId = horario_RotaItinerarioId;
    }

    public String getHorarioSaida() {
        return HorarioSaida;
    }

    public void setHorarioSaida(String horarioSaida) {
        HorarioSaida = horarioSaida;
    }

    public String getHorarioChegada() {
        return HorarioChegada;
    }

    public void setHorarioChegada(String horarioChegada) {
        HorarioChegada = horarioChegada;
    }

    public double getHorarioPreco() {
        return HorarioPreco;
    }

    public void setHorarioPreco(double horarioPreco) {
        HorarioPreco = horarioPreco;
    }

    public int getHorario_MotoristaId() {
        return Horario_MotoristaId;
    }

    public void setHorario_MotoristaId(int horario_MotoristaId) {
        Horario_MotoristaId = horario_MotoristaId;
    }

    public int getHorario_OnibusId() {
        return Horario_OnibusId;
    }

    public void setHorario_OnibusId(int horario_OnibusId) {
        Horario_OnibusId = horario_OnibusId;
    }
}
