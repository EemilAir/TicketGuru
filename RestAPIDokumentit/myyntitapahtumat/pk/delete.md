# Myyntitapahtuman poistaminen

Poistaa yksittäisen myyntitapahtuman.

**URL** : `/api/myyntitapahtumat/{id}`

**Metodi** : `DELETE`

**Oikeudet vaaditaan** : ADMIN tai USER

**Query-parametrit:** 

`id` (pakollinen): Myyntitapahtuman yksilöivä arvo.


## Onnistunut Vastaus

**Ehto** : Jos myyntitapahtuma on olemassa.

**Koodi** : `204 NO CONTENT`

**Sisältö** : `{}`

## Epäonnistunut vastaus

**Ehto** : Myyntitapahtumaa ei ole annetulla id:llä

**Koodi** : `404 NOT FOUND`

**Sisältö** : {

```json
{
    "viesti": "Myyntitapahtumaa ei löydy ID:llä 999",
    "aikaleima": "2024-11-23T21:57:08.8181558",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/myyntitapahtumat/999",
    "virheet": {}
}
```
}
