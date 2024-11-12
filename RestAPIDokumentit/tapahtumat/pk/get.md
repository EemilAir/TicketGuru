# Näytä tapahtuma

Nayta haetun tapahtuman tiedot.

**URL** : `/api/tapahtumat/{id}`

**Metodi** : `GET`

**Oikeudet vaaditaan** : ADMIN tai USER

## Onnistunut vastaus

**Koodi** : `200 OK`

**Sisältöesimerkit**

Tapahtuma, jonka id on 1 ja jolle on tallennettu tiedot.

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
    "lippujaJaljella": 994,
    "lipputyypit": [
        {
            "id": {
                "tapahtumaId": 1,
                "lipputyyppiId": 1
            },
            "nimi": "Normaali",
            "kuvaus": "Normaali lippu",
            "hinta": 25.0
        },
        {
            "id": {
                "tapahtumaId": 1,
                "lipputyyppiId": 2
            },
            "nimi": "VIP",
            "kuvaus": "VIP lippu",
            "hinta": 50.0
        }
    ]
}
```

## Epäonnistunut vastaus

**Koodi** : `404 NOT FOUND`

**Sisältöesimerkit**

Tapahtuma, jonka id on 8 ja jolle ei ole tallennettu tietoja.

```json
{
    "viesti": "Tapahtumaa ei löytynyt ID:llä 8",
    "aikaleima": "2024-11-12T13:54:05.3566644",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/tapahtumat/8",
    "virheet": {}
}
```
