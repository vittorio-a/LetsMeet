DROP SCHEMA IF EXISTS letsmeet;
CREATE SCHEMA letsmeet;
USE letsmeet;


CREATE TABLE Utente(
	idUtente INTEGER (11) PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(80) UNIQUE,
    passwordUtente BINARY(32),
    email VARCHAR(256) UNIQUE,
	feedback FLOAT,
    stato ENUM{'ATTIVO','INVISIBILE','BANNATO'},
    reactivationDay TIMESTAMP
);


CREATE TABLE CodiceRilasciato(
	idUtente INTEGER(11),
    codice SMALLINT AUTO_INCREMENT,
    FOREIGN KEY(idUtente) REFERENCES utente(idUtente)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE SuperAdmin(
	idSuperAdmin TINYINT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(80) UNIQUE,
	passwordAdmin BINARY(32)
);


CREATE TABLE Tipo(
	idTipo TINYINT PRIMARY KEY AUTO_INCREMENT,
    nomeTipo VARCHAR(100),
    descrizione VARCHAR(100)
);


CREATE TABLE Comune(
	idComune INTEGER(11) PRIMARY KEY AUTO_INCREMENT,
    nomeComune VARCHAR(256)
);


CREATE TABLE Posizione(
	idPosizione INTEGER(11) PRIMARY KEY AUTO_INCREMENT,
    longitudine DECIMAL(8,5),
    latitudine DECIMAL(7,5),
    formattedAddress VARCHAR(256)
);


CREATE TABLE AppartenenzaComune(
	idPosizione INTEGER(11),
    idComune INTEGER(11),
	FOREIGN KEY (idPosizione) REFERENCES Posizione(idPosizione)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idComune) REFERENCES Comune(idComune)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Provincia(
	idProvincia INTEGER(11) PRIMARY KEY AUTO_INCREMENT,
    nomeProvincia VARCHAR(256),
	sigla VARCHAR(256)
);


CREATE TABLE AppartenenzaProvincia(
	idComune INTEGER(11),
	idProvincia INTEGER(11),
	FOREIGN KEY (idComune) REFERENCES Comune(idComune)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idProvincia) REFERENCES Provincia(idProvincia)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Regione(
	idRegione INTEGER(11) PRIMARY KEY AUTO_INCREMENT,
	nomeRegione VARCHAR(256)
);


CREATE TABLE AppartenenzaRegione(
	idRegione INTEGER(11),
	idProvincia INTEGER(11),
    FOREIGN KEY (idRegione) REFERENCES Regione(idRegione)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idProvincia) REFERENCES Provincia(idProvincia)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Nazione(
	idNazione INTEGER(11) PRIMARY KEY AUTO_INCREMENT,
    nomeNazione VARCHAR(256)
);


CREATE TABLE AppartenezaNazione(
	idRegione INTEGER(11),
    idNazione INTEGER(11),
    FOREIGN KEY(idRegione) REFERENCES Regione(idRegione)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idNazione) REFERENCES Nazione(idNazione)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Evento(
	idEvento INTEGER(11) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    feedback FLOAT,
    npartecipanti INTEGER(11),
    nverificati INTEGER(11),
    oraInizio TIMESTAMP,
    oraFine TIMESTAMP,
    idUtente INTEGER(11),
    idTipo TINYINT ,
    idPosizione INTEGER(11),
    isVisibile BOOLEAN,
    FOREIGN KEY(idTipo) REFERENCES Tipo(idTipo)
		ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (idPosizione) REFERENCES Posizione(idPosizione)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idUtente) REFERENCES Utente(idUtente)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Rating(
	idUtente INTEGER(11),
    idEvento INTEGER(11),
    voto BOOLEAN,
    FOREIGN KEY (idUtente) REFERENCES Utente(idutente)
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Commento(
	idCommento INTEGER(11) PRIMARY KEY AUTO_INCREMENT,
	idMittente INTEGER(11),
    contenuto VARCHAR(256),
	idEvento INTEGER,
	creationTime TIMESTAMP,
    FOREIGN KEY(idMittente) REFERENCES Utente(idUtente)
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Partecipazione(
	idUtente INTEGER(11),
	idEvento INTEGER(11),
	isVerificato BOOLEAN,
    FOREIGN KEY (idUtente) REFERENCES Utente(idUtente)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE SegnalazioneEvento(
	idSegnalazione INTEGER(11) AUTO_INCREMENT,
    idUtente INTEGER(11),
	idEvento INTEGER(11),
    FOREIGN KEY (idUtente) REFERENCES Utente(idUtente)
		ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE SegnalazioneCommento(
	idSegnalazione INTEGER(11) AUTO_INCREMENT,
    idUtente INTEGER(11),
	idCommento INTEGER(11),
	FOREIGN KEY (idUtente) REFERENCES Utente(idUtente)
		ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY(idCommento) REFERENCES Commento(idCommento)
		ON DELETE CASCADE ON UPDATE CASCADE
);
