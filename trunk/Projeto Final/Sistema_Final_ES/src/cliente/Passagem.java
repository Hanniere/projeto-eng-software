package cliente;

public class Passagem {

	private int id;
	private String data;
	private String clienteRg;
	private String clienteNome;
	private int assentoComprado;
	private int horario;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getClienteRg() {
		return clienteRg;
	}
	public void setClienteRg(String clienteRg) {
		this.clienteRg = clienteRg;
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
