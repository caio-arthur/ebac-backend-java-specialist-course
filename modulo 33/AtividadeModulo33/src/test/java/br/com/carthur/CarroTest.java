package br.com.carthur;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Test;

import br.com.carthur.dao.AcessorioDAO;
import br.com.carthur.dao.CarroAcessorioDAO;
import br.com.carthur.dao.CarroDAO;
import br.com.carthur.dao.IAcessorioDAO;
import br.com.carthur.dao.ICarroAcessorioDAO;
import br.com.carthur.dao.ICarroDAO;
import br.com.carthur.dao.IMarcaDAO;
import br.com.carthur.dao.MarcaDAO;
import br.com.carthur.domain.Acessorio;
import br.com.carthur.domain.Carro;
import br.com.carthur.domain.Marca;

public class CarroTest {

	private ICarroDAO carroDAO;
	private IMarcaDAO marcaDAO;
	private IAcessorioDAO acessorioDAO;
	private ICarroAcessorioDAO carroAcessorioDAO;
	
	public CarroTest() {
		carroDAO = new CarroDAO();
		marcaDAO = new MarcaDAO();
		acessorioDAO = new AcessorioDAO();
		carroAcessorioDAO = new CarroAcessorioDAO();
	}
	
	@After
    public void tearDown() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.createNativeQuery("DELETE FROM carro_acessorio").executeUpdate();
        
        em.createNativeQuery("DELETE FROM acessorio").executeUpdate();
        
        em.createNativeQuery("DELETE FROM carro").executeUpdate();
        
        em.createNativeQuery("DELETE FROM marca").executeUpdate();
        
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

	
	@Test
	public void cadastrarCarro() {
		// Cadastrar uma marca
		Marca marca = cadastrarMarca();
		
		// Cadastrar acessórios
		List<Acessorio> acessorios = cadastrarAcessorios();
		
		// Cadastrar um carro
		Carro carro = new Carro();
        carro.setModelo("Fusca");
		carro.setPreco(6000.0);
		carro.setAnoFabricacao(
			    LocalDate.parse("11-02-1960", DateTimeFormatter.ofPattern("dd-MM-yyyy"))
			             .atStartOfDay(ZoneId.systemDefault())
			             .toInstant()
			);
		carro.setCor("Azul");
		carro.setMarca(marca);
		carro = carroDAO.cadastrar(carro);
		
		assertNotNull(carro.getId());
		
		// Associar acessórios ao carro
		for (Acessorio acessorio : acessorios) {
			carroAcessorioDAO.cadastrar(acessorio.getId(), carro.getId());
		}
		
		// Verificar se os acessórios foram associados corretamente
		List<Acessorio> acessoriosCarro = carroAcessorioDAO.buscarAcessoriosPorCarroId(carro.getId());
		
		assertNotNull(acessoriosCarro);
		assertTrue(acessoriosCarro.size() == acessorios.size());
		
		
	}
	
	// Cadastra uma marca
	private Marca cadastrarMarca() {
		Marca marca = new Marca();
		marca.setNome("Volkswagen");
		marca.setDescricao("Marca de carros");
		marca.setAnoFundacao(
			    LocalDate.parse("11-02-1937", DateTimeFormatter.ofPattern("dd-MM-yyyy"))
			             .atStartOfDay(ZoneId.systemDefault())
			             .toInstant()
			);
		marca.setDescricao("Marca de carros");
		marca.setPaisOrigem("Alemanha");
		
		marca = marcaDAO.cadastrar(marca);
		assertNotNull(marca.getId());
		return marca;
	}
	
	private void excluirMarca(Marca marca) {
		marcaDAO.excluir(marca);
		Marca marcaExcluida = marcaDAO.buscarPorId(marca.getId());
		assertTrue(marcaExcluida == null);
	}
	
	private List<Acessorio> cadastrarAcessorios() {
		// Cadastrar um acessório
		Acessorio acessorio1 = new Acessorio();
		acessorio1.setNome("Ar-condicionado");
		acessorio1.setDescricao("Acessório de ar-condicionado");
		acessorio1.setPreco(650.0);
		
		acessorio1 = acessorioDAO.cadastrar(acessorio1);
		assertNotNull(acessorio1.getId());
		
		Acessorio acessorio2 = new Acessorio();
		acessorio2.setNome("Direção hidráulica");
		acessorio2.setDescricao("Acessório de direção hidráulica");
		acessorio2.setPreco(1200.0);
		
		acessorio2 = acessorioDAO.cadastrar(acessorio2);
		assertNotNull(acessorio2.getId());
		
		Acessorio acessorio3 = new Acessorio();
		acessorio3.setNome("Roda de liga leve");
		acessorio3.setDescricao("Acessório de roda de liga leve");
		acessorio3.setPreco(800.0);
		
		acessorio3 = acessorioDAO.cadastrar(acessorio3);
		assertNotNull(acessorio3.getId());
		
		return List.of(acessorio1, acessorio2, acessorio3);
	}
}
