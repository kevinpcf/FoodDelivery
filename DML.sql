CREATE SCHEMA servizio_delivery;

USE servizio_delivery;

/* Tabella Ristorante */
CREATE TABLE ristorante
(
   Partita_IVA VARCHAR(11) NOT NULL PRIMARY KEY,
   Nome VARCHAR(50) NOT NULL,
   Città VARCHAR(50) NOT NULL,
   Via VARCHAR(50) NOT NULL,
   CAP VARCHAR(5) NOT NULL,
   Numero_Telefono VARCHAR(50),
   Numero_Prenotazioni INT,
   Nome_Proprietario VARCHAR(50) NOT NULL
);

/* Tabella Raider */
CREATE TABLE raider
(
   Codice_Fiscale VARCHAR(16) NOT NULL PRIMARY KEY,
   Nome VARCHAR(50) NOT NULL,
   Cognome VARCHAR(50) NOT NULL,
   Data_Primo_Impiego DATE NOT NULL,
   Disponibilità VARCHAR(50)
);

/* Tabella Dipendente */
CREATE TABLE dipendente
(
   Codice_Fiscale VARCHAR(16) NOT NULL PRIMARY KEY,
   Nome VARCHAR(50) NOT NULL,
   Cognome VARCHAR(50) NOT NULL,
   Curriculum VARCHAR(500) NOT NULL,
   Numero_Anni_Esperienza INT NOT NULL
);

/* Tabella Società */
CREATE TABLE società
(
   Partita_IVA VARCHAR(11) NOT NULL PRIMARY KEY,
   Nome VARCHAR(50) NOT NULL,
   Amministratore VARCHAR(50) NOT NULL
);

/* Tabella Cliente */
CREATE TABLE cliente
(
   Codice_Fiscale VARCHAR(16) NOT NULL PRIMARY KEY,
   Nome VARCHAR(50) NOT NULL,
   Cognome VARCHAR(50) NOT NULL,
   Data_Nascita DATE NOT NULL,
   Numero_Telefono VARCHAR(50),
   Data_Registrazione DATE NOT NULL,
   Raider VARCHAR (16),
   FOREIGN KEY(Raider) REFERENCES raider(Codice_Fiscale)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
   Punteggio_Recensione INT, 
   Data_Recensione DATE
);

/* Tabella Ordine */
CREATE TABLE ordine
(
   Codice VARCHAR(50) NOT NULL PRIMARY KEY,
   Descrizione VARCHAR(100),
   Data_Ordine DATE NOT NULL,
   Stato VARCHAR(50) NOT NULL,
   Tipo VARCHAR(50) NOT NULL,
   Numero_Ordini_Giornalieri INT,
   Ristorante VARCHAR(11) NOT NULL,
   FOREIGN KEY(Ristorante) REFERENCES ristorante(Partita_IVA)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

/* Tabella Consegna: relazione tra ordine e cliente */
CREATE TABLE consegna
(
   Cliente VARCHAR(16) NOT NULL,
   Ordine VARCHAR(50) NOT NULL,
   PRIMARY KEY(Cliente, Ordine),
   FOREIGN KEY(Cliente) REFERENCES cliente(Codice_Fiscale)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
   FOREIGN KEY(Ordine) REFERENCES ordine(Codice)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
	Orario_Previsto TIME NOT NULL,
    Orario_Effettivo TIME NOT NULL,
    Nome_Destinatario VARCHAR(50) NOT NULL
);
   
/* Tabella Servizio */
CREATE TABLE servizio
(
   Codice VARCHAR(50) NOT NULL PRIMARY KEY,
   Descrizione VARCHAR(50),
   Data_Servizio DATE NOT NULL,
   Cadenza_Settimanale VARCHAR(50) NOT NULL,
   Società VARCHAR(11),
   FOREIGN KEY(Società) REFERENCES società(Partita_IVA)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);
    
/* Tabella Riferimento: relazione tra ristorante e servizio */   
CREATE TABLE riferimento
(
   Ristorante VARCHAR(11) NOT NULL ,
   Servizio VARCHAR(50) NOT NULL,
   PRIMARY KEY(Ristorante, Servizio),
   FOREIGN KEY(Ristorante) REFERENCES ristorante(Partita_IVA)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
   FOREIGN KEY(Servizio) REFERENCES servizio(Codice)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);
      
/* Tabella Automobile */
CREATE TABLE automobile
(
   Targa VARCHAR(7) NOT NULL PRIMARY KEY,
   Modello VARCHAR(50) NOT NULL,
   Raider VARCHAR(16) NOT NULL,
   FOREIGN KEY(Raider) REFERENCES raider(Codice_Fiscale)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

/* Tabella Assunzione: relazione tra dipendente e servizio */
CREATE TABLE assunzione
(
   Dipendente VARCHAR(16) NOT NULL,
   Servizio VARCHAR(50) NOT NULL,
   PRIMARY KEY(Dipendente, Servizio),
   FOREIGN KEY(Dipendente) REFERENCES dipendente(Codice_Fiscale)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
   FOREIGN KEY(Servizio) REFERENCES servizio(Codice)
      ON UPDATE CASCADE
      ON DELETE CASCADE,  
   Tipo_Contratto VARCHAR(50) NOT NULL,
   Data_Inizio_Contratto DATE NOT NULL
);

/* Tabella Impiego: relazione tra società e raider */
CREATE TABLE impiego
(
   Società VARCHAR(50) NOT NULL,
   Raider VARCHAR(16) NOT NULL,
   PRIMARY KEY(Società, Raider),
   FOREIGN KEY(Società) REFERENCES società(Partita_IVA)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
   FOREIGN KEY(Raider) REFERENCES raider(Codice_Fiscale)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
   Quota_Oraria DOUBLE NOT NULL,
   Data_Impiego DATE NOT NULL
);