package br.com.carthur.service;

import br.com.carthur.dao.ClienteDao;
import br.com.carthur.dao.ClienteDaoMock;
import br.com.carthur.dao.IClienteDao;

/**
 * @author rodrigo.pires
 */
public class ClienteService {

    private IClienteDao clienteDao;

    public ClienteService(IClienteDao clienteDao) {
        //clienteDao = new ClienteDao();
        //clienteDao = new ClienteDaoMock();
        this.clienteDao = clienteDao;
    }

    public String salvar() {
        clienteDao.salvar();
        return "Sucesso";
    }
}
