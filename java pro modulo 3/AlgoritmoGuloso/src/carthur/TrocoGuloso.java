package carthur;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;

public class TrocoGuloso {

    public Map<Integer, Integer> darTroco(int quantia, Integer[] moedasDisponiveis) {
        Map<Integer, Integer> troco = new HashMap<>();
        Arrays.sort(moedasDisponiveis, Collections.reverseOrder());

        int quantiaRestante = quantia;

        System.out.println("Calculando troco para a quantia: " + quantia);
        System.out.println("Moedas disponíveis: " + Arrays.toString(moedasDisponiveis));
        System.out.println("------------------------------------");

        for (int moeda : moedasDisponiveis) {
            if (quantiaRestante >= moeda) {
                int quantidadeDeMoedas = quantiaRestante / moeda;
                
                troco.put(moeda, quantidadeDeMoedas);

                quantiaRestante = quantiaRestante % moeda;
                
                System.out.printf("Usando %d moeda(s) de %d. Falta: %d\n", quantidadeDeMoedas, moeda, quantiaRestante);
            }
        }
        
        if (quantiaRestante > 0) {
            System.out.println("\nNão foi possível dar o troco exato com as moedas fornecidas.");
            return new HashMap<>(); 
        }

        return troco;
    }

    public static void main(String[] args) {
        TrocoGuloso calculadora = new TrocoGuloso();

        int quantiaExemplo1 = 18;
        Integer[] moedasExemplo1 = {5, 2, 1};
        
        Map<Integer, Integer> resultado1 = calculadora.darTroco(quantiaExemplo1, moedasExemplo1);

        System.out.println("\n--- Resultado Final ---");
        System.out.println("Troco para " + quantiaExemplo1 + ": " + resultado1);
        
        System.out.println("\n====================================\n");
        
        int quantiaExemplo2 = 488; // R$ 4,88
        Integer[] moedasExemplo2 = {100, 50, 25, 10, 5, 1}; // Moedas em centavos
        
        Map<Integer, Integer> resultado2 = calculadora.darTroco(quantiaExemplo2, moedasExemplo2);

        System.out.println("\n--- Resultado Final ---");
        System.out.println("Troco para " + quantiaExemplo2 + " centavos: " + resultado2);

    }
}
