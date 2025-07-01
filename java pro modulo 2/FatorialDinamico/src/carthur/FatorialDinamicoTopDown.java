package carthur;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FatorialDinamicoTopDown {

    private static Map<Integer, BigInteger> memo = new HashMap<>();

    public static BigInteger calcularFatorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("O número não deve ser negativo.");
        }
        if (n == 0) {
            return BigInteger.ONE;
        }
        
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        BigInteger resultado = BigInteger.valueOf(n).multiply(calcularFatorial(n - 1));
        memo.put(n, resultado);
        return resultado;
    }
    
    public static void main(String[] args) {
        int entrada = 7; 
        BigInteger saida = calcularFatorial(entrada);
        System.out.println("Entrada: " + entrada);
        System.out.println("Saída: " + saida); 
    }
}
