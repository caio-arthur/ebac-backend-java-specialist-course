package carthur;

import java.math.BigInteger;

public class FatorialDinamicoBottomUp {

    public static BigInteger calcularFatorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("O número não deve ser negativo.");
        }
        
        BigInteger[] dp = new BigInteger[n + 1];
        
        dp[0] = BigInteger.ONE;
        
        for (int i = 1; i <= n; i++) {
            dp[i] = BigInteger.valueOf(i).multiply(dp[i - 1]);
        }
        
        return dp[n];
    }
    
    public static void main(String[] args) {
        int entrada = 7;
        BigInteger saida = calcularFatorial(entrada);
        System.out.println("Entrada: " + entrada);
        System.out.println("Saída: " + saida); // Saída: 5040
    }
}
