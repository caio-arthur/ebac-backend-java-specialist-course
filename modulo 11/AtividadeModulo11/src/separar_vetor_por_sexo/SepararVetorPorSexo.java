package separar_vetor_por_sexo;

public class SepararVetorPorSexo {
	
	public String[] SepararMasculino(String[] nomesESexo) {
        int contadorMasculino = 0;
        for (String nomeESexo : nomesESexo) {
            String[] partes = nomeESexo.split("-");
            String sexo = partes[1];
            if (sexo.equalsIgnoreCase("M")) {
                contadorMasculino++;
            }
        }
        
        String[] listaMasculino = new String[contadorMasculino];
        contadorMasculino = 0;
        for (String nomeESexo : nomesESexo) {
            String[] partes = nomeESexo.split("-");
            String nome = partes[0];
            String sexo = partes[1];
            if (sexo.equalsIgnoreCase("M")) {
                listaMasculino[contadorMasculino++] = nome;
            }
        }
        
        return listaMasculino;
    }
    
    public String[] SepararFeminino(String[] nomesESexo) {
        int contadorFeminino = 0;
        for (String nomeESexo : nomesESexo) {
            String[] partes = nomeESexo.split("-");
            String sexo = partes[1];
            if (sexo.equalsIgnoreCase("F")) {
                contadorFeminino++;
            }
        }
        
        String[] listaFeminino = new String[contadorFeminino];
        contadorFeminino = 0;
        for (String nomeESexo : nomesESexo) {
            String[] partes = nomeESexo.split("-");
            String nome = partes[0];
            String sexo = partes[1];
            if (sexo.equalsIgnoreCase("F")) {
                listaFeminino[contadorFeminino++] = nome;
            }
        }
        
        return listaFeminino;
    }
	
}
