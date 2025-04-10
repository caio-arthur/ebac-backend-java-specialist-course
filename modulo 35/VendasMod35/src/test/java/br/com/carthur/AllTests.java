package br.com.carthur;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ClienteDAOTeste.class, ProdutoDAOTeste.class, VendaDAOTeste.class})
public class AllTests {

}
