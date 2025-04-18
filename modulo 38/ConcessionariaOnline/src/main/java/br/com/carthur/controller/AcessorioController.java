package br.com.carthur.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.carthur.domain.Acessorio;
import br.com.carthur.service.IAcessorioService;

@Named
@ViewScoped
public class AcessorioController implements Serializable {

	private static final long serialVersionUID = 8115699842483842109L;
	
	private Acessorio acessorio;
	private Collection<Acessorio> acessorios;
	private Boolean isUpdate;

	@Inject
	private IAcessorioService acessorioService;
	
	@PostConstruct
	public void init() {
		try {
			this.isUpdate = false;
			this.acessorio = new Acessorio();
			this.acessorios = acessorioService.buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Acessorio getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(Acessorio acessorio) {
		this.acessorio = acessorio;
	}

	public Collection<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(Collection<Acessorio> acessorios) {
		this.acessorios = acessorios;
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
			this.acessorio = new Acessorio();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar cancelar a operação"));
		}
	}
	
	public void edit(Acessorio acessorio) {
		try {
			this.isUpdate = true;
			this.acessorio = acessorio;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar editar o acessório"));
		}
	}
	
	public void delete(Acessorio acessorio) {
		try {
			acessorioService.excluir(acessorio);
			acessorios.remove(acessorio);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar excluir o acessório"));
		}
	}
	
	public void add() {
		try {
			acessorioService.cadastrar(acessorio);
			this.acessorios = acessorioService.buscarTodos();
			this.acessorio = new Acessorio();
			cancel();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar cadastrar o acessório"));
		}
	}
	
	public void update() {
		try {
			acessorioService.alterar(this.acessorio);
			cancel();
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Acessório atualizado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar atualizar o acessório"));
		}
	}

}
