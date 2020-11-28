

import javax.faces.bean.RequestScoped;
import javax.inject.Named;





@Named("menueMB")
@RequestScoped
public class MenuMB {
	
	private String Name = "hi123";

	

	public MenuMB() {
	}

	
	


	//Wieso wird das nicht aufgerufen????
	public void testeForm() {
		System.out.println("hiushfdiusohdui");
	
		
	}


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}