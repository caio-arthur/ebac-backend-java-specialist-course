package br.com.carthur.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.carthur.domain.Acessorio;
import br.com.carthur.exceptions.DAOException;
import br.com.carthur.service.IAcessorioService;
import br.com.carthur.service.ICarroService;

@Named
@ViewScoped
public class CarroAcessorioController implements Serializable {

	private static final long serialVersionUID = 8115699842483842109L;

	private Acessorio acessorio;
	private Collection<Acessorio> todosAcessorios;
	public Collection<Acessorio> getTodosAcessorios() {
		return todosAcessorios;
	}

	public void setTodosAcessorios(Collection<Acessorio> todosAcessorios) {
		this.todosAcessorios = todosAcessorios;
	}

	private Collection<Acessorio> acessorios;
	private Boolean isUpdate;
	private Long idCarro;
	private Long idAcessorio;

	public Long getIdAcessorio() {
		return idAcessorio;
	}

	public void setIdAcessorio(Long idAcessorio) {
		this.idAcessorio = idAcessorio;
	}

	public Long getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(Long idCarro) {
		this.idCarro = idCarro;
	}

	@Inject
	private ICarroService carroService;
	
	@Inject
	private IAcessorioService acessorioService;

	@PostConstruct
	public void init() throws DAOException {
		acessorios = new ArrayList<>();
	    todosAcessorios = acessorioService.buscarTodos(); // Initialize to avoid null
	    String idParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
	    if (idParam != null) {
	        try {
	            idCarro = Long.parseLong(idParam);
	            buscarTodosAcessorios(idCarro);
	        } catch (NumberFormatException e) {
	            idCarro = null;
	        }
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

	public void addAcessorio() {
		try {
			carroService.addAcessorio(idCarro, idAcessorio);
			acessorios.add(acessorio);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("Erro ao tentar adicionar o acessório"));
		}
	}

	public void buscarTodosAcessorios(Long idCarro) {
		try {
			this.acessorios = carroService.buscarTodosAcessorios(idCarro);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("Erro ao tentar buscar os acessórios"));
		}
	}
	
	public void removeAcessorio(Long acessorioId) {
	    try {
	        carroService.excluirAcessorio(idCarro, acessorioId);
	        acessorios.removeIf(acessorio -> acessorio.getId().equals(acessorioId));
	    } catch (Exception e) {
	        FacesContext.getCurrentInstance().addMessage("growl",
	                new FacesMessage("Erro ao tentar remover o acessório"));
	    }
	
	}
}
