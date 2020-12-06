package Beans;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named("fragenMB")
@RequestScoped
public class FragenMB {

	/**
	 * 	!!!!!!!!!!IMPORTANT INFORMATION!!!!!!!!!!!!!!!!!!!!!!!!
	 	******Diese Klasse ist nur fuer Testzwecke des Frontends gedacht. Der Zugriff auf die Fragen 
	 	soll spaeter ueber den Fragenkatalog laufen und die Speicherung der Antworten ueber den Antrag******
	 	*
	 */
	
	
	private static final long serialVersionUID = 2526217096894280449L;
	
	private String [] alleFragen = new String [30];
	private int FragenCounter = 0;
	private String eineFrage = "Wer wie was?";
	private String Antworten;


	public FragenMB() {
	}


	
	public String getFragen() {
		FragenCounter++;
		System.out.println("FragenCounter");
	
		return alleFragen[FragenCounter-1];
		
	}
	
	public void iniFragen() {
		// Kategorie 1
		alleFragen[0] = "Wie gehts es Ihnen heute?";
		alleFragen[1] = "Was haben Sie heute vor";
		alleFragen[2] = "Wo drueckt der Schuh?";
		
		// Kategorie 2
		alleFragen[3] = "Wieso liegt hier Stroh?";
		alleFragen[4] = "Wo ist der Bus?";
		alleFragen[5] = "Wie war die Nacht?";
		alleFragen[6] = "Was gabs zum Fruehstueck?";
		alleFragen[7] = "Wie ist das Wetter?";
		alleFragen[8] = "Wieso bist du hier?";
	
	}



	public String getAntworten() {
		return Antworten;
	}



	public void setAntworten(String antworten) {
		Antworten = antworten;
	}



	public String[] getAlleFragen() {
		return alleFragen;
	}



	public void setAlleFragen(String[] alleFragen) {
		this.alleFragen = alleFragen;
	}



	public String getEineFrage() {
		return eineFrage;
	}



	public void setEineFrage(String eineFrage) {
		this.eineFrage = eineFrage;
	}
	
	
	
	
	



	

}
