import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArvorePessoas {
	private CelulaPessoa raiz;

	String nomeDoArquivo;

	public ArvorePessoas(String nomeDoArquivo) {
		this.raiz = null;
		this.nomeDoArquivo = nomeDoArquivo;
		this.lerArquivo(nomeDoArquivo);
	}

	public CelulaPessoa getRaiz() {
		return this.raiz;
	}

	public Boolean arvoreVazia() {
		if (this.raiz == null)
			return true;
		else
			return false;
	}

	private int alturaCelula(CelulaPessoa celulaPessoa) {
		return celulaPessoa == null ? -1 : celulaPessoa.altura;
	}

	private int alturaMax( int alturaEsqueda, int alturaDireira ) {
		return alturaEsqueda > alturaDireira ? alturaEsqueda : alturaDireira;
	}

	private int fatorBalanceamento (CelulaPessoa celulaPessoa) {
		return this.alturaCelula(celulaPessoa.esquerda) - this.alturaCelula(celulaPessoa.direita);
	}

	public CelulaPessoa balanceamento (CelulaPessoa celulaPessoa) {
		if (fatorBalanceamento(celulaPessoa) == 2) {
			if (fatorBalanceamento (celulaPessoa.esquerda) > 0)
				celulaPessoa = rotacaoSimplesDireita(celulaPessoa);
			else
				celulaPessoa = rotacaoDuplaDireita(celulaPessoa);
		}
		else if (fatorBalanceamento(celulaPessoa) == -2) {
			if (fatorBalanceamento(celulaPessoa.direita) < 0)
				celulaPessoa = rotacaoSimplesEsquerda(celulaPessoa);
			else
				celulaPessoa = rotacaoDuplaEsquerda(celulaPessoa);
		}
		celulaPessoa.altura = alturaMax(alturaCelula(celulaPessoa.esquerda), alturaCelula(celulaPessoa.direita)) + 1;
		return celulaPessoa;
	}

	private CelulaPessoa rotacaoSimplesDireita(CelulaPessoa celulaRaiz) {
		CelulaPessoa novaRaiz = celulaRaiz.esquerda;
		celulaRaiz.esquerda = novaRaiz.direita;
		novaRaiz.direita = celulaRaiz;

		celulaRaiz.altura = alturaMax(alturaCelula(celulaRaiz.esquerda), alturaCelula(celulaRaiz.direita)) + 1;
		novaRaiz.altura = alturaMax(alturaCelula(novaRaiz.esquerda), alturaCelula(novaRaiz.direita)) + 1;
		return novaRaiz;
	}

	private CelulaPessoa rotacaoSimplesEsquerda( CelulaPessoa celulaRaiz ) {
		CelulaPessoa novaRaiz = celulaRaiz.direita;
		celulaRaiz.direita = novaRaiz.esquerda;
		novaRaiz.esquerda = celulaRaiz;

		celulaRaiz.altura = alturaMax(alturaCelula(celulaRaiz.esquerda), alturaCelula(celulaRaiz.direita)) + 1;
		novaRaiz.altura = alturaMax(alturaCelula(novaRaiz.esquerda), alturaCelula(novaRaiz.direita)) + 1;
		return novaRaiz;
	}

	private CelulaPessoa rotacaoDuplaDireita(CelulaPessoa celulaPessoa) {
		celulaPessoa.esquerda = rotacaoSimplesEsquerda(celulaPessoa.esquerda);
		return rotacaoSimplesDireita(celulaPessoa);
	}

	private CelulaPessoa rotacaoDuplaEsquerda(CelulaPessoa celulaPessoa) {
		celulaPessoa.direita = rotacaoSimplesDireita(celulaPessoa.direita);
		return rotacaoSimplesEsquerda(celulaPessoa);
	}

	private CelulaPessoa adicionar(CelulaPessoa raizArvore, Pessoa pessoaNova) {
		if (raizArvore == null)
			raizArvore = new CelulaPessoa(pessoaNova);
		else if (raizArvore.pessoa.getIdentidade() > pessoaNova.getIdentidade()) {
			raizArvore.esquerda = adicionar(raizArvore.esquerda, pessoaNova);
		}
		else if (raizArvore.pessoa.getIdentidade() < pessoaNova.getIdentidade()) {
			raizArvore.direita = adicionar(raizArvore.direita, pessoaNova);
		}
		else {
			System.out.println("A pessoa " + pessoaNova.getNome() +
					" (identidade " + pessoaNova.getIdentidade() +
					") já foi inserida anteriormente na árvore.");
		}
		return this.balanceamento(raizArvore);
	}

	public void adicionar(Pessoa pessoaNova) {
		this.raiz = adicionar(this.raiz, pessoaNova);
		this.escreverArquivo();
	}
	
	private void adicionarInterno(Pessoa pessoaNova) {
		this.raiz = adicionar(this.raiz, pessoaNova);
	}

	private CelulaPessoa antecessor(CelulaPessoa pessoaRetirar, CelulaPessoa raizArvore) {
		if (raizArvore.direita != null) {
			raizArvore.direita = antecessor(pessoaRetirar, raizArvore.direita);
			return raizArvore;
		} else {
			pessoaRetirar.pessoa.setIdentidade(raizArvore.pessoa.getIdentidade());
			pessoaRetirar.pessoa.setNome(raizArvore.pessoa.getNome().toUpperCase());
			pessoaRetirar.pessoa.setSexo(raizArvore.pessoa.getSexo());
			pessoaRetirar.pessoa.setIdade(raizArvore.pessoa.getIdade());
			pessoaRetirar.pessoa.setMoradia(raizArvore.pessoa.getMoradia().toUpperCase());
			pessoaRetirar.pessoa.setEstadoCivil(raizArvore.pessoa.getEstadoCivil().toUpperCase());
			pessoaRetirar.pessoa.setCor(raizArvore.pessoa.getCor().toUpperCase());
			return raizArvore.esquerda;
		}
	}

	private Pessoa localizar(CelulaPessoa celula, long identidade) {
		if (celula == null) {
			return null;
		}
		if (celula.pessoa.getIdentidade() == identidade) {
			return celula.pessoa;
		}
		else if (identidade < celula.pessoa.getIdentidade()) {
			return this.localizar(celula.esquerda, identidade);
		}
		else if (identidade > celula.pessoa.getIdentidade()) {
			return this.localizar(celula.direita, identidade);
		}
		return null;
	}

	public Pessoa localizar(long identidade) {
		return this.localizar(this.raiz, identidade);
	}
	
	public void atualizar() {
		this.escreverArquivo();
	}
	
	private CelulaPessoa remover(CelulaPessoa raizArvore, double identidade) {
		if (raizArvore == null) {
			System.out.println("A pessoa (identidade " + identidade + ") não foi encontrada.");
			return raizArvore;
		} else {
			if (raizArvore.pessoa.getIdentidade() == identidade) {
				if (raizArvore.direita == null)
					return (raizArvore.esquerda);
				else {
					if (raizArvore.esquerda == null)
						return (raizArvore.direita);
					else {
						raizArvore.esquerda = antecessor(raizArvore, raizArvore.esquerda);
						return (raizArvore);
					}
				}
			}
			else {
				if (raizArvore.pessoa.getIdentidade() > identidade)
					raizArvore.esquerda = remover(raizArvore.esquerda, identidade);
				else
					raizArvore.direita = remover(raizArvore.direita, identidade);
				return raizArvore;
			}
		}
	}

	public void remover(double identidadeRemover) {
		this.raiz = remover(this.raiz, identidadeRemover);
		this.escreverArquivo();
	}

	private void lerArquivo(String nomeArquivo) {
		try (BufferedReader entrada = new BufferedReader(new FileReader(nomeArquivo))){
			String dados = "";
			while((dados = entrada.readLine()) != null) {
				String dadosSplit[] = dados.split(";");
				Pessoa pessoa = new Pessoa(
						Long.parseLong(dadosSplit[0]),
						dadosSplit[1],
						dadosSplit[2].charAt(0), 
						Integer.parseInt(dadosSplit[3]),
						dadosSplit[4],
						dadosSplit[5],
						dadosSplit[6]);
				this.adicionarInterno(pessoa);
			}
		} catch (FileNotFoundException excecao) {
			System.out.println("Arquivo não encontrado.");
		} catch (IOException excecao) {
			System.out.println("Erro na abertura do arquivo de leitura: " + nomeArquivo);
		}
	}

	void escreverArquivo() {
		this.escreverArquivo(this.nomeDoArquivo);
	}

	public void escreverArquivo(String nomeArquivo) {
		File file = new File(nomeArquivo);
		if(file.exists())
			file.delete();
		percorrerEmOrdem(this.raiz, nomeArquivo);
	}

	private void percorrerEmOrdem(CelulaPessoa celula, String nomeArquivo) {
		if (celula.esquerda != null) {
			percorrerEmOrdem(celula.esquerda, nomeArquivo);
		}
		if (celula != null) {
			try (BufferedWriter saida = new BufferedWriter(new FileWriter(nomeArquivo, true))){
				saida.write(Long.toString(celula.pessoa.getIdentidade()) + ";");
				saida.write(celula.pessoa.getNome().toUpperCase() + ";");
				saida.write(celula.pessoa.getSexo() + ";");
				saida.write(celula.pessoa.getIdade() + ";");
				saida.write(celula.pessoa.getMoradia().toUpperCase() + ";");
				saida.write(celula.pessoa.getEstadoCivil().toUpperCase() + ";");
				saida.write(celula.pessoa.getCor().toUpperCase() + "\n");
			} catch (FileNotFoundException excecao) {
				System.out.println("Arquivo não encontrado.");
			} catch (IOException excecao) {
				System.out.println("Erro no fechamento do arquivo de escrita: " + excecao);	
			}
		}
		if (celula.direita != null) {
			percorrerEmOrdem(celula.direita, nomeArquivo);
		}
	}
	
	private int cont(CelulaPessoa celula , int cont) {
		if (celula.esquerda != null) {
			cont = this.cont(celula.esquerda, cont);
		}
		if (celula != null) {
			cont++;
		}
		if (celula.direita != null) {
			cont = this.cont(celula.direita, cont);
		}
		return cont;
	}
	
	private int toList(CelulaPessoa celula, Pessoa[] pessoas, int cont) {
		if (celula.esquerda != null) {
			cont = this.toList(celula.esquerda, pessoas, cont);
		}
		if (celula != null) {
			pessoas[cont++] = celula.pessoa;
		}
		if (celula.direita != null) {
			cont = this.toList(celula.direita, pessoas, cont);
		}
		return cont;
	}
	
	public Pessoa[] toList() {
		Pessoa[] pessoas = new Pessoa[cont(raiz, 0)];
		toList(raiz, pessoas, 0);
		return pessoas;
	}
}
