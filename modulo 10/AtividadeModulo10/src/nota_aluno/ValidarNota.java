package nota_aluno;
import java.util.Scanner;

public class ValidarNota {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        float[] notas = new float[4]; 
        
        for (int i = 0; i < 4; i++) {
            System.out.print("Digite a " + (i + 1) + "ᵃ nota: ");
            notas[i] = input.nextFloat();
        }

        MediaAritmeticaNotas calculadora = new MediaAritmeticaNotas(notas[0], notas[1], notas[2], notas[3]);
        float media = calculadora.CalcularMedia();
        
        System.out.println("Média Atingida: " + media);
        
        if (media >= 7) {
        	System.out.println("Aluno Aprovado!");
        } else if (media >= 5) {
        	System.out.println("Aluno de Recuperação.");
        } else {
        	System.out.println("Aluno Reprovado.");
        }
    }

}
