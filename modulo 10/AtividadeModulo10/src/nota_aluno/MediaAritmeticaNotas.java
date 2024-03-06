package nota_aluno;

public class MediaAritmeticaNotas {
	
	public float nota1;
    public float nota2;
    public float nota3;
    public float nota4;
    
    public MediaAritmeticaNotas(float nota1, float nota2, float nota3, float nota4) {
    	this.nota1 = nota1;
    	this.nota2 = nota2;
    	this.nota3 = nota3;
    	this.nota4 = nota4;
    }
    
    //Função para realizar o cálculo da Média Aritmética
    public float CalcularMedia() {
        float somaNotas = nota1 + nota2 + nota3 + nota4;
        return somaNotas / 4;
    }
}
