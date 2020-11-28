package pack;


import java.io.IOException;

import javax.annotation.security.RolesAllowed;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;





@Named("menueMB")
@RequestScoped
public class MenuMB {

	

	public MenuMB() {
	}

	
	




	public String starteKursAnlegen() {
		return "KURS_ANLEGEN";
	}

	public String starteKursBelegung() {
		return "KURS_BELEGUNG";
	}

}