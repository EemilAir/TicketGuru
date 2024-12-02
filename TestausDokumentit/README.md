# Testausdokumentaatio: TicketGuru-projekti

## Yleiskatsaus

Tämä dokumentaatio kattaa **TicketGuru-projektin** testaustoiminnan ja testausympäristön kuvauksen. Projektissa on useita eri komponentteja, kuten REST-rajapintoja ja liiketoimintalogiikkaa, joita testataan yksikkötesteillä, integraatiotesteillä sekä API-testauksella.

Testauksella varmistetaan, että projektin toiminnallisuus on virheetöntä, ja että kaikki rajapinnat, kuten **MyyntitapahtumaRestController** ja muut komponentit, toimivat odotetusti. Dokumentissa käsitellään käytetyt testausvälineet, testitapaukset sekä testausympäristön asetukset.

Testauksen pääasialliset tavoitteet ovat:

- Varmistaa, että kaikki komponentit toimivat oikein.
- Testata API-rajapinnat ja niiden palauttamat HTTP-vastaukset.
- Varmistaa, että liiketoimintalogiikka ja validoinnit toimivat oikein.

## Sisällysluettelo

1. Controllerit
    1.1 [MyyntitapahtumatRestControllerTestit](controllerit/MyyntitapahtumatRestControllerTestit.md)
2. Entityt
    2.1 [MyyntitapahtumatTestit](entityt/MyyntitapahtumaTestit.md)

---* [Näytä tapahtumat](tapahtumat/get.md) : `GET /api/tapahtumat/`

**Tärkeimmät testityökalut ja -menetelmät:**

- **MockMvc**: Käytetään API-pyyntöjen simuloimiseen ja vastausten tarkistamiseen.
- **JUnit**: Testien ajamiseen ja validointiin.
- **Mockito**: Mockataan palveluita ja komponentteja testauksen aikana.
- **Spring Boot Test**: Testeissä käytetään Spring Bootin tarjoamaa testausta ja kontekstin luontia.

---

**Liittyvät tiedostot:**

- **Controller**: `MyyntitapahtumaRestController.java`
- **Service**: `MyyntitapahtumaService.java`
- **Model**: `Myyntitapahtuma.java`, `Maksutapa.java`, `Kayttaja.java`
- **Testitiedostot**: `MyyntitapahtumaRestControllerTest.java`, `MyyntitapahtumaTest.java`

---
