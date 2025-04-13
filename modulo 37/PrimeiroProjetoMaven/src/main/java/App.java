import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.carthur.domain.Cliente;

public class App {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void main(String[] args) {
        // Criar cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Caio Arthur");
        cliente.setEmail("carthur_dev@hotmail.com");
        cliente.setTelefone("123456789");

        // Persistir cliente
        openConnection();
        try {
            em.persist(cliente);
            em.getTransaction().commit();
            System.out.println("Cliente salvo com sucesso!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private static void openConnection() {
        emf = Persistence.createEntityManagerFactory("ExemploJPA");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    private static void closeConnection() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
