package carros;

public class Esportivo extends Carro {

    private boolean isTetoSolarAberto;

    public Esportivo(String marca, String modelo, int ano, double preco) {
        super(marca, modelo, ano, preco);
        this.isTetoSolarAberto = false;
    }

    // Método para verificar se o teto solar está aberto
    public boolean isTetoSolarAberto() {
        return isTetoSolarAberto;
    }

    // Método para abrir ou fechar o teto solar
    public void alterarEstadoTetoSolar() {
        if (isTetoSolarAberto) {
            System.out.println("Fechando o teto solar.");
            isTetoSolarAberto = false;
        } else {
            System.out.println("Abrindo o teto solar.");
            isTetoSolarAberto = true;
        }
    }

    // Método para sobrescrever o método ligar da superclasse
    @Override
    public void ligar() {
        super.ligar(); // Chama o método ligar da superclasse
        if (!isTetoSolarAberto) {
            System.out.println("Atenção: O teto solar está fechado.");
        }
    }
}
