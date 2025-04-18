package br.com.carthur.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.carthur.domain.Marca;
import br.com.carthur.service.IMarcaService;

@Named
@ViewScoped
public class MarcaController implements Serializable {

	private static final long serialVersionUID = 8115699842483842109L;
	
	private Marca marca;
	private Collection<Marca> marcas;
	private Boolean isUpdate;

	@Inject
	private IMarcaService marcaService;
	
	@PostConstruct
	public void init() {
		try {
			this.isUpdate = false;
			this.marca = new Marca();
			this.marcas = marcaService.buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Collection<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(Collection<Marca> marcas) {
		this.marcas = marcas;
	}
	
	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void cancel() {
		try {
			this.isUpdate = false;
			this.marca = new Marca();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar cancelar a operação"));
		}
	}
	
	public void edit(Marca marca) {
		try {
			this.isUpdate = true;
			this.marca = marca;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar editar o cliente"));
		}
	}
	
	public void delete(Marca marca) {
		try {
			marcaService.excluir(marca);
			marcas.remove(marca);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar excluir a marca"));
		}
	}
	
	public void add() {
		try {
			marcaService.cadastrar(marca);
			this.marcas = marcaService.buscarTodos();
			this.marca = new Marca();
			cancel();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar cadastrar a marca"));
		}
	}
	
	public void update() {
		try {
			marcaService.alterar(this.marca);
			cancel();
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Marca atualizada com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar atualizar a marca"));
		}
	}

}
