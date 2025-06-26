package carthur;

/**
 * Implementação de uma estrutura de dados de Lista Encadeada para inteiros.
 */
public class ListaEncadeada {
    private No cabeca; // O primeiro nó da lista
    private int tamanho;

    /**
     * Construtor para criar uma lista vazia.
     */
    public ListaEncadeada() {
        this.cabeca = null;
        this.tamanho = 0;
    }

    /**
     * Retorna o tamanho atual da lista.
     * @return O número de nós na lista.
     */
    public int size() {
        return this.tamanho;
    }

    /**
     * Adiciona um nó ao fim da lista.
     * @param novoNo O nó a ser adicionado.
     */
    public void push(No novoNo) {
        if (cabeca == null) {
            // Se a lista está vazia, o novo nó se torna a cabeça
            cabeca = novoNo;
        } else {
            // Se não, percorre a lista até o fim para adicionar o novo nó
            No atual = cabeca;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
        }
        tamanho++;
    }

    /**
     * Remove o último nó da lista e o retorna.
     * @return O nó que foi removido do fim da lista, ou null se a lista estiver vazia.
     */
    public No pop() {
        if (cabeca == null) {
            return null; // Lista vazia
        }

        tamanho--;
        // Se houver apenas um elemento, remove a cabeça
        if (cabeca.proximo == null) {
            No noRemovido = cabeca;
            cabeca = null;
            return noRemovido;
        }

        // Encontra o penúltimo nó
        No penultimo = cabeca;
        while (penultimo.proximo.proximo != null) {
            penultimo = penultimo.proximo;
        }
        
        No noRemovido = penultimo.proximo;
        penultimo.proximo = null; // Remove a referência ao último nó
        return noRemovido;
    }

    /**
     * Retorna o nó em um índice específico.
     * @param index A posição do nó desejado.
     * @return O nó no índice especificado, ou null se o índice for inválido.
     */
    public No elementAt(int index) {
        if (index < 0 || index >= tamanho) {
            System.out.println("Erro: Índice inválido.");
            return null;
        }
        No atual = cabeca;
        for (int i = 0; i < index; i++) {
            atual = atual.proximo;
        }
        return atual;
    }

    /**
     * Insere um nó em um índice específico da lista.
     * @param index A posição onde o nó será inserido.
     * @param novoNo O nó a ser inserido.
     */
    public void insert(int index, No novoNo) {
        if (index < 0 || index > tamanho) {
            System.out.println("Erro: Índice inválido para inserção.");
            return;
        }

        if (index == 0) {
            // Insere na cabeça
            novoNo.proximo = cabeca;
            cabeca = novoNo;
        } else {
            // Encontra o nó anterior ao ponto de inserção
            No anterior = elementAt(index - 1);
            novoNo.proximo = anterior.proximo;
            anterior.proximo = novoNo;
        }
        tamanho++;
    }
    
    /**
     * Remove um nó de um índice específico.
     * @param index A posição do nó a ser removido.
     */
    public void remove(int index) {
        if (index < 0 || index >= tamanho) {
            System.out.println("Erro: Índice inválido para remoção.");
            return;
        }
        
        if (index == 0) {
            // Remove a cabeça
            cabeca = cabeca.proximo;
        } else {
            // Encontra o nó anterior ao que será removido
            No anterior = elementAt(index - 1);
            anterior.proximo = anterior.proximo.proximo;
        }
        tamanho--;
    }

    /**
     * Imprime uma representação textual da lista no console.
     */
    public void printList() {
        if (cabeca == null) {
            System.out.println("Lista: [vazia]");
            return;
        }

        StringBuilder sb = new StringBuilder("Lista: ");
        No atual = cabeca;
        while (atual != null) {
            sb.append(atual.valor).append(" -> ");
            atual = atual.proximo;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }
}
