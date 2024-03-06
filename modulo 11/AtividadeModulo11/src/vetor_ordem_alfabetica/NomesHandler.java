package vetor_ordem_alfabetica;

import java.util.Arrays;

public class NomesHandler {
    
	public void OrganizarEmOrdemAlfabetica(String[] nomes) {
        Arrays.sort(nomes);
        System.out.println("Lista Ordem Alfabética:");
        for (String nome : nomes) {
            System.out.println(nome);
        }
    }

	public boolean validarNome(String nome) {
	    // Verifica se o nome começa com letra maiúscula seguida de letras minúsculas
	    return nome.matches("[A-Z][a-z]*");
	}
}
