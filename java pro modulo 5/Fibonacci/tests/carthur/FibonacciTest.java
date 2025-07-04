package carthur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FibonacciTest {

    @Test
    @DisplayName("Deve retornar 0 para o elemento na posição 0")
    void testFibonacciBaseCaseZero() {
        assertEquals(0, Fibonacci.findElementDP(0), "O primeiro elemento (posição 0) deve ser 0.");
    }

    @Test
    @DisplayName("Deve retornar 1 para o elemento na posição 1")
    void testFibonacciBaseCaseOne() {
        assertEquals(1, Fibonacci.findElementDP(1), "O segundo elemento (posição 1) deve ser 1.");
    }

    @Test
    @DisplayName("Deve calcular corretamente para um número pequeno (ex: 7)")
    void testFibonacciForSmallNumber() {
        
        assertEquals(13, Fibonacci.findElementDP(7));
    }

    @Test
    @DisplayName("Deve calcular corretamente para um número médio (ex: 12)")
    void testFibonacciForMediumNumber() {
        
        assertEquals(144, Fibonacci.findElementDP(12));
    }
    
    @Test
    @DisplayName("Deve calcular corretamente para um número grande (ex: 40)")
    void testFibonacciForLargeNumber() {
        
        assertEquals(102334155, Fibonacci.findElementDP(40));
    }

    @Test
    @DisplayName("Deve lançar exceção para entrada negativa")
    void testThrowsExceptionForNegativeInput() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Fibonacci.findElementDP(-1);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção para entrada fora do limite do array (100)")
    void testThrowsExceptionForInputExceedingLimit() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Fibonacci.findElementDP(100);
        });
    }
}
