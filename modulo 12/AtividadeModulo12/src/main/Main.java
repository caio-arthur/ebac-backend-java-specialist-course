package main;
import java.util.Scanner;
import java.util.ArrayList;
import usuario.Usuario;

public class Main {

	public static void main(String[] args) {
		//Front: input, a ação para rodar a funcionalidade, e o resultado
		Scanner input = new Scanner(System.in);
		ArrayList<Usuario> usuarios = new ArrayList<>();
		 
		boolean isCadastrando = true;
		int contadorUsuarios = 0;
		
		while (isCadastrando) {
			
		    System.out.println("[" + (contadorUsuarios + 1) + "º usuário]: ");
		    System.out.print("Nome: ");
		    String nome = input.nextLine();
		    
		    System.out.print("Sexo (M ou F): ");
		    
		    boolean resposta1Valida = false;
		    char sexo = 'a';
		    
		    while (!resposta1Valida) {
		    	sexo = input.nextLine().charAt(0);
		        if (sexo == 'M' || sexo == 'F'){
		            resposta1Valida = true;
		        } else {
		            System.out.println("Por favor, digite novamente 'M' para masculino ou 'F' para feminino: ");
		        }
		    }
		    
		    Usuario usuario = new Usuario(nome, sexo);
		    usuarios.add(usuario);
		    contadorUsuarios++;
		    
		    System.out.println("Deseja cadastrar outro usuário? (S/N)");
		    
		    boolean resposta2Valida = false;
		    String resposta = "";
		    
		    while (!resposta2Valida) {
		        resposta = input.nextLine();
		        if (resposta.equalsIgnoreCase("S")) {
		            resposta2Valida = true;
		        } else if (resposta.equalsIgnoreCase("N")) {
		        	isCadastrando = false;
		        } else {
		            System.out.println("Resposta inválida. Por favor, digite 'S' para sim ou 'N' para não: ");
		        }
		    }

            System.out.println("===============");
		}
		
		ArrayList<String> grupoMasculino = new ArrayList<>();
        ArrayList<String> grupoFeminino = new ArrayList<>();
		
        for (Usuario usuario : usuarios) {
            String nomeDoUsuario = usuario.getNome();
            char sexoDoUsuario = usuario.getSexo();
            
            if (sexoDoUsuario == 'M') {
                grupoMasculino.add(nomeDoUsuario); 
            } else if (sexoDoUsuario == 'F') {
                grupoFeminino.add(nomeDoUsuario); 
            }
        }
		
        System.out.println("Grupo Masculino:");
        for (String nome : grupoMasculino) {
            System.out.println(nome);
        }
        System.out.println("===============");
        
        System.out.println("Grupo Feminino:");
        for (String nome : grupoFeminino) {
            System.out.println(nome);
        }
        System.out.println("===============");
        
        
        input.close();
		
	}

}
