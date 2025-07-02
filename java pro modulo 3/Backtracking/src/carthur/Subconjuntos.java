package carthur;

import java.util.ArrayList;
import java.util.List;

public class Subconjuntos {


    public List<List<Integer>> encontrarSubconjuntos(int[] S, int n) {
        List<List<Integer>> resultado = new ArrayList<>();
        
        backtrack(resultado, new ArrayList<>(), S, n, 0); 
        return resultado;
    }


    private void backtrack(List<List<Integer>> resultado, List<Integer> subconjuntoAtual, int[] S, int n, int inicio) {
        if (subconjuntoAtual.size() == n) {
            resultado.add(new ArrayList<>(subconjuntoAtual));
            return; 
        }
        
        for (int i = inicio; i < S.length; i++) {
            subconjuntoAtual.add(S[i]);
            backtrack(resultado, subconjuntoAtual, S, n, i + 1);
            subconjuntoAtual.remove(subconjuntoAtual.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subconjuntos solucao = new Subconjuntos();

        // Exemplo 1
        System.out.println("Exemplo 1:");
        int[] S1 = {1, 2, 3};
        int n1 = 2;
        List<List<Integer>> resultado1 = solucao.encontrarSubconjuntos(S1, n1);
        System.out.println("Entrada: S = [1, 2, 3], n = 2");
        System.out.println("Saída: " + resultado1); // Saída: [[1, 2], [1, 3], [2, 3]]

        System.out.println("\n-----------------------------------\n");

        // Exemplo 2
        System.out.println("Exemplo 2:");
        int[] S2 = {1, 2, 3, 4};
        int n2 = 1;
        List<List<Integer>> resultado2 = solucao.encontrarSubconjuntos(S2, n2);
        System.out.println("Entrada: S = [1, 2, 3, 4], n = 1");
        System.out.println("Saída: " + resultado2); // Saída: [[1], [2], [3], [4]]
        
        System.out.println("\n-----------------------------------\n");

        // Exemplo 3
        System.out.println("Exemplo 3:");
        int[] S3 = {1, 2, 3, 4, 5};
        int n3 = 3;
        List<List<Integer>> resultado3 = solucao.encontrarSubconjuntos(S3, n3);
        System.out.println("Entrada: S = [1, 2, 3, 4, 5], n = 3");
        System.out.println("Saída: " + resultado3); // Saída: [[1, 2, 3], [1, 2, 4], [1, 2, 5], [1, 3, 4], [1, 3, 5], [1, 4, 5], [2, 3, 4], [2, 3, 5], [2, 4, 5], [3, 4, 5]]
    }
}
