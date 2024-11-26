# Tapahtuman poistaminen

Poistaa yksittäisen tapahtuman.

**URL** : `/api/tapahtumat/{id}`

**Metodi** : `DELETE`

**Oikeudet vaaditaan** : ADMIN

**Query-parametrit:** 

`id` (pakollinen): Tapahtuman yksilöivä arvo.


## Onnistunut Vastaus

**Ehto** : Tapahtuman poisto onnistui.

**Koodi** : `204 NO CONTENT`

**Sisältö** : `{}`

## Epäonnistunut vastaus

**Ehto** : Tapahtumaa ei löydy annetulla id:llä.

**Koodi** : `404 NOT FOUND`

**Sisältöesimerkit** : 

```json
{
    "viesti": "Tapahtumaa ei löydy ID:llä 8",
    "aikaleima": "2024-11-12T14:20:01.2268692",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/tapahtumat/8",
    "virheet": {}
}
```
### Tai

**Ehto** : Jos tapahtuman id on väärässä muodossa

**Koodi** : `400 BAD REQUEST`

**Sisältöesimerkit** :

Kun yritetään poistaa tapahtumaa id:llä "d".

```json
{
    "viesti": "Virheellinen arvo 'd' parametrille 'id'. Odotettu tyyppi on 'Long'",
    "aikaleima": "2024-11-23T22:23:38.6256599",
    "tilakoodi": 400,
    "tila": "Bad Request",
    "polku": "uri=/api/tapahtumat/d",
    "virheet": {}
}
```