package media_aritmetica_notas;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
			//Instancia o Scanner
	        Scanner input = new Scanner(System.in);
	        //Recebe 4 valores digitados pelo usuário
	        System.out.println("Digite a 1ᵃ nota:");
	        float nota1 = input.nextFloat();
	        System.out.println("Digite a 2ᵃ nota:");
	        float nota2 = input.nextFloat();
	        System.out.println("Digite a 3ᵃ nota:");
	        float nota3 = input.nextFloat();
	        System.out.println("Digite a 4ᵃ nota:");
	        float nota4 = input.nextFloat();

	        // Cria uma instância da classe MediaAritmeticaNotas
	        MediaAritmeticaNotas calculadora = new MediaAritmeticaNotas(nota1, nota2, nota3, nota4);
	        float media = calculadora.CalcularMedia();

	        System.out.println("A média aritmética entre os valores digitados é: " + media);

	        input.close();

	}

}
