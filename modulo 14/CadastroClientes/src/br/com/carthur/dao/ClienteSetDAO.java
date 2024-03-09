package br.com.carthur.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import br.com.carthur.domain.Cliente;
/**
*
* @author caio.arthur
*/

public class ClienteSetDAO implements IClienteDAO {

    private Set<Cliente> set;

    public ClienteSetDAO() {
        this.set = new HashSet<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        return this.set.add(cliente);
    }

    @Override
    public void excluir(String cpf) {
        Cliente clienteParaExcluir = null;
        for (Cliente cliente : this.set) {
            if (cliente.getCpf().equals(cpf)) {
                clienteParaExcluir = cliente;
                break;
            }
        }
        if (clienteParaExcluir != null) {
            this.set.remove(clienteParaExcluir);
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        for (Cliente c : set) {
            if (c.getCpf().equals(cliente.getCpf())) {
                c.setNome(cliente.getNome());
                c.setTel(cliente.getTel());
                c.setNumero(cliente.getNumero());
                c.setEnd(cliente.getEnd());
                c.setCidade(cliente.getCidade());
                c.setEstado(cliente.getEstado());
                break;
            }
        }
    }

    @Override
    public Cliente consultar(String cpf) {
        for (Cliente cliente : this.set) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.set;
    }
}
