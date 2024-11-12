# Myyntitapahtuman poistaminen

Poistaa yksittäisen myyntitapahtuman

**URL** : `/api/myyntitapahtumat/:pk/`

**URL parametrit** : `pk=[integer]`, missä `pk` on myyntitapahtuman ID tietokannassa.

**Metodi** : `DELETE`

**Oikeudet vaaditaan** : ADMIN tai USER

**Data** : `{}`

## Onnistunut Vastaus

**Ehto** : Jos myyntitapahtuma on olemassa.

**Koodi** : `204 NO CONTENT`

**Sisältö** : `{}`

## Epäonnistunut vastaus

**Ehto** : Myyntitapahtumaa ei ole annetulla id:llä

**Koodi** : `404 NOT FOUND`

**Sisältö** : {

```json
[
    {
        "viesti": "Myyntitapahtumaa ei löydy ID:llä 8",
        "aikaleima": "2024-11-12T13:29:21.2306056",
        "tilakoodi": 404,
        "tila": "Not Found",
        "polku": "uri=/api/myyntitapahtumat/8",
        "virheet": {}
    }
]
```
}
