# Näytä tapahtumat

Näytä kaikkien tapahtumien tiedot

**URL** : `/api/tapahtumat/`

**Metodi** : `GET`

**Oikeudet vaaditaan** : Ei

## Onnistuneet vastaukset

**Ehto**: Käyttäjä ei näe mitään tapahtumia.

**Koodi** : `200 OK`

**Sisältö** : {[]}

### Tai

**Ehto** : Käyttäjä näkee yhden tai useamman tapahtuman.

**Koodi** : `200 OK`

**Sisältö** : Tässä esimerkissä käyttäjä voi nähdä 3 tapahtumaa:

```json
[
    {
        "tapahtumaId": 1,
        "nimi": "Tapahtuma 1",
        "kuvaus": "Kuvaus 1",
        "kategoria": "Kategoria 1",
        "aloituspvm": "2024-09-29T12:00:27.043543",
        "lopetuspvm": "2024-09-30T12:00:27.043543",
        "katuosoite": "Katuosoite1",
        "lippujaJaljella": 100,
        "osoite": {
            "osoiteId": 1,
            "postinumero": "00100",
            "postitmp": "Helsinki"
        },
        "liput": [],
        "tapahtumanLipputyypit": []
    },
    {
        "tapahtumaId": 2,
        "nimi": "Tapahtuma 2",
        "kuvaus": "Kuvaus 2",
        "kategoria": "Kategoria 2",
        "aloituspvm": "2024-10-01T12:00:27.047547",
        "lopetuspvm": "2024-10-02T12:00:27.047547",
        "katuosoite": "Katuosoite2",
        "lippujaJaljella": 200,
        "osoite": {
            "osoiteId": 2,
            "postinumero": "00200",
            "postitmp": "Espoo"
        },
        "liput": [],
        "tapahtumanLipputyypit": []
    },
    {
        "tapahtumaId": 3,
        "nimi": "Tapahtuma 3",
        "kuvaus": "Kuvaus 3",
        "kategoria": "Kategoria 3",
        "aloituspvm": "2024-10-03T12:00:27.048548",
        "lopetuspvm": "2024-10-04T12:00:27.048548",
        "katuosoite": "Katuosoite3",
        "lippujaJaljella": 300,
        "osoite": {
            "osoiteId": 3,
            "postinumero": "00300",
            "postitmp": "Vantaa"
        },
        "liput": [],
        "tapahtumanLipputyypit": []
    }
]
```