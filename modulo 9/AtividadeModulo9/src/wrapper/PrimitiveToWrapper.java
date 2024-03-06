package wrapper;
import java.util.Scanner;

public class PrimitiveToWrapper {
	public static void main(String[] Args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Digite um número inteiro: ");
		
		int numeroPrimitivo = input.nextInt();
		Integer numeroWrapper = Integer.valueOf(numeroPrimitivo);
		
		System.out.println("Primitovo: " + numeroPrimitivo);
		System.out.println("Wrapper: " + numeroWrapper);
		
	}
	
}
