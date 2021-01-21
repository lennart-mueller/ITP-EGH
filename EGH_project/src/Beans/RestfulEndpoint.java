package Beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import antragsverwaltung.entity.AnswerNormalTO;
import antragsverwaltung.entity.ApplicationFormTO;
import antragsverwaltung.usesecase.impl.IRequestForm;
import antragsverwaltung.usesecase.impl.ISaveForm;

//https://www.javacodegeeks.com/2013/01/get-post-with-restful-client-api.html

@RequestScoped
@Path("/egh")
public class RestfulEndpoint {

	// http://localhost:8080/EGH_project/rest/egh/htmlForm/6
	

	private ApplicationFormTO formTO;

	private List<Integer> answerlist = new ArrayList<Integer>();


	@Inject
	IRequestForm requestForm;

	@Inject
	ISaveForm saveForm;




	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getFormByNr/{param}")
	public ApplicationFormTO getFormByNr(@PathParam("param") int key)
			throws AnwendungskernException, DatenhaltungsException {

		try {
			this.formTO = requestForm.getApplicationForm(key);
		} catch (Exception e) {
			System.out.println("Fehler - Form nicht gefunden");

		}

		if (formTO == null) {
			System.out.println("Fehler - Form nicht gefunden");
			return null;

		} else {
			
			System.out.println("laenge der liste"+formTO.getNormalAnswers().size());


			return formTO;
		}

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("changeForm/{param}")
	public void changeForm(@PathParam("param") String message) throws AnwendungskernException, DatenhaltungsException {

		ApplicationFormTO aFormTO = requestForm.getApplicationForm(1);
		aFormTO.getIndividualQuestions().get(0).setQuestion(message);
		saveForm.updateForm(aFormTO);

	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newTodo(@FormParam("formNr") int formNr, @FormParam("AnswerNr") int answerNr,
			@FormParam("Answer") int answer, @Context HttpServletResponse servletResponse)
			throws IOException, AnwendungskernException, DatenhaltungsException {

		ApplicationFormTO aFormTO = requestForm.getApplicationForm(formNr);
		aFormTO.getNormalAnswers().get(answerNr).setOneAnswerNormal(answer);
		saveForm.updateForm(aFormTO);

		servletResponse.sendRedirect("http://localhost:8080/EGH_project/pages/create_form.html");
	}



}