package carros;

public class TracaoQuatroRodas extends Carro {
    private boolean tracaoQuatroRodas;

    public TracaoQuatroRodas(String marca, String modelo, int ano, double preco) {
        super(marca, modelo, ano, preco);
        this.tracaoQuatroRodas = false; 
    }

    // Método para verificar se a tração nas quatro rodas está ativada
    public boolean isTracaoQuatroRodas() {
        return tracaoQuatroRodas;
    }

    // Método para ativar ou desativar a tração nas quatro rodas
    public void alterarEstadoTracaoQuatroRodas() {
        if (tracaoQuatroRodas) {
            System.out.println("Desativando a tração nas quatro rodas.");
            tracaoQuatroRodas = false;
        } else {
            System.out.println("Ativando a tração nas quatro rodas.");
            tracaoQuatroRodas = true;
        }
    }
}
