package fabrica;

/**
 * @author caio.arthur
 */
	public abstract class Carro {
	    private int potenciaCavalo;
	    private String fonteCombustivel;
	    private String cor;

	    public Carro(int potenciaCavalo, String fonteCombustivel, String cor) {
	        this.potenciaCavalo = potenciaCavalo;
	        this.fonteCombustivel = fonteCombustivel;
	        this.cor = cor;
	    }

	    public void ligarMotor() {
	        System.out.println(getClass().getSimpleName());
	        System.out.println("O motor " + fonteCombustivel + " foi ligado e está pronto para utilizar " + potenciaCavalo + " cavalos de potência.\n");
	    }

	    public void limpar() {
	        System.out.println("O carro foi limpo e a cor " + cor.toLowerCase() + " brilha");
	    }

	    public void verificarMecanica() {
	        System.out.println("O carro foi verificado pelo mecânico. Tudo parece estar em ordem!");
	    }

	    public void abastecerCarro() {
	        System.out.println("O carro está sendo abastecido com " + fonteCombustivel.toLowerCase());
	    }
	}


