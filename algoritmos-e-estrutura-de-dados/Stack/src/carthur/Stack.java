package carthur;

public class Stack {
	private int[] stack;
	private int top;
	private int capacity;

	public Stack(int size) {
		stack = new int[size];
		capacity = size;
		top = -1;
	}

	public void push(int item) {
		if (top == capacity - 1) {
			System.out.println("Stack overflow");
			return;
		}
		stack[++top] = item;
	}

	public int pop() {
		if (top == -1) {
			System.out.println("Stack underflow");
			return -1; // Indicating empty stack
		}
		return stack[top--];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public int top() {
		if (top == -1) {
			System.out.println("Stack is empty");
			return -1; // Indicating empty stack
		}
		return stack[top];
	}
	
	public int size() {
		return top + 1;
	}
}
