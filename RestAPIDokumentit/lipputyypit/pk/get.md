# Näytä lipputyyppi

Nayta haetun lipputyypin tiedot.

**URL** : `/api/lipputyypit/{id}`

**Metodi** : `GET`

**Oikeudet vaaditaan** : ADMIN tai USER

**Query-parametrit:** 

`id` (pakollinen): lipputyypin yksilöivä arvo.

## Onnistunut vastaus

**Koodi** : `200 OK`

**Sisältöesimerkit** :

Lipputyyppi, jonka id on 1 ja jolle on tallennettu tiedot.

```json
{
    "id": 1,
    "lipputyyppiNimi": "Normaali",
    "kuvaus": "Normaali lippu"
}
```

## Epäonnistunut vastaus

**Koodi** : `404 NOT FOUND`

**Sisältöesimerkit** :

Lipputyyppi, jonka id on 99 ja jolle ei ole tallennettu tietoja.

```json
{
    "viesti": "Lipputyyppiä ei löytynyt ID:llä 99",
    "aikaleima": "2024-12-11T10:40:23.6497303",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/lipputyypit/99",
    "virheet": {}
}
```
### Tai

**Ehto** : Jos id on väärässä muodossa

**Koodi** : `400 BAD REQUEST`

**Sisältöesimerkit** :

Kun yritetään poistaa lipputyyppiä id:llä "f".

```json
{
    "viesti": "Virheellinen arvo 'f' parametrille 'id'. Odotettu tyyppi on 'Long'",
    "aikaleima": "2024-12-11T10:40:36.5560975",
    "tilakoodi": 400,
    "tila": "Bad Request",
    "polku": "uri=/api/lipputyypit/f",
    "virheet": {}
}
```