
public class ArvorePessoas {


	private CelulaPessoa raiz; // refer�ncia � raiz da �rvore.

    /// Construtor da classe.
    /// Esse construtor cria uma nova �rvore bin�ria de pessoas vazia. Para isso, esse m�todo atribui null � raiz da �rvore.
    public ArvorePessoas()
    {
        raiz = null;
    }

    /// M�todo booleano que indica se a �rvore est� vazia ou n�o.
    /// Retorna: 
    /// verdadeiro: se a raiz da �rvore for null, o que significa que a �rvore est� vazia.
    /// falso: se a raiz da �rvore n�o for null, o que significa que a �rvore n�o est� vazia.
    public Boolean arvoreVazia()
    {
        if (this.raiz == null)
            return true;
        else
            return false;
    }

    /// M�todo recursivo respons�vel por adicionar uma pessoa � �rvore.
    /// Par�metro "raizArvore": raiz da �rvore ou sub-�rvore em que a pessoa ser� adicionada.
    /// Par�metro "pessoaNova": pessoa que dever� ser adicionada � �rvore.
    /// Retorna a raiz atualizada da �rvore ou sub-�rvore em que a pessoa foi adicionada.
    private CelulaPessoa adicionar(CelulaPessoa raizArvore, Pessoa pessoaNova)
    {
        /// Se a raiz da �rvore ou sub-�rvore for null, a �rvore est� vazia e ent�o uma nova pessoa � inserida.
        if (raizArvore == null)
            raizArvore = new CelulaPessoa(pessoaNova);
        else
        {
            /// Se o n�mero de identidade da pessoa armazenada na raiz da �rvore for maior do que o n�mero de identidade da pessoa que dever� ser inserida na �rvore:
            /// adicione essa pessoa � sub-�rvore esquerda; e atualize a refer�ncia para a sub-�rvore esquerda modificada. 
            if (raizArvore.dados.getIdentidade() > pessoaNova.getIdentidade())
                raizArvore.esquerda = adicionar(raizArvore.esquerda, pessoaNova);
            else
            {
            	/// Se o n�mero de identidade da pessoa armazenada na raiz da �rvore for menor do que o n�mero de identidade da pessoa que dever� ser inserida na �rvore:
                /// adicione essa pessoa � sub-�rvore direita; e atualize a refer�ncia para a sub-�rvore direita modificada.
                if (raizArvore.dados.getIdentidade() < pessoaNova.getIdentidade())
                    raizArvore.direita = adicionar(raizArvore.direita, pessoaNova);
                else
                    /// O n�mero de identidade da pessoa armazenada na raiz da �rvore for igual do que o n�mero de identidade da pessoa que dever� ser inserida na �rvore:
                    System.out.println("A pessoa " + pessoaNova.getNome() + " (identidade " + pessoaNova.getIdentidade() + ") j� foi inserida anteriormente na �rvore.");
            }
        }
        /// Retorna a raiz atualizada da �rvore ou sub-�rvore em que a pessoa foi adicionada.
        return raizArvore;
    }

    /// M�todo que encapsula a adi��o recursiva de pessoas � �rvore.
    /// Par�metro "pessoaNova": pessoa que dever� ser inserida na �rvore.
    public void inserir(Pessoa pessoaNova)
    {
        /// Chama o m�todo recursivo "adicionar", que ser� respons�vel por adicionar a pessoa passada como par�metro � �rvore.
        /// O m�todo "adicionar" receber�, como primeiro par�metro, a raiz atual da �rvore; e, como segundo par�metro, a pessoa que dever� ser adicionada � �rvore.
        /// Por fim, a raiz atual da �rvore � atualizada, com a raiz retornada pelo m�todo "adicionar".
        this.raiz = adicionar(this.raiz, pessoaNova);
    }

    /// M�todo recursivo respons�vel por localizar na �rvore ou sub-�rvore o antecessor do n� que dever� ser retirado. 
    /// O antecessor do n� que dever� ser retirado da �rvore corresponde
    /// ao n� que armazena os dados da pessoa cujo n�mero de identidade � o maior, 
    /// dentre os n�meros de identidade menores do que o n�mero de identidade do n� que dever� ser retirado.
    /// Depois de ser localizado na �rvore ou sub-�rvore, 
    /// o antecessor do n� que dever� ser retirado da �rvore o substitui.
    /// Adicionalmente, a �rvore ou sub-�rvore � atualizada com a remo��o do antecessor.
    /// Par�metro "pessoaRetirar": refer�ncia ao n� que armazena a pessoa que dever� ser retirada da �rvore.
    /// Par�metro "raizArvore": raiz da �rvore ou sub-�rvore em que o antecessor do n� que dever� ser retirado dever� ser localizado.
    /// Retorna: raiz atualizada da �rvore ou sub-�rvore ap�s a remo��o do antecessor do n� que foi retirado da �rvore.
    private CelulaPessoa antecessor(CelulaPessoa pessoaRetirar, CelulaPessoa raizArvore)
    {
        /// Se o antecessor do n� que dever� ser retirado da �rvore ainda n�o foi encontrado...
        if (raizArvore.direita != null)
        {
            /// Pesquise o antecessor na sub-�rvore direita.
            raizArvore.direita = antecessor(pessoaRetirar, raizArvore.direita);
            return raizArvore;
        }
        /// O antecessor do n� que dever� ser retirado da �rvore foi encontrado.
        else
        {
            /// O antecessor do n� que dever� ser retirado da �rvore foi localizado e dever� substitui-lo.
        	pessoaRetirar.dados.setIdentidade(raizArvore.dados.getIdentidade());
        	pessoaRetirar.dados.setNome(raizArvore.dados.getNome());
        	pessoaRetirar.dados.setSexo(raizArvore.dados.getSexo());
        	pessoaRetirar.dados.setIdade(raizArvore.dados.getIdade());
        	pessoaRetirar.dados.setMoradia(raizArvore.dados.getMoradia());
        	pessoaRetirar.dados.setEstadoCivil(raizArvore.dados.getEstadoCivil());
        	pessoaRetirar.dados.setRaca(raizArvore.dados.getRaca());
        	
            /// A raiz da �rvore ou sub-�rvore � atualizada com os descendentes � esquerda do antecessor.
            /// Ou seja, retira-se o antecessor da �rvore.
            return raizArvore.esquerda;
        }
    }

    /// M�todo recursivo respons�vel por localizar uma pessoa na �rvore e retir�-la da �rvore.
    /// Par�metro "raizArvore": raiz da �rvore ou sub-�rvore da qual a pessoa ser� retirada.
    /// Par�metro "identidade": n�mero de identidade da pessoa que dever� ser localizada e removida da �rvore.
    /// Retorna a raiz atualizada da �rvore ou sub-�rvore da qual a pessoa foi retirada; ou null, caso n�o tenha sido localizada pessoa com o n�mero de identidade informado.
    private CelulaPessoa retirar(CelulaPessoa raizArvore, double identidade)
    {
        /// Se a raiz da �rvore ou sub-�rvore for null, a �rvore est� vazia e a pessoa, que deveria ser retirada dessa �rvore, n�o foi encontrada.
        /// Nesse caso, deve-se retornar null.
        if (raizArvore == null)
        {
        	 System.out.println("a pessoa (identidade " + identidade + ") n�o foi encontrada.");
            return raizArvore;
        }
        else
        {
            /// O n�mero de identidade da pessoa armazenada na raiz da �rvore � igual ao n�mero de identidade da pessoa que deve ser retirada dessa �rvore.
            /// Ou seja, a pessoa que deve ser retirada da �rvore foi encontrada.
            if (raizArvore.dados.getIdentidade() == identidade)
            {
                /// O n� da �rvore que ser� retirado n�o possui descendentes � direita.
                /// Nesse caso, os descendentes � esquerda do n� que est� sendo retirado da �rvore passar�o a ser descendentes do n�-pai do n� que est� sendo retirado.
                if (raizArvore.direita == null)
                    return (raizArvore.esquerda);
                else {
                    /// O n� da �rvore que ser� retirado n�o possui descendentes � esquerda.
                    /// Nesse caso, os descendentes � direita do n� que est� sendo retirado da �rvore passar�o a ser descendentes do n�-pai do n� que est� sendo retirado.
                    if (raizArvore.esquerda == null)
                        return (raizArvore.direita);
                    else
                    {
                        /// O n� que est� sendo retirado da �rvore possui descendentes � esquerda e � direita.
                        /// Nesse caso, o antecessor do n� que est� sendo retirado � localizado na sub-�rvore esquerda desse n�. 
                        /// O antecessor do n� que est� sendo retirado da �rvore corresponde
                        /// ao n� que armazena a pessoa cujo n�mero de identidade � o maior, 
                        /// dentre os n�meros de identidade menores do que o n�mero de identidade do n� que est� sendo retirado.
                        /// Depois de ser localizado na sub-�rvore esquerda do n� que est� sendo retirado, 
                        /// o antecessor desse n� o substitui.
                        /// A sub-�rvore esquerda do n� que foi retirado � atualizada com a remo��o do antecessor.
                        raizArvore.esquerda = antecessor(raizArvore, raizArvore.esquerda);
                        /// Retorna a raiz atualizada da �rvore ou sub-�rvore da qual a pessoa foi retirado.
                        return (raizArvore);
                    }
                }
            }
            else
            {
                /// Se o n�mero de identidade da pessoa armazenada na raiz da �rvore for maior do que o n�mero de identidade da pessoa que dever� ser localizada e retirada da �rvore:
                /// pesquise e retire essa pessoa da sub-�rvore esquerda.
                if (raizArvore.dados.getIdentidade() > identidade)
                    raizArvore.esquerda = retirar(raizArvore.esquerda, identidade);
                else
                    /// Se o n�mero de identidade da pessoa armazenada na raiz da �rvore for menor do que o n�mero de identidade da pessoa que dever� ser localizada e retirada da �rvore:
                    /// pesquise e retire essa pessoa da sub-�rvore direita.
                    raizArvore.direita = retirar(raizArvore.direita, identidade);
                /// Retorna a raiz atualizada da �rvore ou sub-�rvore da qual a pessoa foi retirada.
                return raizArvore;
            }
        }
    }

    /// M�todo que encapsula a retirada recursiva de uma pessoa da �rvore.
    /// Par�metro "identidadeRemover": n�mero de identidade da pessoa que dever� ser localizada e removida da �rvore.
    public void remover(int identidadeRemover)
    {
        /// Chama o m�todo recursivo "retirar", que ser� respons�vel por pesquisar a pessoa, cujo n�mero de identidade foi passado como par�metro na �rvore e retir�-la da �rvore.
        /// O m�todo "retirar" receber�, como primeiro par�metro, a raiz atual da �rvore; e, como segundo par�metro, o n�mero de identidade da pessoa que dever� ser localizada e retirada dessa �rvore.
        this.raiz = retirar(this.raiz, identidadeRemover);
    }
}
