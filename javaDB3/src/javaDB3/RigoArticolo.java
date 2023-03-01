package javaDB3;

public class RigoArticolo {
	
	private int id;
	private String descrizione;
	private int quantita;
	private float prezzoAcquisto;
	private int giacNuova;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public float getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	public void setPrezzoAcquisto(float prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}
	public int getGiacNuova() {
		return giacNuova;
	}
	public void setGiacNuova(int giacNuova) {
		this.giacNuova = giacNuova;
	}
	
}
