# Lipunmyyntijärjestelmä

Tiimi: Jere Holopainen, Miikka Vartiainen, Jami Norja, Eemil Airaksinen, Anton Asikainen

## Johdanto

**TicketGuru** on lipunmyyntijärjestelmä, joka on suunniteltu lipputoimiston käyttöön. Järjestelmä mahdollistaa lippujen myymisen, hallinnan ja tulostamisen myyntipisteessä, sekä lipputarkistuksen tapahtumapaikalla. Tulevaisuudessa järjestelmään voidaan lisätä verkkokauppa, joka mahdollistaa lipun ostamisen verkossa.

### Toteutus- ja toimintaympäristö
- **Palvelinpuolen ratkaisut ja teknologiat:**
    - Palvelinpuoli toteutetaan **Spring Boot** -kehystä käyttäen
    - **Tietokannan suunnittelu** *
    - **REST API** *
    - **Rajapinta ja olioiden väliset yhteydet** *
    - **Vastauskoodit ja virhetilanteiden käsittely** *
    - **Autentikointi ja auktorisointi** *
    - **Tietokanta ja julkaisu** *

- **Käyttöliittymäratkaisut ja teknologiat:**
    - Järjestelmä suunnitellaan **desktop-laitteille**, mutta se on responsiivinen, jolloin se toimii myös tabletilla ja mobiililaitteilla. 
    - **Web-ohjelmointi** *
`NOTE: [tähdellä (*) merkittyjen kohtien tarkemmat tiedot lisätään kurssin edetessä.]`

### Projektin lopputulos

Lopputuloksena on käyttövalmis TicketGuru-järjestelmä lipputoimistolle, joka sisältää tapahtumien hallinnan, lippujen myynnin ja tulostamisen sekä lipputarkastuksen. Järjestelmä on myös valmis laajentumaan verkkokaupaksi.

---

## Järjestelmän määrittely

TicketGuru-järjestelmässä on useita selkeitä keskeisiä käyttäjärooleja. 
Näistä rooleista on muodostettu käyttäjätarinoita, joiden avulla pystytään syventymään roolien tarpeisiin ja järjestelmän tavoitteisiin.

1. **Lipunmyyjä**
- Myy ja tulostaa liput. Tarkastaa liput ovella ja merkitsee ne käytetyiksi järjestelmään lipussa olevalla koodilla.

    >*Käyttäjätarinat:*
    > - Lipunmyyjänä haluan myydä lippuja asiakkaille ja tulostaa ne järjestelmästä, jotta asiakas saa lipun heti mukaansa paikan päälle.
    >>
    > - Lipunmyyjänä haluan merkitä tarkastetut liput käytetyiksi järjestelmässä, jotta samaa lippua ei voida käyttää uudelleen ja estää väärinkäytökset.
    >> 
    > - Lipunmyyjänä haluan pystyä muuttamaan lipun tietoja, jotta voin vastata sujuvasti asiakkaan muutospyyntöihin.
    >>
    > - Lipunmyyjänä haluan voida peruuttaa asiakkaan lipun ja tekemään hyvityksen, jotta asiakkaan asiakaskokemus säilyy korkeana.

2. **Asiakas**
- Ostaa lippuja paikan päältä, jatkossa myös verkkokaupasta. Paikan päällä saa itselleen tulostetun lipun, verkkokaupasta tilattuna sähköpostitse.

    >*Käyttäjätarinat:*
    > - Asiakkaana haluan ostaa lipun paikan päältä lipunmyyntipisteeltä, jotta voin osallistua haluamaani tapahtumaan heti.
    >>
    > - Asiakkaana haluan saada verkkokaupasta ostetun lipun sähköpostiini, jotta voin tallentaa sen sähköisesti ja esittää sen tapahtuman ovella puhelimesta.
     >>
    > - Asiakkaana haluan pystyä perumaan lippuni ja saada hyvityksen, jotta voin muuttaa suunnitelmiani tarvittaessa.

3. **Järjestelmän ylläpitäjä**
- Vastaa järjestelmän teknisestä ylläpidosta. Toimii myös teknisenä tukena.

    >*Käyttäjätarinat:*
    > - Järjestelmän ylläpitäjänä haluan ylläpitää järjestelmän teknisiä toimintoja, jotta TicketGuru toimii luotettavasti ja tehokkaasti kaikille käyttäjille.
    >>
    > - Järjestelmän ylläpitäjänä haluan tarjota teknistä tukea järjestelmän käyttäjille, jotta voin ratkaista mahdolliset ongelmat nopeasti ja minimoida käyttökatkokset.


4. **Tapahtumien ylläpitäjä**
- Vastaa tapahtumien lisäämisestä, poistamisesta ja muokkaamisesta järjestelmässä. Voi tulostaa myös erilaisia raportteja lippuihin ja niiden myyntiin liittyen.

    >*Käyttäjätarinat:*
    > - Tapahtumien ylläpitäjänä haluan lisätä uusia tapahtumia järjestelmään, jotta lipunmyynti voi alkaa ajoissa ja asiakkaat voivat ostaa lippuja.
    >>
    > - Tapahtumien ylläpitäjänä haluan muokata olemassa olevien tapahtumien tietoja, jotta voin päivittää esimerkiksi tapahtuma-ajan tai -paikan muutokset.
    >>
    > - Tapahtumien ylläpitäjänä haluan poistaa peruutetut tapahtumat järjestelmästä, jotta virheelliset tapahtumat eivät ole näkyvissä asiakkaille ja vältetään sekaannukset.
    >>
    > - Tapahtumien ylläpitäjänä haluan tuottaa järjestelmään tallennetun datan perusteella raportteja lipunmyynnistä, jotta voin parantaa myyntiä tulevaisuudessa.


## Käyttöliittymä
- Käyttöliittymäkaavio

![Käyttöliittymäkaavio](./Images/Kayttoliittymakaavio.PNG)

- Tapahtumasivulta siirtyminen tapahtumien raportteihin ja myyntitapahtumiin

![Käyttöliittymäkaavio](./Images/Kayttoliittymakaavio2.PNG)

## Tietokanta
- TicketGuru-tietokannan UML-luokkakaavio

![UML-luokkakaavio](./Images/UMLclass.png)

> Linkki UML-luokkakaavion sivuille:
> https://lucid.app/lucidchart/cc73c021-a71b-40e2-b45e-cc985ebd1832/edit?viewport_loc=-2463%2C-161%2C2984%2C1477%2CHWEp-vi-RSFO&invitationId=inv_80605f74-82a3-4fa1-99c2-f3d2ad5d97b4
# Tietohakemisto

<details>
<summary>Tietohakemiston taulut</summary>

> ### _Kayttajat_
> _Kayttajat-taulu sisältää järjestelmän käyttäjätiedot. Jokaisella käyttäjällä on uniikki tunniste, ja taulu tallentaa käyttäjän nimen, yhteystiedot ja osoitteen._
> | Kenttä            | Tyyppi                                                             | Kuvaus                                                                                          |
> |-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------ |
> | kayttaja_id       | INT PRIMARY KEY NOT NULL AUTO_INCREMENT                            | Käyttäjän tunniste                                                                              |
> | etunimi           | VARCHAR(50) NOT NULL                                               | Käyttäjän etunimi                                                                               |
> | sukunimi          | VARCHAR(50) NOT NULL                                               | Käyttäjän sukunimi                                                                              |
> | syntyma_aika      | DATE                                                               | Käyttäjän syntymäaika                                                                           |
> | puhelinno         | VARCHAR(20) NOT NULL                                               | Käyttäjän puhelinnumero                                                                         |
> | sposti            | VARCHAR(100) NOT NULL                                              | Käyttäjän sähköposti                                                                            |
> | salasanaHash      | VARCHAR(255) NOT NULL                                              | Käyttäjän salasana                                                                              |
> | katuosoite        | VARCHAR(255) NOT NULL                                              | Käyttäjän kotiosoite                                                                            |
> | osoite_id         | INT NOT NULL FOREIGN KEY REFERENCES osoitteet(osoite_id)           | postinumero ja postitoimipaikka, viittaus osoitetietoihin [Osoitteet](#osoitteet)-taulussa      |

---

> ### _Kayttajaroolit_
> _Kayttajaroolit-taulu sisältää käyttäjäroolit, kuten admin ja myyjä. Jokaisella roolilla on uniikki tunniste ja nimi, mikä mahdollistaa käyttäjien oikeuksien ja pääsyjen hallinnan järjestelmässä._
> | Kenttä            | Tyyppi                                                             | Kuvaus                                                                                          |
> |-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------ |
> | rooli_id          | INT PRIMARY KEY NOT NULL AUTO_INCREMENT                            | Käyttäjäroolin tunniste                                                                         |
> | rooli_nimi        | VARCHAR(50) NOT NULL                                               | Käyttäjäroolin nimi                                                                             |

---

> ### _Kayttajan_Roolit_
> _Kayttajan_Roolit-taulu liittää käyttäjät ja käyttäjäroolit yhteen. Jokaisella käyttäjällä voi olla useita rooleja, kuten admin tai myyjä, ja taulu mahdollistaa käyttäjäroolien hallinnan järjestelmässä._
> | Kenttä            | Tyyppi                                                             | Kuvaus                                                                                          |
> |-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------ |
> |                   | PRIMARY KEY (kayttaja_id, rooli_id)                                | Käyttäjän ja Käyttäjäroolin tunnisteet yhdistetty pääavaimeksi                                  |
> | kayttaja_id       | INT NOT NULL FOREIGN KEY REFERENCES kayttajat(kayttaja_id)         | Käyttäjätiedot, Viittaus käyttäjään [Kayttajat](#Kayttajat)-taulussa                            |
> | rooli_id          | INT NOT NULL FOREIGN KEY REFERENCES kayttajaroolit(rooli_id)       | Käyttäjäroolin tiedot, Viittaus käyttäjärooliin [Kayttajaroolit](#kayttajaroolit)-taulussa      |

---

> ### _Lipunmyyntipisteet_
> _Lipunmyyntipisteet-taulu sisältää myyntipisteiden tiedot, kuten tunnisteen, nimen ja osoitteen. Jokaisella myyntipisteellä on yhteys osoitetietoihin ja myyjään, mikä mahdollistaa lipunmyynnin hallinnan eri sijainneissa._
> | Kenttä            | Tyyppi                                                             | Kuvaus                                                                                          |
> |-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------ |
> | myyntipiste_id    | INT PRIMARY KEY NOT NULL AUTO_INCREMENT                            | Myyntipisteen tunniste                                                                          |
> | myyntipiste_nimi  | VARCHAR(5) NOT NULL                                                | Myyntipisteen nimi                                                                              |
> | katuosoite        | VARCHAR(100) NOT NULL                                              | Myyntipisteen katuosoite                                                                        |
> | osoite_id         | INT NOT NULL FOREIGN KEY REFERENCES osoitteet(osoite_id)           | postinumero ja postitoimipaikka, viittaus osoitetietoihin [Osoitteet](#osoitteet)-taulussa      |
> | myyja_id          | INT NOT NULL FOREIGN KEY REFERENCES kayttajat(kayttaja_id)         | Myyjän käyttäjätiedot, Viittaus käyttäjään [Kayttajat](#kayttajat)-taulussa                     |

---

> ### _Tapahtumat_
> _Tapahtumat-taulu sisältää tietoja eri tapahtumista, kuten tunnisteen, nimen ja kuvauksen. Taulu tallentaa myös tapahtuman päivämäärän ja mahdollisen katuosoitteen, sekä viittaa osoitetietoihin, mikä mahdollistaa tapahtumien hallinnan ja sijainnin määrittämisen._
> | Kenttä            | Tyyppi                                                             | Kuvaus                                                                                          |
> |-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------ |
> | tapahtuma_id      | INT PRIMARY KEY NOT NULL AUTO_INCREMENT                            | Tapahtuman tunniste                                                                             |
> | nimi              | VARCHAR(100) NOT NULL                                              | Tapahtuman nimi                                                                                 |
> | kuvaus            | VARCHAR(100) NOT NULL                                              | Tapahtuman kuvaus                                                                               |
> | paivamaara        | DATE                                                               | Tapahtuman päivämäärä                                                                           |
> | katuosoite        | VARCHAR(100)                                                       | Tapahtuman katuosoite                                                                           |
> | osoite_id         | INT FOREIGN KEY REFERENCES osoitteet(osoite_id)                    | postinumero ja postitoimipaikka, viittaus osoitetietoihin [Osoitteet](#osoitteet)-taulussa      |

---

> ### _Osoitteet_
> _Osoitteet-taulu sisältää osoitetiedot, kuten tunnisteen, postinumeron ja postitoimipaikan. Taulu mahdollistaa eri sijaintien hallinnan ja on yhteydessä muihin tauluihin, joissa tarvitaan osoitetietoja._
> | Kenttä            | Tyyppi                                                             | Kuvaus                                                                                          |
> |-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------ |
> | osoite_id         | INT PRIMARY KEY NOT NULL AUTO_INCREMENT                            | Osoitteen tunniste                                                                              |
> | postino           | VARCHAR(5) NOT NULL                                                | Postinumero                                                                                     |
> | postitmp          | VARCHAR(100) NOT NULL                                              | Postitoimipaikka                                                                                |

---

> ### _Liput_
> _Liput-taulu sisältää tietoja lipuista, kuten tunnisteen, hinnan ja myyntiajan. Taulu tallentaa myös lipun koodin, alkamispäivämäärän ja loppumispäivämäärän. Liput linkitetään myyntikanaviin, lipputyyppeihin, tiloihin, käyttäjiin ja tapahtumiin, mikä mahdollistaa lipun hallinnan ja seurannan eri myyntikanavissa._
> | Kenttä            | Tyyppi                                                             | Kuvaus                                                                                          |
> |-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------ |
> | lippu_id          | INT PRIMARY KEY NOT NULL AUTO_INCREMENT                            | Lipun tunniste                                                                                  |
> | hinta             | DECIMAL(10,2) NOT NULL                                             | Lipun hinta                                                                                     |
> | myyntiaika        | TIMESTAMP NOT NULL                                                 | Lipun myyntiaika                                                                                |
> | koodi             | VARCHAR(255) NOT NULL                                              | Lipun koodi                                                                                     |
> | luontiaika        | TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP                       | Lipun luontiaika                                                                                |
> | alkupvm           | DATE NOT NULL                                                      | Lipun alkamispäivämäärä                                                                         |
> | loppupvm          | DATE NOT NULL                                                      | Lipun loppumispäivämäärä                                                                        |
> | myyntikanava_id   | INT NOT NULL FOREIGN KEY REFERENCES myyntikanavat(myyntikanava_id) | Myyntikanava, viittaus myyntikanavaan [Myyntikanavat](#myyntikanavat)-taulussa                  |
> | lipputyyppi_id    | INT NOT NULL FOREIGN KEY REFERENCES lipputyypit(lipputyyppi_id)    | Lipputyyppi, viittaus lipputyyppiin [Lipputyypit](#lipputyypit)-taulussa                        |
> | tila_id           | INT NOT NULL FOREIGN KEY REFERENCES tilat(tila_id)                 | Lipun tila, viittaus lipun tilaan [Tilat](#tilat)-taulussa                                      |
> | kayttaja_id       | INT NOT NULL FOREIGN KEY REFERENCES kayttajat(kayttaja_id)         | Myyjän käyttäjätiedot, viittaus käyttäjään [Käyttäjät](#kayttajat)-taulussa                     |
> | tapahtuma_id      | INT NOT NULL FOREIGN KEY REFERENCES tapahtumat(tapahtuma_id)       | Tapahtumatiedot, viittaus tapahtumatietoihin [Tapahtumat](#tapahtumat)-taulussa                 |
> | maksutapa_id      | INT NOT NULL FOREIGN KEY REFERENCES maksutavat(maksutapa_id)       | Maksutapa, viittaus maksutapaan [Maksutavat](#maksutavat)-taulussa                              |

---

> ### _Maksutavat_
> _Maksutavat-taulu sisältää maksutapojen tiedot, kuten tunnisteen ja nimen. Taulu mahdollistaa eri maksutapojen hallinnan ja käyttömahdollisuuksien määrittämisen järjestelmässä._
> | Kenttä            | Tyyppi                                                             | Kuvaus                                                                                          |
> |-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------ |
> | maksutapa_id      | INT PRIMARY KEY NOT NULL AUTO_INCREMENT                            | Maksutavan tunniste                                                                             |
> | maksutapa_nimi    | DECIMAL(10,2) NOT NULL                                             | Maksutavan nimi                                                                                 |

---

> ### _Myyntikanavat_
> _Myyntikanavat-taulu sisältää myyntikanavien tiedot, kuten tunnisteen ja nimen. Taulu mahdollistaa eri myyntikanavien hallinnan ja määrittämisen järjestelmässä, mikä tukee lipunmyynnin monipuolisuutta._
> | Kenttä            | Tyyppi                                                             | Kuvaus                                                                                          |
> |-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------ |
> | myyntikanava_id   | INT PRIMARY KEY NOT NULL AUTO_INCREMENT                            | Myyntikanavan tunniste                                                                          |
> | myyntikanava_nimi | DECIMAL(10,2) NOT NULL                                             | Myyntikanavan nimi                                                                              |

---

> ### _Lipputyypit_
> _Lipputyypit-taulu sisältää eri lipputyyppien tiedot, kuten tunnisteen, nimen ja kuvauksen. Taulu mahdollistaa lipputyyppien hallinnan ja erottelun, mikä auttaa käyttäjiä valitsemaan sopivia lippuja eri tapahtumiin._
> | Kenttä            | Tyyppi                                                             | Kuvaus                                                                                          |
> |-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------ |
> | lipputyyppi_id    | INT PRIMARY KEY NOT NULL AUTO_INCREMENT                            | Lipputyypin tunniste                                                                            |
> | lipputyyppi       | VARCHAR(50) NOT NULL                                               | Lipputyypin nimi                                                                                |
> | kuvaus            | VARCHAR(255)                                                       | Lipputyypin kuvaus                                                                              |

---

> ### _Tilat_
> _Tilat-taulu sisältää tietoja eri tiloista, kuten tunnisteen ja nimen. Taulu mahdollistaa tilojen hallinnan ja erottelun, mikä auttaa järjestämään tapahtumia ja hallitsemaan niiden sijainteja._
> | Kenttä            | Tyyppi                                                             | Kuvaus
> |-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------ |
> | tila_id           | INT PRIMARY KEY NOT NULL AUTO_INCREMENT                            | Tilan tunniste                                                                                  |
> | tila_nimi         | VARCHAR(50) NOT NULL                                               | Tilan nimi                                                                                      |

---

> ### _Myyntitapahtuma_
> _Myyntitapahtuma-taulu sisältää tiedon ostettujen lippujen maksupäivämääräärästä, kokonaissummasta, sekä muusta tarvittavasta lippuihin kohdistuvasta tiedosta. Taulu mahdollistaa lippujen tulostamisen asiakkaille, sekä lippumyynnin seuraamisen._
> | Kenttä            | Tyyppi                                                             | Kuvaus
> |-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------ |
> | myyntitapahtuma_id           | INT PRIMARY KEY NOT NULL AUTO_INCREMENT                            | Myyntitapahtuman tunniste                                                                                  |
> | maksupvm         | DATETIME NOT NULL                                               | Lippujen ostopäivämäärä
> | summa           | DECIMAL(10,2) NOT NULL                           | Lippujen yhteissumma
> | lippu_id           | INT NOT NULL FOREIGN KEY REFERENCES liput(lippu_id)                            | Lipun tunniste
</details>

## Tekninen kuvaus

Teknisessä kuvauksessa esitetään järjestelmän toteutuksen suunnittelussa tehdyt tekniset
ratkaisut, esim.

-   Missä mikäkin järjestelmän komponentti ajetaan (tietokone, palvelinohjelma)
    ja komponenttien väliset yhteydet (vaikkapa tähän tyyliin:
    https://security.ufl.edu/it-workers/risk-assessment/creating-an-information-systemdata-flow-diagram/)
-   Palvelintoteutuksen yleiskuvaus: teknologiat, deployment-ratkaisut yms.
-   Keskeisten rajapintojen kuvaukset, esimerkit REST-rajapinta. Tarvittaessa voidaan rajapinnan käyttöä täsmentää
    UML-sekvenssikaavioilla.
-   Toteutuksen yleisiä ratkaisuja, esim. turvallisuus.

Tämän lisäksi

-   ohjelmakoodin tulee olla kommentoitua
-   luokkien, metodien ja muuttujien tulee olla kuvaavasti nimettyjä ja noudattaa
    johdonmukaisia nimeämiskäytäntöjä
-   ohjelmiston pitää olla organisoitu komponentteihin niin, että turhalta toistolta
    vältytään

## Testaus

Tässä kohdin selvitetään, miten ohjelmiston oikea toiminta varmistetaan
testaamalla projektin aikana: millaisia testauksia tehdään ja missä vaiheessa.
Testauksen tarkemmat sisällöt ja testisuoritusten tulosten raportit kirjataan
erillisiin dokumentteihin.

Tänne kirjataan myös lopuksi järjestelmän tunnetut ongelmat, joita ei ole korjattu.

## Asennustiedot

Järjestelmän asennus on syytä dokumentoida kahdesta näkökulmasta:

-   järjestelmän kehitysympäristö: miten järjestelmän kehitysympäristön saisi
    rakennettua johonkin toiseen koneeseen

-   järjestelmän asentaminen tuotantoympäristöön: miten järjestelmän saisi
    asennettua johonkin uuteen ympäristöön.

Asennusohjeesta tulisi ainakin käydä ilmi, miten käytettävä tietokanta ja
käyttäjät tulee ohjelmistoa asentaessa määritellä (käytettävä tietokanta,
käyttäjätunnus, salasana, tietokannan luonti yms.).

## Käynnistys- ja käyttöohje

Tyypillisesti tässä riittää kertoa ohjelman käynnistykseen tarvittava URL sekä
mahdolliset kirjautumiseen tarvittavat tunnukset. Jos järjestelmän
käynnistämiseen tai käyttöön liittyy joitain muita toimenpiteitä tai toimintajärjestykseen liittyviä asioita, nekin kerrotaan tässä yhteydessä.

Usko tai älä, tulet tarvitsemaan tätä itsekin, kun tauon jälkeen palaat
järjestelmän pariin !
