package br.com.carthur.test;

import org.junit.Assert;	
import org.junit.Test;

import br.com.carthur.TesteCliente;

public class TesteClienteTest {

	@Test
	public void testeClasseCliente() {
		TesteCliente cli = new TesteCliente();
		cli.adicionarNome("Caio");
		cli.adicionarNome1("Caio");
		Assert.assertEquals("Caio", cli.getNome());
	}
}
