# Näytä myyntitapahtumaan liittyvät liput

Näytä haetun myyntitapahtuman liput.

**URL** : `/api/myyntitapahtumat/{id}/liput`

**Metodi** : `GET`

**Query-parametrit:** 

`id` (pakollinen): Myyntitapahtuman yksilöivä arvo.

**Oikeudet vaaditaan** : ADMIN tai USER

## Onnistunut vastaus

**Koodi** : `200 OK`

**Sisältöesimerkit**

Lista lippuja, jotka liittyvät myyntitapahtumaan, jonka id on 1.

```json
[
    {
        "lippuId": 1,
        "koodi": "70742224-d917-4760-b2ee-d47ddbbd9a9a",
        "luontiaika": "2024-11-12T12:20:44.134598",
        "kayttoaika": null,
        "lipunTila": 1
    },
    {
        "lippuId": 2,
        "koodi": "006cc0a2-679a-40c4-be52-59ca00564c05",
        "luontiaika": "2024-11-12T12:20:44.141592",
        "kayttoaika": null,
        "lipunTila": 1
    },
    {
        "lippuId": 3,
        "koodi": "e2a58359-a1a6-44c6-8002-880aec272020",
        "luontiaika": "2024-11-12T12:20:44.148331",
        "kayttoaika": null,
        "lipunTila": 1
    }
]
```

## Epäonnistunut vastaus

**Koodi** : `404 NOT FOUND`

**Sisältöesimerkit**

Myyntitapahtuma, jonka id on 8 ja jolle ei ole tallennettu lippuja.

```json
{
    "viesti": "Myyntitapahtumaa ei löytynyt ID:llä 8",
    "aikaleima": "2024-11-12T13:36:08.3850353",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/myyntitapahtumat/8/liput",
    "virheet": {}
}
```