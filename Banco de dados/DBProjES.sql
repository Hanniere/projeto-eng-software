CREATE DATABASE projES;
USE projES;


CREATE TABLE Estado(
	EstadoId INT AUTO_INCREMENT NOT NULL,
	EstadoUF VARCHAR(3) NOT NULL,
	PRIMARY KEY(EstadoId)
);

CREATE TABLE Cidade(
	CidadeId INT AUTO_INCREMENT NOT NULL,
	CidadeNome VARCHAR(200) NOT NULL,
	Cidade_EstadoId INT NOT NULL,
	PRIMARY KEY(CidadeId),
	FOREIGN KEY(Cidade_EstadoId) REFERENCES Estado(EstadoId)
);

CREATE TABLE Itinerario(
	ItinerarioId INT AUTO_INCREMENT NOT NULL,
	Itinerario_CidadeOrigem INT NOT NULL,
	Itinerario_CidadeDestino INT NOT NULL,
	PRIMARY KEY(ItinerarioId),
	FOREIGN KEY(Itinerario_CidadeOrigem) REFERENCES Cidade(CidadeId),
	FOREIGN KEY(Itinerario_CidadeDestino) REFERENCES Cidade(CidadeId)
);

CREATE TABLE Rota(
	RotaId INT AUTO_INCREMENT NOT NULL,
	Rota_CidadeOrigem INT NOT NULL,
	Rota_CidadeDestino INT NOT NULL,
	RotaDuracao VARCHAR(5) NOT NULL,
	PRIMARY KEY(RotaId),
	FOREIGN KEY(Rota_CidadeOrigem) REFERENCES Cidade(CidadeId),
	FOREIGN KEY(Rota_CidadeDestino) REFERENCES Cidade(CidadeId)
);

CREATE TABLE RotaItinerario(
	RotaItinerarioId INT AUTO_INCREMENT NOT NULL,
	RotaItinerario_RotaId INT NOT NULL,
	RotaItinerario_ItinerarioId INT NOT NULL,
	RotaItinerarioOrdem INT NOT NULL,
	PRIMARY KEY(RotaItinerarioId),
	FOREIGN KEY(RotaItinerario_RotaId) REFERENCES Rota(RotaId),
	FOREIGN KEY(RotaItinerario_ItinerarioId) REFERENCES Itinerario(ItinerarioId)
);

CREATE TABLE Onibus(
	OnibusId INT AUTO_INCREMENT NOT NULL, 
	OnibusPlaca VARCHAR(10) NOT NULL UNIQUE,
	OnibusModelo VARCHAR(50) NOT NULL,
	OnibusMarca VARCHAR(50) NOT NULL,
	OnibusAno INT NOT NULL,
	OnibusQtdeAssentos INT NOT NULL,
	PRIMARY KEY(OnibusId)
);

CREATE TABLE Motorista(
	MotoristaId INT AUTO_INCREMENT NOT NULL, 
	MotoristaNome VARCHAR(100) NOT NULL,
	MotoristaRg VARCHAR(13) NOT NULL UNIQUE,
	MotoristaCpf VARCHAR(14) NOT NULL UNIQUE,
	MotoristaEnd VARCHAR(200) NOT NULL,
	MotoristaTel VARCHAR(15),
	MotoristaEmail VARCHAR(50),
	PRIMARY KEY (MotoristaId)
);

CREATE TABLE Horario(
	HorarioId INT NOT NULL,
	HorarioDiaId INT NOT NULL,
	Horario_RotaItinerarioId INT NOT NULL,
	HorarioSaida VARCHAR(8) NOT NULL,
	HorarioChegada VARCHAR(8) NOT NULL,
	HorarioPreco DECIMAL(12,2) NOT NULL,
	Horario_MotoristaId INT NOT NULL,
	Horario_OnibusId INT NOT NULL,
	PRIMARY KEY(HorarioId, HorarioDiaId),
	FOREIGN KEY(Horario_RotaItinerarioId) REFERENCES RotaItinerario(RotaItinerarioId),
	FOREIGN KEY(Horario_MotoristaId) REFERENCES Motorista(MotoristaId),
	FOREIGN KEY(Horario_OnibusId) REFERENCES Onibus(OnibusId)
);

CREATE TABLE Passagem(
	PassagemId INT AUTO_INCREMENT NOT NULL,
	PassagemData VARCHAR(10)NOT NULL,
	Passagem_HorarioId INT NOT NULL,
	Passagem_HorarioDiaId INT NOT NULL,
	PassagemClienteCpf VARCHAR(15) NOT NULL,
	PassagemClienteNome VARCHAR(200) NOT NULL,
	PassagemNumAssentoComprado INT NOT NULL,
	PRIMARY KEY(PassagemId),
	FOREIGN KEY(Passagem_HorarioId,Passagem_HorarioDiaId) REFERENCES Horario		(HorarioId,HorarioDiaId)
);

CREATE TABLE Funcionario(
	FuncionarioId INT AUTO_INCREMENT NOT NULL,
	FuncionarioNome VARCHAR(100) NOT NULL,
	FuncionarioCpf VARCHAR(15) NOT NULL UNIQUE,
	FuncionarioEmail VARCHAR(100) NOT NULL,
	FuncionarioSenha VARCHAR(30) NOT NULL,
	FuncionarioAcesso INT,
	PRIMARY KEY(FuncionarioId)
);