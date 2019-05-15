import java.io.*;

public class Aplicacao {

public static void main(String[] args) throws IOException {
	
		String dados[] = new String [7];
		String pessoa;
		ArquivoTextoLeitura censo = new ArquivoTextoLeitura();

		censo.abrirArquivo("dados ibge pequeno.txt");
		
		while (!censo.equals(null)) {
			pessoa = censo.ler();
			dados = pessoa.split(";");
			System.out.print(dados[1]);	
		}		
	}
}
