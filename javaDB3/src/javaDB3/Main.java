package javaDB3;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

	static ArrayList<String> listaCat = new ArrayList<String>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner ks = new Scanner(System.in);

		char choice = ' ';

		while (choice != 'x') {
			System.out.println("MENU: " + "\n" + "a)Ricerca per descrizione" + "\n" + "b)Ricerca per categoria " + "\n"
					+ "c)Inserisci nuovo articolo " + "\n" + "d)Elenco categorie " + "\n" + "e)Inserire scontrino"
					+ "\n" + "f)Visualizza scontrino" + "\n" + "g)Inserisci nuovo fornitore " + "\n"
					+ "h)Elenco fornitori " + "\n" + "i)Ricerca/modifica fornitori " + "\n" +"x)Fine");
			choice = ks.next().charAt(0);

			switch (choice) {
			case 'a':
				ricercaDesc();
				break;
			case 'b':
				ricercaCat();
				break;
			case 'c':
				inserireArticolo();
				break;
			case 'd':
				elencoCategorie();
				break;
			case 'e':
				inserireScontrino();
				break;
			case 'f':
				visualizzaScontrino();
				break;
			case 'g':
				inserireFornitore();
				break;
			case 'h':
				elencoFornitori();
				break;
			case 'i':
				ricercaFornitore();
				break;
			case 'x':
				System.out.println("Programma terminato.");
				break;
			default:
				System.out.println("Opzione non valida, riprova.");
			}

		}

	}

	public static void ricercaDesc() {
		Scanner ks = new Scanner(System.in);
		BL bl = new BL();
		Articoli articolo = new Articoli();

		System.out.println("Inserisci la descrizione del prodotto che vuoi cercare:");
		String prodotto = ks.nextLine();
		articolo = bl.findArticolo(prodotto);

		if (articolo.getDescrizione() == null) {
			System.out.println("Prodotto non trovato.");
		} else {
			System.out.println("Descrizione: " + articolo.getDescrizione());
			System.out.println("Categoria: " + articolo.getCategoria_articolo());
			System.out.println("Prezzo: " + articolo.getPrezzo_acquisto());
			System.out.println("Ricarico: " + articolo.getRicarico());
			System.out.println("IVA: " + articolo.getA_iva());
			System.out.println("Giacenza: " + articolo.getGiacenze());
		}
	}

	public static void ricercaCat() {
		// ricerca per categoria
		Scanner ks = new Scanner(System.in);
		BL bl = new BL();
		ArrayList<Articoli> listaArticoli = new ArrayList<Articoli>();
		boolean found = false;
		String categoriaArticolo = "";

		elencoCategorie();

		while (found == false) {

			System.out.println("Inserisci il numero di categoria del prodotto che vuoi cercare:");
			int numCategoria = ks.nextInt();

			if (numCategoria > 0 && numCategoria < listaCat.size()) {
				found = true;
				categoriaArticolo = listaCat.get(numCategoria - 1);
			} else {
				System.out.println("Categoria non trovata, riprova.");
			}
		}

		listaArticoli = bl.findCategory(categoriaArticolo);

		for (Articoli articolo : listaArticoli) {
			System.out.println("Descrizione: " + articolo.getDescrizione());
			System.out.println("Categoria: " + articolo.getCategoria_articolo());
			System.out.println("Prezzo: " + articolo.getPrezzo_acquisto());
			System.out.println("Ricarico: " + articolo.getRicarico());
			System.out.println("IVA: " + articolo.getA_iva());
			System.out.println("Giacenza: " + articolo.getGiacenze());
		}

	}

	public static void inserireArticolo() {
		Articoli articolo = new Articoli();
		BL bl = new BL();
		Scanner ks = new Scanner(System.in);

		System.out.println("Inserisci descrizione:");
		articolo.setDescrizione(ks.nextLine());
		System.out.println("Inserisci categoria articolo:");
		articolo.setCategoria_articolo(ks.nextLine());
		System.out.println("Inserisci prezzo acquisto:");
		articolo.setPrezzo_acquisto(ks.nextFloat());
		System.out.println("Inserisci ricarico:");
		articolo.setRicarico(ks.nextInt());
		System.out.println("Inserisci IVA:");
		articolo.setA_iva(ks.nextInt());
		System.out.println("Inserisci giacenza:");
		articolo.setGiacenze(ks.nextInt());

		String valori = "'" + articolo.getDescrizione() + "'" + "," + "'" + articolo.getCategoria_articolo() + "'" + ","
				+ articolo.getPrezzo_acquisto() + "," + articolo.getRicarico() + "," + articolo.getA_iva() + ","
				+ articolo.getGiacenze();

		if (articolo.getFlag()) {
			System.out.println("Errore nella carica di dati, riprova.");
		} else {
			bl.salvaArticolo(valori);
		}

	}

	public static void elencoCategorie() {
		BL bl = new BL();
		System.out.println("Elenco Categorie:");
		listaCat = bl.elencoCategorie();
		int i = 1;
		for (String string : listaCat) {
			System.out.println(i + "- " + string);
			i++;
		}
	}

	public static void inserireScontrino() {
		BL bl = new BL();
		int numScontrino = bl.getNumScontrino();
		LocalDate todaysDate = LocalDate.now();
		Scanner ks = new Scanner(System.in);
		Scanner ks2 = new Scanner(System.in);
		boolean flag = true;
		boolean found = false;
		int idArticolo;
		int qtaNuova = -1;
		ArrayList<RigoArticolo> listaArticoli = new ArrayList<RigoArticolo>();
		System.out.println("Stai inserendo lo scontrino num " + numScontrino + " del giorno " + todaysDate);

		System.out.println("Inserisci il tipo di pagamento:");
		String tipoPagamento = ks.nextLine();

		while (flag) {
			RigoArticolo rigoArt = new RigoArticolo();

			while (found == false) {
				System.out.println("Inserisci articolo:");
				String descArticolo = ks.nextLine();
				idArticolo = bl.findIdArticolo(descArticolo);
				if (idArticolo != 0) {
					rigoArt.setId(idArticolo);
					rigoArt.setDescrizione(descArticolo);
					while (found == false) {
						System.out.println("Inserisci quantita");
						int qta = ks2.nextInt();
						qtaNuova = bl.checkGiacenze(qta, idArticolo);
						if (qtaNuova >= 0) {
							rigoArt.setQuantita(qta);
							rigoArt.setGiacNuova(qtaNuova);
							found = true;
						} else {
							System.out.println("Giacenza non sufficiente, riprova");

						}
					}

				} else {
					System.out.println("Articolo non trovato, riprova");
				}
			}
			found = false;
			listaArticoli.add(rigoArt);

			System.out.println("Sono gia presenti " + listaArticoli.size() + " articoli in questo scontrino.");

			System.out.println("Vuoi inserire un altro articolo? s/n");
			char risposta = ks.nextLine().charAt(0);

			if (risposta == 'n') {
				flag = false;
				bl.registrareTestata(numScontrino, todaysDate, tipoPagamento);
				int idTestata = bl.findIdTestata(numScontrino);

				for (int n = 0; n != listaArticoli.size(); n++) {
					idArticolo = listaArticoli.get(n).getId();
					int qtaArticolo = listaArticoli.get(n).getQuantita();
					bl.registrareRiga(idTestata, idArticolo, qtaArticolo);
					int giacNuova = listaArticoli.get(n).getGiacNuova();
					bl.updateGiacenze(giacNuova, idArticolo);

				}

				System.out.println("Scontrino ingresato correttamente.");
			}

		}
	}

	public static void visualizzaScontrino() {
		BL bl = new BL();
		Scanner ks = new Scanner(System.in);
		boolean flag = false;
		ArrayList<RigoArticolo> righeArticoli = new ArrayList<RigoArticolo>();

		while (flag == false) {
			System.out.println("Inserisci il numero dello scontrino a visualizzare:");
			int numScontrino = ks.nextInt();
			righeArticoli = bl.getRigheScontrino(numScontrino);

			if (righeArticoli.size() == 0) {
				System.out.println("Scontrino non trovato, riprova.");

			} else {
				System.out.println("Articolo	PrezzoUnit");
				for (RigoArticolo rigoArticolo : righeArticoli) {

					System.out.print(rigoArticolo.getDescrizione() + "  x ");
					System.out.print(rigoArticolo.getQuantita() + "	");
					System.out.println(rigoArticolo.getPrezzoAcquisto());
				}
				float totaleScontrino = bl.getTotaleScontrino(numScontrino);
				System.out.println("Totale scontrino: " + totaleScontrino);
				flag = true;
			}
		}

	}

	public static void inserireFornitore() {
		Fornitore fornitore = new Fornitore();
		BL bl = new BL();
		Scanner ks = new Scanner(System.in);
		ArrayList<Fornitore> fornitori=new ArrayList<Fornitore>();
		boolean flag=false;
		
		while(flag==false) {
			System.out.println("Inserisci nome fornitore:");
			String nome= ks.nextLine();
			System.out.println("Inserisci cognome fornitore:");
			String cognome = ks.nextLine();
			
			fornitori=bl.findFornitore(nome, cognome);
			
			if (fornitori.size()!=0) {
				fornitore.setNome(nome);
				fornitore.setCognome(cognome);
				flag=true;
			} else {
				System.out.println("Fornitore gia essistente.Inserisci un altro.");
			}	
		}
		
		System.out.println("Inserisci l'indirizzo:");
		fornitore.setIndirizzo(ks.nextLine());
		System.out.println("Inserisci la citta:");
		fornitore.setCitta(ks.nextLine());
		System.out.println("Inserisci il telefono:");
		fornitore.setTelefono(ks.nextLine());
		System.out.println("Inserisci l'email:");
		fornitore.setEmail(ks.nextLine());

		String datiFornitore = "'" + fornitore.getNome() + "'" + "," + "'" + fornitore.getCognome() + "'" + "," + "'"
				+ fornitore.getIndirizzo() + "'" + "," + "'" + fornitore.getCitta() + "'" + "," + "'"
				+ fornitore.getTelefono() + "'" + "," + "'" + fornitore.getEmail() + "'";

		if (fornitore.isFlag()) {
			System.out.println("Errore nella carica di dati, riprova.");
		} else {
			bl.salvaFornitore(datiFornitore);
		}

	}

	public static void elencoFornitori() {
		BL bl = new BL();
		System.out.println("Elenco Fornitori:");
		ArrayList<Fornitore> listaFornitori = new ArrayList<Fornitore>();
		listaFornitori = bl.elencoFornitori();
		int i = 1;

		for (Fornitore fornitore : listaFornitori) {
			System.out.println("Fornitore num " + i);
			System.out.println("Nome: " + fornitore.getNome());
			System.out.println("Cognome: " + fornitore.getCognome());
			System.out.println("Indirizzo: " + fornitore.getIndirizzo());
			System.out.println("Citta: " + fornitore.getCitta());
			System.out.println("Telefono: " + fornitore.getTelefono());
			System.out.println("Email: " + fornitore.getEmail());
			i++;
		}

	}
	public static void ricercaFornitore() {
		Fornitore fornitore = new Fornitore();
		BL bl = new BL();
		Scanner ks = new Scanner(System.in);
		
		System.out.println("Inserisci nome fornitore:");
		String nome= ks.nextLine();
		System.out.println("Inserisci cognome fornitore:");
		String cognome = ks.nextLine();
		
		fornitore=bl.findFornitore(nome, cognome);
		if(fornitore.getNome()==null) {
			System.out.println("Fornitore non trovato.");
		}else {
			System.out.println("Nome: " + fornitore.getNome());
			System.out.println("Cognome: " + fornitore.getCognome());
			System.out.println("Indirizzo: " + fornitore.getIndirizzo());
			System.out.println("Citta: " + fornitore.getCitta());
			System.out.println("Telefono: " + fornitore.getTelefono());
			System.out.println("Email: " + fornitore.getEmail());
		}
			
		System.out.println("Vuoi modificare dati? s/n");
		char choice = ks.next().charAt(0);
		
		if(choice=='s') {
			System.out.println("Dato a modificare: a-Nome b-Cognome c-Indirizzo d-Citta e-Telefono f-Email");
			choice = ks.next().charAt(0);
			ks.nextLine();
			switch (choice) {
			case 'a':
				System.out.println("Inserisci nuovo nome:");
				String nuovoNome = ks.nextLine();
				bl.updateFornitore("nome", nuovoNome, nome, cognome);
				System.out.println("Nome modificato correttamente.");
				break;
			case 'b':
				System.out.println("Inserisci nuovo cognome:");
				String nuovoCognome = ks.nextLine();
				bl.updateFornitore("cognome", nuovoCognome, nome, cognome);
				System.out.println("Cognome modificato correttamente.");
				break;
			case 'c':
				System.out.println("Inserisci nuovo indirizzo:");
				String nuovoIndirizzo = ks.nextLine();
				bl.updateFornitore("indirizzo", nuovoIndirizzo, nome, cognome);
				System.out.println("Indirizzo modificato correttamente.");
				break;
			case 'd':
				System.out.println("Inserisci la nuova citta:");
				String nuovaCitta = ks.nextLine();
				bl.updateFornitore("citta", nuovaCitta, nome, cognome);
				System.out.println("Citta modificata correttamente.");
				break;
			case 'e':
				System.out.println("Inserisci il nuovo telefono:");
				String nuovoTelefono = ks.nextLine();
				bl.updateFornitore("telefono", nuovoTelefono, nome, cognome);
				System.out.println("Telefono modificato correttamente.");
				break;
			case 'f':
				System.out.println("Inserisci nuovo email:");
				String nuovoEmail = ks.nextLine();
				bl.updateFornitore("email", nuovoEmail, nome, cognome);
				System.out.println("Email modificato correttamente.");
				break;
			default:
				System.out.println("Opzione non valida.");
			}
		}
	}
}
