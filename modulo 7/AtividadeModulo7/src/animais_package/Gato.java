package animais_package;

public class Gato {

    //Atributos da classe Gato (privados para encapsulamento)
	private int idadeEmAnos;
	private double pesoEmKg;
	private String raca;
	private String corPelo;
	private String sexo;
	
	//Método construtor da Classe Gato
	public Gato(int idadeEmAnos, double pesoEmKg, String raca, String corPelo, String sexo) {
		this.idadeEmAnos = idadeEmAnos;
		this.pesoEmKg = pesoEmKg;
		this.raca = raca;
		this.corPelo = corPelo;
		this.sexo = sexo;
	}
	
	//Métodos getters e setters
	public int getIdadeEmAnos() {
		return idadeEmAnos;
	}

	public void setIdadeEmAnos(int idadeEmAnos) {
		this.idadeEmAnos = idadeEmAnos;
	}

	public double getPesoEmKg() {
		return pesoEmKg;
	}

	public void setPesoEmKg(double pesoEmKg) {
		this.pesoEmKg = pesoEmKg;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getCorPelo() {
		return corPelo;
	}

	public void setCorPelo(String corPelo) {
		this.corPelo = corPelo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
	//Métodos para o comportamento do gato
	public void Miar() {
		System.out.println("Meow!");
	}
	
	public void Ronronar() {
		System.out.println("Prrrr...");
	}
 	
	public void Intimidar() {
		System.out.println("Grrrrr!");
	}
	
}
