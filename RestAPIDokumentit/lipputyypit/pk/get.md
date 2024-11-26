# TODO

<!--
# Näytä lipputyyppi

Nayta haetun lipputyyppin tiedot.

**URL** : `/api/lipputyyppit/{id}`

**Metodi** : `GET`

**Oikeudet vaaditaan** : ADMIN tai USER

**Query-parametrit:** 

`id` (pakollinen): lipputyypin yksilöivä arvo.

## Onnistunut vastaus

**Koodi** : `200 OK`

**Sisältöesimerkit** :

Lipputyyppi, jonka id on 1 ja jolle on tallennettu tiedot.

```json

```

## Epäonnistunut vastaus

**Koodi** : `404 NOT FOUND`

**Sisältöesimerkit** :

Lipputyyppi, jonka id on 8 ja jolle ei ole tallennettu tietoja.

```json

```
### Tai

**Ehto** : Jos lipputyypin id on väärässä muodossa

**Koodi** : `400 BAD REQUEST`

**Sisältöesimerkit**

Kun yritetään poistaa lipputyyppiä id:llä "x".

```json

```
-->