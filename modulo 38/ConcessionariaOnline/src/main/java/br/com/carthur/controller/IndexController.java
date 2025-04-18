/**
 * 
 */
package br.com.carthur.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * @author caio.arthur
 *
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

	private static final long serialVersionUID = 2947221482539185802L;

	public String returnHomePage() {
	    return "/?faces-redirect=true";
	}
	
	public String goToMarcaPage() {
	    return "/marca/list.xhtml?faces-redirect=true";
	}

	public String goToAcessorioPage() {
	    return "/acessorio/list.xhtml?faces-redirect=true";
	}

	public String goToCarroPage() {
	    return "/carro/list.xhtml?faces-redirect=true";
	}
	
	public String goToCarroDetailsPage(Long id) {
	    return "/carro/details/info.xhtml?id=" + id + "&faces-redirect=true";
	}
}
