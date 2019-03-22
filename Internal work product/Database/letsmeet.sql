DROP SCHEMA IF EXISTS letsmeet;
CREATE SCHEMA letsmeet;
USE letsmeet;


CREATE TABLE Utente(
	idUtente INTEGER (11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(80) NOT NULL UNIQUE,
    passwordUtente BINARY(32) NOT NULL,
    email VARCHAR(256) UNIQUE NOT NULL ,
	feedback DECIMAL(6,4) NOT NULL DEFAULT 0,
    stato ENUM ('ATTIVO','INVISIBILE','BANNATO') NOT NULL,
    reactivationDay TIMESTAMP
);


CREATE TABLE CodiceRilasciato(
	idUtente INTEGER(11) NOT NULL ,
    codice SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY(idUtente) REFERENCES utente(idUtente)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE SuperAdmin(
	idSuperAdmin TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(80) UNIQUE NOT NULL ,
	passwordAdmin BINARY(32) NOT NULL 
);


CREATE TABLE Tipo(
	idTipo TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nomeTipo VARCHAR(100) NOT NULL,
    descrizione VARCHAR(100)
);


CREATE TABLE Comune(
	idComune INTEGER(11) NOT NULL  PRIMARY KEY AUTO_INCREMENT,
    nomeComune VARCHAR(256) NOT NULL 
);


CREATE TABLE Posizione(
	idPosizione INTEGER(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    longitudine DECIMAL(8,5) NOT NULL,
    latitudine DECIMAL(7,5) NOT NULL,
    formattedAddress VARCHAR(256) NOT NULL 
);


CREATE TABLE AppartenenzaComune(
	idPosizione INTEGER(11) NOT NULL ,
    idComune INTEGER(11) NOT NULL ,
	FOREIGN KEY (idPosizione) REFERENCES Posizione(idPosizione)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idComune) REFERENCES Comune(idComune)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Provincia(
	idProvincia INTEGER(11) NOT NULL  PRIMARY KEY AUTO_INCREMENT,
    nomeProvincia VARCHAR(256) NOT NULL ,
	sigla CHAR(2) NOT NULL 
);


CREATE TABLE AppartenenzaProvincia(
	idComune INTEGER(11) NOT NULL,
	idProvincia INTEGER(11) NOT NULL ,
	FOREIGN KEY (idComune) REFERENCES Comune(idComune)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idProvincia) REFERENCES Provincia(idProvincia)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Regione(
	idRegione INTEGER(11) PRIMARY KEY AUTO_INCREMENT NOT NULL ,
	nomeRegione VARCHAR(256) NOT NULL 
);


CREATE TABLE AppartenenzaRegione(
	idRegione INTEGER(11) NOT NULL ,
	idProvincia INTEGER(11) NOT NULL ,
    FOREIGN KEY (idRegione) REFERENCES Regione(idRegione)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idProvincia) REFERENCES Provincia(idProvincia)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Nazione(
	idNazione INTEGER(11) PRIMARY KEY AUTO_INCREMENT NOT NULL ,
    nomeNazione VARCHAR(256) NOT NULL 
);


CREATE TABLE AppartenezaNazione(
	idRegione INTEGER(11) NOT NULL ,
    idNazione INTEGER(11) NOT NULL ,
    FOREIGN KEY(idRegione) REFERENCES Regione(idRegione)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idNazione) REFERENCES Nazione(idNazione)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Evento(
	idEvento INTEGER(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    feedback DECIMAL(6,4) NOT NULL DEFAULT 0,
    npartecipanti INTEGER(11) NOT NULL DEFAULT 0,
    nverificati INTEGER(11) NOT NULL DEFAULT 0,
    oraInizio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    oraFine TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idUtente INTEGER(11) NOT NULL,
    idTipo TINYINT NOT NULL,
    idPosizione INTEGER(11) NOT NULL,
    isVisibile BOOLEAN NOT NULL DEFAULT TRUE,
	descrizione VARCHAR(256) NOT NULL DEFAULT 'Nessuna descrizione disponibile',
    FOREIGN KEY(idTipo) REFERENCES Tipo(idTipo)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idPosizione) REFERENCES Posizione(idPosizione)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idUtente) REFERENCES Utente(idUtente)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Rating(
	idUtente INTEGER(11) NOT NULL,
    idEvento INTEGER(11) NOT NULL,
    voto BOOLEAN NOT NULL,
    FOREIGN KEY (idUtente) REFERENCES Utente(idutente)
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Commento(
	idCommento INTEGER(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	idMittente INTEGER(11) NOT NULL,
    contenuto VARCHAR(256) NOT NULL,
	idEvento INTEGER NOT NULL,
	creationTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(idMittente) REFERENCES Utente(idUtente)
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Partecipazione(
	idUtente INTEGER(11) NOT NULL,
	idEvento INTEGER(11) NOT NULL,
	isVerificato BOOLEAN NOT NULL,
    FOREIGN KEY (idUtente) REFERENCES Utente(idUtente)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE SegnalazioneEvento(
	idSegnalazione INTEGER(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    idUtente INTEGER(11) NOT NULL,
	idEvento INTEGER(11) NOT NULL,
    FOREIGN KEY (idUtente) REFERENCES Utente(idUtente)
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE SegnalazioneCommento(
	idSegnalazione INTEGER(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    idUtente INTEGER(11) NOT NULL,
	idCommento INTEGER(11) NOT NULL,
	FOREIGN KEY (idUtente) REFERENCES Utente(idUtente)
		ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(idCommento) REFERENCES Commento(idCommento)
		ON DELETE CASCADE ON UPDATE CASCADE
);

DELIMITER $$
CREATE TRIGGER rating_insert AFTER INSERT ON Rating 
FOR EACH ROW
BEGIN
	 DECLARE feedbackEvento INT;
     DECLARE nf INT;
     DECLARE feedbackUtente INT;
     DECLARE idUt INT;
     
	 SET @feedbackEvento = (SELECT e.feedback
	 FROM Evento e
     WHERE e.idEvento = NEW.idEvento);
     
     IF(NEW.voto) THEN
		SET @nf = 1;
	 ELSE 
		SET @nf = -1;
	 END IF;
     UPDATE Evento e
     SET e.feedback = @feedbackEvento + @nf
     WHERE e.idEvento = NEW.idEvento;
    
    
    SET @idUt =  (SELECT e.idUtente
	 FROM Evento e
     WHERE e.idEvento = NEW.idEvento);
     
     SET @feedbackUtente = (SELECT u.feedback
	 FROM Utente u 
     WHERE u.idUtente = @idUt);
     
     UPDATE Utente u
     SET u.feedback = @feedbackUtente + @nf
     WHERE u.idUtente = @idUt;
END;$$


CREATE TRIGGER rating_update AFTER UPDATE ON Rating 
FOR EACH ROW
BEGIN
	 DECLARE feedbackEvento INT;
     DECLARE nf INT;
     DECLARE feedbackUtente INT;
     DECLARE idUt INT;
     
	 SET @feedbackEvento = (SELECT e.feedback
	 FROM Evento e
     WHERE e.idEvento = NEW.idEvento);
     IF(NEW.voto = OLD.voto) THEN
		SET @nf = 0;
	 ELSEIF(NEW.voto) THEN
		SET @nf = 2;
	 ELSE 
		SET @nf = -2;
	 END IF;
     UPDATE Evento e
     SET e.feedback = @feedbackEvento + @nf
     WHERE e.idEvento = NEW.idEvento;
     SET @idUt =  (SELECT e.idUtente
	 FROM Evento e
     WHERE e.idEvento = NEW.idEvento);
     
     SET @feedbackUtente = (SELECT u.feedback
	 FROM Utente u 
     WHERE u.idUtente = @idUt);
     
     UPDATE Utente u
     SET u.feedback = @feedbackUtente + @nf
     WHERE u.idUtente = @idUt;
END;$$


CREATE TRIGGER add_partecipazione AFTER INSERT ON Partecipazione
FOR EACH ROW
BEGIN
	DECLARE nPart INT;
    DECLARE nVer INT;
    
	IF(NEW.isVerificato) THEN
		SET @nVer = (SELECT nVerificati
			FROM Evento e
			WHERE e.idEvento = NEW.idEvento);
		UPDATE Evento e
			SET e.nVerificati = @nVer + 1
			WHERE e.idEvento = NEW.idEvento;    
	END IF;
	SET @nPart = (SELECT nPartecipanti
		FROM Evento e
        WHERE e.idEvento = NEW.idEvento);
	UPDATE Evento e
    SET e.nPartecipanti = @nPart + 1
    WHERE e.idEvento = NEW.idEvento;
    
END;$$

CREATE TRIGGER upd_partecipazione AFTER UPDATE ON Partecipazione
FOR EACH ROW
BEGIN
    DECLARE nVer INT;
	IF(NEW.isVerificato) THEN
		SET @nVer = (SELECT nVerificati
			FROM Evento e
			WHERE e.idEvento = NEW.idEvento);
		UPDATE Evento e
			SET e.nVerificati = @nVer + 1
			WHERE e.idEvento = NEW.idEvento;    
	END IF;
END;$$