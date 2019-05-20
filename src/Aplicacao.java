
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
				
			case 0:
				key = -1;
			break;
			
			default:
				break;
			}
		}
		
		ler.close();

		//		CelulaPessoa novaPessoa;
		//		ArvorePessoas pessoas = new ArvorePessoas();
		//		
		//		String dados[] = new String [7];
		//		String pessoa;
		//		ArquivoTextoLeitura censo = new ArquivoTextoLeitura();
		//
		//		censo.abrirArquivo("dados ibge pequeno.txt");
		//		
		//		while (!censo.equals(null)) {
		//			pessoa = censo.ler();
		//			dados = pessoa.split(";");
		//			System.out.print(dados[1]);	
		//		}	
	}
}
