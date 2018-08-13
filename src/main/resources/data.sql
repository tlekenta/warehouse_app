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


INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (1, 1,1,'2018-04-28 19:00','2018-04-29 03:00', 'P000000001');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (2, 1,2,'2018-04-29 19:00','2018-04-30 03:00', 'P000000001');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (3, 1,1,'2018-04-30 19:00','2018-05-01 03:00', 'P000000001');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (4, 1,2,'2018-05-01 19:00','2018-05-02 03:00', 'P000000001');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (5, 1,1,'2018-05-02 19:00','2018-05-03 03:00', 'P000000001');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (6, 2,1,'2018-04-28 09:00','2018-04-28 18:00', 'P000000002');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (7, 2,2,'2018-04-29 09:00','2018-04-29 18:00', 'P000000002');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (8, 2,1,'2018-04-30 09:00','2018-04-30 18:00', 'P000000002');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (9, 2,2,'2018-05-01 09:00','2018-05-01 18:00', 'P000000002');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (10, 2,1,'2018-05-02 09:00','2018-05-02 18:00', 'P000000002');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (11, 3,2,'2018-04-28 09:00','2018-04-28 18:00', 'P000000003');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (12, 3,1,'2018-04-29 09:00','2018-04-29 18:00', 'P000000003');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (13, 3,2,'2018-04-30 09:00','2018-04-30 18:00', 'P000000003');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (14, 3,1,'2018-05-01 09:00','2018-05-01 18:00', 'P000000003');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (15, 3,2,'2018-05-02 09:00','2018-05-02 18:00', 'P000000003');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (16, 4,1,'2018-04-28 09:00','2018-04-28 18:00', 'P000000004');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (17, 4,2,'2018-04-29 09:00','2018-04-29 18:00', 'P000000004');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (18, 4,1,'2018-04-30 09:00','2018-04-30 18:00', 'P000000004');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (19, 4,2,'2018-05-01 09:00','2018-05-01 18:00', 'P000000004');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (20, 4,1,'2018-05-02 09:00','2018-05-02 18:00', 'P000000004');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (21, 5,2,'2018-04-28 09:00','2018-04-28 18:00', 'P000000005');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (22, 5,1,'2018-04-29 09:00','2018-04-29 18:00', 'P000000005');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (23, 5,2,'2018-04-30 09:00','2018-04-30 18:00', 'P000000005');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (24, 5,1,'2018-05-01 09:00','2018-05-01 18:00', 'P000000005');
INSERT INTO ZrodloSystem_ObecnoscWpracy(id, PracownikId,SklepId,Przybycie,Wyjscie, numerPracownika) VALUES (25, 5,2,'2018-05-02 09:00','2018-05-02 18:00', 'P000000005');

INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Unit VALUES(1, 'szt.');
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Unit VALUES(2, 'kg');
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Unit VALUES(3, 'litry');

INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Product VALUES(1, '123456789012345', 'kalafior', 1, 2.00);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Product VALUES(2, '998745468486432', 'ziemniaki', 2, 0.99);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Product VALUES(3, '595984458752584', 'marchew', 2, 3.50);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Product VALUES(4, '454545487854523', 'mleko', 3, 3.00);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Product VALUES(5, '895547885224152', 'chleb', 1, 3.00);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Product VALUES(6, '365899565788554', 'pomarańcze', 2, 7.00);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Product VALUES(7, '569874521855247', 'pomidory', 2, 5.00);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Product VALUES(8, '964574785522696', 'jabłka', 2, 2.20);

INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Client VALUES (1, 'K000000001');
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Client VALUES (2, 'K000000002');
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Client VALUES (3, 'K000000003');

INSERT INTO ZRODLO_POS.dbo.ZrodloPos_User VALUES (1, 'P000000001');
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_User VALUES (2, 'P000000002');
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_User VALUES (3, 'P000000003');
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_User VALUES (4, 'P000000004');
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_User VALUES (5, 'P000000005');

INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(1, 1, '2018-04-28 19:12:12', 'PAR-001', 0.00, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(2, 2, '2018-04-29 19:13:30', 'PAR-002', 0.00, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(3, 3, '2018-04-30 19:14:40', 'PAR-003', 0.00, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(4, 4, '2018-05-01 19:15:50', 'PAR-004', 0.00, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(5, 5, '2018-05-02 19:16:00', 'PAR-005', 0.00, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(6, 1, '2018-04-28 12:15:12', 'PAR-006', 0.00, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(7, 2, '2018-04-29 12:14:25', 'PAR-007', 0.00, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(8, 3, '2018-04-30 12:20:25', 'PAR-008', 0.00, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(9, 4, '2018-05-01 12:14:25', 'PAR-009', 0.00, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(10, 5, '2018-05-02 12:14:25', 'PAR-010', 0.00, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(11, 1, '2018-04-28 13:54:43', 'PAR-011', 0.00, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(12, 2, '2018-04-29 14:48:59', 'PAR-012', 0.00, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(13, 3, '2018-04-30 17:50:50', 'PAR-013', 0.00, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(14, 4, '2018-05-01 14:23:43', 'PAR-014', 0.00, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(15, 5, '2018-05-02 10:18:09', 'PAR-015', 0.00, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(16, 1, '2018-04-28 12:54:43', 'PAR-016', 0.00, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(17, 2, '2018-04-29 13:48:59', 'PAR-017', 0.00, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(18, 3, '2018-04-30 14:50:50', 'PAR-018', 0.00, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(19, 4, '2018-05-01 15:23:43', 'PAR-019', 0.00, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_Receipt VALUES(20, 5, '2018-05-02 16:18:09', 'PAR-020', 0.00, 4);

INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(1, 1, 1, 'PAR-001', 14.28, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(2, 1, 2, 'PAR-002', 14.09, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(3, 1, 3, 'PAR-003', 15.39, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(4, 1, 4, 'PAR-004', 9.84, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(5, 1, 5, 'PAR-005', 4.91, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(6, 1, 6, 'PAR-006', 0.03, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(7, 1, 7, 'PAR-007', 7.11, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(8, 1, 8, 'PAR-008', 18.83, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(9, 1, 9, 'PAR-009', 5.66, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(10, 1, 10, 'PAR-010', 10.62, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(11, 1, 11, 'PAR-011', 3.62, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(12, 1, 12, 'PAR-012', 2.88, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(13, 1, 13, 'PAR-013', 17.21, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(14, 1, 14, 'PAR-014', 9.11, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(15, 1, 15, 'PAR-015', 13.66, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(16, 1, 16, 'PAR-016', 0.85, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(17, 1, 17, 'PAR-017', 4.66, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(18, 1, 18, 'PAR-018', 19.36, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(19, 1, 19, 'PAR-019', 5.75, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(20, 1, 20, 'PAR-020', 5.93, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(21, 2, 1, 'PAR-001', 4.61, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(22, 2, 2, 'PAR-002', 12.06, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(23, 2, 3, 'PAR-003', 11.98, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(24, 2, 4, 'PAR-004', 19.68, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(25, 2, 5, 'PAR-005', 8.9, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(26, 2, 6, 'PAR-006', 18.57, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(27, 2, 7, 'PAR-007', 17.46, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(28, 2, 8, 'PAR-008', 5.26, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(29, 2, 9, 'PAR-009', 13.8, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(30, 2, 10, 'PAR-010', 14.01, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(31, 2, 11, 'PAR-011', 0.38, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(32, 2, 12, 'PAR-012', 9.52, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(33, 2, 13, 'PAR-013', 15.93, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(34, 2, 14, 'PAR-014', 4.95, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(35, 2, 15, 'PAR-015', 17.16, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(36, 2, 16, 'PAR-016', 11.97, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(37, 2, 17, 'PAR-017', 2.58, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(38, 2, 18, 'PAR-018', 0.98, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(39, 2, 19, 'PAR-019', 18.4, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(40, 2, 20, 'PAR-020', 19.92, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(41, 3, 1, 'PAR-001', 10.56, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(42, 3, 2, 'PAR-002', 12.53, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(43, 3, 3, 'PAR-003', 10.1, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(44, 3, 4, 'PAR-004', 19.88, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(45, 3, 5, 'PAR-005', 16.57, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(46, 3, 6, 'PAR-006', 18.55, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(47, 3, 7, 'PAR-007', 6.54, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(48, 3, 8, 'PAR-008', 10.3, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(49, 3, 9, 'PAR-009', 4.74, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(50, 3, 10, 'PAR-010', 10.22, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(51, 3, 11, 'PAR-011', 1.8, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(52, 3, 12, 'PAR-012', 3.33, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(53, 3, 13, 'PAR-013', 2.01, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(54, 3, 14, 'PAR-014', 12.85, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(55, 3, 15, 'PAR-015', 3.42, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(56, 3, 16, 'PAR-016', 10.12, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(57, 3, 17, 'PAR-017', 16.11, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(58, 3, 18, 'PAR-018', 9.7, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(59, 3, 19, 'PAR-019', 11.3, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(60, 3, 20, 'PAR-020', 10.8, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(61, 4, 1, 'PAR-001', 1.24, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(62, 4, 2, 'PAR-002', 8.46, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(63, 4, 3, 'PAR-003', 11.7, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptId, reciptNumber, amount, productId) VALUES(64, 4, 4, 'PAR-004', 14.82, 8);



USE ZRODLO_POS
GO

WITH tmp AS
(
	SELECT receiptId, sum(amount) AS suma
	FROM ZrodloPos_ReceiptItem
	GROUP BY receiptId
)

UPDATE ZrodloPos_Receipt SET totalCost = tmp.suma FROM ZrodloPos_Receipt r INNER JOIN tmp ON r.id = tmp.receiptId;