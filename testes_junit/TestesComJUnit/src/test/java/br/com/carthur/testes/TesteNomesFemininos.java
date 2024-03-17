package br.com.carthur.testes;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class TesteNomesFemininos {

    @Test
    public void testNomesFemininos() {
    	List<String> mulheres = List.of("Maria-F", "Ana-F", "Joana-F");
        for (String nome : mulheres) {
            Assert.assertTrue(nome.endsWith("-F"));
        }
    }
}
