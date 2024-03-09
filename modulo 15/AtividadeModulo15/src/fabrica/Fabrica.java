package fabrica;

/**
 * @author caio.arthur
 */
public abstract class Fabrica {
	public Carro criar(String requestedGrade) {
        Carro car = recuperarCarro(requestedGrade);
        car = prepararCarro(car);
        return car;
    }
    private Carro prepararCarro(Carro car){
        car.limpar();
        car.verificarMecanica();
        car.abastecerCarro();
        return car;
    }
    abstract Carro recuperarCarro(String requestedGrade);
}
