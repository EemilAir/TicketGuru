# Testausdokumentaatio: TicketGuru-projekti

## Yleiskatsaus

Tämä dokumentaatio kattaa **TicketGuru-projektin** testaustoiminnan ja testausympäristön kuvauksen.

Testauksen pääasialliset tavoitteet ovat:

- Varmistaa, että kaikki komponentit toimivat oikein.
- Testata API-rajapinnat ja niiden palauttamat HTTP-vastaukset.
- Varmistaa, että liiketoimintalogiikka ja validoinnit toimivat oikein.

## Sisällysluettelo

1. Controllerit
    1.1 [MyyntitapahtumaRestControllerTestit](controllerit/MyyntitapahtumaRestControllerTestit.md)
    1.2 [TapahtumaRestControllerTestit](controllerit/TapahtumaRestControllerTestit.md)
2. Entityt
    2.1 [MyyntitapahtumaTestit](entityt/MyyntitapahtumaTestit.md)
    2.2 [TapahtumaTestit](entityt/TapahtumaTestit.md)


**Tärkeimmät testityökalut ja -menetelmät:**

- **MockMvc**: Käytetään API-pyyntöjen simuloimiseen ja vastausten tarkistamiseen.
- **JUnit**: Testien ajamiseen ja validointiin.
- **Mockito**: Mockataan palveluita ja komponentteja testauksen aikana.
- **Spring Boot Test**: Testeissä käytetään Spring Bootin tarjoamaa testausta ja kontekstin luontia.

---

**Liittyvät tiedostot:**

- **Controller**: `MyyntitapahtumaRestController.java`, `TapahtumaRestController.java`
- **Service**: `MyyntitapahtumaService.java`
- **Repository**: `TapahtumaRepository.java`
- **Model**: `Myyntitapahtuma.java`, `Maksutapa.java`, `Kayttaja.java`, `Tapahtuma.java`
- **Testitiedostot**: `MyyntitapahtumaRestControllerTest.java`, `MyyntitapahtumaTest.java`, `TapahtumaRestControllerTest.java`, `TapahtumaTest.java`

---
