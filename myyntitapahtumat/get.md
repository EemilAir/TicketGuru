# Näytä myyntitapahtumat

Näytä kaikkien myyntitapahtumien tiedot tai suodata tapahtumia summan, maksutavan tai käyttäjän perusteella.

**URL** : `/api/myyntitapahtumat/`

**Metodi** : `GET`

**Oikeudet vaaditaan** : -

## Query-parametrit:
* `summa` (valinnainen): Suodatus myyntitapahtuman summan mukaan (esim `?summa=25.0`).
* `kayttajanimi` (valinnainen): Suodatus myyntitapahtuman kayttajanimen mukaan eli kuka on toteuttanut myyntitapahtuman (esim `?kayttajanimi=myyja1`).
* `maksutapa` (valinnainen): Suodatus maksutavan mukaan (esim. `?maksutapa=Käteinen`).
* `summa`- ja `maksutapa`-paremetreilla voi hakea, kuinka suuria maksutapahtumia on tehty tietyillä maksutavoilla (esim `?summa=15.0&maksutapa=Credit`).

**Esimerkit:**
* Hae myyntitapahtumat summan perusteella: `/api/myyntitapahtumat/?summa=30.0`
* Hae myyntitapahtumat käyttäjänimen (eli myyjän) perusteella: `/api/myyntitapahtumat/?kayttajanimi=myyja1`
* Hae myyntitapahtumat maksutavan perusteella: `/api/myyntitapahtumat/?maksutapa=Credit`
* Hae myyntitapahtumat summan ja maksutavan: `/api/myyntitapahtumat/?summa=30.0&maksutapa=Credit`

## Onnistuneet vastaukset

**Ehto**: Käyttäjä ei näe mitään tapahtumia.

**Koodi** : `200 OK`

**Sisältö** : {[]}

### Tai

**Ehto** : Käyttäjä näkee yhden tai useamman tapahtuman.

**Koodi** : `200 OK`

**Sisältö** : Tässä esimerkissä käyttäjälle näytetään sen hetken kaikki myyntitapahtumat:

```json
[
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

**Sisältö**

Myöhemmin toteutetaan haut myös muille myyntitapahtuman tiedoille, kuten myyntipäivälle.