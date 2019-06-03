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
	private int[] estatistica = new int[28];
	private int[] desvio = null;

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
				
				ColetaDados(celula.pessoa);
				
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
	
	public int[] buscaDesvioIdade(String estadoCivil, CelulaPessoa celula, int pos) {

		int novaPos = pos;
		if (celula.esquerda != null) {
			buscaDesvioIdade(estadoCivil, celula.esquerda, novaPos);
		}
		if (celula != null) {
			if(celula.pessoa.getEstadoCivil().toUpperCase() == estadoCivil.toUpperCase()){
				desvio[pos] = celula.pessoa.getIdade();
				novaPos++;
			}
		}
		if (celula.direita != null) {
			buscaDesvioIdade(estadoCivil, celula.direita, novaPos);
		}
		return desvio;
	}	

	public int[] getEstatistica() {
		return estatistica;
	}

	public void setEstatistica(int[] estatistica) {
		this.estatistica = estatistica;
	}

	private void ColetaDados(Pessoa pessoa) {
		/* Referencia:
		 * 
		 * estat[0] = total geral
		 * estat[1] = total feminino
		 * estat[2] = total masculino
		 * estat[3] = total solteiros
		 * estat[4] = total casados
		 * estat[5] = total divorciados
		 * estat[6] = total viuvos
		 * estat[7] = total urbanos
		 * estat[8] = total rurais
		 * estat[9] = total 0 a 12
		 * estat[10] = total 13 a 19
		 * estat[11] = total 20 a 25
		 * estat[12] = total 26 a 30
		 * estat[13] = total 31 a 45
		 * estat[14] = total 46 a 65
		 * estat[15] = total 65 mais
		 * estat[16] = total pardos
		 * estat[17] = total pretos
		 * estat[18] = total brancos
		 * estat[19] = total amarelos
		 * estat[20] = total indigenas
		 * 
		 * estat[21] = soma idade geral
		 * estat[22] = soma idade femininos
		 * estat[23] = soma idade masculinos
		 * estat[24] = soma idade urbanos
		 * estat[25] = soma idade rurais
		 * estat[26] = soma idade solteiros
		 * estat[27] = soma idade casados
		 */
		
		this.estatistica[0] ++;
		
		if(pessoa.getSexo() == 'F') {
			this.estatistica[1] ++;
			this.estatistica[22] += pessoa.getIdade();
		}
		else if(pessoa.getSexo() == 'M') {
			this.estatistica[2] ++;
			this.estatistica[23] += pessoa.getIdade();
		}

		if(pessoa.getEstadoCivil().toUpperCase().equals("SOLTEIRO")) {
			this.estatistica[3] ++;
			this.estatistica[26] += pessoa.getIdade();
		}
		else if(pessoa.getEstadoCivil().toUpperCase().equals("CASADO")) {
			this.estatistica[4] ++;
			this.estatistica[27] += pessoa.getIdade();
		}
		else if(pessoa.getEstadoCivil().toUpperCase().equals("DIVORCIADO"))
			this.estatistica[5] ++;
		else if(pessoa.getEstadoCivil().toUpperCase().equals("VIÚVO"))
			this.estatistica[6] ++;

		if(pessoa.getMoradia().toUpperCase().equals("URBANA")) {
			this.estatistica[7] ++;
			this.estatistica[24] += pessoa.getIdade();
		}
		else if(pessoa.getMoradia().toUpperCase().equals("RURAL")) {
			this.estatistica[8] ++;
			this.estatistica[25] += pessoa.getIdade();
		}

		if(pessoa.getIdade() <= 12)
			this.estatistica[9] ++;
		else if(pessoa.getIdade() >= 13 && pessoa.getIdade() <= 19)
			this.estatistica[10] ++;
		else if(pessoa.getIdade() >= 20 && pessoa.getIdade() <= 25)
			this.estatistica[11] ++;
		else if(pessoa.getIdade() >= 26 && pessoa.getIdade() <= 30)
			this.estatistica[12] ++;
		else if(pessoa.getIdade() >= 31 && pessoa.getIdade() <= 45)
			this.estatistica[13] ++;
		else if(pessoa.getIdade() >= 46 && pessoa.getIdade() <= 65)
			this.estatistica[14] ++;
		else if(pessoa.getIdade() > 65)
			this.estatistica[15] ++;

		if(pessoa.getCor().toUpperCase().equals("PARDA"))
			this.estatistica[16] ++;
		else if(pessoa.getCor().toUpperCase().equals("PRETA"))
			this.estatistica[17] ++;
		else if(pessoa.getCor().toUpperCase().equals("BRANCA"))
			this.estatistica[18] ++;
		else if(pessoa.getCor().toUpperCase().equals("AMARELA"))
			this.estatistica[19] ++;
		else if(pessoa.getCor().toUpperCase().equals("INDIGENA"))
			this.estatistica[20] ++;		

		this.estatistica[21] += pessoa.getIdade();
	}

	public int[] Dados() {
		//int [] estatistica = new int [20];
		// TODO Auto-generated method stub
		return this.estatistica;
	}
//
//	public int[] DadosEstatistico() {
//		int [] estatistica = new int [9];
//		// TODO Auto-generated method stub
//		return estatistica;
//	}

	public void limparColeta() {
		
		for(int i=0; i < this.estatistica.length; i++) {
			this.estatistica[i] = 0;
		}		
	}
}
