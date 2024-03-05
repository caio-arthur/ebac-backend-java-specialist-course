import java.util.Scanner;

public class MediaAritmeticaNotas {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite a 1ᵃ nota:");
        float nota1 = input.nextFloat();
        System.out.println("Digite a 2ᵃ nota:");
        float nota2 = input.nextFloat();
        System.out.println("Digite a 3ᵃ nota:");
        float nota3 = input.nextFloat();
        System.out.println("Digite a 4ᵃ nota:");
        float nota4 = input.nextFloat();

        float media = CalcularMedia(nota1, nota2, nota3, nota4);

        System.out.println("A média aritmética entre os valores digitados é: " + media);

        input.close();
    }
    
    //Função para realizar o Cálculo da Média Aritmética
    public static float CalcularMedia(float nota1, float nota2, float nota3, float nota4) {
        float somaNotas = nota1 + nota2 + nota3 + nota4;
        return somaNotas / 4;
    }
}
