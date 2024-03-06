package main;
import java.util.Scanner;
import vetor_ordem_alfabetica.NomesHandler;
import separar_vetor_por_sexo.SepararVetorPorSexo;

public class Main {

	public static void main(String[] args) {
		
		NomesHandler organizer = new NomesHandler();
		Scanner input = new Scanner(System.in);
		String[] nomes = new String[4];
		
		System.out.println("--------[Atividade 1]--------");
		//Lida com a leitura e validação dos nomes
		for (int i = 0; i < 4; i++) {
            System.out.print("Digite o " + (i + 1) + "º nome: ");
            String nome = input.nextLine();
            if (!organizer.validarNome(nome)) {
                System.out.println("Nome inválido: Digite apenas o primeiro nome. Não deve conter caracteres especiais. O nome deve começar com letra maiúscula seguida de letras minúsculas.");
                i--; // Volta para a mesma iteração do loop
                continue; // Pula para a próxima iteração do loop
            }
            nomes[i] = nome;
        }
		//Organiza o vetor de String em ordem alfabética
		organizer.OrganizarEmOrdemAlfabetica(nomes);
        
		System.out.println("--------[Atividade 2]--------");
		SepararVetorPorSexo separator = new SepararVetorPorSexo();
		String[] nomesESexo = new String[4];
		
		for (int i = 0; i < 4; i++) {
			System.out.println("Digite o nome, informe o sexo. EX: Pedro-M | Maria-F");
			nomesESexo[i] = input.nextLine();
		}
		
		String[] listaMasculino = separator.SepararMasculino(nomesESexo);
		String[] listaFeminino = separator.SepararFeminino(nomesESexo);
		
		System.out.println("Lista Masculinos:");
		for (String nome : listaMasculino) {
		    System.out.println(nome);
		}
		
		System.out.println("--------------------");

		System.out.println("Lista Femininos:");
		for (String nome : listaFeminino) {
		    System.out.println(nome);
		}
		
	}
}
