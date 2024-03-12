package main;
import java.util.*;
import carros.*;

public class Main {

    public static void main(String[] args) {
        Camionete hilux = new Camionete("Toyota", "Hilux", 2020, 226800);
        Camionete ranger = new Camionete("Ford", "Ranger", 2018, 135262);
        Esportivo m4 = new Esportivo("BMW", "M4", 2015, 341436);
        Esportivo carrera = new Esportivo("Porsche", "911 Carrera", 2017, 641860);
        TracaoQuatroRodas forester = new TracaoQuatroRodas("Subaru", "Forester", 2022, 160000);
        TracaoQuatroRodas q5 = new TracaoQuatroRodas("Audi", "Q5", 2023, 240000);

        List<Carro> listaCarros = new ArrayList<>();
       
        listaCarros.add(hilux);
        listaCarros.add(ranger);
        listaCarros.add(m4);
        listaCarros.add(carrera);
        listaCarros.add(forester);
        listaCarros.add(q5);
 
        executarAcoes(listaCarros); // Corrigido para chamar o método executarAcoes
    }

    public static void executarAcoes(List<Carro> carros) {
        for (Carro carro : carros) {
            carro.ligar();

            // Executar método específico de acordo com o tipo do carro
            if (carro instanceof Camionete) {
                ((Camionete) carro).alterarEstadoCarroceria();
            } else if (carro instanceof Esportivo) {
                ((Esportivo) carro).alterarEstadoTetoSolar();
            } else if (carro instanceof TracaoQuatroRodas) {
                ((TracaoQuatroRodas) carro).alterarEstadoTracaoQuatroRodas();
            }

            carro.acelerar();
            carro.frear();

            carro.desligar();

            System.out.println("=================================");
        }
    }
}
