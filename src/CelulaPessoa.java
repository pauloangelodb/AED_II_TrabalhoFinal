
public class CelulaPessoa {
	
	Pessoa pessoa;	
	CelulaPessoa esquerda;
	CelulaPessoa direita;
	
	public CelulaPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		esquerda = null;
		direita = null;
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

	public Pessoa getDados() {
		return this.pessoa;
	}
}
