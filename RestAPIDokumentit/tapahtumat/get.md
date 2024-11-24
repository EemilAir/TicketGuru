# Näytä tapahtumat

Näytä kaikkien tapahtumien tiedot tai suodata tapahtumia nimen ja/tai kategorian perusteella.

**URL** : `/api/tapahtumat/`

**Metodi** : `GET`

**Oikeudet vaaditaan** : ADMIN tai USER

## Query-parametrit:
* `nimi` (valinnainen): Tapahtuman nimen suodatus (esim `?nimi=Tapahtuma1`).
* `kategoria` (valinnainen): Tapahtuman kategorian suodatus (esim `?kategoria=Festivaali`).
* Kumpaakin parametria voi käyttää yhdessä tai erikseen.

**Esimerkit:**
* Hae tapahtumat nimen perusteella: `/api/tapahtumat/?nimi=Ruisrock`
* Hae tapahtumat kategorian perusteella: `/api/tapahtumat/?kategoria=Festivaali`
* Hae tapahtumat nimen ja kategorian perusteella: `/api/tapahtumat/?nimi=Ruisrock&kategoria=Festivaali`

## Onnistuneet vastaukset

**Ehto** : Käyttäjä näkee yhden tai useamman tapahtuman.

**Koodi** : `200 OK`

**Sisältö** : Tässä esimerkissä käyttäjä näkee tapahtuman, jonka nimi parametri on "Ruisrock": (/api/tapahtumat/?nimi=Ruisrock)

```json
[
    {
        "tapahtumaId": 2,
        "nimi": "Ruisrock 2025",
        "kuvaus": "Ruisrock on Turun Ruissalossa järjestettävä musiikkifestivaali.",
        "kategoria": "Festivaali",
        "aloituspvm": "2025-07-04T12:00",
        "lopetuspvm": "2025-07-06T23:00",
        "katuosoite": "Ruissalon puistotie 1",
        "osoite": {
            "osoiteId": 2,
            "postinumero": "20100",
            "postitmp": "Turku"
        },
        "lippujaJaljella": 1995,
        "lipputyypit": [
            {
                "id": {
                    "tapahtumaId": 2,
                    "lipputyyppiId": 1
                },
                "nimi": "Normaali",
                "kuvaus": "Normaali lippu",
                "hinta": 30.0
            },
            {
                "id": {
                    "tapahtumaId": 2,
                    "lipputyyppiId": 2
                },
                "nimi": "VIP",
                "kuvaus": "VIP lippu",
                "hinta": 60.0
            }
        ]
    }
]
```

## Epäonnistuneet vastaukset

**Ehto**: Käyttäjä ei näe mitään tapahtumia (esim. localhost:8080/api/tapahtumat/?nimi=Ilovaarirock).

**Koodi** : `404 NOT FOUND`

**Sisältö** : {

```json
{
    "viesti": "Tapahtumia ei löytynyt",
    "aikaleima": "2024-11-24T22:31:15.2869372",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/tapahtumat/",
    "virheet": {}
}
```
}

