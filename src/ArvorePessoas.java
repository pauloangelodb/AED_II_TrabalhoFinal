
public class ArvorePessoas {


	private CelulaPessoa raiz; // referência à raiz da árvore.

    /// Construtor da classe.
    /// Esse construtor cria uma nova árvore binária de pessoas vazia. Para isso, esse método atribui null à raiz da árvore.
    public ArvorePessoas()
    {
        raiz = null;
    }

    /// Método booleano que indica se a árvore está vazia ou não.
    /// Retorna: 
    /// verdadeiro: se a raiz da árvore for null, o que significa que a árvore está vazia.
    /// falso: se a raiz da árvore não for null, o que significa que a árvore não está vazia.
    public Boolean arvoreVazia()
    {
        if (this.raiz == null)
            return true;
        else
            return false;
    }

    /// Método recursivo responsável por adicionar uma pessoa à árvore.
    /// Parâmetro "raizArvore": raiz da árvore ou sub-árvore em que a pessoa será adicionada.
    /// Parâmetro "pessoaNova": pessoa que deverá ser adicionada à árvore.
    /// Retorna a raiz atualizada da árvore ou sub-árvore em que a pessoa foi adicionada.
    private CelulaPessoa adicionar(CelulaPessoa raizArvore, Pessoa pessoaNova)
    {
        /// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então uma nova pessoa é inserida.
        if (raizArvore == null)
            raizArvore = new CelulaPessoa(pessoaNova);
        else
        {
            /// Se o número de identidade da pessoa armazenada na raiz da árvore for maior do que o número de identidade da pessoa que deverá ser inserida na árvore:
            /// adicione essa pessoa à sub-árvore esquerda; e atualize a referência para a sub-árvore esquerda modificada. 
            if (raizArvore.dados.getIdentidade() > pessoaNova.getIdentidade())
                raizArvore.esquerda = adicionar(raizArvore.esquerda, pessoaNova);
            else
            {
            	/// Se o número de identidade da pessoa armazenada na raiz da árvore for menor do que o número de identidade da pessoa que deverá ser inserida na árvore:
                /// adicione essa pessoa à sub-árvore direita; e atualize a referência para a sub-árvore direita modificada.
                if (raizArvore.dados.getIdentidade() < pessoaNova.getIdentidade())
                    raizArvore.direita = adicionar(raizArvore.direita, pessoaNova);
                else
                    /// O número de identidade da pessoa armazenada na raiz da árvore for igual do que o número de identidade da pessoa que deverá ser inserida na árvore:
                    System.out.println("A pessoa " + pessoaNova.getNome() + " (identidade " + pessoaNova.getIdentidade() + ") já foi inserida anteriormente na árvore.");
            }
        }
        /// Retorna a raiz atualizada da árvore ou sub-árvore em que a pessoa foi adicionada.
        return raizArvore;
    }

    /// Método que encapsula a adição recursiva de pessoas à árvore.
    /// Parâmetro "pessoaNova": pessoa que deverá ser inserida na árvore.
    public void inserir(Pessoa pessoaNova)
    {
        /// Chama o método recursivo "adicionar", que será responsável por adicionar a pessoa passada como parâmetro à árvore.
        /// O método "adicionar" receberá, como primeiro parâmetro, a raiz atual da árvore; e, como segundo parâmetro, a pessoa que deverá ser adicionada à árvore.
        /// Por fim, a raiz atual da árvore é atualizada, com a raiz retornada pelo método "adicionar".
        this.raiz = adicionar(this.raiz, pessoaNova);
    }

    /// Método recursivo responsável por localizar na árvore ou sub-árvore o antecessor do nó que deverá ser retirado. 
    /// O antecessor do nó que deverá ser retirado da árvore corresponde
    /// ao nó que armazena os dados da pessoa cujo número de identidade é o maior, 
    /// dentre os números de identidade menores do que o número de identidade do nó que deverá ser retirado.
    /// Depois de ser localizado na árvore ou sub-árvore, 
    /// o antecessor do nó que deverá ser retirado da árvore o substitui.
    /// Adicionalmente, a árvore ou sub-árvore é atualizada com a remoção do antecessor.
    /// Parâmetro "pessoaRetirar": referência ao nó que armazena a pessoa que deverá ser retirada da árvore.
    /// Parâmetro "raizArvore": raiz da árvore ou sub-árvore em que o antecessor do nó que deverá ser retirado deverá ser localizado.
    /// Retorna: raiz atualizada da árvore ou sub-árvore após a remoção do antecessor do nó que foi retirado da árvore.
    private CelulaPessoa antecessor(CelulaPessoa pessoaRetirar, CelulaPessoa raizArvore)
    {
        /// Se o antecessor do nó que deverá ser retirado da árvore ainda não foi encontrado...
        if (raizArvore.direita != null)
        {
            /// Pesquise o antecessor na sub-árvore direita.
            raizArvore.direita = antecessor(pessoaRetirar, raizArvore.direita);
            return raizArvore;
        }
        /// O antecessor do nó que deverá ser retirado da árvore foi encontrado.
        else
        {
            /// O antecessor do nó que deverá ser retirado da árvore foi localizado e deverá substitui-lo.
        	pessoaRetirar.dados.setIdentidade(raizArvore.dados.getIdentidade());
        	pessoaRetirar.dados.setNome(raizArvore.dados.getNome());
        	pessoaRetirar.dados.setSexo(raizArvore.dados.getSexo());
        	pessoaRetirar.dados.setIdade(raizArvore.dados.getIdade());
        	pessoaRetirar.dados.setMoradia(raizArvore.dados.getMoradia());
        	pessoaRetirar.dados.setEstadoCivil(raizArvore.dados.getEstadoCivil());
        	pessoaRetirar.dados.setRaca(raizArvore.dados.getRaca());
        	
            /// A raiz da árvore ou sub-árvore é atualizada com os descendentes à esquerda do antecessor.
            /// Ou seja, retira-se o antecessor da árvore.
            return raizArvore.esquerda;
        }
    }

    /// Método recursivo responsável por localizar uma pessoa na árvore e retirá-la da árvore.
    /// Parâmetro "raizArvore": raiz da árvore ou sub-árvore da qual a pessoa será retirada.
    /// Parâmetro "identidade": número de identidade da pessoa que deverá ser localizada e removida da árvore.
    /// Retorna a raiz atualizada da árvore ou sub-árvore da qual a pessoa foi retirada; ou null, caso não tenha sido localizada pessoa com o número de identidade informado.
    private CelulaPessoa retirar(CelulaPessoa raizArvore, double identidade)
    {
        /// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e a pessoa, que deveria ser retirada dessa árvore, não foi encontrada.
        /// Nesse caso, deve-se retornar null.
        if (raizArvore == null)
        {
        	 System.out.println("a pessoa (identidade " + identidade + ") não foi encontrada.");
            return raizArvore;
        }
        else
        {
            /// O número de identidade da pessoa armazenada na raiz da árvore é igual ao número de identidade da pessoa que deve ser retirada dessa árvore.
            /// Ou seja, a pessoa que deve ser retirada da árvore foi encontrada.
            if (raizArvore.dados.getIdentidade() == identidade)
            {
                /// O nó da árvore que será retirado não possui descendentes à direita.
                /// Nesse caso, os descendentes à esquerda do nó que está sendo retirado da árvore passarão a ser descendentes do nó-pai do nó que está sendo retirado.
                if (raizArvore.direita == null)
                    return (raizArvore.esquerda);
                else {
                    /// O nó da árvore que será retirado não possui descendentes à esquerda.
                    /// Nesse caso, os descendentes à direita do nó que está sendo retirado da árvore passarão a ser descendentes do nó-pai do nó que está sendo retirado.
                    if (raizArvore.esquerda == null)
                        return (raizArvore.direita);
                    else
                    {
                        /// O nó que está sendo retirado da árvore possui descendentes à esquerda e à direita.
                        /// Nesse caso, o antecessor do nó que está sendo retirado é localizado na sub-árvore esquerda desse nó. 
                        /// O antecessor do nó que está sendo retirado da árvore corresponde
                        /// ao nó que armazena a pessoa cujo número de identidade é o maior, 
                        /// dentre os números de identidade menores do que o número de identidade do nó que está sendo retirado.
                        /// Depois de ser localizado na sub-árvore esquerda do nó que está sendo retirado, 
                        /// o antecessor desse nó o substitui.
                        /// A sub-árvore esquerda do nó que foi retirado é atualizada com a remoção do antecessor.
                        raizArvore.esquerda = antecessor(raizArvore, raizArvore.esquerda);
                        /// Retorna a raiz atualizada da árvore ou sub-árvore da qual a pessoa foi retirado.
                        return (raizArvore);
                    }
                }
            }
            else
            {
                /// Se o número de identidade da pessoa armazenada na raiz da árvore for maior do que o número de identidade da pessoa que deverá ser localizada e retirada da árvore:
                /// pesquise e retire essa pessoa da sub-árvore esquerda.
                if (raizArvore.dados.getIdentidade() > identidade)
                    raizArvore.esquerda = retirar(raizArvore.esquerda, identidade);
                else
                    /// Se o número de identidade da pessoa armazenada na raiz da árvore for menor do que o número de identidade da pessoa que deverá ser localizada e retirada da árvore:
                    /// pesquise e retire essa pessoa da sub-árvore direita.
                    raizArvore.direita = retirar(raizArvore.direita, identidade);
                /// Retorna a raiz atualizada da árvore ou sub-árvore da qual a pessoa foi retirada.
                return raizArvore;
            }
        }
    }

    /// Método que encapsula a retirada recursiva de uma pessoa da árvore.
    /// Parâmetro "identidadeRemover": número de identidade da pessoa que deverá ser localizada e removida da árvore.
    public void remover(int identidadeRemover)
    {
        /// Chama o método recursivo "retirar", que será responsável por pesquisar a pessoa, cujo número de identidade foi passado como parâmetro na árvore e retirá-la da árvore.
        /// O método "retirar" receberá, como primeiro parâmetro, a raiz atual da árvore; e, como segundo parâmetro, o número de identidade da pessoa que deverá ser localizada e retirada dessa árvore.
        this.raiz = retirar(this.raiz, identidadeRemover);
    }
}
