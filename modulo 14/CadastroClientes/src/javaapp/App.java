package javaapp;

import br.com.carthur.dao.ClienteMapDAO;
import br.com.carthur.dao.ClienteSetDAO;
import br.com.carthur.dao.IClienteDAO;
import br.com.carthur.domain.Cliente;

import javax.swing.*;

/**
 * @author rodrigo.pires
 */
public class App {

    private static IClienteDAO iClienteDAO;

    public static void main(String args[]) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(
        	    null,
        	    "Digite a opção desejada:\n" +
        	    "1 - Cadastro\n" +
        	    "2 - Consulta\n" +
        	    "3 - Exclusão\n" +
        	    "4 - Alteração\n" +
        	    "5 - Sair",
        	    "Seu Sistema",
        	    JOptionPane.INFORMATION_MESSAGE
        	);

        
        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(
            	    null,
            	    "Digite a opção desejada:\n" +
            	    "1 - Cadastro\n" +
            	    "2 - Consulta\n" +
            	    "3 - Exclusão\n" +
            	    "4 - Alteração\n" +
            	    "5 - Sair",
            	    "Seu Sistema",
            	    JOptionPane.INFORMATION_MESSAGE
            	);
        }

        while (isOpcaoValida(opcao)) {
            
            if (isOpcaoSair(opcao)) {
                sair();
            }else if (isCadastro(opcao)) {
            	String dados = JOptionPane.showInputDialog(
            		    null,
            		    "Digite os dados do cliente:\n\n" +
            		    "Formato: Nome, CPF, Telefone, Endereço, Número, Cidade, Estado\n\n" +
            		    "Exemplo: João da Silva, 123.456.789-00, (11) 98765-4321, Rua das Flores, 123, São Paulo, SP",
            		    "Cadastro",
            		    JOptionPane.INFORMATION_MESSAGE
            		);
                cadastrar(dados);
            } else if (isConsulta(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente",
                        "Consulta cliente", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            } else if (isExclusao(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente",
                        "Consulta cliente", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            } else if (isAlteracao(opcao)) {
                String cpfConsulta = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente que deseja alterar",
                        "Consulta cliente", JOptionPane.INFORMATION_MESSAGE);
                Cliente clienteConsulta = iClienteDAO.consultar(cpfConsulta);
                if (clienteConsulta != null) {
                    String dados = JOptionPane.showInputDialog(null,
                            "Digite os novos dados do cliente separados por vígula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade e Estado",
                            "Atualização", JOptionPane.INFORMATION_MESSAGE);
                    atualizar(dados);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            opcao = JOptionPane.showInputDialog(
            	    null,
            	    "Digite a opção desejada:\n" +
            	    "1 - Cadastro\n" +
            	    "2 - Consulta\n" +
            	    "3 - Exclusão\n" +
            	    "4 - Alteração\n" +
            	    "5 - Sair",
            	    "Seu Sistema",
            	    JOptionPane.INFORMATION_MESSAGE
            	);

        }

    }

    private static void atualizar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente novoCliente = new Cliente(dadosSeparados[0],dadosSeparados[1].trim(),dadosSeparados[2],dadosSeparados[3],dadosSeparados[4],dadosSeparados[5],dadosSeparados[6]);
        iClienteDAO.alterar(novoCliente);
        JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void excluir(String dados) {
        iClienteDAO.excluir(dados);
        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso: ", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }


    private static void consultar(String dados) {
        Cliente cliente = iClienteDAO.consultar(dados);
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado com sucesso: " + cliente.toString(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private static boolean isAlteracao(String opcao) {
        if ("4".equals(opcao)) {
            return true;
        }
        return false;
    }


    private static void cadastrar(String dados) {
        String[] dadosSeparados  = dados.split(",");
        System.out.println(dadosSeparados[0] + dadosSeparados[1] + dadosSeparados[2] + dadosSeparados[3] + dadosSeparados[4] + dadosSeparados[5] + dadosSeparados[6]);
        Cliente cliente = new Cliente(dadosSeparados[0],dadosSeparados[1],dadosSeparados[2],dadosSeparados[3],dadosSeparados[4],dadosSeparados[5],dadosSeparados[6]);
        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso ", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado", "Erro",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static boolean isExclusao(String opcao) {
        if ("3".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isConsulta(String opcao) {
        if ("2".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static void sair() {
        String clientesCadastrados = "";
        for (Cliente cliente : iClienteDAO.buscarTodos()) {
            clientesCadastrados += cliente.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, "Clientes cadastrados: " + clientesCadastrados, "Até logo",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao)
                || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        }
        return false;
    }

}