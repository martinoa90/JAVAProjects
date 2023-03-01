package javaDB3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.time.LocalDate;

public class BL {

	public Articoli findArticolo(String descrizione) {
		Articoli result = new Articoli();

		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		ResultSet resultSet = null;

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "SELECT * from articoli WHERE descrizione=" + "'" + descrizione + "'"; // 2 scrivo query
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) { // 3 leggere risultati
				result.setDescrizione(resultSet.getString("descrizione"));
				result.setCategoria_articolo(resultSet.getString("categoria_articolo"));
				result.setPrezzo_acquisto(resultSet.getFloat("prezzo_acquisto"));
				result.setRicarico(resultSet.getInt("ricarico"));
				result.setA_iva(resultSet.getInt("a_iva"));
				result.setGiacenze(resultSet.getInt("giacenze"));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public ArrayList<Articoli> findCategory(String categoria_articolo) {

		ArrayList<Articoli> result = new ArrayList<Articoli>();

		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		ResultSet resultSet = null;

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "SELECT * from articoli WHERE categoria_articolo=" + "'" + categoria_articolo + "'";

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Articoli articolo = new Articoli();
				articolo.setDescrizione(resultSet.getString("descrizione"));
				articolo.setCategoria_articolo(resultSet.getString("categoria_articolo"));
				articolo.setPrezzo_acquisto(resultSet.getFloat("prezzo_acquisto"));
				articolo.setRicarico(resultSet.getInt("ricarico"));
				articolo.setA_iva(resultSet.getInt("a_iva"));
				articolo.setGiacenze(resultSet.getInt("giacenze"));

				result.add(articolo);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public void salvaArticolo(String valori) {
		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		ResultSet resultSet = null;

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO articoli (descrizione, categoria_articolo, prezzo_acquisto, ricarico, a_iva, giacenze) VALUES ("
					+ valori + ");";
			statement.executeUpdate(sql);
			System.out.println("Articolo inserito correttamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public ArrayList<String> elencoCategorie() {

		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		ArrayList<String> result = new ArrayList<String>();

		ResultSet resultSet = null;

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "SELECT DISTINCT categoria_articolo from articoli "; // 2 scrivo query
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) { // 3 leggere risultati
				result.add(resultSet.getString("categoria_articolo"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public int getNumScontrino() {
		int numScontrino = 0;
		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";
		ResultSet resultSet = null;
		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "SELECT max(numero_scontrino) from testata_scontrino"; // 2 scrivo query
			resultSet = statement.executeQuery(sql);
			resultSet.next();
			numScontrino = resultSet.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return (numScontrino + 1);
	}

	public void registrareTestata(int numScontrino, LocalDate date, String tipoPagamento) {
		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO testata_scontrino VALUES ('" + date + "'," + numScontrino + ", '" + tipoPagamento
					+ "')";

			statement.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int findIdTestata(int numScontrino) {
		int idTestata = 0;
		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		ResultSet resultSet = null;
		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "SELECT id from testata_scontrino WHERE numero_scontrino=" + numScontrino; // 2 scrivo query
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) { // 3 leggere risultati
				idTestata = resultSet.getInt(1);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return idTestata;

	}

	public int findIdArticolo(String descArticolo) {
		int idArticolo = 0;
		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		ResultSet resultSet = null;

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "SELECT id from articoli WHERE descrizione=" + "'" + descArticolo + "'"; // 2 scrivo query
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) { // 3 leggere risultati
				idArticolo = resultSet.getInt(1);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return idArticolo;

	}

	public void registrareRiga(int idTestata, int idArticolo, int qta) {
		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO righe_scontrino VALUES (" + idTestata + "," + idArticolo + ", " + qta + ")";

			statement.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public ArrayList<RigoArticolo> getRigheScontrino(int numScontrino) {

		ArrayList<RigoArticolo> result = new ArrayList<RigoArticolo>();

		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		ResultSet resultSet = null;

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "select * from tot_scontrini where numero_scontrino=" + numScontrino;

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				RigoArticolo articolo = new RigoArticolo();
				articolo.setDescrizione(resultSet.getString("descrizione"));
				articolo.setPrezzoAcquisto(resultSet.getFloat("prezzo_acquisto"));
				articolo.setQuantita(resultSet.getInt("qta"));

				result.add(articolo);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public float getTotaleScontrino(int numScontrino) {
		float totale = 0;
		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		ResultSet resultSet = null;

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "select sum(prezzo_acquisto * qta) as totale from tot_scontrini where numero_scontrino="
					+ numScontrino;

			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) { // 3 leggere risultati
				totale = resultSet.getFloat(1);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return totale;
	}

	public int checkGiacenze(int qta, int idArticolo) {
		int stockFinale = 0;
		int stock = 0;
		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		ResultSet resultSet = null;

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "select giacenze from articoli where id=" + idArticolo;

			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) { // 3 leggere risultati
				stock = resultSet.getInt(1);
			}

			stockFinale = stock - qta;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return stockFinale;
	}

	public void updateGiacenze(int qta, int idArticolo) {
		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "UPDATE articoli SET giacenze =" + qta + " where id=" + idArticolo;

			statement.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void salvaFornitore(String datiFornitore) {
		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		ResultSet resultSet = null;

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO fornitori (nome, cognome, indirizzo, citta, telefono, email) VALUES ("
					+ datiFornitore + ");";
			statement.executeUpdate(sql);
			System.out.println("Fornitore inserito correttamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	public ArrayList<Fornitore> elencoFornitori() {

		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		ArrayList<Fornitore> result = new ArrayList<Fornitore>();

		ResultSet resultSet = null;

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "SELECT * from fornitori"; // 2 scrivo query
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) { // 3 leggere risultati
				Fornitore fornitore=new Fornitore();
				fornitore.setNome(resultSet.getString("nome"));
				fornitore.setCognome(resultSet.getString("cognome"));
				fornitore.setIndirizzo(resultSet.getString("indirizzo"));
				fornitore.setCitta(resultSet.getString("citta"));
				fornitore.setTelefono(resultSet.getString("telefono"));
				fornitore.setEmail(resultSet.getString("email"));
				
				result.add(fornitore);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public Fornitore findFornitore(String nome, String cognome) {

		Fornitore fornitore=new Fornitore();
		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		ResultSet resultSet = null;
		try {	
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "SELECT * from fornitori WHERE nome='" + nome + "' AND cognome= '"+cognome+ "'"; // 2 scrivo query
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) { // 3 leggere risultati
				fornitore.setNome(resultSet.getString("nome"));
				fornitore.setCognome(resultSet.getString("cognome"));
				fornitore.setIndirizzo(resultSet.getString("indirizzo"));
				fornitore.setCitta(resultSet.getString("citta"));
				fornitore.setTelefono(resultSet.getString("telefono"));
				fornitore.setEmail(resultSet.getString("email"));
			
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return fornitore;
	}
	
	public void updateFornitore(String colonna, String valore, String nome, String cognome) {
		String connectionString = "jdbc:sqlserver://martinolivieri.mssql.somee.com;user=martinoa90_SQLLogin_1;password=odbjhmohhf;trustServerCertificate=true;databaseName=martinolivieri;encrypt=true;integratedSecurity=false;";

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			String sql = "UPDATE fornitori SET " + colonna + "= '"+ valore +"' WHERE nome='" + nome + "' AND cognome= '"+cognome+ "'";

			statement.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}