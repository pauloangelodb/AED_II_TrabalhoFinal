
public class Pessoa {
	private long identidade;
	private int idade;
	private char sexo;
	private String nome, moradia, estadocivil, cor;
	
	public Pessoa (long identidade, String nome, char sexo, int idade, String moradia, String estadocivil, String cor) {
		this.setIdentidade(identidade);
		this.setNome(nome);
		this.setSexo(sexo);
		this.setIdade(idade);
		this.setMoradia(moradia);
		this.setEstadoCivil(estadocivil);
		this.setCor(cor);
	}
	
	public long getIdentidade() {
		return identidade;
	}

	public void setIdentidade(long identidade) {
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

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

}
