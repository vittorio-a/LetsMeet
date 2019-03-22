SET SQL_SAFE_UPDATES = 0;

SET NAMES 'utf8';
USE letsmeet;
--
-- Delete data from the table 'segnalazioneevento'
--
TRUNCATE TABLE segnalazioneevento;
--
-- Delete data from the table 'segnalazionecommento'
--
TRUNCATE TABLE segnalazionecommento;
--
-- Delete data from the table 'rating'
--
TRUNCATE TABLE rating;
--
-- Delete data from the table 'partecipazione'
--
TRUNCATE TABLE partecipazione;
--
-- Delete data from the table 'codicerilasciato'
--
TRUNCATE TABLE codicerilasciato;
--
-- Delete data from the table 'appartenezanazione'
--
TRUNCATE TABLE appartenezanazione;
--
-- Delete data from the table 'appartenenzaregione'
--
TRUNCATE TABLE appartenenzaregione;
--
-- Delete data from the table 'appartenenzaprovincia'
--
TRUNCATE TABLE appartenenzaprovincia;
--
-- Delete data from the table 'appartenenzacomune'
--
TRUNCATE TABLE appartenenzacomune;
--
-- Delete data from the table 'commento'
--
DELETE FROM commento;
--
-- Delete data from the table 'evento'
--
DELETE FROM evento;
--
-- Delete data from the table 'utente'
--
DELETE FROM utente;
--
-- Delete data from the table 'tipo'
--
DELETE FROM tipo;
--
-- Delete data from the table 'superadmin'
--
TRUNCATE TABLE superadmin;
--
-- Delete data from the table 'regione'
--
DELETE FROM regione;
--
-- Delete data from the table 'provincia'
--
DELETE FROM provincia;
--
-- Delete data from the table 'posizione'
--
DELETE FROM posizione;
--
-- Delete data from the table 'nazione'
--
DELETE FROM nazione;
--
-- Delete data from the table 'comune'
--
DELETE FROM comune;

--
-- Inserting data into table comune
--
INSERT INTO comune(idComune, nomeComune) VALUES
(1, 'Kawana'),
(2, 'Palmerston'),
(3, 'Victoria Point'),
(4, 'Gladstone'),
(5, 'Singleton'),
(6, 'Melbourne'),
(7, 'Bowral'),
(8, 'Albury'),
(9, 'Craigieburn'),
(10, 'Brisbane'),
(11, 'Kempsey'),
(12, 'Rockingham'),
(13, 'Wagga Wagga'),
(14, 'Gold Coast'),
(15, 'Melton'),
(16, 'Parkes'),
(17, 'Sunbury'),
(18, 'Wangaratta'),
(19, 'Kiama'),
(20, 'Sunshine Coast'),
(21, 'Alice Springs'),
(22, 'Mildura'),
(23, 'Cranbourne'),
(24, 'Warragul'),
(25, 'Goulburn'),
(26, 'Broken Hill'),
(27, 'Armidale'),
(28, 'Mount Gambier'),
(29, 'Perth'),
(30, 'Kingaroy'),
(31, 'Mount Isa'),
(32, 'Swan Hill'),
(33, 'Warrnambool'),
(34, 'Broome'),
(35, 'Moe'),
(36, 'Sydney'),
(37, 'Dalby'),
(38, 'Warwick'),
(39, 'Kingston'),
(40, 'Port Augusta'),
(41, 'Mornington'),
(42, 'Bacchus Marsh'),
(43, 'Grafton'),
(44, 'Buderim'),
(45, 'Bairnsdale'),
(46, 'Mount Martha'),
(47, 'Tamworth'),
(48, 'Port Hedland'),
(49, 'Mudgee'),
(50, 'Whyalla');

--
-- Inserting data into table nazione
--
INSERT INTO nazione(idNazione, nomeNazione) VALUES
(1, 'Italia');

--
-- Inserting data into table posizione
--
INSERT INTO posizione(idPosizione, longitudine, latitudine, formattedAddress) VALUES
(1, 66.52596, 3.92754, '2 Beaumont Court'),
(2, 168.60515, 29.50107, '5 Knight''s Hill'),
(3, 67.20512, -52.55434, '4-9 Carnarvon Road'),
(4, 133.87633, -26.83125, '4-9 Hornsey Road'),
(5, 45.32827, -89.12511, '33-48 Abercairn Road'),
(6, 101.84058, -43.6752, '1-6 Deepdene Close'),
(7, -8.78496, 64.61547, '2-6 Ganton Street'),
(8, 17.01093, 56.72454, '24-19 Ariel Way'),
(9, -93.47685, 84.45923, '79 Owen Street'),
(10, 14.01435, 50.80913, '4 Hill Close'),
(11, 78.63044, 46.18733, '4 Cambridge Avenue'),
(12, 9.20997, -66.69789, '7 foundharrow'),
(13, 142.2631, -52.01225, '23-56 Vassall Road'),
(14, -74.89427, 66.79418, '2 Apollo Place'),
(15, -71.63478, 29.46066, '5 Charlwood Street'),
(16, -50.54938, 47.00517, '1-6 Abbey Street'),
(17, -69.94565, -18.27919, '43-29 Berryhill'),
(18, -150.53909, -11.38348, '1-6 Doughty Street'),
(19, 47.80911, 29.14053, '9 Normanton Road'),
(20, -76.5705, 26.94095, '1-8 Leicester Street'),
(21, 127.74024, -19.76184, '5-9 Longlands Court'),
(22, 29.47381, 9.12515, '33-19 Macclesfield Street'),
(23, 119.93771, -86.35901, '2 Buckingham Gate'),
(24, -3.36103, 54.27549, '11 Sussex Place'),
(25, -69.96418, 24.87651, '1-6 Fairfield Road'),
(26, 104.68861, 22.71267, '55-37 Hallingbury Court'),
(27, 147.27978, 49.26632, '5 Alander Mews'),
(28, 1.59158, -87.03112, '4 Birkenhead Street'),
(29, -42.50072, -74.89581, '25-46 Northholt Road'),
(30, 150.94736, -75.77914, '4 A-E Ferry Lane'),
(31, -77.8099, -74.55925, '6 A-B Grove Hill'),
(32, -169.05423, 21.1443, '35-36 Abbotsbury Road'),
(33, 46.94984, 85.18506, '1 Wandsworth High Street'),
(34, 27.26052, 45.79049, '1 Barbauld Road'),
(35, 0.17767, 8.83327, '4-9 Wellesley Road'),
(36, 91.18385, -71.57186, '1 Coppice Way'),
(37, -2.5263, 37.71914, '2-8 Cromwell Road'),
(38, 9.1421, 0.81044, '1-8 Eastbank Close'),
(39, -4.55279, -81.87938, '13-16 Barnet Road'),
(40, 7.95193, 41.94047, '54 Abbotsbury Road'),
(41, 61.81017, -35.56035, '4 A-D Childebert Road'),
(42, -53.68814, -7.37185, '5-7 Litchfield Court'),
(43, -180, -85.27091, '2-9 Lichfield Grove'),
(44, -171.39732, -66.17927, '48A Ashburnham Road'),
(45, 92.81395, -49.54209, '5 Acton Lane'),
(46, -64.31747, -74.74789, '12 Addison Road'),
(47, -149.10271, 4.85799, '35-17 Whistlers Avenue'),
(48, -5.16487, 48.49425, '5 Redchurch Street'),
(49, 23.68575, 36.90946, '52-17 All Souls Avenue'),
(50, 176.39793, -84.9513, '7A Cherry Tree Walk');

--
-- Inserting data into table provincia
--
INSERT INTO provincia(idProvincia, nomeProvincia, sigla) VALUES
(1, 'Prince Edward Island', 'JD'),
(2, 'Prince Edward Island', 'M'),
(3, 'New Brunswick', 'WO'),
(4, 'Prince Edward Island', 'H'),
(5, 'Nunavut', 'BZ'),
(6, 'Quebec', 'HF'),
(7, 'Northwest Territories', 'LE'),
(8, 'British Columbia', 'TX'),
(9, 'Newfoundland and Labrador', 'EF'),
(10, 'Manitoba', 'MD'),
(11, 'Northwest Territories', 'U'),
(12, 'Northwest Territories', 'IU'),
(13, 'Manitoba', 'RZ'),
(14, 'Nova Scotia', 'O'),
(15, 'Quebec', 'WX'),
(16, 'British Columbia', 'A'),
(17, 'New Brunswick', 'B'),
(18, 'Northwest Territories', 'JR'),
(19, 'Quebec', 'E'),
(20, 'New Brunswick', 'EB');

--
-- Inserting data into table regione
--
INSERT INTO regione(idRegione, nomeRegione) VALUES
(1, 'South Carolina'),
(2, 'South Dakota');

--
-- Inserting data into table superadmin
--
INSERT INTO superadmin(idSuperAdmin, username, passwordAdmin) VALUES
(1, 'Michael15', x'B1A200533D3225D3001A80A24BD103BD3304B4D97E121F4AA607620845BEA798'),
(2, 'Ahern4', x'7A28015901CD61909EE80137D22F793C13903D0004B074D7057A8E36CCAAB9E1');

--
-- Inserting data into table tipo
--
INSERT INTO tipo(idTipo, nomeTipo, descrizione) VALUES
(1, 'Cultura', 'It should rather be regarded as an integral part of the development sequence.'),
(2, 'Sport', 'This could inevitably be a result of a hardware maintenance.'),
(3, 'Educazione', 'The breach is quite a comprehensive matter.'),
(4, 'Divertimento', 'Therefore, the concept of the referential arguments can be treated as the only solution.'),
(5, 'Arte', 'It should rather be regarded as an integral part of the structure absorption.'),
(6, 'Ingegneria del software', 'No desc'),
(7, 'Altro', 'It should rather be regarded as an integral part of the corporate asset growth.');

--
-- Inserting data into table utente
--
INSERT INTO utente(idUtente, username, passwordUtente, email, feedback, stato, reactivationDay) VALUES
(1, 'Rhett696', x'136AA268594E3A7754400C2AB9E042856287F006FE13078463006B2C7BE55ACF', 'Rayford917@example.com', 0, 'BANNATO', '2019-02-28 22:02:59'),
(2, 'Madelene723', x'24BE3E5406D6434F4B5CF9F37406D64BD569CA76729BDB8A1E3E51D6237C00AD', 'Rubin117@example.com', 0, 'ATTIVO', '2019-02-12 14:00:19'),
(3, 'Ostrander2001', x'6F9431E9C02049BDC3BD9267F4158B2B5CC9951EAC91A04C575BFC2739C26F2A', 'Tillie_Zielinski@example.com', 0, 'INVISIBILE', '2019-02-22 17:31:31'),
(4, 'Boyd869', x'4B58E981757309A608862B6100527F5B1C155DAF94B3005C0C0295F25C401051', 'Nance221@example.com', 0, 'ATTIVO', '2019-02-10 12:09:48'),
(5, 'Letha1999', x'3F1011C55843243C659098BF74D20CE50153B7B28BD26FD8467B49985D2C7FDA', 'Schiller936@example.com', 0, 'ATTIVO', '2019-02-23 22:06:56'),
(6, 'Morey994', x'1492272118288503DB7AFF9BF4E013B3082C8E37F1724F30483F83F9C02A4603', 'Ouellette@example.com', 0, 'ATTIVO', '2019-02-03 12:07:07'),
(7, 'Delbert843', x'2F31580506F1AE19011B2E4702E4397213EB4CA91806A9065C2936760E364D48', 'Cuevas@nowhere.com', 0, 'ATTIVO', '2019-02-03 18:02:13'),
(8, 'Clinton2001', x'1145940675642E59935AF166947AB1AED27F951ABAAFEB7552D8817E0F8ABBEF', 'Henley@example.com', 0, 'BANNATO', '2019-03-01 05:06:59'),
(9, 'Couture1950', x'FFFEEC51567F1602C61211D0EB62C01C0105DB25BE90D1CAB4070B0F9507D27A', 'zwwujez0922@example.com', 0, 'ATTIVO', '2019-02-02 02:45:23'),
(10, 'Provost555', x'082F27BA391F4C343001088EAE876143DDC3361702F09D6ABA3D1D378F788700', 'BeauLachance@example.com', 0, 'ATTIVO', '2019-02-19 12:56:56'),
(11, 'Weldon1968', x'2DE9B0A538F0EB7417059C9226FF1E05EED081D2260AEE9D4116054B0832C7B8', 'BulahHeard7@example.com', 0, 'ATTIVO', '2019-02-17 17:16:31'),
(12, 'Ted2004', x'6420E3ECB86F9C3DA0A8414A5089A9365D1E6D5E04122F1B042701019DFAFA7B', 'AdolfoCromwell918@example.com', 0, 'ATTIVO', '2019-03-03 04:57:48'),
(13, 'Stanford2009', x'63E3852124180A821708E35FD58A642FD6C19E959DDF8AF97C8FECA33B09D116', 'gzeq7863@example.com', 0, 'ATTIVO', '2019-02-02 12:40:25'),
(14, 'Adan71', x'B3DEE70B87ACC5DD07563782E410357648F97C028A09A81D92848F09906269DC', 'Ackerman89@example.com', 0, 'ATTIVO', '2019-02-24 21:20:40'),
(15, 'Manuel2026', x'724BF87FCB735F08D71192291FAD0AAD9641000E1120F5F9D723DA73CBC8DF05', 'Leontine_Haskins588@example.com', 0, 'ATTIVO', '2019-02-23 14:58:56'),
(16, 'Dewey58', x'0196D605320D63C1285F240876063CA942D4B3CD4C4F68F641573E780178B803', 'Joette.Osteen3@nowhere.com', 0, 'ATTIVO', '2019-02-17 06:53:02'),
(17, 'Antwan3', x'A801FD6923E6206CE22FEC09617F0248540EF90719536BC1AAAC7D4FE2FAF057', 'Ashley_Keating@example.com', 0, 'INVISIBILE', '2019-02-25 02:48:47'),
(18, 'Adelia139', x'2898C5099ED50009192371C03156EA0EF8AD2409E95905C87B134B507BFEAF4C', 'matsd6611@example.com', 0, 'ATTIVO', '2019-02-21 06:05:59'),
(19, 'Manuel516', x'6148E2EC429CB3148547931A0DF0DF186F1CED9EB2FF042BA8072C2165D50742', 'DeonXBarber@example.com', 0, 'ATTIVO', '2019-02-12 16:13:30'),
(20, 'Godwin2005', x'EE6DEC6B2B488445C0ED1492C5AE6BB74BE5059255017A7063CB1E8943A31BC4', 'xuvcg6818@example.com', 0, 'ATTIVO', '2019-02-17 16:10:51'),
(21, 'Guerin19', x'6E8B80FD4509520208400F4F99C22F458C04836C790B51D5C8180108B4D10248', 'Akers@example.com', 0, 'ATTIVO', '2019-03-02 12:02:13'),
(22, 'Royal2', x'3E5A91E00C5B0845A6DE2E8C550D016A9041106F47017374FD2219966A2BD1F1', 'Lyman.Corcoran@example.com', 0, 'BANNATO', '2019-02-12 12:25:36'),
(23, 'Oh493', x'409266EA833EC0D4384D28393C64F3738DFA28D4E457DC21F60A4EBBFB44F10E', 'AbbieBergman@nowhere.com', 0, 'ATTIVO', '2019-02-27 08:17:09'),
(24, 'Gabrielle1973', x'A952F91E94402A0689E82BC210136BFC078E83C8AF25FAA96C2DE5242EBC3544', 'Jeromy.B.Mcfarlane@example.com', 0, 'ATTIVO', '2019-02-23 21:18:46'),
(25, 'Dylan2007', x'D939FD004287087399EA79A11F01BA6073830EF9E0EC1E75EDE3E28BFC978705', 'Genaro.E.Kaplan@example.com', 0, 'BANNATO', '2019-02-10 15:13:37'),
(26, 'Tandra825', x'69F3E12CCA0B331B009D6A49DB95B95608C243043439E42FD548068A6C05A3F9', 'DorseyCausey@example.com', 0, 'INVISIBILE', '2019-02-04 15:18:05'),
(27, 'Alexander685', x'937A3A990F5704195504F306DA309ADBF08F08048302D7D20692EBD78D37EB4C', 'Vitale3@example.com', 0, 'ATTIVO', '2019-02-05 02:58:41'),
(28, 'Patrica1975', x'19183A5A06083BBA2A754B09C098B234B8586C078E977FC70782578AC66E9AE0', 'NataliaAguiar216@example.com', 0, 'ATTIVO', '2019-02-22 07:21:59'),
(29, 'Theodora84', x'39978C1833C70AE306B4277950E0921879CD03000D77054EA6A2006D88A80B4D', 'AdalineBickford@example.com', 0, 'ATTIVO', '2019-02-10 11:51:06'),
(30, 'Barbara2027', x'6C2F53C179AB5FF9072C5A426418A79405CFED948BF971EA93555E6793CFB13D', 'Eusebio.Hummel@example.com', 0, 'ATTIVO', '2019-02-05 05:47:55'),
(31, 'Caruso1950', x'0A3FE6C2F130928C4717725F5DE83EF481F72DD3C14254B79CABDA6A078CC406', 'ValeriaBeaulieu@example.com', 0, 'ATTIVO', '2019-02-26 10:17:41'),
(32, 'Edison314', x'DDD0CC9303C26301E7012570035304A44C88C511DC9459B8526735F090501E5B', 'Mathew.D_Aiello362@nowhere.com', 0, 'ATTIVO', '2019-02-26 12:59:00'),
(33, 'Alfonso1964', x'A44B8407F07090DF06A7C97711F190120BB130544D62D5CA55F2594B6BFFE5CF', 'Theo_Alford@nowhere.com', 0, 'ATTIVO', '2019-02-19 08:33:49'),
(34, 'Mcclellan2026', x'8C12DB33300382A7DA9B0AEE4F9E422104474C7D42E84E1C6C02EB2800527FDE', 'qjgdndtu_mxbo@nowhere.com', 0, 'INVISIBILE', '2019-02-05 19:08:41'),
(35, 'Shaunda334', x'182881EA27B67067A662514DC0B0BA88070397196B54FC7A3F1E221DF0003505', 'Kim_TSmyth968@example.com', 0, 'ATTIVO', '2019-02-26 15:58:22'),
(36, 'Edwardo9', x'8D4505BF22042C50CD06036811398187C9DD2E8AB13BE5C4A7052E1ACA53F875', 'ScarboroughY@example.com', 0, 'ATTIVO', '2019-03-02 21:46:59'),
(37, 'Charlott446', x'9281BCE82066F1B82149CA326745A70801A8049650E707089956EC4C12C70503', 'BattleY41@example.com', 0, 'ATTIVO', '2019-02-28 18:23:41'),
(38, 'Aguiar53', x'C1039E20C3033325DF8B93D5FF18E34D0852D1F4050CFC18B91A24F710A85EDB', 'Ezekiel.Henson@example.com', 0, 'INVISIBILE', '2019-02-06 00:44:14'),
(39, 'Colvin1999', x'C671157322D5E9524296753351B4535C3B26967E22645FA47373F8E8391D73B1', 'GenaroAleman@nowhere.com', 0, 'BANNATO', '2019-02-20 07:38:22'),
(40, 'Latonia7', x'44E856B69620203F5D7AA25C6FC10B5597A2206311E8C2353324A6659A393DAB', 'Blunt726@nowhere.com', 0, 'ATTIVO', '2019-02-17 22:04:51'),
(41, 'Brain445', x'08C917CA42538B5CB466896E0F999053ECCE6BE7C22B4532A2E385D4A8B6A75F', 'AdalbertoD.Caraballo@example.com', 0, 'INVISIBILE', '2019-02-02 12:00:16'),
(42, 'Lloyd2002', x'B0D7DA950368E5105B010EF747BDA898075E394EAEB6975C0E05241247680032', 'LuciusWray@example.com', 0, 'INVISIBILE', '2019-02-14 18:21:17'),
(43, 'Mark2011', x'9DF9A7A3C09F11593C05C6C4A01E1E04E78FA7A6C07EC80409A43A5F698618EA', 'AlarconT@example.com', 0, 'BANNATO', '2019-02-11 13:21:15'),
(44, 'Shawanna1957', x'6D6FE93B0BCF1B6E7593EBB13650D315155C9BDD9E4C088A9D09885EF70BAB9B', 'Abel@example.com', 0, 'ATTIVO', '2019-02-17 00:52:38'),
(45, 'Baugh262', x'040920C87DADE23920AD3DB23E024BF6AAD1D9ED5C0028C4A0753CAC05FB0E7F', 'Crutcher@example.com', 0, 'ATTIVO', '2019-02-17 22:42:36'),
(46, 'Alisa249', x'0821E5E9910704881F7FC32A6924C7C58236EB49341C07383110D25FCC8E0B0C', 'Stanton@example.com', 0, 'INVISIBILE', '2019-02-20 05:00:28'),
(47, 'Kozlowski2010', x'AEAE1891C7A814BFBEE6E28AA2067D137AA617FA4EDA6D01B8CD7BEC132003F0', 'gbcz0953@example.com', 0, 'ATTIVO', '2019-02-14 22:40:23'),
(48, 'Faye1996', x'77CF61177E7F5BB9A862E1DEEAE11054600A07C8E4F8032FC03AB6770F434F4A', 'WilliamsKeith@nowhere.com', 0, 'BANNATO', '2019-02-14 16:22:47'),
(49, 'Carroll2001', x'00E4BD194F364B02AED8A6C545B41CAF9D04396BCC910159317DC786F01D50DE', 'PeeblesA276@nowhere.com', 0, 'ATTIVO', '2019-02-10 06:28:16'),
(50, 'Harlan2015', x'2706DDAF40D0F2E24D86D7DE5DC44818A29FC9284906840FD2ED6BCE67C7DE42', 'ScotBegay@nowhere.com', 0, 'ATTIVO', '2019-02-06 01:08:27');

--
-- Inserting data into table evento
--
INSERT INTO evento(idEvento, nome, feedback, npartecipanti, nverificati, oraInizio, oraFine, idUtente, idTipo, idPosizione, isVisibile) VALUES
(1, 'Such tendency may approximately originate from the base configuration.', 3, 0, 0, '2019-02-07 15:22:40', '2019-02-08 00:33:37.615864', 3, 6, 44, 1),
(2, 'For instance, in terms of the well-known practice is rationally considerable.', 8, 0, 0, '2019-02-01 21:54:43', '2019-02-02 01:46:50.752564', 13, 4, 29, 1),
(3, 'The technology is quite a cardinal matter.', 10, 0, 0, '2019-02-15 14:44:47', '2019-02-15 17:35:34.293441', 33, 1, 4, 1),
(4, 'The impact is quite a trial matter.', 10, 0, 0, '2019-02-10 01:57:54', '2019-02-10 06:24:17.242505', 39, 2, 8, 1),
(5, 'In respect of the task analysis will require a vast knowledge.', 3, 0, 0, '2019-02-16 05:09:25', '2019-02-16 14:25:19.94295', 38, 2, 23, 1),
(6, 'The real reason of the quality guidelines steadily the valuable information.', 5, 0, 0, '2019-02-07 01:30:29', '2019-02-07 08:05:39.059657', 28, 7, 10, 1),
(7, 'This may be done through the strategically developed techniques.', 2, 0, 0, '2019-02-01 22:25:06', '2019-02-02 00:57:24.714745', 28, 4, 44, 1),
(8, 'Admitting that some of the mechanism provides a solid basis for the questionable thesis.  ', 6, 0, 0, '2019-02-13 20:29:53', '2019-02-14 03:14:25.532181', 46, 2, 6, 0),
(9, 'The index is quite a enhanced matter.', 8, 0, 0, '2019-02-11 02:40:18', '2019-02-11 06:20:03.161519', 28, 1, 42, 1),
(10, 'Notwithstanding that a closer study of the coherent software the matrix of available.', 9, 0, 0, '2019-02-12 17:54:01', '2019-02-13 01:56:23.84104', 21, 4, 16, 1),
(11, 'This may be done through the consequential risks.', 6, 0, 0, '2019-02-06 23:20:03', '2019-02-07 02:34:30.586581', 37, 1, 27, 1),
(12, 'This can eventually cause certain issues the entire picture.', 10, 0, 0, '2019-02-12 11:00:05', '2019-02-12 17:10:00.242707', 20, 6, 6, 1),
(13, 'The crucial development skills turns it into something easily real.', 6, 0, 0, '2019-02-07 01:56:19', '2019-02-07 08:34:44.515765', 12, 2, 38, 1),
(14, 'Thus a complete understanding is missing.', 2, 0, 0, '2019-02-05 10:23:11', '2019-02-05 19:52:30.970062', 28, 1, 29, 1),
(15, 'This could smoothly be a result of a share of corporate responsibilities.', 3, 0, 0, '2019-02-02 19:16:39', '2019-02-02 23:57:25.331405', 20, 3, 5, 1),
(16, 'Therefore, the concept of the task analysis can be treated as the only solution.', 5, 0, 0, '2019-02-04 14:28:21', '2019-02-04 20:50:56.180386', 7, 5, 43, 1),
(17, 'This seems to be a globally obvious step towards the comprehensive set of policy statements.', 9, 0, 0, '2019-02-01 17:07:51', '2019-02-01 20:35:08.980261', 23, 4, 40, 1),
(18, 'It should rather be regarded as an integral part of the share of corporate responsibilities.', 9, 0, 0, '2019-02-08 21:04:48', '2019-02-09 03:47:33.492794', 4, 4, 4, 1),
(19, 'The real reason of the network development absolutely the critical acclaim of the.', 5, 0, 0, '2019-02-11 10:15:49', '2019-02-11 12:51:05.554394', 23, 6, 42, 1),
(20, 'This may be done through the feedback system.', 6, 0, 0, '2019-02-15 05:22:04', '2019-02-15 09:53:03.053544', 2, 4, 43, 1),
(21, 'This may be done through the strategic planning the major outcomes and the entire picture.', 6, 0, 0, '2019-02-11 02:26:16', '2019-02-11 10:07:40.764427', 28, 7, 5, 1),
(22, 'This may be done through the strategic decisions.', 5, 0, 0, '2019-02-07 04:03:21', '2019-02-07 11:03:01.718819', 48, 1, 14, 1),
(23, 'Fortunately, any deep analysis boosts the growth of the conceptual design.  ', 2, 0, 0, '2019-02-08 15:13:21', '2019-02-08 20:41:57.930492', 32, 4, 1, 1),
(24, 'Thus a complete understanding is missing.', 9, 0, 0, '2019-02-14 16:49:13', '2019-02-15 00:32:00.786138', 7, 3, 1, 1),
(25, 'It is obvious, that the layout of the potential role models is traditionally considerable.', 10, 0, 0, '2019-02-09 01:21:29', '2019-02-09 05:36:32.718588', 28, 2, 37, 0),
(26, 'In short, efforts of the skills minimizes influence of the major outcomes.', 10, 0, 0, '2019-02-09 02:02:36', '2019-02-09 04:56:19.888855', 43, 5, 33, 1),
(27, 'It should rather be regarded as an integral part of the program functionality.', 6, 0, 0, '2019-02-06 23:31:41', '2019-02-07 07:46:59.131959', 30, 3, 46, 1),
(28, 'This may be done through the source of permanent growth.', 8, 0, 0, '2019-02-09 12:34:33', '2019-02-09 16:11:21.158005', 28, 3, 19, 1),
(29, 'As concerns an overview of the development process , it can be quite risky.', 9, 0, 0, '2019-02-03 06:47:58', '2019-02-03 15:03:38.02756', 40, 3, 4, 1),
(30, 'In any case, we can rapidly change the mechanism of the final draft.', 8, 0, 0, '2019-02-03 16:11:40', '2019-02-03 18:35:43.33562', 30, 1, 23, 1),
(31, 'It should rather be regarded as an integral part of the continuing support.', 9, 0, 0, '2019-02-05 05:50:37', '2019-02-05 11:47:49.524959', 28, 6, 37, 0),
(32, 'This can eventually cause certain issues.', 8, 0, 0, '2019-02-09 02:54:44', '2019-02-09 08:25:11.006532', 5, 4, 12, 1),
(33, 'Thus a complete understanding is missing.', 6, 0, 0, '2019-02-15 07:46:30', '2019-02-15 14:45:56.403458', 36, 2, 50, 1),
(34, 'The global management concepts turns it into something fairly real.', 3, 0, 0, '2019-02-09 23:20:18', '2019-02-10 06:43:51.212349', 18, 5, 19, 1),
(35, 'This may be done through the major decisions, that lie behind the predictable behavior.', 5, 0, 0, '2019-02-06 15:30:35', '2019-02-06 21:43:01.008036', 17, 7, 36, 1),
(36, 'Besides, the raw draft of the change of marketing strategy prudently the systems approach.', 10, 0, 0, '2019-02-02 12:46:34', '2019-02-02 20:42:44.726679', 9, 5, 35, 1),
(37, 'In plain English, the crucial development skills will require a vast knowledge.', 7, 0, 0, '2019-02-13 05:16:12', '2019-02-13 12:09:54.461714', 26, 4, 35, 1),
(38, 'Looking it another way, the framework of the the profit will require a vast knowledge.', 7, 0, 0, '2019-02-05 17:28:33', '2019-02-06 00:20:25.082898', 5, 2, 6, 1),
(39, 'This may be done through the independent knowledge.', 10, 0, 0, '2019-02-06 13:34:07', '2019-02-06 21:18:28.500575', 31, 6, 1, 1),
(40, 'Therefore, the concept of the sufficient amount can be treated as the only solution.', 8, 0, 0, '2019-02-03 03:16:57', '2019-02-03 10:25:02.128092', 45, 5, 16, 1),
(41, 'This could habitually be a result of a continuing support.', 5, 0, 0, '2019-02-07 08:57:25', '2019-02-07 18:50:19.734073', 15, 2, 31, 1),
(42, 'The overall scores turns it into something methodically real.', 1, 0, 0, '2019-02-07 17:19:36', '2019-02-07 22:19:58.735104', 20, 2, 2, 1),
(43, 'As concerns the patterns of the network development, it can be quite risky.', 4, 0, 0, '2019-02-07 08:10:02', '2019-02-07 10:27:06.458808', 49, 7, 4, 1),
(44, 'The software is quite a scientific matter.', 2, 0, 0, '2019-02-04 19:20:11', '2019-02-05 02:55:01.032063', 26, 6, 10, 1),
(45, 'The systematization is quite a enduring matter.', 8, 0, 0, '2019-02-03 01:35:45', '2019-02-03 10:03:42.6347', 50, 4, 43, 1),
(46, 'For instance, after the completion of the entity integrity is seamlessly considerable.', 6, 0, 0, '2019-02-13 18:50:36', '2019-02-14 00:50:26.088269', 2, 5, 5, 1),
(47, 'It should rather be regarded as an integral part of the principles of effective management.', 2, 0, 0, '2019-02-13 13:10:03', '2019-02-13 21:13:33.77685', 34, 7, 47, 1),
(48, 'Thus a complete understanding is missing.', 2, 0, 0, '2019-02-02 15:56:49', '2019-02-02 21:44:20.145095', 34, 3, 49, 1),
(49, 'It should rather be regarded as an integral part of the matrix of available.', 1, 0, 0, '2019-02-15 18:37:32', '2019-02-15 21:37:19.590978', 45, 7, 37, 1),
(50, 'Everyone understands what it takes to the standards control.', 2, 0, 0, '2019-02-01 17:09:47', '2019-02-01 20:50:03.045415', 42, 5, 35, 1);

--
-- Inserting data into table commento
--
INSERT INTO commento(idCommento, idMittente, contenuto, idEvento, creationTime) VALUES
(1, 10, 'Therefore, the concept of the ability bias can be treated as the only solution.\r\nAccording to some experts, the problem of some of the final phase should keep its influence over the positive influence of any task analysis.  ', 18, '2019-02-01 00:04:26'),
(2, 10, 'To be more specific, study of exact practices highlights the importance of the proper engagement of the task analysis.  \r\nSuch tendency may instantaneously originate from the formal review of opportunities.', 1, '2019-01-14 04:07:34'),
(3, 11, 'The change of marketing strategy turns it into something notably real.\r\nOn the contrary, any further consideration provides rich insights into this existing network.This can eventually cause certain issues.', 46, '2019-01-15 19:59:10'),
(4, 48, 'On the contrary, the unification of the strategic management results in a complete compliance with any successful or consecutive approach.  \r\nSuch tendency may systematically originate from the major outcomes.', 11, '2019-01-30 12:34:53'),
(5, 35, 'It turns out that a number of brand new approaches has been tested during the the improvement of the preliminary network design.\r\nOn the other hand, the core principles will possibly result in complete failure of the supposed theory.  ', 37, '2019-01-14 17:44:47'),
(6, 35, 'This may be done through the task analysis.\r\nFirst and foremost, any further consideration poses problems and challenges for both the grand strategy and the referential arguments. The internal network turns it into something methodically real.  ', 6, '2019-01-25 22:49:29'),
(7, 7, 'The program is quite a focused matter.\r\nResulting from review or analysis of threats and opportunities, we can presume that the evolution of the arguments and claims manages to obtain what is conventionally known as first-class package.  ', 20, '2019-01-23 21:32:55'),
(8, 5, 'Although, cost of the deep analysis shows a stable performance in development of what is conventionally known as task analysis.  \r\nTherefore, the concept of the best practice patterns can be treated as the only solution.', 20, '2019-01-01 00:03:18'),
(9, 29, 'On top of that the understanding of the great significance of the strategic management accordingly changes the principles of the minor details of principles of effective management.  \r\nThis can eventually cause certain issues.', 22, '2019-01-28 17:26:07'),
(10, 14, 'This could closely be a result of a goals and objectives.\r\nEventually, the exceptional results of the product design and development provides a foundation for any strategic management. This may be done through the network development.  ', 32, '2019-01-23 13:49:51'),
(11, 31, 'Notwithstanding that a symmetric action of the center of the development methodology is recognized by the questionable thesis.  \r\nOtherwise speaking, elements of the product functionality impacts systematically on every prominent landmarks.', 10, '2019-01-17 17:53:37'),
(12, 36, 'This seems to be a partially obvious step towards the feedback system.\r\nTo all effects and purposes, elements of the deep analysis provides a solid basis for every contradiction between the structure absorption and the sufficient amount.  ', 16, '2019-01-15 20:47:33'),
(13, 13, 'This seems to be a methodically obvious step towards the strategic planning.\r\nConsequently, the optimization of the comprehensive methods cannot rely only on this crucial development skills. This can eventually cause certain issues.  ', 6, '2019-01-07 09:46:27'),
(14, 21, 'On the contrary, some part of the arguments and claims reveals the patterns of complete failure of the supposed theory.  \r\nAlthough, a section of the arguments and claims can hardly be compared with the network development on a modern economy.  ', 35, '2019-01-22 13:44:00'),
(15, 21, 'A number of key issues arise from the belief that the results of the comprehensive methods facilitates access to every contradiction between the valuable information and the program functionality.  \r\nThe duplication is quite a integrating matter.', 17, '2019-01-11 16:03:19'),
(16, 1, 'The real reason of the key principles reliably the benefits of data integrity.\r\nThus a complete understanding is missing.', 13, '2019-01-03 07:12:46'),
(17, 31, 'It is stated that the remainder of the crucial component is notably considerable.\r\nThe real reason of the matrix of available instantaneously the questionable thesis the ground-breaking technology.The decision is quite a dominant matter.', 43, '2019-01-18 11:33:51'),
(18, 32, 'Notwithstanding that the layout of the design patterns discards the principle of the preliminary action plan.  \r\nOn the contrary, a number of brand new approaches has been tested during the the improvement of the change of marketing strategy.', 8, '2019-01-28 08:49:17'),
(19, 20, 'As concerns the portion of the technical terms, it can be quite risky.\r\nIn any case, we can particularly change the mechanism of the structured technology analysis.', 28, '2019-01-12 22:35:11'),
(20, 42, 'Furthermore, one should not forget that any strategic decision has more common features with the development methodology.\r\nSo far, the problem of one of the driving factor focuses our attention on the conceptual design.  ', 34, '2019-01-01 01:10:18'),
(21, 18, 'The best practice patterns cannot be developed under such circumstances.\r\nTo straighten it out, the accurate predictions of the development methodology makes no difference to the integration prospects.  ', 26, '2019-01-13 08:19:16'),
(22, 1, 'There is no doubt, that Luis Quintana is the firs person who formulated that organization of the treatment provides a strict control over the entire picture.  \r\nThe final phase turns it into something traditionally real.', 45, '2019-01-01 00:00:10'),
(23, 48, 'The real reason of the final phase remotely the task analysis.\r\nIt may reveal how the preliminary network design positively the final draft.', 15, '2019-01-13 21:35:21'),
(24, 44, 'Which seems to confirm the idea that discussions of the essential component makes no difference to what is conventionally known as referential arguments.  \r\nSuch tendency may literally originate from the relational approach.', 45, '2019-01-20 22:23:32'),
(25, 37, 'This can eventually cause certain issues the questionable thesis.\r\nHowever, details of the development process  can partly be used for the individual elements.Such tendency may fully originate from the final draft.', 10, '2019-01-12 18:23:51'),
(26, 4, 'This may be done through the best practice patterns.\r\nTo sort everything out, it is worth mentioning that the analysis of the primary element facilitates access to an initial attempt in development of the critical thinking.  ', 44, '2019-01-01 02:01:37'),
(27, 34, 'The coherent software turns it into something traditionally real.\r\nHowever, an overview of the potential role models provides a prominent example of the development process.Thus a complete understanding is missing.', 6, '2019-01-09 20:12:12'),
(28, 26, 'Therefore, the concept of the effective mechanism can be treated as the only solution.\r\nIn a similar manner, components of elements of the operations research should keep its influence over the irrelevance of advancement.  ', 34, '2019-01-14 14:44:48'),
(29, 39, 'The flexibility is quite a successful matter.\r\nEveryone understands what it takes to the continuing support.This could inevitably be a result of a final draft the content testing method.', 28, '2019-01-28 16:03:01'),
(30, 37, 'This could objectively be a result of a bilateral act.\r\nA solution might be in a combination of base configuration and strategic decisions the overall scores.Thus a complete understanding is missing.', 25, '2019-01-01 00:02:21'),
(31, 20, 'One cannot possibly accept the fact that elements of the deep analysis establishes sound conditions for the preliminary action plan.  \r\nThe hardware maintenance turns it into something relatively real.', 20, '2019-01-04 12:14:27'),
(32, 1, 'The standards control the task analysis.\r\nThe main reason of the application interface is to facilitate the conceptual design.', 11, '2019-01-01 18:16:25'),
(33, 1, 'Thus a complete understanding is missing.\r\nIn the meantime the patterns of the the profit poses problems and challenges for both the quality guidelines and an initial attempt in development of the ability bias.  ', 8, '2019-01-26 14:31:46'),
(34, 34, 'Thus, the matter of the criterion would facilitate the development of what can be classified as the outline design stage.  \r\nSuch tendency may rapidly originate from the independent knowledge.', 8, '2019-02-01 12:55:44'),
(35, 7, 'Everyone understands what it takes to the questionable thesis what can be classified as the system mechanism.\r\nThis could effectively be a result of a basics of planning and scheduling.', 26, '2019-01-19 15:39:01'),
(36, 7, 'Although, the efficiency of the comprehensive methods gives an overview of an initial attempt in development of the efficient decision.  \r\nThis could slightly be a result of a strategic decisions.', 28, '2019-01-13 18:39:11'),
(37, 46, 'Such tendency may particularly originate from the grand strategy.\r\nThe exchange is quite a significant matter.', 5, '2019-01-31 04:51:35'),
(38, 18, 'Although, support of the essence gives us a clear notion of the internal network.\r\nIt may reveal how the predictable behavior closely the critical acclaim of the.', 20, '2019-01-14 14:33:41'),
(39, 9, 'The facility is quite a intrinsic matter.\r\nBy some means, the total volume of the skills is recognized by the share of corporate responsibilities. This could concurrently be a result of a final draft.  ', 18, '2019-01-25 03:19:13'),
(40, 15, 'That is to say the accurate predictions of the first-class package reinforces the argument for complete failure of the supposed theory.  \r\nAs a matter of fact, the skills becomes extremely important for the matrix of available on a modern economy.  ', 6, '2019-01-01 00:00:04'),
(41, 19, 'The development is quite a equivalent matter.\r\nCuriously, support of the comprehensive project management is completely considerable.This can eventually cause certain issues.', 4, '2019-01-16 22:22:06'),
(42, 21, 'In any case, we can constantly change the mechanism of the more production cycle of the matters of peculiar interest.\r\nHowever, the portion of the market tendencies provides rich insights into the preliminary action plan.', 26, '2019-01-25 11:31:13'),
(43, 3, 'On the contrary, the capacity of the mechanism makes it easy to see perspectives of an importance of the key factor.  \r\nThe application is quite a drastic matter.', 42, '2019-01-05 10:17:59'),
(44, 36, 'For a number of reasons, the growth of the essential component should focus on the conceptual design.  \r\nThe factor is quite a definitive matter.', 33, '2019-01-01 00:15:42'),
(45, 31, 'Under the assumption that the efficiency of the essential component should keep its influence over the entire picture.  \r\nIn this regard, efforts of the essence represents a bond between the permanent growth and an importance of the strategic planning.  ', 7, '2019-01-21 11:08:34'),
(46, 22, 'In any case, we can habitually change the mechanism of the application rules.\r\nTo be quite frank, some features of the task analysis offers good prospects for improvement of the preliminary action plan.  ', 18, '2019-01-01 00:00:04'),
(47, 31, 'It should rather be regarded as an integral part of the operating speed model.\r\nAs a matter of fact the point of the mechanism any basics of planning and scheduling.', 5, '2019-01-26 05:51:27'),
(48, 44, 'Frankly speaking, an basic component of core concept of the task analysis commits resources to the entire picture.  \r\nTo straighten it out, the unification of the effective mechanism the quality guidelines.This can eventually cause certain issues.', 34, '2019-01-26 11:41:47'),
(49, 5, 'As a matter of fact the layout of the increasing growth of technology and productivity impacts equally on every strategic management.\r\nThis could successfully be a result of a entity integrity.', 12, '2019-01-23 08:02:28'),
(50, 1, 'Besides, the example of the vital decisions commits resources to any indicative or insufficient approach.  \r\nTherefore, the concept of the functional programming can be treated as the only solution.', 49, '2019-01-06 02:43:30');

--
-- Inserting data into table appartenenzacomune
--
INSERT INTO appartenenzacomune(idPosizione, idComune) VALUES
(18, 32),
(50, 23),
(47, 41),
(24, 21),
(30, 32),
(45, 49),
(48, 18),
(25, 40),
(13, 3),
(31, 22),
(41, 28),
(19, 1),
(26, 44),
(46, 6),
(32, 31),
(5, 47),
(11, 50),
(49, 42),
(27, 48),
(36, 11),
(6, 34),
(1, 24),
(14, 6),
(7, 8),
(20, 16),
(33, 18),
(15, 11),
(42, 22),
(37, 40),
(2, 38),
(8, 33),
(3, 24),
(21, 8),
(43, 2),
(16, 49),
(38, 45),
(44, 4),
(9, 12),
(22, 49),
(17, 16),
(4, 12),
(12, 30),
(28, 24),
(23, 11),
(10, 37),
(29, 22),
(39, 45),
(34, 6),
(40, 46),
(35, 39);

--
-- Inserting data into table appartenenzaprovincia
--
INSERT INTO appartenenzaprovincia(idComune, idProvincia) VALUES
(48, 13),
(44, 3),
(50, 18),
(39, 2),
(24, 20),
(30, 16),
(25, 10),
(34, 8),
(31, 9),
(40, 7),
(46, 9),
(6, 3),
(49, 7),
(1, 1),
(47, 20),
(45, 9),
(16, 2),
(22, 14),
(7, 19),
(35, 4),
(41, 14),
(26, 10),
(2, 3),
(17, 12),
(12, 15),
(32, 14),
(18, 12),
(8, 20),
(13, 4),
(27, 6),
(36, 5),
(3, 6),
(33, 8),
(28, 14),
(23, 20),
(42, 12),
(29, 1),
(9, 11),
(4, 3),
(10, 1),
(37, 15),
(43, 20),
(5, 3),
(19, 14),
(14, 14),
(11, 5),
(38, 4),
(20, 6),
(15, 3),
(21, 4);

--
-- Inserting data into table appartenenzaregione
--
INSERT INTO appartenenzaregione(idRegione, idProvincia) VALUES
(1, 19),
(2, 2),
(1, 5),
(1, 3),
(1, 12),
(2, 6),
(1, 9),
(1, 1),
(2, 7),
(1, 15),
(2, 20),
(1, 4),
(2, 13),
(1, 18),
(2, 17),
(1, 10),
(1, 8),
(2, 11),
(1, 16),
(1, 14);

--
-- Inserting data into table appartenezanazione
--
INSERT INTO appartenezanazione(idRegione, idNazione) VALUES
(1, 1),
(2, 1);

--
-- Inserting data into table codicerilasciato
--
INSERT INTO codicerilasciato(codice, idUtente) VALUES
(27929, 23),
(1959, 22),
(31203, 17),
(5237, 12),
(1960, 43),
(17520, 47),
(14243, 29),
(5238, 38),
(12198, 4),
(8921, 24);

--
-- Inserting data into table partecipazione
--
INSERT INTO partecipazione(idUtente, idEvento, isVerificato) VALUES
(5, 45, 0),
(46, 15, 0),
(15, 34, 0),
(10, 1, 1),
(10, 28, 0),
(19, 25, 1),
(24, 49, 0),
(34, 32, 0),
(12, 35, 1),
(47, 23, 0),
(50, 27, 0),
(21, 14, 0),
(10, 31, 1),
(11, 48, 1),
(6, 10, 0),
(16, 6, 0),
(18, 22, 1),
(12, 14, 0),
(5, 26, 1),
(24, 13, 0),
(13, 43, 1),
(32, 16, 1),
(20, 29, 0),
(5, 26, 0),
(2, 32, 1),
(34, 12, 1),
(50, 44, 0),
(14, 7, 1),
(38, 46, 1),
(50, 16, 0),
(15, 17, 1),
(16, 42, 0),
(50, 40, 1),
(41, 37, 1),
(26, 27, 0),
(41, 13, 1),
(16, 45, 1),
(14, 45, 0),
(28, 39, 1),
(33, 13, 0),
(29, 50, 0),
(39, 34, 1),
(34, 16, 0),
(11, 22, 1),
(18, 9, 0),
(39, 19, 0),
(16, 14, 1),
(10, 5, 0),
(10, 37, 0),
(26, 38, 1);

--
-- Inserting data into table rating
--
INSERT INTO rating(idUtente, idEvento, voto) VALUES
(12, 16, 1),
(34, 21, 1),
(19, 20, 0),
(48, 14, 1),
(16, 2, 1),
(30, 23, 1),
(33, 6, 1),
(45, 38, 0),
(12, 7, 0),
(47, 20, 1),
(12, 1, 1),
(6, 17, 1),
(21, 30, 1),
(19, 28, 1),
(37, 15, 1),
(16, 40, 1),
(20, 12, 1),
(24, 48, 1),
(4, 2, 1),
(42, 2, 1),
(4, 28, 0),
(13, 8, 1),
(6, 6, 0),
(46, 27, 1),
(34, 41, 0),
(32, 36, 1),
(1, 18, 1),
(49, 7, 0),
(9, 28, 0),
(40, 41, 1),
(42, 4, 1),
(7, 21, 1),
(1, 6, 1),
(11, 31, 1),
(13, 10, 1),
(41, 14, 1),
(33, 16, 1),
(41, 7, 1),
(20, 9, 1),
(27, 30, 1),
(40, 49, 1),
(6, 27, 1),
(23, 10, 1),
(5, 30, 0),
(37, 50, 1),
(22, 9, 1),
(37, 45, 1),
(16, 47, 0),
(35, 45, 1),
(36, 9, 1);

--
-- Inserting data into table segnalazionecommento
--
INSERT INTO segnalazionecommento(idSegnalazione, idUtente, idCommento) VALUES
(1, 23, 40),
(2, 12, 23),
(3, 1, 43),
(4, 12, 41),
(5, 5, 26);

--
-- Inserting data into table segnalazioneevento
--
INSERT INTO segnalazioneevento(idSegnalazione, idUtente, idEvento) VALUES
(1, 38, 39),
(2, 16, 15),
(3, 36, 14),
(4, 16, 25),
(5, 10, 44);


SET SQL_SAFE_UPDATES = 1;
