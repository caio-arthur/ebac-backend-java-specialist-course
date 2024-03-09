package fabrica;

public class Demo {
	public static void main(String[] args) {
		Cliente cliente = new Cliente("A", true);
		Fabrica fabrica = getFabrica(cliente);
		Carro carro = fabrica.criar(cliente.getRequisicaoGrade());
		carro.ligarMotor();
	}
	
	private static Fabrica getFabrica(Cliente cliente) {
		if(cliente.possuiContratoEmpresa() ) {
			return new ContratosFabrica();
		} else {
			return new SemContratosFabrica();
		}
	}
}
