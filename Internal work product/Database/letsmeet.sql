DROP database if exists letsmeet;
create database letsmeet;
Use letsmeet;

drop table if exists Utente;

create table Utente(
	idUtente integer(11) primary key,
    username varchar(80) unique,
    passwordUtente binary(32),
    email varchar(256) unique,
	feedback float,
    reactivationDay date
);

drop table if exists CodiceRilasciato;

create table CodiceRilasciato(
	idUtente integer(11),
    codice smallint,
	foreign key (idUtente) references Utente(idUtente)
		on delete cascade on update cascade
);

drop table if exists SuperAdmin;

create table SuperAdmin(
	idSuperAdmin tinyint(8) primary key,
	username varchar(80) unique,
	passordAdmin binary(32)
);


drop table if exists Tipo;

create table Tipo(
	idTipo tinyint primary key,
    nomeTipo varchar(100),
    descrizione varchar(100)
);

drop table if exists Posizione;

create table Posizione(
	idPosizione integer(11) primary key,
    longitudine decimal(8,5),
    latitudine decimal(7,5),
    formatted_address varchar(256),
    idComune integer(11),
    foreign key (idComune) references Comune(idComune)
		on delete cascade on update cascade
);

drop table if exists Evento;

create table Evento(
	idEvento integer(11) primary key,
    nome varchar(100),
    feedback float,
    npartecipanti integer(11),
    nverificati integer(11),
    oraInizio datetime,
    oraFine datetime,
    idUtente integer(11),
    idTipo tinyint ,
    idPosizione integer(11),
    visibile boolean,
    foreign key (idTipo) references Tipo(idTipo)
		on delete cascade on update cascade,
    foreign key (idPosizione) references Posizione(idPosizione)    
		on delete cascade on update cascade,
    foreign key (idUtente) references Utente(idUtente)
		on delete cascade on update cascade
);


drop table if exists Rating;

create table Rating(
	idUtente integer(11),
    idEvento integer(11),
    voto boolean,
    foreign key (idUtente) references Utente(idutente)
		on delete  cascade on update cascade,
	foreign key (idEvento) references Evento(idEvento)
		on delete cascade on update cascade


);

drop table if exists Commento;

create table Commento(
	idCommento integer(11) primary key,
	idMittente integer(11),
    contenuto varchar(256),
	idEvento integer,
	creationTime datetime,
    foreign key (idMittente) references Utente(idUtente)
		on delete cascade on update cascade,
	foreign key (idEvento) references Evento(idEvento)
		on delete cascade on update cascade
);


drop table if exists Partecipazione;

create table Partecipazione(
	idUtente integer(11),
	idEvento integer(11),
	isVerificato boolean,
    foreign key (idUtente) references Utente(idUtente)
		on delete cascade on update cascade,
    foreign key (idEvento) references Evento(idEvento)
		on delete cascade on update cascade
);

drop table if exists Segnalazione;

create table Segnalazione(
	idSegnalazione integer(11) primary key,
	idUtente integer(11),
    foreign key (idUtente) references Utente(idUtente)
		on delete cascade on update cascade
);

drop table if exists SegnalazioneEvento;

create table SegnalazioneEvento(
	idSegnalazione integer(11),
    idUtente integer(11),
	idEvento integer(11),
    foreign key (idSegnalazione) references Segnalazione(idSegnlazione)
		on update cascade on delete cascade,
    foreign key (idUtente) references Utente(idUtente)
		on update cascade on delete cascade,
	foreign key (idEvento) references Evento(idEvento)
		on update cascade on delete cascade
);

drop table if exists SegnalazionCommento;

create table SegnalazioneCommento(
	idSegnalazione integer(11),
    idUtente integer(11),
	idCommento integer(11),
    foreign key (idSegnalazione) references Segnalazione(idSegnalazione)
		on delete cascade on update cascade,
	foreign key (idUtente) references Utente(idUtente)
		on delete cascade on update cascade,
    foreign key(idCommento) references Commento(idCommento)
		on update cascade  on delete cascade
);

drop table if exists Comune;

create table Comune(
	idComune integer(11) primary key,
    nomeComune varchar(256)
);

drop table if exists AppartenenzaComune;

create table AppartenenzaComune(
	idPosizione integer(11),
    idComune integer(11),
	foreign key (idPosizione) references Posizione(idPosizione)
		on delete cascade on update cascade,
    foreign key (idComune) references Comune(idComune)
		on delete cascade on update cascade
);

drop table if exists Provincia;

create table Provincia(
	idProvincia integer(11) primary key,
    nomeProvincia varchar(256),
	sigla varchar(256)
);

drop table if exists AppartenenzaProvincia;

create table AppartenenzaProvincia(
	idComune integer(11),
	idProvincia integer(11),
	foreign key (idComune) references Comune(idComune)
		on delete cascade on update cascade,
    foreign key (idProvincia) references Provincia(idProvincia)
		on delete cascade on update cascade
);

drop table if exists Regione;

create table Regione(
	idRegione integer(11) primary key,
	nomeRegione varchar(256)
);

drop table if exists AppartenenzaRegione;

create table AppartenenzaRegione(
	idRegione integer(11),
	idProvincia integer(11),
    foreign key (idRegione) references Regione(idRegione)
		on update cascade on delete cascade,
    foreign key (idProvincia) references Provincia(idProvincia)
		on delete cascade on update cascade
);

drop table if exists Nazione;

create table Nazione(
	idNazione integer(11) primary key,
    nomeNazione varchar(256)
);

drop table if exists  AppartenenzaNazione;

create table AppartenezaNazione(
	idRegione integer(11),
    idNazione integer(11),
    foreign key(idRegione) references Regione(idRegione)
		on delete cascade on update cascade,
    foreign key (idNazione) references Nazione(idNazione)
		on delete cascade on update cascade
);	

