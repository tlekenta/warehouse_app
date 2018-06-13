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

INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(1, 1, 1, 'PAR-001', 14.28, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(2, 1, 2, 'PAR-002', 14.09, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(3, 1, 3, 'PAR-003', 15.39, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(4, 1, 4, 'PAR-004', 9.84, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(5, 1, 5, 'PAR-005', 4.91, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(6, 1, 6, 'PAR-006', 0.03, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(7, 1, 7, 'PAR-007', 7.11, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(8, 1, 8, 'PAR-008', 18.83, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(9, 1, 9, 'PAR-009', 5.66, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(10, 1, 10, 'PAR-010', 10.62, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(11, 1, 11, 'PAR-011', 3.62, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(12, 1, 12, 'PAR-012', 2.88, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(13, 1, 13, 'PAR-013', 17.21, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(14, 1, 14, 'PAR-014', 9.11, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(15, 1, 15, 'PAR-015', 13.66, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(16, 1, 16, 'PAR-016', 0.85, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(17, 1, 17, 'PAR-017', 4.66, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(18, 1, 18, 'PAR-018', 19.36, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(19, 1, 19, 'PAR-019', 5.75, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(20, 1, 20, 'PAR-020', 5.93, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(21, 2, 1, 'PAR-001', 4.61, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(22, 2, 2, 'PAR-002', 12.06, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(23, 2, 3, 'PAR-003', 11.98, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(24, 2, 4, 'PAR-004', 19.68, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(25, 2, 5, 'PAR-005', 8.9, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(26, 2, 6, 'PAR-006', 18.57, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(27, 2, 7, 'PAR-007', 17.46, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(28, 2, 8, 'PAR-008', 5.26, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(29, 2, 9, 'PAR-009', 13.8, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(30, 2, 10, 'PAR-010', 14.01, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(31, 2, 11, 'PAR-011', 0.38, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(32, 2, 12, 'PAR-012', 9.52, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(33, 2, 13, 'PAR-013', 15.93, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(34, 2, 14, 'PAR-014', 4.95, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(35, 2, 15, 'PAR-015', 17.16, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(36, 2, 16, 'PAR-016', 11.97, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(37, 2, 17, 'PAR-017', 2.58, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(38, 2, 18, 'PAR-018', 0.98, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(39, 2, 19, 'PAR-019', 18.4, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(40, 2, 20, 'PAR-020', 19.92, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(41, 3, 1, 'PAR-001', 10.56, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(42, 3, 2, 'PAR-002', 12.53, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(43, 3, 3, 'PAR-003', 10.1, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(44, 3, 4, 'PAR-004', 19.88, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(45, 3, 5, 'PAR-005', 16.57, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(46, 3, 6, 'PAR-006', 18.55, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(47, 3, 7, 'PAR-007', 6.54, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(48, 3, 8, 'PAR-008', 10.3, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(49, 3, 9, 'PAR-009', 4.74, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(50, 3, 10, 'PAR-010', 10.22, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(51, 3, 11, 'PAR-011', 1.8, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(52, 3, 12, 'PAR-012', 3.33, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(53, 3, 13, 'PAR-013', 2.01, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(54, 3, 14, 'PAR-014', 12.85, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(55, 3, 15, 'PAR-015', 3.42, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(56, 3, 16, 'PAR-016', 10.12, 8);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(57, 3, 17, 'PAR-017', 16.11, 1);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(58, 3, 18, 'PAR-018', 9.7, 2);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(59, 3, 19, 'PAR-019', 11.3, 3);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(60, 3, 20, 'PAR-020', 10.8, 4);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(61, 4, 1, 'PAR-001', 1.24, 5);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(62, 4, 2, 'PAR-002', 8.46, 6);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(63, 4, 3, 'PAR-003', 11.7, 7);
INSERT INTO ZRODLO_POS.dbo.ZrodloPos_ReceiptItem(id, position, receiptCode, reciptNumber, amount, kodKreskowy) VALUES(64, 4, 4, 'PAR-004', 14.82, 8);



USE ZRODLO_POS
GO

WITH tmp AS 
(
	SELECT receiptCode, sum(amount) AS suma
	FROM ZrodloPos_ReceiptItem
	GROUP BY receiptCode
)

UPDATE ZrodloPos_Receipt SET totalCost = tmp.suma FROM ZrodloPos_Receipt r INNER JOIN tmp ON r.id = tmp.receiptCode;