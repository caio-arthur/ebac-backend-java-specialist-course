package br.com.carthur.dao.generic;

import br.com.carthur.annotation.*;
import br.com.carthur.dao.jbdc.ConnectionFactory;
import br.com.carthur.domain.*;
import br.com.carthur.exception.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caio.arthur
 *
 * Classe genérica que implementa interface genérica com os métodos de CRUD
 */
public abstract class GenericDAO<T extends Persistente, E extends Serializable> implements IGenericDAO<T,E> {

    //protected Map<Class, Map<Long, T>> map = new HashMap<>();

    /**
     * Necessário utilizar Singleton para ter apenas um MAP no sistema
     */
    public abstract Class<T> getTipoClasse();

    public abstract void atualizarDados(T entity, T entityCadastrado);
    
    protected abstract String getQueryInsercao();
    protected abstract String getQueryExclusao();
    protected abstract String getQueryAtualizacao();
    protected abstract void setParametrosQueryInsercao(PreparedStatement stmInsert, T entity) throws SQLException;
    protected abstract void setParametrosQueryExclusao(PreparedStatement stmDelete, E valor) throws SQLException;
    protected abstract void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, T entity) throws SQLException;
    protected abstract void setParametrosQuerySelect(PreparedStatement stmSelect, E valor) throws SQLException;
    
    public GenericDAO() {
    }

    public E getChave(T entity) throws TipoChaveNaoEncontradaException {
        Field[] fields = entity.getClass().getDeclaredFields();
        E returnValue = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(TipoChave.class)) {
                TipoChave tipoChave = field.getAnnotation(TipoChave.class);
                String nomeMetodo = tipoChave.value();
                try {
                    Method method = entity.getClass().getMethod(nomeMetodo);
                    returnValue = (E) method.invoke(entity);
                    return returnValue;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    //Criar exception de negócio TipoChaveNaoEncontradaException
                    e.printStackTrace();
                    throw new TipoChaveNaoEncontradaException("Chave principal do objeto " + entity.getClass() + " não encontrada", e);
                }
            }
        }
        if (returnValue == null) {
            String msg = "Chave principal do objeto " + entity.getClass() + " não encontrada";
            System.out.println("**** ERRO ****" + msg);
            throw new TipoChaveNaoEncontradaException(msg);
        }
        return null;
    }

    @Override
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
        Connection connection = null;
        PreparedStatement stm = null;
        
        try {
        	connection = getConnection();
			stm = connection.prepareStatement(getQueryInsercao(), Statement.RETURN_GENERATED_KEYS);
			setParametrosQueryInsercao(stm, entity);
			int rowsAffected = stm.executeUpdate();
			
			if (rowsAffected > 0) {
				// Obter a chave gerada
				try (ResultSet rs = stm.getGeneratedKeys()) {
					if (rs.next()) {
						Persistente per = (Persistente) entity;
						per.setId(rs.getLong(1));
					}
					return true;
				}
			}
			
		} catch (SQLException e) {
			throw new DAOException("Erro ao cadastrar o objeto ", e);
		} finally {
			closeConnection(connection, stm, null);
        }
		return false;
    }
    

    @Override
    public void excluir(E valor) throws DAOException, SQLException {
		Connection connection = getConnection();
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(getQueryExclusao());
			setParametrosQueryExclusao(stm, valor);
			stm.executeUpdate();
			
			} catch (SQLException e) {
				throw new DAOException("Erro ao excluir o objeto ", e);
			} finally {
				closeConnection(connection, stm, null);
			}
    }

    @Override
    public void alterar(T entity) throws TipoChaveNaoEncontradaException, SQLException, DAOException {
        Connection connection = getConnection();
        PreparedStatement stm = null;
        try {
			stm = connection.prepareStatement(getQueryAtualizacao());
			setParametrosQueryAtualizacao(stm, entity);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Erro ao alterar o objeto ", e);
		} finally {
			closeConnection(connection, stm, null);
		}
        
    }

    @Override
    public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException, SQLException {
        try {
        	validarMaisDeUmRegistro(valor);
        	Connection connection = getConnection();
        	PreparedStatement stm = connection.prepareStatement("SELECT * FROM " + getTableName() + " WHERE " + getNomeCampoChave(getTipoClasse()) + " = ?");
        	setParametrosQuerySelect(stm, valor);
        	ResultSet rs = stm.executeQuery();
        	if (rs.next()) {
        		T entity = getTipoClasse().getConstructor(null).newInstance();
        		Field[] fields = entity.getClass().getDeclaredFields();
        		for (Field field : fields) {
					if (field.isAnnotationPresent(ColunaTabela.class)) {
						ColunaTabela coluna = field.getAnnotation(ColunaTabela.class);
						String dbName = coluna.dbName();
						String javaSetName = coluna.setJavaName();
						Class<?> classField = field.getType();
						try {
							Method method = entity.getClass().getMethod(javaSetName, classField);
							setValueByType(entity, method, classField, rs, dbName);
						} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
							throw new DAOException("Erro ao invocar o método " + javaSetName, e);
							
						} catch (TipoElementoNaoConhecidoException e) {
							throw new DAOException("Erro ao converter o tipo do elemento " + dbName, e);
						}
					}
				}
        		return entity;
        	}
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException | SQLException | NoSuchMethodException | TipoChaveNaoEncontradaException | InvocationTargetException e) {
        	throw new DAOException("Erro consultando objeto ", e);
        } finally {
			closeConnection(getConnection(), null, null);
		}
        return null;
    }
    
    private void setValueByType(T entity, Method method, Class<?> classField, ResultSet rs, String fieldName) throws SQLException, TipoElementoNaoConhecidoException, IllegalAccessException, InvocationTargetException {
    	if (classField.equals(Integer.class)) {
    		Integer value = rs.getInt(fieldName);
    		method.invoke(entity, value);
    	} else if (classField.equals(Long.class)) {
			Long value = rs.getLong(fieldName);
			method.invoke(entity, value);
		} else if (classField.equals(String.class)) {
			String value = rs.getString(fieldName);
			method.invoke(entity, value);
		} else if (classField.equals(Double.class)) {
			Double value = rs.getDouble(fieldName);
			method.invoke(entity, value);
		} else if (classField.equals(Short.class)) {
			Short value = rs.getShort(fieldName);
			method.invoke(entity, value);
		} else if (classField.equals(BigDecimal.class)) {
			BigDecimal value = rs.getBigDecimal(fieldName);
			method.invoke(entity, value);
		} else {
			throw new TipoElementoNaoConhecidoException("Tipo de elemento não conhecido: " + classField.getName());
		}
    }
    
    private String getTableName() throws TableException {
		if (getTipoClasse().isAnnotationPresent(Tabela.class)) {
			Tabela tabela = getTipoClasse().getAnnotation(Tabela.class);
			return tabela.value();
		} else {
			throw new TableException("Tabela não encontrada para a classe " + getTipoClasse());
		}
	}
    
    public String getNomeCampoChave(Class<?> clazz) throws TipoChaveNaoEncontradaException {
    	Field[] fields = clazz.getDeclaredFields();
    	for (Field field : fields) {
			if (field.isAnnotationPresent(TipoChave.class)) {
				ColunaTabela coluna = field.getAnnotation(ColunaTabela.class);
				return coluna.dbName();
			}
		}
    	return null;
    }

    @Override
    public Collection<T> buscarTodos() throws TableException, DAOException, SQLException {
    	List<T> list = new ArrayList<>();
    	try {
        	Connection connection = getConnection();
        	PreparedStatement stm = connection.prepareStatement("SELECT * FROM " + getTableName());
        	ResultSet rs = stm.executeQuery();
        	while (rs.next()) {
        		T entity = getTipoClasse().getConstructor(null).newInstance();
        		Field[] fields = entity.getClass().getDeclaredFields();
        		for (Field field : fields) {
					if (field.isAnnotationPresent(ColunaTabela.class)) {
						ColunaTabela coluna = field.getAnnotation(ColunaTabela.class);
						String dbName = coluna.dbName();
						String javaSetName = coluna.setJavaName();
						Class<?> classField = field.getType();
						try {
							Method method = entity.getClass().getMethod(javaSetName, classField);
							setValueByType(entity, method, classField, rs, dbName);
						} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
							throw new DAOException("Erro ao invocar o método " + javaSetName, e);
							
						} catch (TipoElementoNaoConhecidoException e) {
							throw new DAOException("Erro ao converter o tipo do elemento " + dbName, e);
						}
					}
				}
        		list.add(entity);
        	}
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException | SQLException | NoSuchMethodException | InvocationTargetException e) {
        	throw new DAOException("Erro consultando objeto ", e);
        } finally {
        	closeConnection(getConnection(), null, null);
        }
        return list;
    }
    
	private Long validarMaisDeUmRegistro(E valor) throws MaisDeUmRegistroException, TableException, TipoChaveNaoEncontradaException, DAOException, SQLException {
    	Connection connection = getConnection();
		PreparedStatement stm = null;
		ResultSet rs = null;
		Long count = null;
		try {
			stm = connection.prepareStatement("SELECT count(*) FROM " + getTableName() + " WHERE " + getNomeCampoChave(getTipoClasse()) + " = ?");
			setParametrosQuerySelect(stm, valor);
			rs = stm.executeQuery();
			if (rs.next()) {
				count = rs.getLong(1);
				if (count > 1) {
					throw new MaisDeUmRegistroException("ENCONTRADO MAIS DE UM REGISTRO DE " + getTableName());
				}
			}
			
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, stm, rs);
		}
		return count;
    }
    
    protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnection();
	}
    
    protected void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}