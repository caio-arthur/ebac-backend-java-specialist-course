package carthur;

public class Main {
	public static void main(String[] args) {
		// Exemplo de uso da pilha
		Stack stack = new Stack(5);
		stack.push(10);
		stack.push(20);
		stack.push(30);
		
		System.out.println("Top element: " + stack.top()); // Deve imprimir 30
		System.out.println("Stack size: " + stack.size()); // Deve imprimir 3
		
		while (!stack.isEmpty()) {
			System.out.println("Popped element: " + stack.pop());
		}
		
		System.out.println("Stack empty? " + stack.isEmpty()); // Deve imprimir true
	}
}
