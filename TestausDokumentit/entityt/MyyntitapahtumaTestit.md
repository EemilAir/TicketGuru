# Myyntitapahtuma

## Testausympäristö

### Testauksen tiedot

- **Testikehys**: JUnit
- **Käytetty annotaatio**: `@BeforeEach` (testiluokan alustamiseksi), `@Test` (yksittäisten testien merkintä)
- **Validointi**: `jakarta.validation.Validator` käytetään myyntitapahtuman kenttien validointiin.
  
## Testitapaukset

**1. Testi: Myyntitapahtuman luonti**

- **Kuvaus**: Testaa, että myyntitapahtuman-olio luodaan oikein ja sen attribuutit saavat odotetut arvot.
- **Syöte**: Ei erillistä syötettä, vaan luodaan `Myyntitapahtuma`-olio `@BeforeEach`-metodissa.
  
**Odotettu tulos:**
- **Tarkistukset**: 
  - Summan tarkistaminen (100.0)
  - Maksupäivämäärän tarkistaminen (2023-10-01T10:00:00)
  - Maksutavan nimen tarkistaminen ("Käteinen")
  - Käyttäjänimen tarkistaminen ("myyja1")

```java
assertEquals(100.0, myyntitapahtuma.getSumma());
assertEquals(LocalDateTime.of(2023, 10, 1, 10, 0), myyntitapahtuma.getMaksupvm());
assertEquals("Käteinen", myyntitapahtuma.getMaksutapa().getMaksutapaNimi());
assertEquals("myyja1", myyntitapahtuma.getKayttaja().getKayttajanimi());
```

## Testi: Myyntitapahtuman päivitys

### Kuvaus:
Testaa, että myyntitapahtuman attribuutit päivittyvät oikein.

### Syöte:
Muutetaan myyntitapahtuman attribuutteja seuraavilla metodeilla:
- `setSumma()`
- `setMaksupvm()`
- `setMaksutapa()`
- `setKayttaja()`

### Odotettu tulos:

#### Tarkistukset:
- Päivitetyn summan tarkistaminen: `200.0`
- Päivitetyn maksupäivämäärän tarkistaminen: `2023-11-01T10:00:00`
- Päivitetyn maksutavan nimen tarkistaminen: `"Debit"`
- Päivitetyn käyttäjänimen tarkistaminen: `"myyja2"`

```java
assertEquals(200.0, myyntitapahtuma.getSumma());
assertEquals(LocalDateTime.of(2023, 11, 1, 10, 0), myyntitapahtuma.getMaksupvm());
assertEquals("Debit", myyntitapahtuma.getMaksutapa().getMaksutapaNimi());
assertEquals("myyja2", myyntitapahtuma.getKayttaja().getKayttajanimi());
```

## Testi: Myyntitapahtuman summan validointi (negatiivinen arvo)

### Kuvaus:
Testaa, että myyntitapahtuman summa ei voi olla negatiivinen.

### Syöte:
Asetetaan myyntitapahtuman summaksi `-100.0`.

### Odotettu tulos:

#### Validointivirhe:
- Virheviesti: `"Summan pitää olla positiivinen luku"`

#### Violaatiot:
- Testissä tarkistetaan, että validointivirhe havaitaan ja virheviesti palautetaan.

```java
assertFalse(violations.isEmpty());
assertEquals("Summan pitää olla positiivinen luku", violation.getMessage());
```

## Testi: Myyntitapahtuman maksupäivämäärän validointi (null-arvo)

### Kuvaus:
Testaa, että myyntitapahtuman maksupvm ei voi olla null.

### Syöte:
Asetetaan maksupvm-arvoksi `null`.

### Odotettu tulos:

#### Validointivirhe:
- Virheviesti: `"Maksupvm ei voi olla tyhjä"`
- Violaatiot: Testissä tarkistetaan, että validointivirhe havaitaan.

```java
assertFalse(violations.isEmpty());
assertEquals("Maksupvm ei voi olla tyhjä", violation.getMessage());

```
## Mockit ja riippuvuudet

### Testien komponentit

- **Validator**: Käytetään `jakarta.validation.Validator`-komponenttia myyntitapahtuman kenttien validointiin.
- **Maksutapa**: Mockattu maksutapa-olio.
- **Kayttaja**: Mockattu käyttäjä-olio,
- **Myyntitapahtuma**: Testattava pääkomponentti.

```java
// Alustetaan Myyntitapahtuma ja muut olioiden kentät testissä
Maksutapa maksutapa = new Maksutapa();
maksutapa.setMaksutapaNimi("Käteinen");

Kayttaja kayttaja = new Kayttaja();
kayttaja.setKayttajanimi("myyja1");

Myyntitapahtuma myyntitapahtuma = new Myyntitapahtuma();
myyntitapahtuma.setSumma(100.0);
myyntitapahtuma.setMaksupvm(LocalDateTime.of(2023, 10, 1, 10, 0));
myyntitapahtuma.setMaksutapa(maksutapa);
myyntitapahtuma.setKayttaja(kayttaja);
```

## Keskeiset testitarkistukset

### Testin tarkistukset

- **Summan validointi**: Varmistaa, että summa ei voi olla negatiivinen.
- **Maksupvm validointi**: Varmistaa, että maksupvm ei voi olla null.
- **Attribuuttien oikeellisuus**: Testaa, että luodun tai päivitetyn myyntitapahtuman kentät vastaavat odotettuja arvoja.

### Liittyvät tiedostot

- **Model**: `Myyntitapahtuma.java`, `Maksutapa.java`, `Kayttaja.java`
- **Testitiedosto**: `MyyntitapahtumaTest.java`



