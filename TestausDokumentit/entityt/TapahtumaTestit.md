# Tapahtuma

## Testausympäristö

### Testauksen tiedot

- **Testikehys**: JUnit
- **Käytetty annotaatio**: `@BeforeEach` (testiluokan alustamiseksi), `@Test` (yksittäisten testien merkintä)
- **Validointi**: `jakarta.validation.Validator` käytetään tapahtuman kenttien validointiin.
  
## Testitapaukset

**1. Testi: Tapahtuman luonti (shouldRetrieveCorrectAttributes)**

- **Kuvaus**: Testaa, että tapahtuman-olio luodaan oikein ja sen attribuutit saavat odotetut arvot.
- **Syöte**: Ei erillistä syötettä, vaan luodaan `Tapahtuma`-olio `@BeforeEach`-metodissa.
  
**Odotettu tulos:**
- **Tarkistukset**: 
  - Nimen tarkistaminen (Testi tapahtuma)
  - Kuvauksen tarkistaminen (Tämä on testi tapahtuma)
  - Kategorian tarkistaminen ("Testi kategoria")
  - Aloituspvm tarkistaminen ("2023-10-01T10:00:00")
  - Lopetuspvm tarkistaminen ("2023-10-01T18:00:00")
  - Katuosoitteen tarkistaminen ("Testi katu 123")
  - Osoite asetettu null testeissä, joten ei tarkisteta
  - LippujaJaljella tarkistaminen ("100")

```java
assertEquals("Testi tapahtuma", tapahtuma.getNimi());
assertEquals("Tämä on testi tapahtuma.", tapahtuma.getKuvaus());
assertEquals("Testi kategoria", tapahtuma.getKategoria());
assertEquals(LocalDateTime.of(2023, 10, 1, 10, 0), tapahtuma.getAloituspvm());
assertEquals(LocalDateTime.of(2023, 10, 1, 18, 0), tapahtuma.getLopetuspvm());
assertEquals("Testi katu 123", tapahtuma.getKatuosoite());
assertEquals(100, tapahtuma.getLippujaJaljella());
```

**2. Testi: Tapahtuman päivitys (shouldUpdateAttributesCorrectly)**

### Kuvaus:
Testaa, että tapahtuman attribuutit päivittyvät oikein.

### Syöte:
Muutetaan tapahtuman attribuutteja seuraavilla metodeilla:
- `setNimi()`
- `setKuvaus()`
- `setKategoria()`
- `setAloituspvm()`
- `setLopetuspvm()`
- `setKatuosoite()`
- `setLippujaJaljella()`

### Odotettu tulos:

#### Tarkistukset:
- Päivitetyn nimen tarkistaminen: `Päivitetty tapahtuma`
- Päivitetyn kuvauksen tarkistaminen: `Päivitetty kuvaus`
- Päivitetyn kategorian tarkistaminen: `"Päivitetty kategoria"`
- Päivitetyn aloituspvm tarkistaminen: `"2023-11-01T10:00:00"`
- Päivitetyn lopetuspvm tarkistaminen: `"2023-11-01T18:00:00"`
- Päivitetyn katuosoite tarkistaminen: `"Päivitetty katu 456"`
- Päivitetyn lippujaJaljella tarkistaminen: `"200"`

```java
assertEquals("Päivitetty tapahtuma", tapahtuma.getNimi());
assertEquals("Päivitetty kuvaus.", tapahtuma.getKuvaus());
assertEquals("Päivitetty kategoria", tapahtuma.getKategoria());
assertEquals(LocalDateTime.of(2023, 11, 1, 10, 0), tapahtuma.getAloituspvm());
assertEquals(LocalDateTime.of(2023, 11, 1, 18, 0), tapahtuma.getLopetuspvm());
assertEquals("Päivitetty katu 456", tapahtuma.getKatuosoite());
assertEquals(200, tapahtuma.getLippujaJaljella());
```

**3. Testi: Tapahtuman nimen validointi (shouldNotAcceptNullNimi)**

### Kuvaus:
Testaa, että tapahtuman nimi ei voi olla null.

### Syöte:
Asetetaan tapahtuman nimi-arvoksi `null`.

### Odotettu tulos:

#### Validointivirhe:
- Virheviesti: `"Tapahtuman nimi ei voi olla tyhjä"`

#### Violaatiot:
- Testissä tarkistetaan, että validointivirhe havaitaan ja virheviesti palautetaan.

```java
assertFalse(violations.isEmpty());
assertEquals("Tapahtuman nimi ei voi olla tyhjä", violation.getMessage());
```

**4. Testi: Tapahtuman nimen validointi (shouldNotAcceptTooShortNimi)**

### Kuvaus:
Testaa, että tapahtuman nimi ei voi olla kolmea merkkiä lyhyempi.

### Syöte:
Asetetaan tapahtuman nimeksi `Ta`.

### Odotettu tulos:

#### Validointivirhe:
- Virheviesti: `"Tapahtuman nimi voi olla korkeintaan 100 merkkiä pitkä ja 3 merkkiä lyhyt"`

#### Violaatiot:
- Testissä tarkistetaan, että validointivirhe havaitaan ja virheviesti palautetaan.

```java
assertFalse(violations.isEmpty());
assertEquals("Tapahtuman nimi voi olla korkeintaan 100 merkkiä pitkä ja 3 merkkiä lyhyt", violation.getMessage());

```
## Mockit ja riippuvuudet

### Testien komponentit

- **Validator**: Käytetään `jakarta.validation.Validator`-komponenttia tapahtuman kenttien validointiin.
- **Tapahtuma**: Testattava pääkomponentti.

```java
// Alustetaan Tapahtuma
tapahtuma = new Tapahtuma(
                "Testi tapahtuma",
                "Tämä on testi tapahtuma.",
                "Testi kategoria",
                LocalDateTime.of(2023, 10, 1, 10, 0),
                LocalDateTime.of(2023, 10, 1, 18, 0),
                "Testi katu 123",
                null, // Oletetaan, että osoite on null tässä testissä
                100);
```

## Keskeiset testitarkistukset

### Testin tarkistukset

- **Nimen validointi (notTooShort)**: Varmistaa, että tapahtuman nimi ei voi olla kolmea merkkiä lyhyempi.
- **Nimen validointi (notNull)**: Varmistaa, että tapahtuman nimi ei voi olla null.
- **Attribuuttien oikeellisuus**: Testaa, että luodun tai päivitetyn tapahtuman kentät vastaavat odotettuja arvoja.

### Liittyvät tiedostot

- **Model**: `Tapahtuma.java`
- **Testitiedosto**: `TapahtumaTest.java`
