package br.com.carthur.test;

import org.junit.Assert;	
import org.junit.Test;

import br.com.carthur.dao.*;
import br.com.carthur.dao.mocks.ContratoDaoMock;
import br.com.carthur.service.*;

/**
 * @author caio.arthur
 */
public class ContratoServiceTest {

    @Test
    public void salvarTest() {
        IContratoDao dao = new ContratoDaoMock();
        IContratoService service = new ContratoService(dao);
        String retorno = service.salvar();
        Assert.assertEquals("Sucesso", retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroNoSalvarComBancoDeDadosTest() {
        IContratoDao dao = new ContratoDao();
        IContratoService service = new ContratoService(dao);
        String retorno = service.salvar();
        Assert.assertEquals("Sucesso", retorno);
    }

    @Test
    public void buscarTest() {
        IContratoDao dao = new ContratoDaoMock();
        IContratoService service = new ContratoService(dao);
        String retorno = service.buscar();
        Assert.assertEquals("Sucesso", retorno);
    }

    @Test
    public void excluirTest() {
        IContratoDao dao = new ContratoDaoMock();
        IContratoService service = new ContratoService(dao);
        String retorno = service.excluir();
        Assert.assertEquals("Sucesso", retorno);
    }
    
    @Test
    public void atualizarTest() {
        IContratoDao dao = new ContratoDaoMock();
        IContratoService service = new ContratoService(dao);
        String retorno = service.atualizar();
        Assert.assertEquals("Sucesso", retorno);
    }
    
}
