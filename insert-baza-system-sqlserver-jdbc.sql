USE ZRODLO_PRACOWNICY
GO

INSERT INTO ZrodloSystem_Adres(id, Ulica, NumerBudynku, NumerLokalu) VALUES (1, 'Słowackiego', 21, 1);
INSERT INTO ZrodloSystem_Adres(id, Ulica, NumerBudynku, NumerLokalu) VALUES (2, 'Słowackiego', 21, 14);
INSERT INTO ZrodloSystem_Adres(id, Ulica, NumerBudynku, NumerLokalu) VALUES (3, 'Mickiewicza', 1, 1);
INSERT INTO ZrodloSystem_Adres(id, Ulica, NumerBudynku, NumerLokalu) VALUES (4, 'Mickiewicza', 11, 11);
INSERT INTO ZrodloSystem_Adres(id, Ulica, NumerBudynku, NumerLokalu) VALUES (5, 'Słoneczna', 12, 3);
INSERT INTO ZrodloSystem_Adres(id, Ulica, NumerBudynku, NumerLokalu) VALUES (6, 'Dmowskiego', 1, 3);
INSERT INTO ZrodloSystem_Adres(id, Ulica, NumerBudynku, NumerLokalu) VALUES (7, 'Lelewela', 4, 1);
INSERT INTO ZrodloSystem_Adres(id, Ulica, NumerBudynku, NumerLokalu) VALUES (8, 'Karmelicka', 1, 152);
INSERT INTO ZrodloSystem_Adres(id, Ulica, NumerBudynku, NumerLokalu) VALUES (9, 'Anielewicza', 12, 91);
INSERT INTO ZrodloSystem_Adres(id, Ulica, NumerBudynku, NumerLokalu) VALUES (10, 'Jana Pawła II', 2, 1);

INSERT INTO ZrodloSystem_Sklep(id, NumerSklepu,Nazwa,Telefon,AdresID) VALUES (1, 'S000000001','Sklep nr 1','534-281-234',1);
INSERT INTO ZrodloSystem_Sklep(id, NumerSklepu,Nazwa,Telefon,AdresID) VALUES (2, 'S000000002','Sklep nr 2','542-421-123',2);


INSERT INTO ZrodloSystem_TypPracownika(id, Typ) VALUES (1, 'Sprzedawca');
INSERT INTO ZrodloSystem_TypPracownika(id, Typ) VALUES (2, 'Kierownik zmiany');
INSERT INTO ZrodloSystem_TypPracownika(id, Typ) VALUES (3, 'Magazynier');
INSERT INTO ZrodloSystem_TypPracownika(id, Typ) VALUES (4, 'Kierownik');


INSERT INTO ZrodloSystem_Pracownik(id, NumerPracownika,Pesel,Telefon,TypPracownikaId,AdresID,Imie,Nazwisko) VALUES (1, 'P000000003','77102318371','666-221-521',3,3,'Michał','Trąba');
INSERT INTO ZrodloSystem_Pracownik(id, NumerPracownika,Pesel,Telefon,TypPracownikaId,AdresID,Imie,Nazwisko) VALUES (2, 'P000000004','77102381214','902-301-213',4,4,'Dariusz','Urban');
INSERT INTO ZrodloSystem_Pracownik(id, NumerPracownika,Pesel,Telefon,TypPracownikaId,AdresID,Imie,Nazwisko) VALUES (3, 'P000000005','77102332586','902-301-213',1,5,'Genowefa','Polak');
INSERT INTO ZrodloSystem_Pracownik(id, NumerPracownika,Pesel,Telefon,TypPracownikaId,AdresID,Imie,Nazwisko) VALUES (4, 'P000000001','77102327922','900-281-091',1,6,'Alfons','Bobek');
INSERT INTO ZrodloSystem_Pracownik(id, NumerPracownika,Pesel,Telefon,TypPracownikaId,AdresID,Imie,Nazwisko) VALUES (5, 'P000000002','77102389410','320-311-091',2,7,'Katarzyna','Kowal');


INSERT INTO ZrodloSystem_Klient(id, NumerKlienta,Telefon,AdresID,Imie,Nazwisko) VALUES (1, 'K000000001','821-312-312',8,'Piotr','Polak');
INSERT INTO ZrodloSystem_Klient(id, NumerKlienta,Telefon,AdresID,Imie,Nazwisko) VALUES (2, 'K000000002','321-312-312',9,'Beata','Tyśkiewicz');
INSERT INTO ZrodloSystem_Klient(id, NumerKlienta,Telefon,AdresID,Imie,Nazwisko) VALUES (3, 'K000000003','221-312-321',10,'Łukasz','Małachowski');


INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (1, 1,1,'2018-04-28 19:00','2018-04-28 03:00', 'P000000001');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (2, 1,2,'2018-04-29 19:00','2018-04-28 03:00', 'P000000001');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (3, 1,1,'2018-04-30 19:00','2018-04-28 03:00', 'P000000001');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (4, 1,2,'2018-05-01 19:00','2018-04-28 03:00', 'P000000001');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (5, 1,1,'2018-05-02 19:00','2018-04-28 03:00', 'P000000001');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (6, 2,1,'2018-04-28 09:00','2018-04-28 18:00', 'P000000002');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (7, 2,2,'2018-04-29 09:00','2018-04-28 18:00', 'P000000002');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (8, 2,1,'2018-04-30 09:00','2018-04-28 18:00', 'P000000002');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (9, 2,2,'2018-05-01 09:00','2018-04-28 18:00', 'P000000002');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (10, 2,1,'2018-05-02 09:00','2018-04-28 18:00', 'P000000002');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (11, 3,2,'2018-04-28 09:00','2018-04-28 18:00', 'P000000003');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (12, 3,1,'2018-04-29 09:00','2018-04-28 18:00', 'P000000003');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (13, 3,2,'2018-04-30 09:00','2018-04-28 18:00', 'P000000003');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (14, 3,1,'2018-05-01 09:00','2018-04-28 18:00', 'P000000003');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (15, 3,2,'2018-05-02 09:00','2018-04-28 18:00', 'P000000003');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (16, 4,1,'2018-04-28 09:00','2018-04-28 18:00', 'P000000004');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (17, 4,2,'2018-04-29 09:00','2018-04-28 18:00', 'P000000004');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (18, 4,1,'2018-04-30 09:00','2018-04-28 18:00', 'P000000004');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (19, 4,2,'2018-05-01 09:00','2018-04-28 18:00', 'P000000004');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (20, 4,1,'2018-05-02 09:00','2018-04-28 18:00', 'P000000004');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (21, 5,2,'2018-04-28 09:00','2018-04-28 18:00', 'P000000005');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (22, 5,1,'2018-04-29 09:00','2018-04-28 18:00', 'P000000005');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (23, 5,2,'2018-04-30 09:00','2018-04-28 18:00', 'P000000005');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (24, 5,1,'2018-05-01 09:00','2018-04-28 18:00', 'P000000005');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (25, 5,2,'2018-05-02 09:00','2018-04-28 18:00', 'P000000005');

