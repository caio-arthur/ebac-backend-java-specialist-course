package tabelas;

import annotation.TabelaAnnotation;

public class AppReflections {
    public static void main(String[] args) throws NoSuchMethodException {

        Class classeClientes = Clientes.class;
        printTabelaValue(classeClientes);

        Class classeProdutos = Produtos.class;
        printTabelaValue(classeProdutos);

        Class classeVendas = Vendas.class;
        printTabelaValue(classeVendas);
    }

	private static <T> void printTabelaValue(Class<T> clazz) throws NoSuchMethodException {
	    if (clazz.isAnnotationPresent(TabelaAnnotation.class)) {
	        TabelaAnnotation tabelaAnnotation = clazz.getAnnotation(TabelaAnnotation.class);
	        String value = tabelaAnnotation.value();
	        System.out.println("Valor da anotação para " + clazz.getSimpleName() + ": " + value);
	    } else {
	        System.out.println("A anotação não está presente na classe " + clazz.getSimpleName());
	    }
	}
}