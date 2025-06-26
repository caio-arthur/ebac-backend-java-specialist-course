package carthur;

import java.util.NoSuchElementException;

public class Queue {

    // Classe interna para representar um nó na fila
    private static class No {
        private int valor;
        private No proximo;

        public No(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    private No frente;  // O início da fila (de onde os elementos são removidos)
    private No tras;    // O fim da fila (onde os elementos são adicionados)
    private int tamanho; // O número de elementos na fila

    /**
     * Construtor para criar uma fila vazia.
     */
    public Queue() {
        this.frente = null;
        this.tras = null;
        this.tamanho = 0;
    }

    /**
     * Adiciona um inteiro ao fim da fila.
     * Complexidade: O(1)
     * @param valor O inteiro a ser adicionado.
     */
    public void enqueue(int valor) {
        No novoNo = new No(valor);
        if (isEmpty()) {
            // Se a fila está vazia, o novo nó é tanto a frente quanto o fim
            frente = novoNo;
        } else {
            // Se não, o nó atual do fim aponta para o novo nó
            tras.proximo = novoNo;
        }
        // O novo nó se torna o novo fim da fila
        tras = novoNo;
        tamanho++;
    }

    /**
     * Remove e retorna o inteiro do início da fila.
     * Complexidade: O(1)
     * @return O inteiro removido do início da fila.
     * @throws NoSuchElementException se a fila estiver vazia.
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia. Não é possível remover elementos.");
        }
        int valorRemovido = frente.valor;
        frente = frente.proximo;
        tamanho--;

        // Se após a remoção a fila ficar vazia, o fim também deve ser null
        if (isEmpty()) {
            tras = null;
        }
        return valorRemovido;
    }

    /**
     * Retorna o inteiro que está no fim da fila sem removê-lo.
     * @return O último inteiro da fila.
     * @throws NoSuchElementException se a fila estiver vazia.
     */
    public int rear() {
        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia.");
        }
        return tras.valor;
    }

    /**
     * Retorna o inteiro que está na frente da fila sem removê-lo.
     * @return O primeiro inteiro da fila.
     * @throws NoSuchElementException se a fila estiver vazia.
     */
    public int front() {
        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia.");
        }
        return frente.valor;
    }

    /**
     * Retorna o número de elementos na fila.
     * @return O tamanho da fila.
     */
    public int size() {
        return tamanho;
    }

    /**
     * Verifica se a fila está vazia.
     * @return true se a fila não contiver elementos, false caso contrário.
     */
    public boolean isEmpty() {
        return tamanho == 0;
    }
}