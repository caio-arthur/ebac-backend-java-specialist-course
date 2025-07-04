package carthur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

 private Calculadora calculadora;

 @BeforeEach
 void setUp() {
     calculadora = new Calculadora();
 }

 @Test
 @DisplayName("Deve somar dois números inteiros corretamente")
 void deveAdicionarDoisNumerosPositivos() {
     assertEquals(8, calculadora.adicionar(5, 3));
 }

 @Test
 @DisplayName("Deve subtrair o segundo número do primeiro")
 void deveSubtrairDoisNumeros() {
     assertEquals(6, calculadora.subtrair(10, 4));
 }

 @Test
 @DisplayName("Deve multiplicar dois números corretamente")
 void deveMultiplicarDoisNumeros() {
     assertEquals(21, calculadora.multiplicar(7, 3));
 }

 @Test
 @DisplayName("Deve realizar a divisão inteira de dois números")
 void deveDividirDoisNumeros() {
     assertEquals(5, calculadora.dividir(10, 2));
 }

 @Test
 @DisplayName("Deve lançar IllegalArgumentException ao tentar dividir por zero")
 void deveLancarExcecaoAoDividirPorZero() {
     IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
         calculadora.dividir(10, 0);
     });

     String expectedMessage = "Divisão por zero não é permitida.";
     assertEquals(expectedMessage, exception.getMessage());
 }
}
