package entidades;

public class Passagem {

	private int id;
	private String data;
	private String clienteCpf;
	private String clienteNome;
	private int assentoComprado;
	private int horario;
        private int diaId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
        public int getDiaId() {
		return diaId;
	}
	public void setDiaId(int diaId) {
		this.diaId = diaId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getClienteCpf() {
		return clienteCpf;
	}
	public void setClienteCpf(String clienteCpf) {
		this.clienteCpf = clienteCpf;
	}
	public String getClienteNome() {
		return clienteNome;
	}
	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}
	public int getAssentoComprado() {
		return assentoComprado;
	}
	public void setAssentoComprado(int assentoComprado) {
		this.assentoComprado = assentoComprado;
	}
	public int getHorario() {
		return horario;
	}
	public void setHorario(int horario) {
		this.horario = horario;
	}
	
}
