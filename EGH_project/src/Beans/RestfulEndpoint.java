package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import antragsverwaltung.entity.ApplicationFormTO;
import antragsverwaltung.usesecase.impl.IRequestForm;



@RequestScoped
@Path("/egh")
public class RestfulEndpoint {


	private ApplicationFormTO formTO;
	
	private List<Integer> answerlist = new ArrayList<Integer>();

	@Inject
	IRequestForm requestForm;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getForm/{param}")
	public List<Integer> teilnehmerSuchenByNr(@PathParam("param") int key) throws AnwendungskernException, DatenhaltungsException {

	
		try {
			this.formTO = requestForm.getApplicationForm(key);
		} catch (Exception e) {
			System.out.println("Fehler - Form nicht gefunden");

		}

		if (formTO == null) {
			System.out.println("Fehler - Form nicht gefunden");
			return null;

		} else {
			
			for (Integer kteilnnr : formTO.getFormAnswer()) {
				answerlist.add(kteilnnr);
				

				
			}
			return answerlist;
		}

	}


}