package entidades;

public class RotaItinerario {

    private int RotaItinerarioId;
    private int rotaitinerario_rotaId;
    private int rotaitinerario_itinerarioId;
    private int rotaitinerarioOrdem;

    public int getRotaItinerarioId() {
        return RotaItinerarioId;
    }

    public void setRotaItinerarioId(int rotaItinerarioId) {
        RotaItinerarioId = rotaItinerarioId;
    }

    public int getRotaitinerario_rotaId() {
        return rotaitinerario_rotaId;
    }

    public void setRotaitinerario_rotaId(int rotaitinerario_rotaId) {
        this.rotaitinerario_rotaId = rotaitinerario_rotaId;
    }

    public int getRotaitinerario_itinerarioId() {
        return rotaitinerario_itinerarioId;
    }

    public void setRotaitinerario_itinerarioId(int rotaitinerario_itinerarioId) {
        this.rotaitinerario_itinerarioId = rotaitinerario_itinerarioId;
    }

    public int getRotaitinerarioOrdem() {
        return rotaitinerarioOrdem;
    }

    public void setRotaitinerarioOrdem(int rotaitinerarioOrdem) {
        this.rotaitinerarioOrdem = rotaitinerarioOrdem;
    }
}
