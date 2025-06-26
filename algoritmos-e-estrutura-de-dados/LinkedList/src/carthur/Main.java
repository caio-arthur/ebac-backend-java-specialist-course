package carthur;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Iniciando a Lista Encadeada ---");
        ListaEncadeada minhaLista = new ListaEncadeada();
        minhaLista.printList();
        System.out.println("Tamanho: " + minhaLista.size());

        System.out.println("\n--- Adicionando elementos com push() ---");
        minhaLista.push(new No(10));
        minhaLista.push(new No(20));
        minhaLista.push(new No(30));
        minhaLista.printList(); // Lista: 10 -> 20 -> 30 -> null
        System.out.println("Tamanho: " + minhaLista.size());

        System.out.println("\n--- Inserindo um elemento com insert() na posição 1 ---");
        minhaLista.insert(1, new No(15));
        minhaLista.printList(); // Lista: 10 -> 15 -> 20 -> 30 -> null
        System.out.println("Tamanho: " + minhaLista.size());

        System.out.println("\n--- Removendo o elemento na posição 2 com remove() ---");
        minhaLista.remove(2);
        minhaLista.printList(); // Lista: 10 -> 15 -> 30 -> null
        System.out.println("Tamanho: " + minhaLista.size());

        System.out.println("\n--- Buscando o elemento na posição 1 com elementAt() ---");
        No elemento = minhaLista.elementAt(1);
        if (elemento != null) {
            System.out.println("Valor encontrado: " + elemento.valor); // Valor encontrado: 15
        }
        
        System.out.println("\n--- Removendo o último elemento com pop() ---");
        No noRemovido = minhaLista.pop();
        if (noRemovido != null) {
            System.out.println("Nó removido com pop(): " + noRemovido.valor); // Nó removido com pop(): 30
        }
        minhaLista.printList(); // Lista: 10 -> 15 -> null
        System.out.println("Tamanho: " + minhaLista.size());
    }
}