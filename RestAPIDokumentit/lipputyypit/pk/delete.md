# Lipputyypin poistaminen

Poistaa yksittäisen lipputyypin.

**URL** : `/api/lipputyypit/{id}`

**Metodi** : `DELETE`

**Oikeudet vaaditaan** : ADMIN

**Query-parametrit:** 

`id` (pakollinen): Lipputyypin yksilöivä arvo.


## Onnistunut Vastaus

**Ehto** : Lipputyypin poisto onnistui.

**Koodi** : `204 NO CONTENT`

**Sisältö** : `{}`

## Epäonnistunut vastaus

**Ehto** : Lipputyyppiä ei löydy annetulla id:llä.

**Koodi** : `404 NOT FOUND`

**Sisältö** : 

```json
{
    "viesti": "Lipputyyppiä ei löydy ID:llä 999",
    "aikaleima": "2024-11-26T23:17:14.3193681",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/lipputyypit/999",
    "virheet": {}
}
```
### Tai

**Ehto** : Jos lipputyypin id on väärässä muodossa

**Koodi** : `400 BAD REQUEST`

**Sisältöesimerkit**

Kun yritetään poistaa lipputyyppiä id:llä "x".

```json
{
    "viesti": "Virheellinen arvo 'x' parametrille 'id'. Odotettu tyyppi on 'Long'",
    "aikaleima": "2024-11-26T23:17:29.6514833",
    "tilakoodi": 400,
    "tila": "Bad Request",
    "polku": "uri=/api/lipputyypit/x",
    "virheet": {}
}
```