# MyyntitapahtumaRestController

## Testausympäristö

### Testauksen tiedot

- **Testikehys**: JUnit
- **Mock-toteutukset**: Spring Boot `@MockBean`, Mockito
- **REST-testaus**: MockMvc
- **Käyttäjän rooli testeissä**: ADMIN (`@WithMockUser`)
- **Käytetty annotaatio**: `@SpringBootTest`


## Testitapaukset

**1. Testi: Myyntitapahtuman luominen**

-**Kuvaus:** Testaa myyntitapahtuman luontia POST:lla.
-**Polku:** POST /api/myyntitapahtumat/
-**Syöte:**

```json
{
  "kayttajaId": 1,
  "maksutapaId": 1,
  "liput": [
    { "tapahtumaId": 1, "lipputyyppiId": 1, "maara": 3 }
  ]
}
```

**Mockattu toiminnallisuus:**

MyyntitapahtumaService.luoMyyntitapahtumaJaLiput() palauttaa testidatan.

**Odotettu tulos:**

-**HTTP-statuskoodi:** 201 Created
-**Vastaus JSON-muodossa:**

```json
{
    "myyntitapahtumaId": 1,
    "summa": 100.0,
    "maksupvm": "2024-10-01T10:00:00",
    "maksutapa": "Käteinen",
    "kayttajaId": 1
}
```

**Validoinnit:**

Tarkistaa, että myyntitapahtuma on luotu onnistuneesti ja vastauksen arvot ovat odotetut.

## Mockit ja riippuvuudet

### Testien komponentit

- **MockMvc**: Käytetään simuloimaan HTTP-pyyntöjä ja validointia.
- **MyyntitapahtumaService**: Mockattu palvelu, joka vastaa liiketoimintalogiikan toteutuksesta.
- **MyyntitapahtumaResponseDTO**: Testidatan tyyppi.


## Esimerkki testidatasta
```java
DTO Testidatan alustaminen:

MyyntitapahtumaResponseDTO myyntitapahtumaResponseDTO = new MyyntitapahtumaResponseDTO();
myyntitapahtumaResponseDTO.setMyyntitapahtumaId(1L);
myyntitapahtumaResponseDTO.setSumma(100.0);
myyntitapahtumaResponseDTO.setMaksupvm(LocalDateTime.of(2024, 10, 1, 10, 0));
myyntitapahtumaResponseDTO.setMaksutapa("Käteinen");
myyntitapahtumaResponseDTO.setKayttajaId(1L);
```

## Keskeiset testitarkistukset

### Testin tarkistukset

- **Statuskoodi**: Varmistaa, että vastaus on 201 CREATED.
- **JSON-vastauksen kentät**:
  - **myyntitapahtumaId**: Tarkistetaan, että ID vastaa mockattua dataa.
  - **summa**: Varmistaa summan oikeellisuuden.
  - **maksupvm**: Varmistaa päivämäärän.
  - **maksutapa**: Tarkistaa maksutavan nimen.
  - **kayttajaId**: Varmistaa käyttäjän ID:n oikeellisuuden.


## Liittyvät tiedostot

- **Controller**: MyyntitapahtumaRestController.java
- **Service**: MyyntitapahtumaService.java
- **Testitiedosto**: MyyntitapahtumaRestControllerTest.java