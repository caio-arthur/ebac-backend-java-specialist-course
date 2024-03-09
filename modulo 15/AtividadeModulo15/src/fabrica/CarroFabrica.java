package fabrica;

public class CarroFabrica extends Fabrica {

	@Override
	Carro recuperarCarro(String requisicaoGrade) {
		switch(requisicaoGrade) {
			case "A":
				return new Celta(78,"cheio", "azul");
			case "B":
				return new Palio(55,"cheio", "azul");
			default:
				System.out.println("o carro solicitado infelizmente não estava disponível");
				return null;
		}
	}

	
}
