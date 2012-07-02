USE ProjES;

/* Estados */

INSERT INTO Estado VALUES(0,'SP');
INSERT INTO Estado VALUES(0,'MG');
INSERT INTO Estado VALUES(0,'RJ');
INSERT INTO Estado VALUES(0,'PR');
INSERT INTO Estado VALUES(0,'SC');

/* Cidades */

INSERT INTO Cidade VALUES(0,'Sao Jose dos Campos', 1);
INSERT INTO Cidade VALUES(0,'Aparecida do Norte', 1);
INSERT INTO Cidade VALUES(0,'Santo Andre', 1);
INSERT INTO Cidade VALUES(0,'Rezende', 3);
INSERT INTO Cidade VALUES(0,'Niteroi', 3);
INSERT INTO Cidade VALUES(0,'Itajuba', 2);
INSERT INTO Cidade VALUES(0,'Pouso Alegre', 2);
INSERT INTO Cidade VALUES(0,'Cianorte', 4);
INSERT INTO Cidade VALUES(0,'Curitiba', 4);
INSERT INTO Cidade VALUES(0,'Florianopolis', 5);
INSERT INTO Cidade VALUES(0,'Bombinhas', 5);
INSERT INTO Cidade VALUES(0,'Guaratingueta', 1);
INSERT INTO Cidade VALUES(0,'Sao Paulo', 1);
INSERT INTO Cidade VALUES(0,'Juquitiba', 1);
INSERT INTO Cidade VALUES(0,'Cajati', 1);

/* Onibus */

INSERT INTO Onibus VALUES(0,'ERW-1345','Serie F - F250','Scania',2009,50);
INSERT INTO Onibus VALUES(0,'ASF-9371','Serie F - F310','Scania',2010,50);
INSERT INTO Onibus VALUES(0,'DDJ-1345','Serie K - K250','Scania',2008,50);
INSERT INTO Onibus VALUES(0,'FMS-4552','Serie K - K310','Scania',2010,50);
INSERT INTO Onibus VALUES(0,'KCB-0911','O-500 M','Mercedes-Benz',2011,50);
INSERT INTO Onibus VALUES(0,'SHJ-1177','O-500 R','Mercedes-Benz',2011,50);

/* Motorista */

INSERT INTO Motorista VALUES(0,'Jose da Silva','43.567.986-2','234.543.574-34','Rua 1, no 450 - Jd. Queiroz', '(12) 9753.2553', 'jose.silva@onibus.br');
INSERT INTO Motorista VALUES(0,'Marco Antonio Matos','48.522.677-7','567.432.123-24','Rua 2, no 460 - Jd. Queiroz', '(12) 9852.1343', 'm.matos@onibus.br');
INSERT INTO Motorista VALUES(0,'Pablo Jose Alencar','47.111.422-8','124.533.131-11','Rua 3, no 470 - Jd. Queiroz', '(12) 9124.5311', 'p.alencar@onibus.br');
INSERT INTO Motorista VALUES(0,'Jose Moureira','41.121.933-1','379.988.313-33','Rua 4, no 480 - Jd. Queiroz', '(12) 9789.2112', 'j.moureira@onibus.br');
INSERT INTO Motorista VALUES(0,'Reinaldo Souza','42.467.852-x','234.134.567-25','Rua 5, no 490 - Jd. Queiroz', '(12) 8854.3572', 'r.souza@onibus.br');
INSERT INTO Motorista VALUES(0,'Mauro Pereira','43.642.432-2','230.652.578-67','Rua 6, no 410 - Jd. Queiroz', '(12) 9123.4321', 'mauro.pereira@onibus.br');
INSERT INTO Motorista VALUES(0,'Hugo Moraes','44.345.146-x','698.689.764-89','Rua 7, no 420 - Jd. Queiroz', '(12) 9235.6785', 'h.moraes@onibus.br');
INSERT INTO Motorista VALUES(0,'Gabriel Almeida','45.124.864-6','178.532.894-11','Rua 8, no 430 - Jd. Queiroz', '(12) 9986.1334', 'g.almeida@onibus.br');
INSERT INTO Motorista VALUES(0,'Guilherme Amorim','46.591.123-5','274.357.904-74','Rua 9, no 440 - Jd. Queiroz', '(12) 9734.2222', 'g.amorim@onibus.br');

/* Itinerario */

INSERT INTO Itinerario VALUES(0,1,6);
INSERT INTO Itinerario VALUES(0,1,4);
INSERT INTO Itinerario VALUES(0,1,9);
INSERT INTO Itinerario VALUES(0,9,10);
INSERT INTO Itinerario VALUES(0,4,5);

/* Rota */

INSERT INTO Rota VALUES(0,1,12,'60');
INSERT INTO Rota VALUES(0,12,6,'60');
INSERT INTO Rota VALUES(0,1,2,'60');
INSERT INTO Rota VALUES(0,2,3,'60');
INSERT INTO Rota VALUES(0,3,4,'60');
INSERT INTO Rota VALUES(0,1,13,'60');
INSERT INTO Rota VALUES(0,13,14,'60');
INSERT INTO Rota VALUES(0,14,15,'60');
INSERT INTO Rota VALUES(0,15,9,'60');
INSERT INTO Rota VALUES(0,9,3,'60');
INSERT INTO Rota VALUES(0,3,6,'60');
INSERT INTO Rota VALUES(0,6,10,'60');

/* Rota Itinerario */

INSERT INTO RotaItinerario VALUES(0,1,1,1);
INSERT INTO RotaItinerario VALUES(0,2,1,2);
INSERT INTO RotaItinerario VALUES(0,3,2,1);
INSERT INTO RotaItinerario VALUES(0,4,2,2);
INSERT INTO RotaItinerario VALUES(0,5,2,3);
INSERT INTO RotaItinerario VALUES(0,6,3,1);
INSERT INTO RotaItinerario VALUES(0,7,3,2);
INSERT INTO RotaItinerario VALUES(0,8,3,3);
INSERT INTO RotaItinerario VALUES(0,9,3,4);


/* Horarios e dias de Viagem SJC-Itajuba */
INSERT INTO Horario VALUES(0,1,1,'11:00:00','12:00:00',50,2,3);
INSERT INTO Horario VALUES(0,2,1,'11:00:00','12:00:00',50,4,4);
INSERT INTO Horario VALUES(0,3,1,'11:00:00','12:00:00',50,2,3);
INSERT INTO Horario VALUES(0,4,1,'11:00:00','12:00:00',45,4,4);
INSERT INTO Horario VALUES(0,5,1,'11:00:00','12:00:00',40,2,3);
INSERT INTO Horario VALUES(0,6,1,'11:00:00','12:00:00',50,4,4);
INSERT INTO Horario VALUES(0,7,1,'11:00:00','12:00:00',50,2,3);


INSERT INTO Horario VALUES(0,1,2,'12:00:00','13:00:00',20,2,3);
INSERT INTO Horario VALUES(0,2,2,'12:00:00','13:00:00',20,4,4);
INSERT INTO Horario VALUES(0,3,2,'12:00:00','13:00:00',20,2,3);
INSERT INTO Horario VALUES(0,4,2,'12:00:00','13:00:00',15,4,4);
INSERT INTO Horario VALUES(0,5,2,'12:00:00','13:00:00',10,2,3);
INSERT INTO Horario VALUES(0,6,2,'12:00:00','13:00:00',20,4,4);
INSERT INTO Horario VALUES(0,7,2,'12:00:00','13:00:00',20,2,3);


 /* Horarios e dias de viagem SJC-RES */
INSERT INTO Horario VALUES(0,1,3,'15:00:00','16:00:00',50,1,1);
INSERT INTO Horario VALUES(0,2,3,'15:00:00','16:00:00',50,3,2);
INSERT INTO Horario VALUES(0,3,3,'15:00:00','16:00:00',50,1,1);
INSERT INTO Horario VALUES(0,4,3,'15:00:00','16:00:00',45,3,2);
INSERT INTO Horario VALUES(0,5,3,'15:00:00','16:00:00',40,1,1);
INSERT INTO Horario VALUES(0,6,3,'15:00:00','16:00:00',50,3,2);
INSERT INTO Horario VALUES(0,7,3,'15:00:00','16:00:00',50,1,1);

INSERT INTO Horario VALUES(0,1,4,'16:00:00','17:00:00',20,1,1);
INSERT INTO Horario VALUES(0,2,4,'16:00:00','17:00:00',20,3,2);
INSERT INTO Horario VALUES(0,3,4,'16:00:00','17:00:00',20,1,1);
INSERT INTO Horario VALUES(0,4,4,'16:00:00','17:00:00',15,3,2);
INSERT INTO Horario VALUES(0,5,4,'16:00:00','17:00:00',10,1,1);
INSERT INTO Horario VALUES(0,6,4,'16:00:00','17:00:00',20,3,2);
INSERT INTO Horario VALUES(0,7,4,'16:00:00','17:00:00',20,1,1);

INSERT INTO Horario VALUES(0,1,5,'17:00:00','18:00:00',30,1,1);
INSERT INTO Horario VALUES(0,2,5,'17:00:00','18:00:00',30,3,2);
INSERT INTO Horario VALUES(0,3,5,'17:00:00','18:00:00',30,1,1);
INSERT INTO Horario VALUES(0,4,5,'17:00:00','18:00:00',25,3,2);
INSERT INTO Horario VALUES(0,5,5,'17:00:00','18:00:00',20,1,1);
INSERT INTO Horario VALUES(0,6,5,'17:00:00','18:00:00',30,3,2);
INSERT INTO Horario VALUES(0,7,5,'17:00:00','18:00:00',30,1,1);

 /* Horarios e dias de viagem SJC-CUR */
INSERT INTO Horario VALUES(0,1,6,'15:00:00','16:00:00',50,6,5);
INSERT INTO Horario VALUES(0,2,6,'15:00:00','16:00:00',50,7,6);
INSERT INTO Horario VALUES(0,3,6,'15:00:00','16:00:00',50,6,5);
INSERT INTO Horario VALUES(0,4,6,'15:00:00','16:00:00',45,7,6);
INSERT INTO Horario VALUES(0,5,6,'15:00:00','16:00:00',40,6,5);
INSERT INTO Horario VALUES(0,6,6,'15:00:00','16:00:00',50,7,6);
INSERT INTO Horario VALUES(0,7,6,'15:00:00','16:00:00',50,6,5);

INSERT INTO Horario VALUES(0,1,7,'16:00:00','17:00:00',20,6,5);
INSERT INTO Horario VALUES(0,2,7,'16:00:00','17:00:00',20,7,6);
INSERT INTO Horario VALUES(0,3,7,'16:00:00','17:00:00',20,6,5);
INSERT INTO Horario VALUES(0,4,7,'16:00:00','17:00:00',15,7,6);
INSERT INTO Horario VALUES(0,5,7,'16:00:00','17:00:00',10,6,5);
INSERT INTO Horario VALUES(0,6,7,'16:00:00','17:00:00',20,7,6);
INSERT INTO Horario VALUES(0,7,7,'16:00:00','17:00:00',20,6,5);

INSERT INTO Horario VALUES(0,1,8,'17:00:00','18:00:00',30,6,5);
INSERT INTO Horario VALUES(0,2,8,'17:00:00','18:00:00',30,7,6);
INSERT INTO Horario VALUES(0,3,8,'17:00:00','18:00:00',30,6,5);
INSERT INTO Horario VALUES(0,4,8,'17:00:00','18:00:00',25,7,6);
INSERT INTO Horario VALUES(0,5,8,'17:00:00','18:00:00',20,6,5);
INSERT INTO Horario VALUES(0,6,8,'17:00:00','18:00:00',30,7,6);
INSERT INTO Horario VALUES(0,7,8,'17:00:00','18:00:00',30,6,5);

INSERT INTO Horario VALUES(0,1,9,'18:00:00','19:00:00',30,6,5);
INSERT INTO Horario VALUES(0,2,9,'18:00:00','19:00:00',30,7,6);
INSERT INTO Horario VALUES(0,3,9,'18:00:00','19:00:00',30,6,5);
INSERT INTO Horario VALUES(0,4,9,'18:00:00','19:00:00',25,7,6);
INSERT INTO Horario VALUES(0,5,9,'18:00:00','19:00:00',20,6,5);
INSERT INTO Horario VALUES(0,6,9,'18:00:00','19:00:00',30,7,6);
INSERT INTO Horario VALUES(0,7,9,'18:00:00','19:00:00',30,6,5);


/*TESTES MARCELA
INSERT INTO Horario VALUES(0,1,1,'10:00:00','12:00:00',15,6,5);

INSERT INTO Passagem VALUES
(0,'01/01/2012',8,1,'cpf 456','Matheus',30),
(0,'01/01/2012',8,1,'cpf 789','Matheus',31),
(0,'01/01/2012',8,1,'cpf 123','Matheus',20);
*/

/* PASSAGEM */
INSERT INTO Passagem VALUES(0,'16/06/2012',1,1,'123','Matheus',9);
INSERT INTO Passagem VALUES(0,'27/06/2012',25,4,'456','Goias',17);
INSERT INTO Passagem VALUES(0,'12/07/2012',40,5,'789','Victor',21);
