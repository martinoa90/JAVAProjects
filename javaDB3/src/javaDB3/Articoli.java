package javaDB3;

public class Articoli {

	private int id;
	private String descrizione;
	private String categoria_articolo;
	private float prezzo_acquisto;
	private int ricarico;
	private int a_iva;
	private int giacenze;
	private boolean flag = false;

	public boolean getFlag() {
		return flag;
	}
	
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
		if (descrizione.length() < 2) {
			System.out.println("Inserimento non valido.");
			flag = true;
		} else {
			this.descrizione = descrizione;
		}

	}

	public String getCategoria_articolo() {
		return categoria_articolo;
	}

	public void setCategoria_articolo(String categoria_articolo) {
		if (categoria_articolo.length() < 2) {
			System.out.println("Inserimento non valido.");
			flag = true;
		} else {
			this.categoria_articolo = categoria_articolo;
		}
	}

	public float getPrezzo_acquisto() {
		return prezzo_acquisto;
	}

	public void setPrezzo_acquisto(float prezzo_acquisto) {
		if (prezzo_acquisto < 0) {
			System.out.println("Inserimento non valido.");
			flag = true;
		} else {
			this.prezzo_acquisto = prezzo_acquisto;
		}

	}

	public int getRicarico() {
		return ricarico;
	}

	public void setRicarico(int ricarico) {
		if (ricarico < 0) {
			System.out.println("Inserimento non valido.");
			flag = true;
		} else {
			this.ricarico = ricarico;
		}

	}

	public int getA_iva() {
		return a_iva;
	}

	public void setA_iva(int a_iva) {
		if (a_iva < 0) {
			System.out.println("Inserimento non valido.");
			flag = true;
		} else {
			this.a_iva = a_iva;
		}

	}

	public int getGiacenze() {
		return giacenze;
	}

	public void setGiacenze(int giacenze) {
		if (giacenze < 0) {
			System.out.println("Inserimento non valido.");
			flag = true;
		} else {
			this.giacenze = giacenze;
		}

	}

}
