
public class CelulaPessoa {
	
	Pessoa dados;	
	CelulaPessoa esquerda;
	CelulaPessoa direita;
	
	public CelulaPessoa(Pessoa registro) {
		dados = registro;
		esquerda = null;
		direita = null;
	}

	public CelulaPessoa getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(CelulaPessoa esquerda) {
		this.esquerda = esquerda;
	}

	public CelulaPessoa getDireita() {
		return direita;
	}

	public void setDireita(CelulaPessoa direita) {
		this.direita = direita;
	}

	public Pessoa getDados() {
		return dados;
	}
}
