DROP SCHEMA IF EXISTS easygames;
CREATE SCHEMA easygames;
USE easygames;


CREATE TABLE gioco
(
	id char(3) NOT NULL PRIMARY KEY,
	nome varchar(25) NOT NULL,
	descrizione text(2000) NOT NULL,
	piattaforma varchar(20) NOT NULL,
	quantita int NOT NULL,
	prezzo float NOT NULL,
	copertina mediumblob DEFAULT NULL
);

CREATE TABLE account
(
	nickname varchar(30) NOT NULL PRIMARY KEY,
	nome varchar(25) NOT NULL,
	cognome varchar(25) NOT NULL,
	data_nascita date NOT NULL,
	email varchar(50) NOT NULL UNIQUE,
	password varchar(30) NOT NULL
);

CREATE TABLE cliente
(
	account varchar(30) NOT NULL,
	PRIMARY KEY(account),
	FOREIGN KEY(account) REFERENCES account(nickname) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE amministratore
(
	account varchar(30) NOT NULL,
	PRIMARY KEY(account),
	FOREIGN KEY(account) REFERENCES account(nickname) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE acquisto
(
	codice int NOT NULL AUTO_INCREMENT,
	data date NOT NULL,
	ora time NOT NULL,
	account varchar(30) NOT NULL,
	PRIMARY KEY(codice),
	FOREIGN KEY(account) REFERENCES cliente(account) ON UPDATE cascade ON DELETE cascade
)AUTO_INCREMENT=1;

CREATE TABLE giochi_acquistati
(
	gioco char(3) NOT NULL,
	acquisto int NOT NULL,
	PRIMARY KEY(gioco,acquisto),
	FOREIGN KEY(gioco) REFERENCES gioco(id) ON UPDATE cascade ON DELETE cascade,
	FOREIGN KEY(acquisto) REFERENCES acquisto(codice) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE genere
(
	genere varchar(25) NOT NULL PRIMARY KEY
);

CREATE TABLE appartieneGenere
(
	gioco char(3) NOT NULL,
	genere varchar(25) NOT NULL,
	PRIMARY KEY(gioco,genere),
	FOREIGN KEY(gioco) REFERENCES gioco(id) ON UPDATE cascade ON DELETE cascade,
	FOREIGN KEY(genere) REFERENCES genere(genere) ON UPDATE cascade ON DELETE cascade
);
