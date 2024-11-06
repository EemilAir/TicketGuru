# Näytä myyntitapahtumat

Näytä kaikkien myyntitapahtumien tiedot tai suodata tapahtumia summan, maksutavan tai käyttäjän perusteella.

**URL** : `/api/myyntitapahtumat/`

**Metodi** : `GET`

**Oikeudet vaaditaan** : EI TOISTAISEKSI

## Query-parametrit:
* `summa` (valinnainen): Suodatus myyntitapahtuman summan mukaan (esim `?summa=25.0`).
* `kayttajanimi` (valinnainen): Suodatus myyntitapahtuman käyttäjänimen mukaan eli myyntitapahtuman toteuttaneen käyttäjän mukaan (esim `?kayttajanimi=myyja1`).
* `maksutapa` (valinnainen): Suodatus maksutavan mukaan (esim. `?maksutapa=Käteinen`).
* `summa`- ja `maksutapa`-paremetreilla voi hakea, kuinka suuria maksutapahtumia on tehty tietyillä maksutavoilla (esim `?summa=15.0&maksutapa=Credit`).

**Esimerkit:**
* Hae myyntitapahtumat summan perusteella: `/api/myyntitapahtumat/?summa=30.0`
* Hae myyntitapahtumat käyttäjänimen (eli myyjän) perusteella: `/api/myyntitapahtumat/?kayttajanimi=myyja1`
* Hae myyntitapahtumat maksutavan perusteella: `/api/myyntitapahtumat/?maksutapa=Credit`
* Hae myyntitapahtumat summan ja maksutavan perusteella `/api/myyntitapahtumat/?summa=30.0&maksutapa=Credit`

## Onnistuneet vastaukset

**Ehto**: Käyttäjä ei näe yhtään myyntitapahtumaa, joka vastaa annettuja suodatuskriteerejä.

**Koodi** : `200 OK`

**Sisältö** : {[]}

### Tai

**Ehto** : Käyttäjä näkee yhden tai useamman myyntitapahtuman.

**Koodi** : `200 OK`

**Sisältö** : Tässä esimerkissä käyttäjälle näytetään neljä eri myyntitapahtumaa.

```json
[
    {
        "myyntitapahtumaId": 1,
        "summa": 25.0,
        "maksupvm": "2024-10-06T17:06:25.412807",
        "maksutapa": {
            "maksutapaId": 1,
            "maksutapa": "Käteinen"
        },
        "kayttaja": {
            "kayttajaId": 1,
            "kayttajanimi": "admin",
            "sposti": "admin@ticketguru.fi",
            "salasanaHash": "salasana",
            "kayttajarooli": "ADMIN"
        }
    },
    {
        "myyntitapahtumaId": 2,
        "summa": 30.0,
        "maksupvm": "2024-10-06T17:06:25.424821",
        "maksutapa": {
            "maksutapaId": 2,
            "maksutapa": "Debit"
        },
        "kayttaja": {
            "kayttajaId": 2,
            "kayttajanimi": "myyja1",
            "sposti": "myyja1@ticketguru.fi",
            "salasanaHash": "salasana",
            "kayttajarooli": "MYYJA"
        }
    },
    {
        "myyntitapahtumaId": 3,
        "summa": 30.0,
        "maksupvm": "2024-10-06T17:06:25.424821",
        "maksutapa": {
            "maksutapaId": 3,
            "maksutapa": "Credit"
        },
        "kayttaja": {
            "kayttajaId": 3,
            "kayttajanimi": "myyja2",
            "sposti": "myyja2@ticketguru.fi",
            "salasanaHash": "salasana",
            "kayttajarooli": "MYYJA"
        }
    },
    {
        "myyntitapahtumaId": 4,
        "summa": 35.0,
        "maksupvm": "2024-10-06T17:06:25.424821",
        "maksutapa": {
            "maksutapaId": 1,
            "maksutapa": "Käteinen"
        },
        "kayttaja": {
            "kayttajaId": 1,
            "kayttajanimi": "admin",
            "sposti": "admin@ticketguru.fi",
            "salasanaHash": "salasana",
            "kayttajarooli": "ADMIN"
        }
    }
]

```

## Virhevastaukset

**Ehto**: Virheellinen pyyntö (esim. virheelliset query-parametrit, kuten `api/myyntitapahtumat/summa=d`).

**Koodi** : `400 Bad Request`

**Sisältö** : {

```json
[
    {
        "timestamp": "2024-10-06T14:40:03.989+00:00",
        "status": 400,
        "error": "Bad Request",
        "message": "Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \"summa=d\"",
        "path": "/api/myyntitapahtumat/summa=d"
    }
]
```
}

**Huomautukset**

Myöhemmin toteutetaan haut myös muille myyntitapahtuman tiedoille, kuten myyntipäivälle. Samoin myös virheenkäsittely toteutetaan myöhemmin.