package carros;

public class Camionete extends Carro {

    private boolean isCarroceriaAberta;

    public Camionete(String marca, String modelo, int ano, double preco) {
        super(marca, modelo, ano, preco);
        this.isCarroceriaAberta = false; 
    }

    // Método para verificar se a carroceria está aberta
    public boolean isCarroceriaAberta() {
        return isCarroceriaAberta;
    }

    // Método para abrir ou fechar a carroceria
    public void alterarEstadoCarroceria() {
        if (isCarroceriaAberta) {
            System.out.println("Fechando a carroceria.");
            isCarroceriaAberta = false;
        } else {
            System.out.println("Abrindo a carroceria.");
            isCarroceriaAberta = true;
        }
    }

    @Override
    public void ligar() {
        super.ligar(); 
        if (isCarroceriaAberta) {
            System.out.println("Atenção: A carroceria está aberta.");
        }
    }
}
