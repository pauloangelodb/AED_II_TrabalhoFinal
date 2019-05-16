import java.io.*;
import java.util.Scanner;

public class Aplicacao {

public static void main(String[] args) throws IOException {
	ArvorePessoas lista = new ArvorePessoas("dados.txt");
	Scanner ler = new Scanner(System.in);
	System.out.println("tecla");
	String a = ler.next();
	ler.close();
	System.out.println("pos tecla");
	lista.escreverArquivo("dados.txt");
	
	
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
