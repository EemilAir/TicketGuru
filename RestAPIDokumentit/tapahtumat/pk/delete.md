# Tapahtuman poistaminen

Poistaa yksittäisen tapahtuman.

**URL** : `/api/events/:pk/`

**URL Parametrit** : `pk=[integer]`, missä `pk` on tapahtuman ID tietokannassa.

**Metodi** : `DELETE`

**Oikeudet vaaditaan** : ADMIN

**Data** : `{}`

## Onnistunut Vastaus

**Ehto** : Tapahtuman poisto onnistui.

**Koodi** : `204 NO CONTENT`

**Sisältö** : `{}`

## Epäonnistunut vastaus

**Ehto** : Tapahtumaa ei löydy annetulla id:llä.

**Koodi** : `404 NOT FOUND`

**Sisältö** : {

```json
[
    {
        "viesti": "Tapahtumaa ei löydy ID:llä 8",
        "aikaleima": "2024-11-12T14:20:01.2268692",
        "tilakoodi": 404,
        "tila": "Not Found",
        "polku": "uri=/api/tapahtumat/8",
        "virheet": {}
    }
]
```
}