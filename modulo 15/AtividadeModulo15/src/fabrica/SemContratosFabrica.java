package fabrica;

public class SemContratosFabrica extends Fabrica {

	@Override
	Carro recuperarCarro(String requestedGrade) {
		 if ("A".equals(requestedGrade)) {
	            return new Palio(55, "cheio", "azul");
	        } else {
	        	return new Uno(77, "cheio", "azul");
	        }
	}

}
