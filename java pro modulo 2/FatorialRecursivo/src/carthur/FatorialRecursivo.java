package carthur;

public class FatorialRecursivo {

    public static long calcularFatorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("O número deve ser não-negativo.");
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        return n * calcularFatorial(n - 1);
    }

    // Este código NÃO é recomendado para uso prático.
    public int fibonacciIngenuo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("O número deve ser não-negativo.");
        }
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacciIngenuo(n - 1) + fibonacciIngenuo(n - 2);
    }

    public static void main(String[] args) {
        int entrada1 = 3;
        long saida1 = calcularFatorial(entrada1);
        System.out.println("Entrada: " + entrada1);
        System.out.println("Saída: " + saida1);

        System.out.println("--------------------");

        int entrada2 = 7;
        long saida2 = calcularFatorial(entrada2);
        System.out.println("Entrada: " + entrada2);
        System.out.println("Saída: " + saida2);
    }
}