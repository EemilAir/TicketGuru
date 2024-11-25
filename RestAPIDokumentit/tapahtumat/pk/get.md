# Näytä tapahtuma

Nayta haetun tapahtuman tiedot.

**URL** : `/api/tapahtumat/{id}`

**Metodi** : `GET`

**Oikeudet vaaditaan** : ADMIN tai USER

**Query-parametrit:** 

`id` (pakollinen): Tapahtuman yksilöivä arvo.

## Onnistunut vastaus

**Koodi** : `200 OK`

**Sisältöesimerkit** :

Tapahtuma, jonka id on 1 ja jolle on tallennettu tiedot.

```json
{
    "tapahtumaId": 1,
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
    "lippujaJaljella": 997,
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

**Sisältöesimerkit** :

Tapahtuma, jonka id on 8 ja jolle ei ole tallennettu tietoja.

```json
{
    "viesti": "Tapahtumaa ei löytynyt ID:llä 8",
    "aikaleima": "2024-11-24T22:25:18.6091578",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/tapahtumat/8",
    "virheet": {}
}
```
### Tai

**Ehto** : Jos tapahtumaId on väärässä muodossa

**Koodi** : `400 BAD REQUEST`

**Sisältöesimerkit**

Kun yritetään poistaa tapahtumaa id:llä "x".

```json
{
    "viesti": "Virheellinen arvo 'x' parametrille 'id'. Odotettu tyyppi on 'Long'",
    "aikaleima": "2024-11-24T22:25:59.4750422",
    "tilakoodi": 400,
    "tila": "Bad Request",
    "polku": "uri=/api/tapahtumat/x",
    "virheet": {}
}
```