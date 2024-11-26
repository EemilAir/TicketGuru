# Näytä myyntitapahtuma

Nayta haetun myyntitapahtuman tiedot.

**URL** : `/api/myyntitapahtumat/{id}`

**Metodi** : `GET`

**Oikeudet vaaditaan** : ADMIN tai USER

**Query-parametrit:** 

`id` (pakollinen): Myyntitapahtuman yksilöivä arvo.


## Onnistunut vastaus

**Koodi** : `200 OK`

**Sisältöesimerkit** :

Myyntitapahtuma, jonka id on 2 ja jolle on tallennettu vaaditut tiedot.

```json
{
    "myyntitapahtumaId": 2,
    "summa": 210.0,
    "maksutapa": "Debit",
    "maksupvm": "2024-11-12T12:20:44.211686",
    "kayttajaId": 3,
    "liput": [
        {
            "koodi": "fe12f5a7-77d9-4905-9cea-730188dda16c",
            "tapahtumaId": 2,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "c76c0485-1c9f-4bbd-9ae9-10a3187d3935",
            "tapahtumaId": 2,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "f771c685-756a-4e69-aec9-1de7a5ea8915",
            "tapahtumaId": 2,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "5f0a50b3-17db-406b-a519-4457edd05bf5",
            "tapahtumaId": 2,
            "lipputyyppi": "VIP",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "c52626a1-6496-4989-bf6f-657aedda90c6",
            "tapahtumaId": 2,
            "lipputyyppi": "VIP",
            "tila": 1,
            "kayttoaika": null
        }
    ]
}
```

## Epäonnistunut vastaus

**Koodi** : `404 NOT FOUND`

**Sisältöesimerkit** :

Myyntitapahtuma, jonka id on 8 ja jolle ei ole tallennettu tietoja.

```json
{
    "viesti": "Myyntitapahtumaa ei löytynyt ID:llä 8",
    "aikaleima": "2024-11-12T13:10:31.483327",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/myyntitapahtumat/8",
    "virheet": {}
}
```

### Tai

**Ehto** : Jos myyntitapahtumaId on väärässä muodossa

**Koodi** : `400 BAD REQUEST`

**Sisältöesimerkit** :

Kun yritetään hakea myyntitapahtumaa id:llä "d".

```json
{
    "viesti": "Virheellinen arvo 'd' parametrille 'id'. Odotettu tyyppi on 'Long'",
    "aikaleima": "2024-11-23T21:49:48.6208161",
    "tilakoodi": 400,
    "tila": "Bad Request",
    "polku": "uri=/api/myyntitapahtumat/f",
    "virheet": {}
}
```