package br.com.lincoln.intencaovoto.mb;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.lincoln.intencaovoto.ejb.GerenciadorFicha;
import br.com.lincoln.intencaovoto.jpa.Ficha;
import br.com.lincoln.intencaovoto.util.TestConnectionManager;

@ManagedBean
@ViewScoped
public class IndexMB {

	@EJB
	private GerenciadorFicha gf = new GerenciadorFicha();

	private static boolean cloudStatus;
	
	private Ficha ficha;
	
	private Ficha fichaBrowser;
	

	public IndexMB() {
		cloudStatus = TestConnectionManager.getTc().getStatusConnection();
	}
	
	public Ficha getFicha() {
		if (ficha == null) {
			ficha = new Ficha();
		}
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public Ficha getFichaBrowser() {
		if(fichaBrowser == null){
			fichaBrowser = new Ficha();
		}
		return fichaBrowser;
	}

	public void setFichaBrowser(Ficha fichaBrowser) {
		this.fichaBrowser = fichaBrowser;
	}

	public void salvarFicha(ActionEvent event) {
		cloudStatus = TestConnectionManager.getTc().getStatusConnection();
		try {
			if(cloudStatus){
				gf.addFicha(ficha);
				ficha = new Ficha();
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível salvar sua ficha, salve offline!", null));  
			gf.testConnection();
		}
	}
	
	public String salvarFichaBrowser() {
		cloudStatus = TestConnectionManager.getTc().getStatusConnection();
		try {
			if(cloudStatus){
				gf.addFicha(fichaBrowser);
				fichaBrowser = new Ficha();
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			gf.testConnection();
		}
		return null;
	}

	public boolean getCloudStatus() {
		cloudStatus = TestConnectionManager.getTc().getStatusConnection();
		return cloudStatus;
	}

}
