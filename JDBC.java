import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;

public class ServizioDelivery 
{
	public static void main(String[] args)
	{
		System.out.println("Inserire operazione da inserire");
		System.out.println("1- Registrazione di un ordine");
		System.out.println("2- Consegna di un ordine");
		System.out.println("3- Verifica della possibilità di effettuare un ordine ad un determinato ristorante");
		System.out.println("4- Visualizzazione dei ristoranti disponibili all’accettazione di un ordine");
		System.out.println("5- Valutazione di un rider");
		System.out.println("6- Visualizzazione per ogni cliente del numero di ordini effettuati"); 
		System.out.println("7- Abilitazione dell’affidamento ad una società di un servizio di delivery");
		System.out.println("8- Assunzione di un nuovo dipendente per la consegna degli ordini");
		System.out.println("9- Visualizzazione dei nomi dei ristoranti che impiegano dipendenti propri per la consegna o che si affidano ai servizi della società «Food Delivery»");
		System.out.println("10- Visualizzazione degli ordini consegnati da Raider ancora non valutati"); 
		System.out.println("11- Cancellazione di un ordine ancora non consegnato");
		System.out.println("12- Stampa di tutte le persone (nome, cognome) che abbiano consegnato ordini a «Giuseppe Verdi»");
		System.out.println("13- Stampa di un report che mostri i dati dei ristoranti, incluso la coda di ordini attuale");
		System.out.println("14- Stampa settimanale di un report che mostri i dati dei rider, incluso lo score medio ottenuto nelle valutazioni da parte dei clienti");
		System.out.println("15- Stampa settimanale di tutti i clienti che nell’ultima settimana abbiano effettuato almeno una valutazione inferiore al corrispondente score medio di un raider");
		
		Scanner in = new Scanner(System.in);
		int scelta = in.nextInt();
		switch(scelta)
		{
		    case 1:  primaQuery();
		             break;
		            
		    case 2:  secondaQuery();
		             break;
		            
		    case 3:  terzaQuery();
		             break;	
		    
		    case 4:  quartaQuery();
		             break;
		    
		    case 5:  quintaQuery();   
		             break;
		    
		    case 6:  sestaQuery();
		             break;
		    	
		    case 7:  settimaQuery();
		             break;
		    	
		    case 8:  ottavaQuery();
		             break;
		    	
		    case 9:  nonaQuery();
		             break;
		    	
		    case 10: decimaQuery();
		             break;
		    	
		    case 11: undicesimaQuery();
		             break;
		    	
		    case 12: dodicesimaQuery();
		             break;
		    	
		    case 13: tredicesimaQuery();
		             break;
		    	
		    case 14: quattordicesimaQuery();
		             break;
		    	
		    case 15: quindicesimaQuery();
		             break;
		
		}

	}
	
	public static void primaQuery()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			
			Scanner in = new Scanner(System.in);
			System.out.println("Inserire: Codice Ordine, Descrizione, Data, Stato, Tipo, Numero ordini giornalieri, Partita IVA ristorante");
			String codice, descrizione, data, stato, tipo, nordini, iva;
			codice = in.nextLine();
			descrizione = in.nextLine();
			data = in.nextLine();
			stato = in.nextLine();
			tipo = in.nextLine();
			nordini = in.nextLine();
			iva = in.nextLine();
			
			String query = "INSERT INTO ordine VALUES ('"+codice+"','"+descrizione+"','"+data+"','"+stato+"','"+tipo+"','"+nordini+"','"+iva+"');";
			Statement s = conn.createStatement();
			s.executeUpdate(query);
			System.out.println("Query eseguita correttamente");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}
	}
	
	public static void secondaQuery()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			
			Scanner in = new Scanner(System.in);
			System.out.println("Inserire codice dell'ordine da consegnare");
			int n = in.nextInt();
			
			String query = "DELETE FROM ordine WHERE Codice="+n+";";
			Statement s = conn.createStatement();
			s.executeUpdate(query);
			System.out.println("Query eseguita correttamente");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}
	}
	
	public static void terzaQuery()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			
			Scanner in = new Scanner(System.in);
			System.out.println("Inserire la partita IVA del ristorante da eseguire");
			String s = in.nextLine();
			
			String query = "SELECT 1 FROM ristorante WHERE Partita_IVA = "+s+";";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(query);
			
			if(rs.next())
				System.out.println("E' possibile effettuare ordini per questo ristorante");
			else
				System.out.println("Impossibile effettuare ordini per questo ristorante");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}

	}
	
	public static void quartaQuery()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			
			Scanner in = new Scanner(System.in);
			System.out.println("Inserire partita IVA del ristorante per verificarne la disponibilità");
			String s = in.nextLine();
			
			String query = "SELECT COUNT(*) FROM ordine WHERE Ristorante = "+s+";";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(query);
			rs.next();
			System.out.println(rs.getInt(1));
			if(rs.getInt(1) < 50)
				System.out.println("E' possibile effettuare ordini per questo ristorante");
			else
				System.out.println("Impossibile effettuare ordini per questo ristorante");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}
	}
	
	public static void quintaQuery()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			
			Scanner in = new Scanner(System.in);
			System.out.println("Inserire il Codice Fiscale del raider da valutare");
			String raider = in.nextLine();
			boolean flag = false;
			int recensione = 0;
			System.out.println("Inserire punteggio valutazione da 1 a 5 stelle");
			while(!flag)
			{
				recensione = Integer.parseInt(in.nextLine());
				if(recensione < 1 || recensione > 5)
					System.out.println("Punteggio recensione non valido");
				else
					flag = true;
			}
			
			String query = "UPDATE cliente SET Punteggio_Recensione = '"+recensione+"' WHERE Raider = '"+raider+"';";
			Statement stm = conn.createStatement();
			stm.executeUpdate(query);
			
			System.out.println("Query eseguita correttamente");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
	}

	public static void sestaQuery()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			Statement s = conn.createStatement();
			ResultSet result = s.executeQuery("SELECT Cliente FROM consegna");
			ArrayList<String> clienti = new ArrayList<String>();
			while(result.next())
			{
				clienti.add(result.getString("Cliente"));
			}
			
			for(int i=0; i<clienti.size(); i++)
			{
				result = s.executeQuery("SELECT Ordine FROM consegna WHERE Cliente = '"+clienti.get(i)+"';");
				
				ArrayList<String> codici = new ArrayList<String> ();
				while(result.next())
				{
					codici.add(result.getString("Ordine"));
					for(int j=0; j<codici.size(); j++)
					{
						result = s.executeQuery("SELECT Numero_Ordini_Giornalieri FROM ordine WHERE Codice = '"+codici.get(j)+"';");
						while(result.next())
						{
							System.out.println("Cliente : "+clienti.get(i)+"  Numero ordini: "+result.getInt("Numero_Ordini_Giornalieri"));
						}
					}
				}
			}
			 
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database"); 
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
	}
	
	public static void settimaQuery()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			
			Scanner in = new Scanner(System.in);
			System.out.println("Inserire partita Iva della società e codice, descrizione, data, cadenza settimanale del servizio");
			String iva = in.nextLine();
			String codice = in.nextLine();
			String descrizione = in.nextLine();
			String data = in.nextLine();
			String cadenza = in.nextLine();
			
			String query = "INSERT INTO servizio VALUE ('"+codice+"','"+descrizione+"','"+data+"','"+cadenza+"','"+iva+"');";
			Statement s = conn.createStatement();
			s.executeUpdate(query);
			
			System.out.println("Query eseguita correttamente");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}
	}
	
	public static void ottavaQuery()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			
			Scanner in = new Scanner(System.in);
			System.out.println("Inserire codice fiscale, nome, cognome, curriculum, numero anni di esperienza");
			String cf = in.nextLine();
			String nome = in.nextLine();
			String cognome = in.nextLine();
			String curriculum = in.nextLine();
			String nanni = in.nextLine();
			
			System.out.println("Inserire codice del servizio, tipo contratto e data inizio contratto"); 
			String codice = in.nextLine();
			String contratto = in.nextLine();
			String data = in.nextLine();
			
			String query = "INSERT INTO dipendente VALUE ('"+cf+"','"+nome+"','"+cognome+"','"+curriculum+"','"+nanni+"');";
			Statement s = conn.createStatement();
			s.executeUpdate(query);
			
			s.executeUpdate("INSERT INTO assunzione VALUE ('"+cf+"','"+codice+"','"+contratto+"','"+data+"');");
			
			System.out.println("Query eseguita correttamente");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}
	}
	
	public static void nonaQuery()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			Statement s = conn.createStatement();
			ResultSet result = s.executeQuery("SELECT Servizio FROM assunzione");
			ArrayList<String> codici = new ArrayList<String>();
			System.out.println("Nome dei ristoranti che affidano la consegna ai dipendenti: ");
			while(result.next())
			{
				codici.add(result.getString("Servizio"));
			}
			
			for(int i=0; i<codici.size(); i++)
			{
				result = s.executeQuery("SELECT Ristorante FROM riferimento WHERE Servizio = '"+codici.get(i)+"';");
				ArrayList<String> iva = new ArrayList<String> ();
				while(result.next())
				{
					iva.add(result.getString("Ristorante"));
				}
				for(int j=0; j<iva.size(); j++)
				{
					result = s.executeQuery("SELECT Nome FROM ristorante WHERE Partita_Iva = '"+iva.get(j)+"';");
					while(result.next())
					{
					    System.out.println(result.getString("Nome"));
					}
				}
				
			}
			
			result = s.executeQuery("SELECT Partita_IVA FROM società WHERE Nome = 'FOOD DELIVERY';");
			ArrayList<String> ivasocieta = new ArrayList<String> ();
			while(result.next())
			{
				ivasocieta.add(result.getString("Partita_IVA"));
			}
			System.out.println("\nNome dei ristoranti che affidano la consegna alla società 'Food Delivery': ");
			for(int i=0; i<ivasocieta.size(); i++)
			{
				result = s.executeQuery("SELECT Codice FROM servizio WHERE Società = '"+ivasocieta.get(i)+"';");
				ArrayList<String> servizio = new ArrayList<String> ();
				while(result.next())
				{
					servizio.add(result.getString("Codice"));
				}
				
				for(int j=0; j<servizio.size(); j++)
				{
					result = s.executeQuery("SELECT Ristorante FROM riferimento WHERE Servizio = '"+servizio.get(j)+"';");
					ArrayList<String> ristorante = new ArrayList<String> ();
					
					while(result.next())
					{
						ristorante.add(result.getString("Ristorante"));
					}
					
					for(int z=0; z<ristorante.size(); z++)
					{
						result = s.executeQuery("SELECT Nome FROM ristorante WHERE Partita_IVA = '"+ristorante.get(z)+"';");
						while(result.next())
						{
							System.out.println(result.getString("Nome"));
						}
					}
				}
			}

		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}
	}
	
	public static void decimaQuery()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");

			Statement s = conn.createStatement();
			ResultSet result = s.executeQuery("SELECT Codice_Fiscale FROM raider");
			ArrayList<String> cf = new ArrayList<String> ();
			ArrayList<String> raider = new ArrayList<String> ();
			while(result.next())
			{
				cf.add(result.getString("Codice_Fiscale"));
			}
			
			result = s.executeQuery("SELECT Raider FROM cliente");
			while(result.next())
			{
				raider.add(result.getString("Raider"));
			}

			for(int i=0; i<cf.size(); i++)
			{
				for(int j=0; j<raider.size(); j++)
				{
					if(cf.get(i) == raider.get(j))
						cf.remove(i);
				}
			}
			
			for(int i=0; i<cf.size(); i++)
			{
				result = s.executeQuery("SELECT Società FROM impiego WHERE Raider = '"+cf.get(i)+"';");
				ArrayList<String> società = new ArrayList<String>();
				while(result.next())
				{
					società.add(result.getString("Società"));
				}
				
				for(int j=0; j<società.size(); j++)
				{
					result = s.executeQuery("SELECT Codice FROM servizio WHERE Società = '"+società.get(j)+"';");
					ArrayList<String> servizio = new ArrayList<String>();
					while(result.next())
					{
						servizio.add(result.getString("Codice"));
					}
					
					for(int k=0; k<servizio.size(); k++)
					{
						result = s.executeQuery("SELECT Ristorante FROM riferimento WHERE Servizio = '"+servizio.get(k)+"';");
						ArrayList<String> ristorante = new ArrayList<String>();
						while(result.next())
						{
							ristorante.add(result.getString("Ristorante"));
						}
						
						for(int l=0; l<ristorante.size(); l++)
						{
							result = s.executeQuery("SELECT Codice FROM ordine WHERE Ristorante = '"+ristorante.get(l)+"';");
							while(result.next())
							{
								System.out.println(result.getString("Codice"));
							}
						}	
					}	
			    }
			
		    }
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void undicesimaQuery()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			System.out.println("Tutti gli ordini non consegnati sono stati rimossi");
			Statement s = conn.createStatement();
			s.executeUpdate("DELETE FROM ordine WHERE Stato = 'NON CONSEGNATO'");
			System.out.println("Query eseguita correttamente");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}
	}
	
	public static void dodicesimaQuery()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			Statement s = conn.createStatement();
			ResultSet result = s.executeQuery("SELECT Codice_Fiscale FROM cliente WHERE Nome = 'GIUSEPPE VERDI'");
			ArrayList<String> cliente = new ArrayList<String> ();
			
			while(result.next())
			{
				cliente.add(result.getString("Codice_Fiscale"));
			}
			
			for(int i=0; i<cliente.size(); i++)
			{
			   	result = s.executeQuery("SELECT Ordine FROM consegna WHERE Cliente = '"+cliente.get(i)+"';");
				ArrayList<String> ordine = new ArrayList<String> ();
				
				while(result.next())
				{
					ordine.add(result.getString("Ordine"));
				}
				
				for(int j=0; j<ordine.size(); j++)
				{
					result = s.executeQuery("SELECT Ristorante FROM ordine WHERE Codice = '"+ordine.get(j)+"';");
					ArrayList<String> ristorante = new ArrayList<String> ();
					
					while(result.next())
					{
						ristorante.add(result.getString("Ristorante"));
					}
					
					for(int k=0; k<ristorante.size(); j++)
					{
						result = s.executeQuery("SELECT Servizio FROM riferimento WHERE Ristorante = '"+ristorante.get(k)+"';");
						ArrayList<String> servizio = new ArrayList<String> ();
						
						while(result.next())
						{
							servizio.add(result.getString("Servizio"));
						}
						
						for(int l=0; l<servizio.size(); l++)
						{
							result = s.executeQuery("SELECT Dipendente FROM assunzione WHERE Servizio = '"+servizio.get(l)+"';");
							ArrayList<String> dipendente = new ArrayList<String> ();
							
							while(result.next())
							{
								dipendente.add(result.getString("Dipendente"));
							}
							
							for(int m=0; m<dipendente.size(); m++)
							{
								result = s.executeQuery("SELECT Nome, Cognome FROM dipendente WHERE Codice_Fiscale = '"+dipendente.get(m)+"';");
								
								while(result.next())
								{
									System.out.println(result.getString("Nome"));
									System.out.println(result.getString("Cognome"));
								}

							}
						}
						
						for(int n=0; n<servizio.size(); n++)
						{
							result = s.executeQuery("SELECT Società FROM servizio WHERE Codice = '"+servizio.get(n)+"';");
							ArrayList<String> società = new ArrayList<String> ();
							
							while(result.next())
							{
								società.add(result.getString("Società"));
							}
							
							for(int o=0; o<società.size(); o++)
							{
								result = s.executeQuery("SELECT Raider FROM impiego WHERE Società = '"+società.get(o)+"';");
								ArrayList<String> raider = new ArrayList<String> ();
								
								while(result.next())
								{
									raider.add(result.getString("Raider"));
								}
								
								for(int p=0; p<raider.size(); p++)
								{
									result = s.executeQuery("SELECT Nome,Cognome FROM raider WHERE Codice_Fiscale = '"+raider.get(p)+"';");
									
									while(result.next())
									{
										System.out.println(result.getString("Nome"));
										System.out.println(result.getString("Cognome"));
									}
								}

							}
					     }
				    }   
		        }            
			}
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}
	}
	
	public static void tredicesimaQuery()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			Statement s = conn.createStatement();
			ResultSet result = s.executeQuery("SELECT * FROM ristorante");
			System.out.println("Dati dei ristoranti: ");
			
			while(result.next())
			{
				System.out.println("\n");
				System.out.println(result.getNString("Partita_IVA"));
				System.out.println(result.getString("Nome"));
				System.out.println(result.getString("Città"));
				System.out.println(result.getString("Via"));
				System.out.println(result.getString("CAP"));
				System.out.println(result.getString("Numero_Telefono"));
				System.out.println(result.getString("Numero_Prenotazioni"));
				System.out.println(result.getString("Nome_Proprietario"));

			}
			
			result = s.executeQuery("SELECT * FROM ordine WHERE Stato = 'NON CONSEGNATO'");
			System.out.println("\n\nOrdini ancora non consegnati: ");
			
			while(result.next())
			{
				System.out.println("\n");
				System.out.println(result.getString("Codice"));
				System.out.println(result.getString("Descrizione"));
				System.out.println(result.getString("Data_Ordine"));
				System.out.println(result.getString("Stato"));
				System.out.println(result.getString("Tipo"));
				System.out.println(result.getString("Numero_Ordini_Giornalieri"));
				System.out.println(result.getString("Ristorante"));
			}
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}
	}
	
	public static void quattordicesimaQuery()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			Statement s = conn.createStatement();
			ResultSet result = s.executeQuery("SELECT * FROM raider");
			System.out.println("Dati dei raider: ");
			
			while(result.next())
			{
				System.out.println("\n");
				System.out.println(result.getNString("Codice_Fiscale"));
				System.out.println(result.getString("Nome"));
				System.out.println(result.getString("Cognome"));
				System.out.println(result.getString("Data_Primo_Impiego"));
				System.out.println(result.getString("Disponibilità"));
			}
			
			result = s.executeQuery("SELECT * FROM cliente");
			System.out.println("\n\nNome e Cognome e punteggio dei clienti al raider");			
			while(result.next())
			{
				System.out.println("\n");
				System.out.println(result.getString("Raider"));
				System.out.println(result.getString("Punteggio_Recensione"));
				System.out.println(result.getString("Nome"));
				System.out.println(result.getString("Cognome"));
			}
			
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}
	}
	
	public static void quindicesimaQuery()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servizio_delivery","root","shalletta14");
			Statement s = conn.createStatement();
			ResultSet result = s.executeQuery("SELECT Codice_Fiscale FROM cliente WHERE Punteggio_Recensione IS NOT NULL");
			while(result.next())
			{
				System.out.println(result.getString("Codice_Fiscale"));
			}			
		}
		
		catch(ClassNotFoundException e)
		{
			System.out.println("Errore di connessione al driver");
		}
		catch(SQLException e)
		{
			System.out.println("Errore di connessione al Database");
		}
	}
}
