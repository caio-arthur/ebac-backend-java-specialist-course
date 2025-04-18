package br.com.carthur.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.carthur.domain.Carro;
import br.com.carthur.service.ICarroService;
import br.com.carthur.service.IMarcaService;

@Named
@ViewScoped
public class CarroController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Carro carro;
	private Collection<Carro> carros;
	
	private Boolean isUpdate;

	private Long marcaId;

	@Inject
	private ICarroService carroService;

	@Inject
	private IMarcaService marcaService;

	@PostConstruct
	public void init() {
		try {
			this.isUpdate = false;
			this.carro = new Carro();
			this.carros = carroService.buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Collection<Carro> getCarros() {
		return carros;
	}

	public void setCarros(Collection<Carro> carros) {
		this.carros = carros;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public Long getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Long marcaId) {
		this.marcaId = marcaId;
	}

	public void cancel() {
		try {
			this.isUpdate = false;
			this.carro = new Carro();
			this.marcaId = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("Erro ao tentar cancelar a operação"));
		}
	}

	public void edit(Carro carro) {
		try {
			this.isUpdate = true;
			this.carro = carro;
			this.marcaId = carro.getMarca().getId();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar editar o carro"));
		}
	}

	public void delete(Carro carro) {
		try {
			carroService.excluir(carro);
			carros.remove(carro);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar excluir o carro"));
		}
	}

	public void add() {
		try {
			if (marcaId != null) {
				carro.setMarca(marcaService.consultar(marcaId));
			}
			carroService.cadastrar(carro);
			this.carros = carroService.buscarTodos();
			this.carro = new Carro();

			cancel();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar cadastrar o carro"));
		}
	}

	public void update() {
	    try {
	        if (marcaId != null) {
	            carro.setMarca(marcaService.consultar(marcaId));
	        }
	        carroService.alterar(this.carro);
	        cancel();
	        FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Carro atualizado com sucesso"));
	    } catch (Exception e) {
	        FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar atualizar o carro"));
	    }
	}
	
//	public void addAcessorio(Long idCarro, Long idAcessorio) {
//		try {
//			carroService.addAcessorio(idCarro, idAcessorio);
//			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Acessório adicionado com sucesso"));
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar adicionar o acessório"));
//		}
//	}
//	
//	public void buscarTodosAcessorios(Long idCarro) {
//		try {
//			this.acessorios = carroService.buscarTodosAcessorios(idCarro);
//		} catch (Exception e) {
//			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Erro ao tentar buscar os acessórios"));
//		}
//	}	
}
