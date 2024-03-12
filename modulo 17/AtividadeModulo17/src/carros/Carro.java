package carros;

public class Carro {

    private String marca;
    private String modelo;
    private int ano;
    private double preco;

    public Carro(String marca, String modelo, int ano, double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void exibirInformacoes() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Preço: R$" + preco);
    }

    // Método para ligar o carro
    public void ligar() {
        System.out.println("O carro está ligado.");
    }

    // Método para desligar o carro
    public void desligar() {
        System.out.println("O carro está desligado.");
    }

    // Método para acelerar
    public void acelerar() {
        System.out.println("O carro está acelerando.");
    }

    // Método para frear
    public void frear() {
        System.out.println("O carro está freando.");
    }
}

