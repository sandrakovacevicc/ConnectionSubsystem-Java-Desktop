CREATE OR REPLACE TYPE prikljucak_podaci AS OBJECT
( mesto_prikljucenja varchar2(50),
 mesto_vezivanja varchar2(50),
 merni_uredjaj VARCHAR2(50),
 zastitni_uredjaj VARCHAR2(50),
MEMBER FUNCTION get_mesto_prikljucenja RETURN varchar2,
MEMBER FUNCTION get_mesto_vezivanja RETURN varchar2,
MEMBER FUNCTION get_merni_uredjaj RETURN varchar2,
MEMBER FUNCTION get_zastitni_uredjaj RETURN varchar2
)
INSTANTIABLE NOT FINAL;


CREATE OR REPLACE
TYPE BODY prikljucak_podaci AS
 MEMBER FUNCTION get_mesto_prikljucenja RETURN varchar2 IS
 BEGIN
 RETURN SELF.mesto_prikljucenja;
 END;
 MEMBER FUNCTION get_mesto_vezivanja RETURN varchar2 IS
 BEGIN
 RETURN SELF.mesto_vezivanja;
 END;
 MEMBER FUNCTION get_merni_uredjaj RETURN varchar2 IS
 BEGIN
 RETURN SELF.merni_uredjaj;
 END;
 MEMBER FUNCTION get_zastitni_uredjaj RETURN varchar2 IS
 BEGIN
 RETURN SELF.zastitni_uredjaj;
 END;
END; 




CREATE TABLE prikljucak ( id_prikljucak number NOT NULL,
naziv VARCHAR2(50),
opis VARCHAR2(100),
prikljucak_podaci prikljucak_podaci,
PRIMARY KEY(id_prikljucak)
); 


INSERT INTO prikljucak 
VALUES (1, 'Bazna stanica', 'Priključenje bazne stanice', NEW prikljucak_podaci('TS 10/0,4 kV “Dinkova” - izlaz 12',
'Kablovski sistem X00-Ax4x150mm²','Merni uređaj za dvotarifno tarifiranje','Zaštitni uređaj u skladu sa ZUDS propisima'));

INSERT INTO prikljucak 
VALUES (2, 'Industrijski objekat', 'Priključenje industrijskog objekta', NEW prikljucak_podaci(
'TS 35/10 kV “Sever” - izlaz 5','Podzemni kabl 3x240mm²','Merni uređaj sa direktnim merenjem',
'Zaštitni uređaj sa automatskim isključenjem'));

INSERT INTO prikljucak 
VALUES (3, 'Stambena zgrada', 'Priključenje stambene zgrade', NEW prikljucak_podaci(
'TS 10/0,4 kV “Jug” - izlaz 3','Nadzemni vod ABC 4x50mm²','Merni uređaj za jedno-tarifno merenje',
'Zaštitni uređaj sa prenaponskom zaštitom'));


SELECT
 p.id_prikljucak "Id",
 p.naziv "Naziv",
 p.opis "Opis",
 p.prikljucak_podaci.get_mesto_prikljucenja() "Mesto prikljucenja",
 p.prikljucak_podaci.get_mesto_vezivanja() "Mesto vezivanja",
 p.prikljucak_podaci.get_merni_uredjaj() "Merni uredjaj",
 p.prikljucak_podaci.get_zastitni_uredjaj() "Zastitni uredjaj"
FROM prikljucak p; 

UPDATE prikljucak
SET prikljucak_podaci = prikljucak_podaci('TS 10/0,4 kV “Prikljucak” - izlaz 12','Kablovski sistem X00-Ax5x150mm²',
'Merni uređaj za jednotarifno tarifiranje','U skladu sa ZUDS propisima')
WHERE id_prikljucak = 1;

DELETE FROM prikljucak
WHERE prikljucak_podaci = prikljucak_podaci('TS 10/0,4 kV “Jug” - izlaz 3',
'Nadzemni vod ABC 4x50mm²','Merni uređaj za jedno-tarifno merenje',
'Zaštitni uređaj sa prenaponskom zaštitom');

CREATE OR REPLACE TYPE br_iz_LKRM AS OBJECT (
    br_iz_lkrm number
) INSTANTIABLE FINAL;


CREATE TABLE grad ( postanski_br number NOT NULL,
naziv VARCHAR2(20),
PRIMARY KEY(postanski_br)
); 

INSERT INTO grad (postanski_br, naziv) VALUES (18000, 'Niš');
INSERT INTO grad (postanski_br, naziv) VALUES (11000, 'Beograd');
INSERT INTO grad (postanski_br, naziv) VALUES (21000, 'Novi Sad');
INSERT INTO grad (postanski_br, naziv) VALUES (34000, 'Kragujevac');

CREATE TABLE katastarska_opstina (
    id_opstine NUMBER NOT NULL,
    naziv VARCHAR2(50),
    PRIMARY KEY(id_opstine)
);

INSERT INTO katastarska_opstina (id_opstine, naziv) VALUES (1, 'Medijana');
INSERT INTO katastarska_opstina (id_opstine, naziv) VALUES (2, 'Vozdovac');
INSERT INTO katastarska_opstina (id_opstine, naziv) VALUES (3, 'Pantelej');
INSERT INTO katastarska_opstina (id_opstine, naziv) VALUES (4, 'Novi Beograd');
INSERT INTO katastarska_opstina (id_opstine, naziv) VALUES (5, 'Palilula');
INSERT INTO katastarska_opstina (id_opstine, naziv) VALUES (6, 'Vracar');


CREATE TABLE ulica (
    postanski_br NUMBER NOT NULL,
    id_ulice NUMBER NOT NULL,
    naziv VARCHAR2(50),
    id_opstine NUMBER,
    PRIMARY KEY (postanski_br, id_ulice),
    FOREIGN KEY (id_opstine) REFERENCES katastarska_opstina(id_opstine)
);

INSERT INTO ulica (postanski_br, id_ulice, naziv, id_opstine) VALUES (11000, 1, 'Bulevar Nemanjića', 1);
INSERT INTO ulica (postanski_br, id_ulice, naziv, id_opstine) VALUES (11000, 2, 'Voždova', 3);
INSERT INTO ulica (postanski_br, id_ulice, naziv, id_opstine) VALUES (11000, 3, 'Kneza Miloša', 2);
INSERT INTO ulica (postanski_br, id_ulice, naziv, id_opstine) VALUES (11000, 4, 'Bulevar Kralja Aleksandra', 2);

CREATE TABLE tip_instalacije (
    id_instalacije NUMBER NOT NULL PRIMARY KEY,
    tip VARCHAR2(50)
);

INSERT INTO tip_instalacije VALUES (1, 'Nova');
INSERT INTO tip_instalacije VALUES (2, 'Postojeca/Povecanje snage');
INSERT INTO tip_instalacije VALUES (3, 'Postojeca/Smanjenje snage');

CREATE TABLE vrsta_objekta (
    id_vrste_objekta NUMBER NOT NULL PRIMARY KEY,
    naziv VARCHAR2(50) 
);

INSERT INTO vrsta_objekta  VALUES (1, 'Stambeni objekat');
INSERT INTO vrsta_objekta  VALUES (2, 'Poslovni prostor');
INSERT INTO vrsta_objekta  VALUES (3, 'Industrijski objekat');
INSERT INTO vrsta_objekta  VALUES (4, 'Bazna stanica mobilne telefonije');

CREATE TABLE nacin_grejanja (
    id_nacin_grejanja NUMBER NOT NULL PRIMARY KEY,
    naziv VARCHAR2(50)
);

INSERT INTO nacin_grejanja  VALUES (1, 'cvrsto gorivo');
INSERT INTO nacin_grejanja  VALUES (2, 'gas');
INSERT INTO nacin_grejanja  VALUES (3, 'nema');

CREATE TABLE namena_objekta (
    id_namena_objekta NUMBER NOT NULL PRIMARY KEY,
    naziv VARCHAR2(50)
);

INSERT INTO namena_objekta  VALUES (1, 'stanovanje');
INSERT INTO namena_objekta  VALUES (2, 'radionica');
INSERT INTO namena_objekta  VALUES (3, 'bazna stanica mobilne telefonije');

CREATE TABLE vrsta_prikljucka (
    id_vrste_prikljucka NUMBER NOT NULL PRIMARY KEY,
    naziv VARCHAR2(50)
);

INSERT INTO vrsta_prikljucka  VALUES (1, 'monofazni');
INSERT INTO vrsta_prikljucka  VALUES (2, 'trofazni');

CREATE TABLE filijala (
    id_filijale NUMBER NOT NULL PRIMARY KEY,
    naziv VARCHAR2(50) 
);

INSERT INTO filijala  VALUES (1, 'Sluzba za planiranje i izgradnju mreze');
INSERT INTO filijala  VALUES (2, 'Sluzba za mrezne operacije');

CREATE TABLE objekat (
    id_objekta NUMBER NOT NULL,
    katastarska_parcela VARCHAR2(20),
    snaga NUMBER,
    postanski_br NUMBER NOT NULL,
    id_ulice NUMBER NOT NULL,
    id_nacin_grejanja NUMBER,
    id_namene_objekta NUMBER,
    id_vrste_prikljucka NUMBER,
    id_vrste_objekta NUMBER,
    id_instalacije NUMBER,
    PRIMARY KEY (id_objekta),
    FOREIGN KEY (postanski_br, id_ulice) REFERENCES ulica(postanski_br, id_ulice),
    FOREIGN KEY (id_nacin_grejanja) REFERENCES nacin_grejanja(id_nacin_grejanja),
    FOREIGN KEY (id_namene_objekta) REFERENCES namena_objekta(id_namena_objekta),
    FOREIGN KEY (id_vrste_prikljucka) REFERENCES vrsta_prikljucka(id_vrste_prikljucka),
    FOREIGN KEY (id_vrste_objekta) REFERENCES vrsta_objekta(id_vrste_objekta),
    FOREIGN KEY (id_instalacije) REFERENCES tip_instalacije(id_instalacije)
);


CREATE TABLE snaga (
    id_snage NUMBER NOT NULL,
    id_objekta NUMBER NOT NULL,
    naziv VARCHAR2(50),
    PRIMARY KEY (id_snage, id_objekta),
    FOREIGN KEY (id_objekta) REFERENCES objekat(id_objekta)
);

INSERT INTO objekat (id_objekta, katastarska_parcela, snaga, postanski_br, id_ulice, id_nacin_grejanja, id_namene_objekta, id_vrste_prikljucka, id_vrste_objekta, id_instalacije)
VALUES (1, '12345', 20, 11000, 1, 1, 1, 1, 1, 1);

INSERT INTO objekat (id_objekta, katastarska_parcela, snaga, postanski_br, id_ulice, id_nacin_grejanja, id_namene_objekta, id_vrste_prikljucka, id_vrste_objekta, id_instalacije)
VALUES (2, '54321', 50, 11000, 4, 1, 1, 1, 1, 1);

INSERT INTO snaga (id_snage, id_objekta, naziv)
VALUES (1, 1, 'Osnovna snaga');
INSERT INTO snaga (id_snage, id_objekta, naziv)
VALUES (2, 1, 'Dodatna snaga');


CREATE TABLE zaposleni (
    id_zaposlenog NUMBER PRIMARY KEY,
    ime VARCHAR2(50) NOT NULL,
    prezime VARCHAR2(50) NOT NULL,
    kontakt VARCHAR2(20) NOT NULL,
    postanski_br NUMBER NOT NULL,
    id_ulice NUMBER NOT NULL,
    id_filijale NUMBER NOT NULL,
    FOREIGN KEY (postanski_br, id_ulice) REFERENCES Ulica(postanski_br, id_ulice),
    FOREIGN KEY (id_filijale) REFERENCES Filijala(id_filijale)
);

INSERT INTO Zaposleni VALUES (1, 'Petar', 'Petrović', '0651234567', 11000, 1, 1);

INSERT INTO Zaposleni VALUES (2, 'Ana', 'Anić', '0649876543', 11000, 3, 2);

INSERT INTO Zaposleni VALUES (3, 'Jovan', 'Jovanović', '0634567890', 11000, 1, 1);


CREATE TABLE gradska_uprava (
    id_uprave NUMBER PRIMARY KEY,
    naziv VARCHAR2(100) NOT NULL,
    postanski_br NUMBER NOT NULL,
    id_ulice NUMBER NOT NULL,
    FOREIGN KEY (postanski_br, id_ulice) REFERENCES Ulica(postanski_br, id_ulice)
);

INSERT INTO gradska_uprava VALUES (1, 'Gradska uprava za urbanizam', 11000, 1);

INSERT INTO gradska_uprava VALUES (2, 'Gradska uprava za imovinu', 11000, 3);


CREATE TABLE molba_za_urbanizam (
    id_molbe NUMBER PRIMARY KEY,
    datum DATE NOT NULL,
    delovodni_br VARCHAR2(50) NOT NULL,
    br_iz_LKRM br_iz_LKRM,  
    id_objekta NUMBER NOT NULL,
    id_kontakt_osobe NUMBER NOT NULL,
    id_koordinatora_tehnike NUMBER NOT NULL,
    id_uprave NUMBER NOT NULL,
    FOREIGN KEY (id_objekta) REFERENCES Objekat(id_objekta),
    FOREIGN KEY (id_kontakt_osobe) REFERENCES Zaposleni(id_zaposlenog),
    FOREIGN KEY (id_koordinatora_tehnike) REFERENCES Zaposleni(id_zaposlenog),
    FOREIGN KEY (id_uprave) REFERENCES Gradska_Uprava(id_uprave)
);


INSERT INTO molba_za_urbanizam (id_molbe, datum, delovodni_br, br_iz_LKRM, id_objekta,
id_kontakt_osobe, id_koordinatora_tehnike, id_uprave) 
VALUES (1, TO_DATE('2024-11-26', 'YYYY-MM-DD'), '123/2024', br_iz_LKRM(1001), 1, 1, 2, 1);

INSERT INTO molba_za_urbanizam (id_molbe, datum, delovodni_br, br_iz_LKRM, id_objekta, 
id_kontakt_osobe, id_koordinatora_tehnike, id_uprave) 
VALUES (2, TO_DATE('2024-11-25', 'YYYY-MM-DD'), '456/2024', br_iz_LKRM(70), 2, 3, 2, 2);

INSERT INTO molba_za_urbanizam (id_molbe, datum, delovodni_br, br_iz_LKRM, id_objekta, 
id_kontakt_osobe, id_koordinatora_tehnike, id_uprave) 
VALUES (4, TO_DATE('2024-11-25', 'YYYY-MM-DD'), '456/2024', br_iz_LKRM(70), 2, 3, 2, 2);


SELECT 
    m.id_molbe "Id molbe",
    m.datum "Datum",
    m.delovodni_br "Delovodni broj",
    m.br_iz_LKRM.br_iz_lkrm "LKRM", 
    m.id_objekta "Id objekta",
    m.id_kontakt_osobe "Kontakt osoba",
    m.id_koordinatora_tehnike "Koordinator tehnike",
    m.id_uprave "Id uprave"
FROM molba_za_urbanizam m;

UPDATE molba_za_urbanizam 
SET br_iz_LKRM = br_iz_LKRM(80) 
WHERE id_molbe = 1;

DELETE FROM molba_za_urbanizam 
WHERE id_molbe = 2;

ALTER TABLE ulica
ADD naziv_grada VARCHAR2(50);

CREATE OR REPLACE TRIGGER UPDATE_GRAD
AFTER UPDATE OF Naziv
ON Grad
FOR EACH ROW
DECLARE
    PRAGMA AUTONOMOUS_TRANSACTION; 
BEGIN
EXECUTE IMMEDIATE 'ALTER TRIGGER ZABRANA_UPDATE_NAZIV DISABLE';
    UPDATE ulica
    SET naziv_grada = :NEW.naziv
    WHERE postanski_br = :NEW.postanski_br;
COMMIT;
BEGIN
    EXECUTE IMMEDIATE 'ALTER TRIGGER ZABRANA_UPDATE_NAZIV ENABLE';
END;
END;

CREATE OR REPLACE TRIGGER ZABRANA_UPDATE_NAZIV
BEFORE UPDATE OF naziv_grada
ON Ulica
BEGIN
    RAISE_APPLICATION_ERROR(-20000, 'Zabranjeno je azuriranje ovog polja');
END;

CREATE OR REPLACE TRIGGER ZABRANA_UPDATE_POSTANSKIBR
BEFORE UPDATE OF postanski_br
ON Ulica
BEGIN
    RAISE_APPLICATION_ERROR(-20000, 'Zabranjeno je azuriranje ovog polja');
END;

CREATE OR REPLACE TRIGGER INSERT_ULICA
BEFORE INSERT
ON Ulica
FOR EACH ROW
DECLARE
    naziv_grada VARCHAR2(50);
BEGIN
    SELECT naziv 
    INTO naziv_grada
    FROM Grad
    WHERE postanski_br = :NEW.postanski_br;
    :NEW.naziv_grada := naziv_grada;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Poštanski broj ne postoji u tabeli Grad.');
END;


UPDATE grad
SET naziv = 'Beograd'
WHERE postanski_br = 11000;

SELECT * FROM ulica;

INSERT INTO ulica (postanski_br, id_ulice, naziv, id_opstine) VALUES (18000, 5, 'Bulevar Nemanjića', 1);

CREATE TABLE lokalna_elektrodistribucija (
    id_elektrodistribucije NUMBER NOT NULL PRIMARY KEY,
    ogranak VARCHAR2(50),
    telefon VARCHAR2(20),
    faks VARCHAR2(20),
    postanski_br NUMBER NOT NULL,
    id_ulice NUMBER NOT NULL,
    FOREIGN KEY (postanski_br, id_ulice) REFERENCES Ulica(postanski_br, id_ulice)
);

CREATE TABLE direktor (
    id_direktora NUMBER PRIMARY KEY,
    ime VARCHAR2(50) NOT NULL,
    prezime VARCHAR2(50) NOT NULL,
    id_elektrodistribucije NUMBER NOT NULL,
    FOREIGN KEY (id_elektrodistribucije) REFERENCES lokalna_elektrodistribucija(id_elektrodistribucije)
);

CREATE TABLE usluga_prikljucivanja (
    id_usluge NUMBER PRIMARY KEY,
    naziv VARCHAR2(50),
    cena NUMBER
);


CREATE TABLE uslovi_zastite (
    id_uslovZ NUMBER PRIMARY KEY,
    naziv VARCHAR2(50) NOT NULL
);

CREATE TABLE uslovi_postavljanja (
    id_UslovP NUMBER PRIMARY KEY,
    naziv VARCHAR2(50) NOT NULL
);

CREATE TABLE opis_prostora (
    id_opisa NUMBER PRIMARY KEY,
    naziv VARCHAR2(50) NOT NULL
);

CREATE TABLE zahtev (
    br_zahteva NUMBER PRIMARY KEY,
    datum DATE NOT NULL,
    zaposleni_podnosioc NUMBER NOT NULL,
    zaposleni_zastupnik NUMBER NOT NULL,
    id_objekta NUMBER NOT NULL,
    FOREIGN KEY (zaposleni_podnosioc) REFERENCES zaposleni (id_zaposlenog),
    FOREIGN KEY (zaposleni_zastupnik) REFERENCES zaposleni (id_zaposlenog),
    FOREIGN KEY (id_objekta) REFERENCES Objekat (id_objekta)
);

--Resenje(IdResenja, Datum, Broj, IdDirektora, BrZahteva, IdUslovaP, IdUslovaZ, IdPrikljucak)

CREATE TABLE resenje (
    id_resenja NUMBER PRIMARY KEY,
    datum DATE NOT NULL,
    broj VARCHAR2(50) NOT NULL,
    id_direktora NUMBER NOT NULL,
    br_zahteva NUMBER NOT NULL,
    id_UslovP NUMBER,
    id_uslovZ NUMBER,
    id_prikljucak NUMBER,
    FOREIGN KEY (id_direktora) REFERENCES direktor (id_direktora),
    FOREIGN KEY (br_zahteva) REFERENCES zahtev (br_zahteva),
    FOREIGN KEY (id_UslovP) REFERENCES uslovi_postavljanja (id_UslovP),
    FOREIGN KEY (id_uslovZ) REFERENCES uslovi_zastite (id_uslovZ),
    FOREIGN KEY (id_prikljucak) REFERENCES prikljucak (id_prikljucak)
);

CREATE TABLE stavka_resenja (
    id_resenja NUMBER NOT NULL,
    id_stavke NUMBER NOT NULL,
    id_usluge NUMBER NOT NULL,
    PRIMARY KEY (id_resenja, id_stavke),
    FOREIGN KEY (id_resenja) REFERENCES resenje (id_resenja),
    FOREIGN KEY (id_usluge) REFERENCES usluga_prikljucivanja (id_usluge)
);

ALTER TABLE resenje
ADD naziv_prikljucka VARCHAR2(50);

create or replace TRIGGER UPDATE_PRIKLJUCAK
AFTER UPDATE OF Naziv
ON prikljucak
FOR EACH ROW
DECLARE
    PRAGMA AUTONOMOUS_TRANSACTION; 
BEGIN
EXECUTE IMMEDIATE 'ALTER TRIGGER ZABRANA_UPDATE_NAZIVPRIKLJUCKA DISABLE';
    UPDATE Resenje
    SET naziv_prikljucka = :NEW.naziv
    WHERE id_prikljucak = :NEW.id_prikljucak;
COMMIT;
BEGIN
    EXECUTE IMMEDIATE 'ALTER TRIGGER ZABRANA_UPDATE_NAZIVPRIKLJUCKA ENABLE';
END;
END;


CREATE OR REPLACE TRIGGER ZABRANA_UPDATE_NAZIVPRIKLJUCKA
BEFORE UPDATE OF naziv_prikljucka
ON resenje
FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20000, 'Zabranjeno je azuriranje kolone naziv_prikljucka.');
END;


CREATE OR REPLACE TRIGGER INSERT_RESENJE
BEFORE INSERT
ON Resenje
FOR EACH ROW
DECLARE
    naziv_prikljucka VARCHAR2(50);
BEGIN
    SELECT naziv 
    INTO naziv_prikljucka
    FROM prikljucak
    WHERE id_prikljucak = :NEW.id_prikljucak;
    :NEW.naziv_prikljucka := naziv_prikljucka;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Id prikljucka ne postoji u tabeli Prikljucak.');
END;

CREATE OR REPLACE TRIGGER UPDATE_RESENJE
BEFORE UPDATE OF id_prikljucak
ON resenje
FOR EACH ROW
BEGIN
    SELECT naziv 
    INTO :NEW.naziv_prikljucka
    FROM prikljucak
    WHERE id_prikljucak = :NEW.id_prikljucak;
END;

INSERT INTO lokalna_elektrodistribucija (id_elektrodistribucije, ogranak, telefon, faks, postanski_br, id_ulice)
VALUES (1, 'Beogradski ogranak', '011/123-456', '011/789-101', 11000, 1);

INSERT INTO lokalna_elektrodistribucija (id_elektrodistribucije, ogranak, telefon, faks, postanski_br, id_ulice)
VALUES (2, 'Novi Sad ogranak', '021/234-567', '021/890-123', 18000, 5);

INSERT INTO direktor (id_direktora, ime, prezime, id_elektrodistribucije)
VALUES (1, 'Marko', 'Marković', 1);

INSERT INTO direktor (id_direktora, ime, prezime, id_elektrodistribucije)
VALUES (2, 'Jelena', 'Janković', 2);

INSERT INTO usluga_prikljucivanja (id_usluge, naziv, cena)
VALUES (1, 'Standardni priključak', 20000);

INSERT INTO usluga_prikljucivanja (id_usluge, naziv, cena)
VALUES (2, 'Brzi priključak', 30000);

INSERT INTO uslovi_zastite (id_uslovZ, naziv)
VALUES (1, 'Vatrootporni materijali');

INSERT INTO uslovi_zastite (id_uslovZ, naziv)
VALUES (2, 'Zaštita od poplava');

INSERT INTO uslovi_postavljanja (id_UslovP, naziv)
VALUES (1, 'Minimalna udaljenost od vodovoda');

INSERT INTO uslovi_postavljanja (id_UslovP, naziv)
VALUES (2, 'Pristupni put širine 3 metra');

INSERT INTO opis_prostora (id_opisa, naziv)
VALUES (1, 'Stambeni prostor');

INSERT INTO opis_prostora (id_opisa, naziv)
VALUES (2, 'Poslovni prostor');

INSERT INTO zahtev (br_zahteva, datum, zaposleni_podnosioc, zaposleni_zastupnik, id_objekta)
VALUES (1, TO_DATE('2024-11-01', 'YYYY-MM-DD'), 1, 2, 1);

INSERT INTO zahtev (br_zahteva, datum, zaposleni_podnosioc, zaposleni_zastupnik, id_objekta)
VALUES (2, TO_DATE('2024-11-15', 'YYYY-MM-DD'), 2, 3, 1);

INSERT INTO resenje (id_resenja, datum, broj, id_direktora, br_zahteva, id_UslovP, id_uslovZ, id_prikljucak, naziv_prikljucka)
VALUES (1, TO_DATE('2024-11-20', 'YYYY-MM-DD'), 'R-001', 1, 1, 1, 1, 1, 'Priključak za stambeni objekat');

INSERT INTO resenje (id_resenja, datum, broj, id_direktora, br_zahteva, id_UslovP, id_uslovZ, id_prikljucak, naziv_prikljucka)
VALUES (2, TO_DATE('2024-11-25', 'YYYY-MM-DD'), 'R-002', 2, 2, 2, 2, 2, 'Priključak za poslovni objekat');
INSERT INTO resenje (id_resenja, datum, broj, id_direktora, br_zahteva, id_UslovP, id_uslovZ, id_prikljucak, naziv_prikljucka)
VALUES (3, TO_DATE('2024-11-25', 'YYYY-MM-DD'), 'R-002', 2, 2, 2, 2, 2, 'Priključak za poslovni objekat');


INSERT INTO stavka_resenja (id_resenja, id_stavke, id_usluge)
VALUES (1, 1, 1);

INSERT INTO stavka_resenja (id_resenja, id_stavke, id_usluge)
VALUES (2, 1, 2);

UPDATE grad
SET naziv = 'Novi Beograd'
WHERE postanski_br = 11000;

SELECT * FROM ULICA;
SELECT * FROM RESENJE;

UPDATE ULICA 
SET naziv_grada = 'Novi Grad'
WHERE postanski_br = 11000;

INSERT INTO ulica (postanski_br, id_ulice, naziv, id_opstine)
VALUES (18000, 6, 'Nova ulica', 1);

UPDATE grad
SET naziv = 'Novi Beograd'
WHERE postanski_br = 11000;

SELECT
 p.id_prikljucak "Id",
 p.naziv "Naziv",
 p.opis "Opis",
 p.PRIKLJUCAK_PODACI.get_mesto_prikljucenja() "Mesto prikljucenja",
 p.PRIKLJUCAK_PODACI.get_mesto_vezivanja() "Mesto vezivanja",
 p.PRIKLJUCAK_PODACI.get_merni_uredjaj() "Merni uredjaj",
 p.PRIKLJUCAK_PODACI.get_zastitni_uredjaj() "Zastitni uredjaj"
FROM prikljucak p; 

UPDATE RESENJE 
SET naziv_prikljucka = 'Novi naziv'
WHERE id_resenja = 1;

SELECT * FROM resenje;

UPDATE resenje
SET id_prikljucak = 1
WHERE id_resenja = 1;

UPDATE prikljucak
SET naziv = 'Bazna stanica'
WHERE id_prikljucak = 1;

ALTER TABLE molba_za_urbanizam 
ADD postanski_br NUMBER;

ALTER TABLE molba_za_urbanizam 
ADD id_ulice NUMBER;

ALTER TABLE molba_za_urbanizam 
ADD CONSTRAINT fk_molba_za_urbanizam FOREIGN KEY (postanski_br, id_ulice) 
REFERENCES ulica(postanski_br, id_ulice);

CREATE OR REPLACE TRIGGER UPDATE_OBJEKAT
AFTER UPDATE OF postanski_br, id_ulice
ON objekat
FOR EACH ROW
DECLARE
    PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
    EXECUTE IMMEDIATE 'ALTER TRIGGER ZABRANA_UPDATE_PBIDULICE DISABLE';

    UPDATE molba_za_urbanizam
    SET postanski_br = :NEW.postanski_br,
        id_ulice = :NEW.id_ulice
    WHERE id_objekta = :NEW.id_objekta;

    EXECUTE IMMEDIATE 'ALTER TRIGGER ZABRANA_UPDATE_PBIDULICE ENABLE';

    COMMIT;
END;



CREATE OR REPLACE TRIGGER ZABRANA_UPDATE_PBIDULICE
BEFORE UPDATE OF postanski_br, id_ulice
ON molba_za_urbanizam
FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20000, 'Zabranjeno je azuriranje ovih kolona');
END;

CREATE OR REPLACE TRIGGER INSERT_MOLBA
BEFORE INSERT
ON molba_za_urbanizam
FOR EACH ROW
DECLARE
    v_postanski_br NUMBER;
    v_id_ulice NUMBER; 
BEGIN
    SELECT postanski_br, id_ulice
    INTO v_postanski_br, v_id_ulice
    FROM objekat
    WHERE id_objekta = :NEW.id_objekta;
    :NEW.postanski_br := v_postanski_br;
    :NEW.id_ulice := v_id_ulice;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'IdObjekat ne postoji u tabeli Objekat.');
END;

CREATE OR REPLACE TRIGGER UPDATE_MOLBA
BEFORE UPDATE OF id_objekta
ON molba_za_urbanizam
FOR EACH ROW
BEGIN
    SELECT postanski_br, id_ulice
    INTO :NEW.postanski_br, :NEW.id_ulice
    FROM objekat
    WHERE id_objekta = :NEW.id_objekta;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'IdObjekat ne postoji u tabeli Objekat.');
END;

INSERT INTO molba_za_urbanizam (id_molbe, datum, delovodni_br, br_iz_LKRM, id_objekta, 
id_kontakt_osobe, id_koordinatora_tehnike, id_uprave) 
VALUES (3, TO_DATE('2024-11-25', 'YYYY-MM-DD'), '456/2024', br_iz_LKRM(70), 2, 3, 2, 2);

UPDATE objekat
SET postanski_br = 18000, id_ulice = 5
WHERE id_objekta = 1;

UPDATE molba_za_urbanizam
SET id_objekta = 2
WHERE id_molbe = 2;

SELECT *
FROM molba_za_urbanizam m;

SELECT *
FROM ZAPOSLENI 
WHERE ime = 'Petar' AND prezime = 'Nikodijević';

create index IDX_ZAPOSLENI_IME_PREZIME on zaposleni (ime, prezime); 

SELECT *
FROM ZAPOSLENI 
WHERE ime = 'Petar' AND prezime = 'Nikodijević';

CREATE TABLE katastarska_opstina (
    id_opstine NUMBER NOT NULL,
    naziv VARCHAR2(50),
    PRIMARY KEY(id_opstine)
);

CREATE TABLE nacelnik (
    id_nacelnika NUMBER PRIMARY KEY,
    ime VARCHAR2(100) NOT NULL,
    prezime VARCHAR2(100) NOT NULL,
    id_uprave NUMBER NOT NULL,
    FOREIGN KEY (id_uprave) REFERENCES gradska_uprava(id_uprave)
);

CREATE TABLE pravnik (
    id_pravnika NUMBER PRIMARY KEY,
    ime VARCHAR2(100) NOT NULL,
    prezime VARCHAR2(100) NOT NULL
);

SELECT *
FROM NACELNIK n;

INSERT INTO NACELNIK n (id_nacelnika, ime, prezime, id_uprave)
VALUES (1, 'Marko', 'Petrović', 1); 

INSERT INTO PRAVNIK p (id_pravnika, ime, prezime)
VALUES (1, 'Nevena', 'Komatina'); 

ALTER TABLE snaga
ADD jacina NUMBER;

UPDATE snaga
SET jacina = 70
WHERE ID_SNAGE  = 2; 

SELECT *
FROM snaga 
WHERE jacina = 45;

CREATE INDEX idx_snaga_objekta 
ON snaga (jacina);

SELECT *
FROM snaga 
WHERE jacina = 45;

SELECT *
FROM RESENJE r;

SELECT constraint_name
FROM all_cons_columns
WHERE table_name = 'RESENJE' AND column_name = 'BR_ZAHTEVA';

ALTER TABLE resenje
DROP CONSTRAINT SYS_C007735;

ALTER TABLE resenje
DROP CONSTRAINT SYS_C007738;

CREATE TABLE Zahtev (
    br_zahteva NUMBER NOT NULL,
    datum DATE NOT NULL,
    zaposleni_podnosioc NUMBER NOT NULL,
    zaposleni_zastupnik NUMBER NOT NULL,
    id_objekta NUMBER NOT NULL,
    CONSTRAINT pk_zahtev PRIMARY KEY (br_zahteva),
    CONSTRAINT fk_podnosilac FOREIGN KEY (zaposleni_podnosioc) REFERENCES zaposleni (id_zaposlenog),
    CONSTRAINT fk_zastupnik FOREIGN KEY (zaposleni_zastupnik) REFERENCES zaposleni (id_zaposlenog),
    CONSTRAINT fk_objekat FOREIGN KEY (id_objekta) REFERENCES objekat (id_objekta)
)
PARTITION BY RANGE (datum)
INTERVAL (NUMTOYMINTERVAL(1, 'YEAR')) (
    PARTITION p2020 VALUES LESS THAN (TO_DATE('01-JAN-2021', 'DD-MON-YYYY')),
    PARTITION p2021 VALUES LESS THAN (TO_DATE('01-JAN-2022', 'DD-MON-YYYY')),
    PARTITION p2022 VALUES LESS THAN (TO_DATE('01-JAN-2023', 'DD-MON-YYYY')),
    PARTITION p2023 VALUES LESS THAN (TO_DATE('01-JAN-2024', 'DD-MON-YYYY'))
);


ALTER TABLE resenje
ADD CONSTRAINT fk_zahtev
FOREIGN KEY (BR_ZAHTEVA)
REFERENCES Zahtev (br_zahteva);

INSERT INTO zahtev (br_zahteva, datum, zaposleni_podnosioc, zaposleni_zastupnik, id_objekta)
VALUES (1, TO_DATE('2024-11-01', 'YYYY-MM-DD'), 1, 2, 1);

INSERT INTO zahtev (br_zahteva, datum, zaposleni_podnosioc, zaposleni_zastupnik, id_objekta)
VALUES (2, TO_DATE('2024-11-15', 'YYYY-MM-DD'), 2, 3, 1);

INSERT INTO zahtev (br_zahteva, datum, zaposleni_podnosioc, zaposleni_zastupnik, id_objekta)
VALUES (3, TO_DATE('2023-11-15', 'YYYY-MM-DD'), 2, 3, 1);

INSERT INTO zahtev (br_zahteva, datum, zaposleni_podnosioc, zaposleni_zastupnik, id_objekta)
VALUES (4, TO_DATE('2023-12-22', 'YYYY-MM-DD'), 1, 3, 1);

UPDATE RESENJE 
SET naziv_prikljucka = 'Novi naziv'
WHERE id_resenja = 1;

SELECT * FROM resenje;

UPDATE resenje
SET id_prikljucak = 1
WHERE id_resenja = 2;

UPDATE prikljucak
SET naziv = 'Bazna stanica'
WHERE id_prikljucak = 1;

SELECT * FROM ZAHTEV PARTITION (p2023);

CREATE TABLE zaposleni (
    id_zaposlenog NUMBER PRIMARY KEY,
    ime VARCHAR2(50) NOT NULL,
    prezime VARCHAR2(50) NOT NULL,
    kontakt VARCHAR2(20) NOT NULL,
    postanski_br NUMBER NOT NULL,
    id_ulice NUMBER NOT NULL,
    id_filijale NUMBER NOT NULL,
    FOREIGN KEY (postanski_br, id_ulice) REFERENCES Ulica(postanski_br, id_ulice),
    FOREIGN KEY (id_filijale) REFERENCES Filijala(id_filijale)
);

CREATE TABLE zaposleni_osnovno (
    id_zaposlenog NUMBER NOT NULL,
    ime VARCHAR2(50) NOT NULL,
    prezime VARCHAR2(50) NOT NULL,
    CONSTRAINT pk_zaposleni_osnovno PRIMARY KEY (id_zaposlenog)
);

CREATE TABLE zaposleni_detalji (
    id_zaposlenog NUMBER PRIMARY KEY,
    kontakt VARCHAR2(20) NOT NULL,
    postanski_br NUMBER NOT NULL,
    id_ulice NUMBER NOT NULL,
    id_filijale NUMBER NOT NULL,
    CONSTRAINT fk_postanski_ulica FOREIGN KEY (postanski_br, id_ulice) REFERENCES Ulica(postanski_br, id_ulice),
    CONSTRAINT fk_filijala FOREIGN KEY (id_filijale) REFERENCES Filijala(id_filijale)
);


CREATE OR REPLACE VIEW ZAPOSLENI_POGLED 
AS
SELECT ZO.id_zaposlenog, ZO.ime, ZO.prezime, 
ZD.kontakt, ZD.postanski_br, ZD.id_ulice, ZD.id_filijale
FROM zaposleni_osnovno ZO, zaposleni_detalji ZD
WHERE ZO.id_zaposlenog = ZD.id_zaposlenog;

SELECT 
    Z.id_zaposlenog "ID zaposlenog", 
    Z.ime "Ime", 
    Z.prezime "Prezime", 
    Z.kontakt "Kontakt", 
    Z.postanski_br "Poštanski broj", 
    Z.id_ulice "ID ulice", 
    Z.id_filijale "ID filijale"
FROM ZAPOSLENI_POGLED Z; 

INSERT INTO zaposleni_osnovno (id_zaposlenog, ime, prezime) VALUES (1, 'Marko', 'Marković');
INSERT INTO zaposleni_osnovno (id_zaposlenog, ime, prezime) VALUES (2, 'Jelena', 'Jovanović');
INSERT INTO zaposleni_osnovno (id_zaposlenog, ime, prezime) VALUES (3, 'Milan', 'Petrović');

INSERT INTO zaposleni_detalji (id_zaposlenog, kontakt, postanski_br, id_ulice, id_filijale) 
VALUES (1, '0641234567', 11000, 1, 1);

INSERT INTO zaposleni_detalji (id_zaposlenog, kontakt, postanski_br, id_ulice, id_filijale) 
VALUES (2, '0659876543', 11000, 3, 2);

INSERT INTO zaposleni_detalji (id_zaposlenog, kontakt, postanski_br, id_ulice, id_filijale) 
VALUES (3, '0631122334', 11000, 4, 2);

CREATE OR REPLACE TRIGGER TR_ZAPOSLENI
INSTEAD OF INSERT ON ZAPOSLENI_POGLED
FOR EACH ROW 
BEGIN 
    INSERT INTO zaposleni_osnovno(id_zaposlenog, ime, prezime)
    VALUES (:NEW.id_zaposlenog, :NEW.ime, :NEW.prezime);
    INSERT INTO zaposleni_detalji(id_zaposlenog, kontakt, postanski_br, id_ulice, id_filijale) 
    VALUES (:NEW.id_zaposlenog, :NEW.kontakt, :NEW.postanski_br, :NEW.id_ulice, :NEW.id_filijale);
END;

INSERT INTO ZAPOSLENI_POGLED VALUES (4, 'Zika', 'Zikic', '0632222222', 18000, 5, 2);

CREATE OR REPLACE TRIGGER TR_ZAPOSLENI_UPDATE
INSTEAD OF UPDATE ON ZAPOSLENI_POGLED
FOR EACH ROW 
BEGIN 
UPDATE zaposleni_osnovno SET id_zaposlenog = :NEW.id_zaposlenog, ime = :NEW.ime, prezime = :NEW.prezime
WHERE id_zaposlenog = :NEW.id_zaposlenog;
UPDATE zaposleni_detalji SET id_zaposlenog = :NEW.id_zaposlenog, kontakt =:NEW.kontakt, 
postanski_br = :NEW.postanski_br,id_ulice = :NEW.id_ulice,id_filijale =:NEW.id_filijale
WHERE id_zaposlenog = :NEW.id_zaposlenog;
END;

UPDATE ZAPOSLENI_POGLED SET IME = 'Mika' WHERE id_zaposlenog = 4;
SELECT  * FROM ZAPOSLENI_OSNOVNO zo ;
SELECT  * FROM ZAPOSLENI_DETALJI zd;
SELECT 
    Z.id_zaposlenog "ID zaposlenog", 
    Z.ime "Ime", 
    Z.prezime "Prezime", 
    Z.kontakt "Kontakt", 
    Z.postanski_br "Poštanski broj", 
    Z.id_ulice "ID ulice", 
    Z.id_filijale "ID filijale"
FROM ZAPOSLENI_POGLED Z; 

ALTER TABLE OBJEKAT 
ADD vrsta_objekta VARCHAR(100)
check(vrsta_objekta 
IN('Bazna stanica mobilne telefonije', 'Industrijski objekat', 'Poslovni prostor', 'Stambeni objekat'));

SELECT  * FROM OBJEKAT o ;

ALTER TABLE OBJEKAT 
DROP CONSTRAINT SYS_C007676;

ALTER TABLE OBJEKAT 
DROP Column ID_VRSTE_OBJEKTA;

UPDATE OBJEKAT
SET vrsta_objekta = 'Industrijski objekat'
WHERE id_objekta = 1;

UPDATE OBJEKAT
SET vrsta_objekta = 'Bazna stanica mobilne telefonije'
WHERE id_objekta = 2;

ALTER TABLE OBJEKAT
ADD ukupna_snaga DECIMAL(10, 2) DEFAULT 0;

CREATE OR REPLACE PACKAGE "C##MY_APP".Triggers_Context AS
    is_internal_update NUMBER := 0; 
END Triggers_Context;


CREATE OR REPLACE TRIGGER "C##MY_APP"."C##MY_APP".ZABRANA_UPDATE_UKUPNASNAGA
BEFORE UPDATE OF ukupna_snaga ON Objekat
FOR EACH ROW
BEGIN
    IF Triggers_Context.is_internal_update = 0 THEN 
        RAISE_APPLICATION_ERROR(-20001, 'Direktno ažuriranje kolone ukupna_snaga nije dozvoljeno!');
    END IF;
END;



CREATE OR REPLACE TRIGGER INSERT_UKUPNASNAGA
AFTER INSERT ON Snaga
FOR EACH ROW
BEGIN
    Triggers_Context.is_internal_update := 1;
    UPDATE Objekat
    SET ukupna_snaga = NVL(ukupna_snaga, 0) + NVL(:NEW.jacina, 0)
    WHERE id_objekta = :NEW.id_objekta;
    Triggers_Context.is_internal_update := 0;
END;





CREATE OR REPLACE TRIGGER UPDATE_UKUPNASNAGA
AFTER UPDATE OF jacina ON Snaga
FOR EACH ROW
BEGIN
    Triggers_Context.is_internal_update := 1;
    UPDATE Objekat
    SET ukupna_snaga = GREATEST(ukupna_snaga + (:NEW.jacina - :OLD.jacina), 0)
    WHERE id_objekta = :NEW.id_objekta;
    Triggers_Context.is_internal_update := 0;
END;



CREATE OR REPLACE TRIGGER DELETE_UKUPNASNAGA
AFTER DELETE ON Snaga
FOR EACH ROW
BEGIN
    Triggers_Context.is_internal_update := 1;
    UPDATE Objekat
    SET ukupna_snaga = GREATEST(ukupna_snaga - NVL(:OLD.jacina, 0), 0)
    WHERE id_objekta = :OLD.id_objekta;
    Triggers_Context.is_internal_update := 0;
END;



ALTER TRIGGER ZABRANA_UPDATE_UKUPNASNAGA COMPILE;
ALTER TRIGGER INSERT_UKUPNASNAGA COMPILE;
ALTER TRIGGER UPDATE_UKUPNASNAGA COMPILE;
ALTER TRIGGER DELETE_UKUPNASNAGA COMPILE;



INSERT INTO Snaga (id_snage, id_objekta, naziv, jacina)
VALUES (1 , 1, 'Osnovna snaga', 22);

INSERT INTO Snaga (id_snage, id_objekta, naziv, jacina)
VALUES (2 , 1, 'Dodatna snaga', 50);

INSERT INTO Snaga (id_snage, id_objekta, naziv, jacina)
VALUES (3 , 1, 'Bazna snaga', 30);

INSERT INTO Snaga (id_snage, id_objekta, naziv, jacina)
VALUES (4 , 2, 'Osnovna snaga', 50);

INSERT INTO Snaga (id_snage, id_objekta, naziv, jacina)
VALUES (5 , 2, 'Dodatna snaga', 45);

UPDATE Snaga
SET jacina = 50
WHERE id_snage = 2;

SELECT * FROM SNAGA s ;

SELECT * FROM OBJEKAT o ;

UPDATE Objekat
SET ukupna_snaga = 100
WHERE id_objekta = 1;

UPDATE Objekat
SET VRSTA_OBJEKTA = 'Bazna stanica mobilne telefonije 2'
WHERE id_objekta = 1;


UPDATE grad
SET naziv = 'Beograd'
WHERE postanski_br = 11000;

SELECT * FROM ulica;

INSERT INTO ulica (postanski_br, id_ulice, naziv, id_opstine) VALUES (21000, 10, 'Petrovaradin', 9);

CREATE OR REPLACE TRIGGER "C##MY_APP".ZABRANA_UPDATE_NAZIV
BEFORE UPDATE OF naziv_grada
ON Ulica
BEGIN
    RAISE_APPLICATION_ERROR(-20000, 'Zabranjeno je azuriranje ovog polja');
END;

UPDATE RESENJE 
SET naziv_prikljucka = 'Novi naziv'
WHERE id_resenja = 1;

SELECT * FROM resenje;

UPDATE resenje
SET id_prikljucak = 1
WHERE id_resenja = 1;

UPDATE prikljucak
SET naziv = 'Bazna stanica'
WHERE id_prikljucak = 1;




