import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArvorePessoas {
	private CelulaPessoa raiz;

    public ArvorePessoas(String nomeDoArquivo) {
        this.raiz = null;
        this.lerArquivo(nomeDoArquivo);
    }

    public Boolean arvoreVazia() {
        if (this.raiz == null)
            return true;
        else
            return false;
    }

    private CelulaPessoa adicionar(CelulaPessoa raizArvore, Pessoa pessoaNova) {
        if (raizArvore == null)
            raizArvore = new CelulaPessoa(pessoaNova);
        else
        {
            if (raizArvore.pessoa.getIdentidade() > pessoaNova.getIdentidade())
                raizArvore.esquerda = adicionar(raizArvore.esquerda, pessoaNova);
            else
            {
                if (raizArvore.pessoa.getIdentidade() < pessoaNova.getIdentidade())
                    raizArvore.direita = adicionar(raizArvore.direita, pessoaNova);
                else
                    System.out.println("A pessoa " + pessoaNova.getNome() + " (identidade " + pessoaNova.getIdentidade() + ") jï¿½ foi inserida anteriormente na ï¿½rvore.");
            }
        }
        return raizArvore;
    }
    
    public void adicionar(Pessoa pessoaNova) {
        this.raiz = adicionar(this.raiz, pessoaNova);
    }

    private CelulaPessoa antecessor(CelulaPessoa pessoaRetirar, CelulaPessoa raizArvore) {
        if (raizArvore.direita != null) {
            raizArvore.direita = antecessor(pessoaRetirar, raizArvore.direita);
            return raizArvore;
        } else {
        	pessoaRetirar.pessoa.setIdentidade(raizArvore.pessoa.getIdentidade());
        	pessoaRetirar.pessoa.setNome(raizArvore.pessoa.getNome());
        	pessoaRetirar.pessoa.setSexo(raizArvore.pessoa.getSexo());
        	pessoaRetirar.pessoa.setIdade(raizArvore.pessoa.getIdade());
        	pessoaRetirar.pessoa.setMoradia(raizArvore.pessoa.getMoradia());
        	pessoaRetirar.pessoa.setEstadoCivil(raizArvore.pessoa.getEstadoCivil());
        	pessoaRetirar.pessoa.setCor(raizArvore.pessoa.getCor());
            return raizArvore.esquerda;
        }
    }

    private CelulaPessoa retirar(CelulaPessoa raizArvore, double identidade) {
        if (raizArvore == null) {
        	 System.out.println("a pessoa (identidade " + identidade + ") nï¿½o foi encontrada.");
            return raizArvore;
        } else {
            if (raizArvore.pessoa.getIdentidade() == identidade)
            {
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
            else
            {
                if (raizArvore.pessoa.getIdentidade() > identidade)
                    raizArvore.esquerda = retirar(raizArvore.esquerda, identidade);
                else
                    raizArvore.direita = retirar(raizArvore.direita, identidade);
                return raizArvore;
            }
        }
    }
    
    public void remover(double identidadeRemover) {
        this.raiz = retirar(this.raiz, identidadeRemover);
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
				this.adicionar(pessoa);
			}
		} catch (FileNotFoundException excecao) {
			System.out.println("Arquivo não encontrado.");
		} catch (IOException excecao) {
			System.out.println("Erro na abertura do arquivo de leitura: " + nomeArquivo);
		}
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
        		saida.write(celula.pessoa.getNome() + ";");
        		saida.write(celula.pessoa.getSexo() + ";");
        		saida.write(celula.pessoa.getIdade() + ";");
        		saida.write(celula.pessoa.getMoradia() + ";");
        		saida.write(celula.pessoa.getEstadoCivil() + ";");
        		saida.write(celula.pessoa.getCor() + "\n");
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
}
