# Päivitä tapahtuman osaa

Muokkaa yksittäistä tapahtumaa.

**URL** : `/api/tapahtumat/{id}`

**Metodi** : `PATCH`

**Oikeudet vaaditaan** : ADMIN

**Sisältöesimerkit**

```json
{
    "lippujaJaljella": 0
}
```

## Onnistuneet Vastaukset

**Ehto** : Tapahtuman muokkaus onnistui

**Koodi** : `200 OK`

**Sisältöesimerkki** :
Tapahtuma 1, jonka lippujaJaljella arvoksi on muutettu 0

Ennen muokkausta:
```json
{
    "nimi": "Tuska Festival 2025",
    "kuvaus": "Tuska on Helsingin Suvilahdessa järjestettävä metallimusiikkiin keskittynyt festivaali.",
    "kategoria": "Festivaali",
    "aloituspvm": "2025-06-27T12:00",
    "lopetuspvm": "2025-06-29T23:00",
    "katuosoite": "Kaasutehtaankatu 1",
    "osoite": {
        "osoiteId": 1,
        "postinumero": "00540",
        "postitmp": "Helsinki"
    },
    "lippujaJaljella": 500,
    "lipputyypit": []
}
```

Muokkauksen jälkeen:
```json
{
    "nimi": "Tuska Festival 2025",
    "kuvaus": "Tuska on Helsingin Suvilahdessa järjestettävä metallimusiikkiin keskittynyt festivaali.",
    "kategoria": "Festivaali",
    "aloituspvm": "2025-06-27T12:00",
    "lopetuspvm": "2025-06-29T23:00",
    "katuosoite": "Kaasutehtaankatu 1",
    "osoite": {
        "osoiteId": 1,
        "postinumero": "00540",
        "postitmp": "Helsinki"
    },
    "lippujaJaljella": 0,
    "lipputyypit": []
}
```

## Epäonnistunut vastaus

**Ehto** : Tapahtuman arvo on laitettu väärään muotoon

**Koodi** : `400 BAD REQUEST`

**Sisältöesimerkit** : 

lippujaJaljella arvoksi on annettu x

```json
{
    "viesti": "Virheellinen JSON-syöte. Tarkista, että arvo on oikeassa muodossa.",
    "aikaleima": "2024-11-24T22:28:46.6797535",
    "tilakoodi": 400,
    "tila": "Bad Request",
    "polku": "uri=/api/tapahtumat/1",
    "virheet": {}
}
```