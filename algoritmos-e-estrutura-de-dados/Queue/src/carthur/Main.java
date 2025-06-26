package carthur;

public class Main {
    public static void main(String[] args) {
        Queue minhaFila = new Queue();

        // Adicionando elementos à fila
        minhaFila.enqueue(10);
        minhaFila.enqueue(20);
        minhaFila.enqueue(30);

        // Exibindo o estado atual da fila
        System.out.println("Elemento da frente: " + minhaFila.front()); // Deve ser 10
        System.out.println("Elemento do fim: " + minhaFila.rear());     // Deve ser 30

        // Removendo um elemento da fila
        System.out.println("Removendo: " + minhaFila.dequeue());        // Deve ser 10

        // Exibindo o estado após a remoção
        System.out.println("Novo elemento da frente: " + minhaFila.front()); // Deve ser 20
    }
}
