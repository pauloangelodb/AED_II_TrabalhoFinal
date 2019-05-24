
public class CelulaPessoa {
	
	Pessoa pessoa;
	int altura;
	CelulaPessoa esquerda, direita;
	
	
	public CelulaPessoa(Pessoa pessoa) {
		this(pessoa, null, null, 0);
	}
	
	public CelulaPessoa(Pessoa pessoa, CelulaPessoa esquerda, CelulaPessoa direita, int altura) {
		this.pessoa = pessoa;
		this.esquerda = esquerda;
		this.direita = direita;
		this.altura = altura;
	}

	public CelulaPessoa getEsquerda() {
		return this.esquerda;
	}

	public void setEsquerda(CelulaPessoa esquerda) {
		this.esquerda = esquerda;
	}

	public CelulaPessoa getDireita() {
		return this.direita;
	}

	public void setDireita(CelulaPessoa direita) {
		this.direita = direita;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public Pessoa getDados() {
		return this.pessoa;
	}
}
