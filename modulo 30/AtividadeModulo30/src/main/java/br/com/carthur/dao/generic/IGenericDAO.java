package br.com.carthur.dao.generic;

import br.com.carthur.exception.*;
import br.com.carthur.domain.*;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author caio.arthur
 *
 * Interface genérica para métodos de CRUD(Create, Read, Update and Delete)
 */
public interface IGenericDAO <T extends Persistente, E extends Serializable>  {

    /**
     * Método para cadastrar novos registro no banco de dados
     *
     * @param entity a ser cadastrado
     * @return retorna verdadeiro para cadastrado e falso para não cadastrado
     * @throws DAOException 
     */
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException;

    /**
     * Método para excluir um registro do banco de dados
     *
     * @param valor chave única do dado a ser excluído
     * @throws DAOException 
     * @throws SQLException 
     */
    public void excluir(E value) throws DAOException, SQLException;

    /**
     *Método para alterar um registro no bando de dados.
     *
     * @param entity a ser atualizado
     * @throws SQLException 
     * @throws DAOException 
     */
    public void alterar(T entity) throws TipoChaveNaoEncontradaException, SQLException, DAOException;

    /**
     * Método para consultar um registro no banco de dados
     *
     * @param valor chave única do dado a ser consultado
     * @return
     * @throws DAOException 
     * @throws TableException 
     * @throws MaisDeUmRegistroException 
     * @throws SQLException 
     */
    public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException, SQLException;

    /**
     * Método que irá retornar todos os registros do banco de dados de uma determinado dado ou tabela
     *
     * @return Registros encontrados
     * @throws TableException 
     * @throws DAOException 
     * @throws SQLException 
     */
    public Collection<T> buscarTodos() throws TableException, DAOException, SQLException;
}