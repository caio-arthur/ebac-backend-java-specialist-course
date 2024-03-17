package br.com.carthur;
import java.util.*;
import java.util.stream.Collectors;
import br.com.carthur.testes.*;
import javax.swing.JOptionPane;


public class Main {

	public static void main(String[] args) {
		
        ArrayList<String> pessoas = new ArrayList<>();

        String mensagem = "Bem-vindo ao Sistema de Cadastro!\n\n"
                + "Este sistema foi projetado para receber várias pessoas, independentemente do sexo.\n"
                + "Após o cadastro, as mulheres serão automaticamente separadas em uma lista específica.\n";
        
		JOptionPane.showMessageDialog(null, mensagem);
		
		boolean isCadastrando = true;
        int contador = 0;

        while (isCadastrando) {
        	contador++;
            String nome;
            String sexo;

            do {
                nome = JOptionPane.showInputDialog("Pessoa " + contador + "\nDigite o nome completo.");
            } while (nome == null || nome.trim().isEmpty());

            do {
                sexo = JOptionPane.showInputDialog("Pessoa " + contador + "\nDigite M para sexo masculino\nDigite F para sexo feminino");
            } while (sexo == null || (!sexo.equalsIgnoreCase("M") && !sexo.equalsIgnoreCase("F")));
            
            
            String nomeESexo = nome + "-" + sexo;
            pessoas.add(nomeESexo);
            
            int resposta = JOptionPane.showConfirmDialog(null, "Pessoas cadastradas: " + contador + "\nDeseja cadastrar mais pessoas?", "Continuar Cadastro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.NO_OPTION) {
                isCadastrando = false;
            }

            
        }
        
        JOptionPane.showMessageDialog(null, "Cadastro finalizado.");
        List<String> mulheres = pessoas.stream()
                .filter(pessoa -> pessoa.endsWith("-F"))
                .collect(Collectors.toList());     
        
        System.out.println("Mulheres:");
        mulheres.forEach(System.out::println);
    }
	
	
		
}

