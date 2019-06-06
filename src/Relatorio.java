
public class Relatorio {

	public Relatorio() {
	}

	public int[] relatorioCategoria(Pessoa[] lista) {
		int[] estatistica = new int[30];

		for (Pessoa pessoa : lista) {
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

			estatistica[0] ++;

			if(pessoa.getSexo() == 'F') {
				estatistica[1] ++;
				estatistica[22] += pessoa.getIdade();
			}
			else if(pessoa.getSexo() == 'M') {
				estatistica[2] ++;
				estatistica[23] += pessoa.getIdade();
			}

			if(pessoa.getEstadoCivil().toUpperCase().equals("SOLTEIRO")) {
				estatistica[3] ++;
				estatistica[26] += pessoa.getIdade();
			}
			else if(pessoa.getEstadoCivil().toUpperCase().equals("CASADO")) {
				estatistica[4] ++;
				estatistica[27] += pessoa.getIdade();
			}
			else if(pessoa.getEstadoCivil().toUpperCase().equals("DIVORCIADO"))
				estatistica[5] ++;
			else if(pessoa.getEstadoCivil().toUpperCase().equals("VIÚVO"))
				estatistica[6] ++;

			if(pessoa.getMoradia().toUpperCase().equals("URBANA")) {
				estatistica[7] ++;
				estatistica[24] += pessoa.getIdade();
			}
			else if(pessoa.getMoradia().toUpperCase().equals("RURAL")) {
				estatistica[8] ++;
				estatistica[25] += pessoa.getIdade();
			}

			if(pessoa.getIdade() <= 12)
				estatistica[9] ++;
			else if(pessoa.getIdade() >= 13 && pessoa.getIdade() <= 19)
				estatistica[10] ++;
			else if(pessoa.getIdade() >= 20 && pessoa.getIdade() <= 25)
				estatistica[11] ++;
			else if(pessoa.getIdade() >= 26 && pessoa.getIdade() <= 30)
				estatistica[12] ++;
			else if(pessoa.getIdade() >= 31 && pessoa.getIdade() <= 45)
				estatistica[13] ++;
			else if(pessoa.getIdade() >= 46 && pessoa.getIdade() <= 65)
				estatistica[14] ++;
			else if(pessoa.getIdade() > 65)
				estatistica[15] ++;

			if(pessoa.getCor().toUpperCase().equals("PARDA"))
				estatistica[16] ++;
			else if(pessoa.getCor().toUpperCase().equals("PRETA"))
				estatistica[17] ++;
			else if(pessoa.getCor().toUpperCase().equals("BRANCA"))
				estatistica[18] ++;
			else if(pessoa.getCor().toUpperCase().equals("AMARELA"))
				estatistica[19] ++;
			else if(pessoa.getCor().toUpperCase().equals("INDIGENA"))
				estatistica[20] ++;		

			estatistica[21] += pessoa.getIdade();
		}
		return estatistica;
	}

	public int[] relatorioEstatistico(Pessoa[] lista) {
		int[] estatistica = relatorioCategoria(lista);
		double somaSolteiro = 0;
		double somaCasado = 0;
		double mediaIdadeSolteiro = (double)estatistica[26] / (double)estatistica[3];
		double mediaIdadeCasado = (double)estatistica[27] / (double)estatistica[4];

		for (Pessoa pessoa : lista) {
			if (pessoa.getEstadoCivil().toUpperCase().equals("SOLTEIRO"))
				somaSolteiro += Math.pow(pessoa.getIdade() - mediaIdadeSolteiro, 2);
			else if (pessoa.getEstadoCivil().toUpperCase().equals("CASADO"))
				somaCasado += Math.pow(pessoa.getIdade() - mediaIdadeCasado, 2);
		}
		estatistica[28] = (int)somaSolteiro/estatistica[3];
		estatistica[29] = (int)somaCasado/estatistica[4];
		return estatistica;
	}
}
