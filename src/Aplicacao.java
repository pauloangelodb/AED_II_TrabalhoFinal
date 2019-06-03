
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Aplicacao {

	public static void main(String[] args) {
		ArvorePessoas lista = new ArvorePessoas("dados.txt");
		Scanner ler = new Scanner(System.in);
		int key = 0;
		while (key != -1) {
			try {
				key = Integer.parseInt(JOptionPane.showInputDialog(null,
						"1 - Localizar" + "\n" +
						"2 - Incluir" + "\n" +
						"3 - Excluir" + "\n" +
						"4 - Alterar" + "\n" +
						"5 - Exibir Relatório de Categorização" + "\n" +
						"6 - Exibir Relatório Estatístico" + "\n" +
						"0 - Sair" + "\n"
						, "Menu", 1));
			} catch (NumberFormatException e) {
				lista.escreverArquivo();
				key = -1;
			}
			
			long identidade = 0;
			Pessoa pessoa = null;
			switch (key) {
			case 1:
				try {
					identidade = Long.parseLong(JOptionPane.showInputDialog(null, "Identidade:", "Localizar", 1));
				} catch (NumberFormatException e) {
				}
				pessoa = lista.localizar(identidade);
				if (pessoa == null) {
					JOptionPane.showMessageDialog(null, "Não Encontrado.", null, 0);
				}else {
					JOptionPane.showMessageDialog(null, 
							pessoa.getNome() + "\n" +
							" Identidade: " + pessoa.getIdentidade() + "\n" +
							"Sexo: " + pessoa.getSexo() + "\n" +
							"Idade: " + pessoa.getIdade() + "\n" +
							"Moradia: " + pessoa.getMoradia() + "\n" +
							"Estado Civil: " + pessoa.getEstadoCivil() + "\n" +
							"Cor: " + pessoa.getCor()
							, null, 1);
				}
				break;

			case 2:
				lista.adicionar(new Pessoa(
						Long.parseLong(JOptionPane.showInputDialog(null, "Identidade:", "Criar Cadastro", 1)),
						JOptionPane.showInputDialog(null, "Nome:", "Criar Cadastro", 1),
						JOptionPane.showInputDialog(null, "Sexo (M/F):", "Criar Cadastro", 1).charAt(0),
						Integer.parseInt(JOptionPane.showInputDialog(null, "Idade:", "Criar Cadastro", 1)),
						JOptionPane.showInputDialog(null, "Moradia:", "Criar Cadastro", 1),
						JOptionPane.showInputDialog(null, "Estado Civil:", "Criar Cadastro", 1),
						JOptionPane.showInputDialog(null, "Cor:", "Criar Cadastro", 1)));
				lista.escreverArquivo();
				break;
				
			case 3:
				lista.remover(Long.parseLong(JOptionPane.showInputDialog(null, "Identidade:", "Excluir Cadastro", 1)));
				lista.escreverArquivo();
				break;
				
			case 4:
				try {
					identidade = Long.parseLong(JOptionPane.showInputDialog(null, "Identidade:", "Localizar", 1));
				} catch (NumberFormatException e) {
				}
				pessoa = lista.localizar(identidade);
				if (pessoa == null) {
					JOptionPane.showMessageDialog(null, "Não Encontrado.", null, 0);
				}else {
					int keyInterno = 0;
					try {
						keyInterno = Integer.parseInt(JOptionPane.showInputDialog(null,
								"1 - Identidade" + "\n" +
								"2 - Nome" + "\n" +
								"3 - Sexo" + "\n" +
								"4 - Idade" + "\n" +
								"5 - Moradia" + "\n" +
								"6 - Estado Civil" + "\n" +
								"7 - Cor" + "\n"
								, "Alterações", 1));
					} catch (NumberFormatException e) {
					}
					switch (keyInterno) {
					case 1:
						pessoa.setIdentidade(Integer.parseInt(JOptionPane.showInputDialog(null, "Nova Identidade:")));
						break;
					case 2:
						pessoa.setNome(JOptionPane.showInputDialog(null, "Novo Nome:"));
						break;
					case 3:
						pessoa.setSexo(JOptionPane.showInputDialog(null, "Sexo(M/F):").charAt(0));
						break;
					case 4:
						pessoa.setIdade(Integer.parseInt(JOptionPane.showInputDialog(null, "Idade:")));
						break;
					case 5:
						pessoa.setMoradia(JOptionPane.showInputDialog(null, "Moradia:"));
						break;
					case 6:
						pessoa.setEstadoCivil(JOptionPane.showInputDialog(null, "Estado Civil:"));
						break;
					case 7:
						pessoa.setCor(JOptionPane.showInputDialog(null, "Cor:"));
						break;

					default:
						break;
					}
					JOptionPane.showMessageDialog(null, 
							pessoa.getNome() + "\n" +
							" Identidade: " + pessoa.getIdentidade() + "\n" +
							"Sexo: " + pessoa.getSexo() + "\n" +
							"Idade: " + pessoa.getIdade() + "\n" +
							"Moradia: " + pessoa.getMoradia() + "\n" +
							"Estado Civil: " + pessoa.getEstadoCivil() + "\n" +
							"Cor: " + pessoa.getCor()
							, null, 1);
					lista.escreverArquivo();
				}
				break;
				
			case 5:
				RelatorioCategoria(lista);
				break;
				
			case 6:
				RelatorioEstatistico(lista);
				break;
				
			case 0:
				key = -1;
				break;
			
			default:
				break;
			}
		}
		
		ler.close();
	}

	static void RelatorioCategoria(ArvorePessoas lista) {
		lista.limparColeta();
		lista.escreverArquivo();
		int[] dados = lista.Dados();
		
		JOptionPane.showMessageDialog(null, 
				"\n------------------------------------------------------------------------------------------\n" +
				"\nRELATÓRIO DE CATEGORIZAÇÃO\n" +
				"\n------------------------------------------------------------------------------------------\n" +
				"\nPopulação total: " + dados[0] + "\n" +
				"\n------------------------------------------------------------------------------------------\n" +	
				"\nCaracterização\nSexo\n" +
				"Feminino: " + dados[1] + " (" + porcentagem(dados[1], dados[0]) + "%)" + "\n" +
				"Masculino: " + dados[2] + " (" + porcentagem(dados[2], dados[0]) + "%)" + "\n" +
				"\n------------------------------------------------------------------------------------------\n" +		
				"\nEstado Civil\n" +
				"Solteiro(a): " + dados[3] + " (" + porcentagem(dados[3], dados[0]) + "%)" + "\n" +
				"Casado(a): " + dados[4] + " (" + porcentagem(dados[4], dados[0]) + "%)" + "\n" +
				"Divorciado(a): " + dados[5] + " (" + porcentagem(dados[5], dados[0]) + "%)" + "\n" +
				"Viúvo(a): " + dados[6] + " (" + porcentagem(dados[6], dados[0]) + "%)" + "\n" +
				"\n------------------------------------------------------------------------------------------\n" +	
				"\nMoradia\n" +
				"Rural: " + dados[7] + " (" + porcentagem(dados[7], dados[0]) + "%)" + "\n" +
				"Urbana: " + dados[8] + " (" + porcentagem(dados[8], dados[0]) + "%)" + "\n" +
				"\n-----------------------------------------------------------------------------------------\n" +		
				"\nFaixa Etária\n" +
				" 0 a 12 anos: " + dados[9] + " (" + porcentagem(dados[9], dados[0]) + "%)" + "\n" +
				"13 a 19 anos: " + dados[10] + " (" + porcentagem(dados[10], dados[0]) + "%)" + "\n" +
				"20 a 25 anos: " + dados[11] + " (" + porcentagem(dados[11], dados[0]) + "%)" + "\n" +
				"26 a 30 anos: " + dados[12] + " (" + porcentagem(dados[12], dados[0]) + "%)" + "\n" +
				"31 a 45 anos: " + dados[13] + " (" + porcentagem(dados[13], dados[0]) + "%)" + "\n" +
				"46 a 65 anos: " + dados[14] + " (" + porcentagem(dados[14], dados[0]) + "%)" + "\n" +
				"Mais de 65 anos: " + dados[15] + " (" + porcentagem(dados[15], dados[0]) + "%)" + "\n" +
				"\n------------------------------------------------------------------------------------------\n" +	
				"\nCor\n" +
				"Parda: " + dados[16] + " (" + porcentagem(dados[16], dados[0]) + "%)" + "\n" +
				"Preta: " + dados[17] + " (" + porcentagem(dados[17], dados[0]) + "%)" + "\n" +
				"Branca: " + dados[18] + " (" + porcentagem(dados[18], dados[0]) + "%)" + "\n" +
				"Amarela: " + dados[19] + " (" + porcentagem(dados[19], dados[0]) + "%)" + "\n" +
				"Indígena: " + dados[20] + " (" + porcentagem(dados[20], dados[0]) + "%)" + "\n" +
				"------------------------------------------------------------------------------------------\n\n"
				, null, 1);
	}
	
	static float porcentagem (int parcela, int total) {
	 	float real = ((float)parcela/(float)total) * 100;
	 	int arredonda = (int)(real *100);
	 	
	 	return (float)arredonda/ 100;
	}

	static float media(int somatorio, int total) {
		float real = (float)somatorio/(float)total;
	 	int arredonda = (int)(real *100);
	 	
	 	return (float)arredonda/ 100;
	}

	static float desvioPadrao(int somatorio, int total) {
		float desvio = 0;
		return desvio;
	}

	static void RelatorioEstatistico(ArvorePessoas lista) {
		lista.limparColeta();
		lista.escreverArquivo();
		int[] dados = lista.Dados();
		
		JOptionPane.showMessageDialog(null, 
				"\n------------------------------------------------------------------------------------------\n" +
				"\nRELATÓRIO ESTATÍSTICO\n" +
				"\n------------------------------------------------------------------------------------------\n" +
				"\nMédia de idade geral: " + media(dados[21],dados[0]) + " anos\n" +
				"\n------------------------------------------------------------------------------------------\n" +	
				"\nCategorizada por sexo:\n" +
				"Feminino: " + media(dados[22],dados[1]) + " anos" + "                 Masculino: " + media(dados[23],dados[2]) + " anos\n" +
				"\n------------------------------------------------------------------------------------------\n" +		
				"\nCategorizada por moradia:\n" +
				"Rural: " + media(dados[25],dados[8]) + " anos" + "                    Urbana: " + media(dados[24],dados[7]) + " anos\n" +
				"\n------------------------------------------------------------------------------------------\n" +	
				"\nSolteiros e casados\n" +
				"                       Média de idade        Desvio padrão\n" +
				"Solteiros:   " + media(dados[26],dados[3]) + " anos" + "                        " + desvioPadrao(dados[26],dados[3]) + " anos\n" +
				"Casados:    " + media(dados[27],dados[4]) + " anos" + "                        " + " anos\n" +
				"------------------------------------------------------------------------------------------\n\n"
				, null, 1);
	}
}
