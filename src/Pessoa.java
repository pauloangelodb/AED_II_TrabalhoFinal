
public class Pessoa {
	private double identidade;
	private int idade;
	private char sexo;
	private String nome, moradia, estadocivil, raca;
	
	public Pessoa (int identidade, String nome, char sexo, int idade, String moradia, String estadocivil, String raca) {
		this.identidade = identidade;
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
		this.moradia = moradia;
		this.estadocivil = estadocivil;
		this.raca = raca;
	}

	public double getIdentidade() {
		return identidade;
	}

	public void setIdentidade(double identidade) {
		this.identidade = identidade;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMoradia() {
		return moradia;
	}

	public void setMoradia(String moradia) {
		this.moradia = moradia;
	}

	public String getEstadoCivil() {
		return estadocivil;
	}

	public void setEstadoCivil(String estadocivil) {
		this.estadocivil = estadocivil;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

}
