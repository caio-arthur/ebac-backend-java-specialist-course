package fabrica;

public class ContratosFabrica extends Fabrica {

	@Override
	Carro recuperarCarro(String requestedGrade) {
		if ("A".equals(requestedGrade)) {
            return new Celta(78, "cheio", "azul");
        } else {
        	return new Onix(82, "cheio", "azul");
        }
	}
	
}
