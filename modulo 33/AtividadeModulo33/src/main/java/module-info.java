/**
 * 
 */
/**
 * 
 */
module AtividadeModulo33 {
	requires java.persistence;
	requires junit;
	
	opens br.com.carthur.domain to org.hibernate.orm.core;
}