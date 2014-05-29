DROP TABLE Client CASCADE CONSTRAINTS
/
DROP TABLE Article CASCADE CONSTRAINTS
/
DROP TABLE Client CASCADE CONSTRAINTS
/
DROP TABLE Categorie CASCADE CONSTRAINTS
/
DROP TABLE Commande CASCADE CONSTRAINTS
/
DROP TABLE LigneCommande CASCADE CONSTRAINTS
/
DROP TABLE Livraison CASCADE CONSTRAINTS
/
DROP TABLE DetailLivraison CASCADE CONSTRAINTS
/
DROP TABLE Systeme CASCADE CONSTRAINTS
/
DROP TABLE Facture CASCADE CONSTRAINTS
/
DROP TABLE Paiement CASCADE CONSTRAINTS
/
DROP TABLE CompteBancaire CASCADE CONSTRAINTS
/
DROP TABLE PaiementParCheque CASCADE CONSTRAINTS
/
DROP TABLE CarteCredit CASCADE CONSTRAINTS
/
DROP TABLE PaiementParCarte CASCADE CONSTRAINTS
/

SET ECHO ON
-- Par Guillaume Lahaie - LAHG04077707
--Script de création de tables pour le TP2 
-- On en crée pas la db ici, on considère qu'elle est déjà créée

-- Creation des tables
CREATE TABLE Client
(noClient 		INTEGER 	NOT NULL,
 nom 			VARCHAR(15) 	NOT NULL,
 prenom			VARCHAR(15)	NOT NULL,
 adresseLivraison	VARCHAR(15)	NOT NULL,
 dateInscription	VARCHAR(15)	NOT NULL,
 heureInscription	VARCHAR(15)	NOT NULL,
 courriel		VARCHAR(30)	NOT NULL,
 motDePasse		CHARACTER(6) 	NOT NULL,
 PRIMARY KEY 	(noClient)
)
/
CREATE TABLE Categorie
(code			INTEGER		NOT NULL,
 libelle		VARCHAR(30)	NOT NULL,
 codeParent		INTEGER,
 PRIMARY KEY	(code)
)
/
CREATE TABLE Article
(noArticle 		INTEGER		NOT NULL,
 description 		VARCHAR(256),
 prixUnitaire 		INTEGER		NOT NULL,
 image			VARCHAR(256),
 URL			VARCHAR(256),
 quantiteEnStock	INTEGER		NOT NULL,
 code			INTEGER,
 PRIMARY KEY (noArticle),
 FOREIGN KEY (code) REFERENCES Categorie
)
/
CREATE TABLE Commande
(noCommande 		INTEGER 	NOT NULL,
 dateCommande		VARCHAR(30)	NOT NULL,
 statutCommande		VARCHAR(20)	NOT NULL,
 noClient		INTEGER		NOT NULL,
 PRIMARY KEY 	(noCommande),
 FOREIGN KEY 	(noClient) REFERENCES Client
)
/
CREATE TABLE LigneCommande
(noCommande 		INTEGER		NOT NULL,
 noArticle 		INTEGER		NOT NULL,
 quantite 		INTEGER		NOT NULL,
 PRIMARY KEY (noCommande, noArticle),
 FOREIGN KEY (noCommande) REFERENCES Commande,
 FOREIGN KEY (noArticle) REFERENCES Article
)
/
CREATE TABLE Livraison
(noLivraison 	INTEGER 		NOT NULL,
 dateLivraison	VARCHAR(30)		NOT NULL,
 PRIMARY KEY (noLivraison)
)
/
CREATE TABLE DetailLivraison
(noLivraison 		INTEGER 	NOT NULL,
 noCommande 		INTEGER		NOT NULL,
 noArticle 		INTEGER		NOT NULL,
 quantiteLivree		INTEGER		NOT NULL
	CHECK (quantiteLivree > 0),
 PRIMARY KEY (noLivraison, noCommande, noArticle),
 FOREIGN KEY (noLivraison) REFERENCES Livraison,
 FOREIGN KEY (noCommande, noArticle) REFERENCES LigneCommande
)
/
CREATE TABLE Systeme
(noSysteme		INTEGER		NOT NULL,
 nomSysteme		VARCHAR(256)	NOT NULL,
 PRIMARY KEY (noSysteme)
)
/
CREATE TABLE Facture
(noFacture		INTEGER		NOT NULL,
 dateLimitePaiement	DATE		NOT NULL,
 noLivraison		INTEGER		NOT NULL,
 PRIMARY KEY (noFacture),
 FOREIGN KEY (noLivraison) REFERENCES Livraison
)
/
CREATE TABLE Paiement
(noPaiement		INTEGER		NOT NULL,
 datePaiement		DATE		NOT NULL,
 montantPaiement	INTEGER		NOT NULL,
 noFacture		INTEGER		NOT NULL,
 PRIMARY KEY (noPaiement),
 FOREIGN KEY (noFacture) REFERENCES Facture
)
/
CREATE TABLE CompteBancaire
(idBanque		INTEGER		NOT NULL,
 noCompte		INTEGER		NOT NULL,
 PRIMARY KEY(idBanque, noCompte)
)
/
CREATE TABLE PaieMentParCheque
(noPaiement		INTEGER		NOT NULL,
 noCheque		INTEGER		NOT NULL,
 idBanque		INTEGER		NOT NULL,
 noCompte		INTEGER		NOT NULL,
 PRIMARY KEY (noPaiement),
 FOREIGN KEY (noPaiement) REFERENCES Paiement,
 FOREIGN KEY (idBanque, noCompte) REFERENCES CompteBancaire
)
/
CREATE TABLE CarteCredit
(noCarte		NUMBER(12)	NOT NULL,
 sorteCarte		VARCHAR(2)	NOT NULL,
 dateExpiration		DATE		NOT NULL,
 PRIMARY KEY (noCarte)
)
/
CREATE TABLE PaiementParCarte
(noPaiement		INTEGER		NOT NULL,
 noAutorisation		INTEGER		NOT NULL,
 noCarte		INTEGER		NOT NULL,
 PRIMARY KEY (noPaiement),
 FOREIGN KEY (noPaiement) REFERENCES Paiement,
 FOREIGN KEY (noCarte) REFERENCES CarteCredit
)
/
INSERT INTO CLIENT VALUES(10, 'lahaie','guillaume', 'parc','25/04/13','10:00:00','courriel@allo.com', 'mpasse')
/
INSERT INTO COMMANDE VALUES(10, '23/04/14', 'confirme',10)
/
INSERT INTO ARTICLE(noArticle, description, prixUnitaire, quantiteEnStock) VALUES (10, 'un article', 350.00, 100)
/
INSERT INTO ARTICLE(noArticle, description, prixUnitaire, quantiteEnStock) VALUES (20, 'un autre article', 100.00, 2)
/
INSERT INTO ARTICLE(noArticle, description, prixUnitaire, quantiteEnStock) VALUES (30, 'un dernier article', 3.00, 1000)
/
INSERT INTO LIGNECOMMANDE(noArticle, noCommande, quantite) VALUES(10, 10, 5)
/
INSERT INTO LIGNECOMMANDE(noArticle, noCommande, quantite) VALUES(20, 10, 2)
/
INSERT INTO LIGNECOMMANDE(noArticle, noCommande, quantite) VALUES(30, 10, 1)
/
INSERT INTO LIVRAISON(noLivraison, dateLivraison) VALUES(10, '24/04/14')
/
INSERT INTO DETAILLIVRAISON(noLivraison, noCommande, noArticle, quantiteLivree) VALUES(10, 10, 10, 3)
/
INSERT INTO DETAILLIVRAISON(noLivraison, noCommande, noArticle, quantiteLivree) VALUES(10, 10, 20, 2)
/
COMMIT
/
