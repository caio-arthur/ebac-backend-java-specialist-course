 package carthur;

 public class Calculadora {

	    /**
	     * Adiciona dois números inteiros.
	     * @param a O primeiro inteiro.
	     * @param b O segundo inteiro.
	     * @return A soma de a e b.
	     */
	    public int adicionar(int a, int b) {
	        return a + b;
	    }

	    /**
	     * Subtrai o segundo número inteiro do primeiro.
	     * @param a O primeiro inteiro.
	     * @param b O segundo inteiro.
	     * @return A diferença entre a e b.
	     */
	    public int subtrair(int a, int b) {
	        return a - b;
	    }

	    /**
	     * Multiplica dois números inteiros.
	     * @param a O primeiro inteiro.
	     * @param b O segundo inteiro.
	     * @return O produto de a e b.
	     */
	    public int multiplicar(int a, int b) {
	        return a * b;
	    }

	    /**
	     * Divide o primeiro número inteiro pelo segundo.
	     * @param a O numerador.
	     * @param b O denominador.
	     * @return O resultado da divisão inteira.
	     * @throws IllegalArgumentException se o denominador (b) for zero.
	     */
	    public int dividir(int a, int b) {
	        if (b == 0) {
	            throw new IllegalArgumentException("Divisão por zero não é permitida.");
	        }
	        return a / b;
	    }
	}
