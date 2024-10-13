# Tapahtuman poistaminen

Poistaa yksittäisen tapahtuman.

**URL** : `/api/events/:pk/`

**URL Parametrit** : `pk=[integer]`, missä `pk` on tapahtuman ID tietokannassa.

**Metodi** : `DELETE`

**Autentikointi vaaditaan** : EI TOISTAISEKSI

**Oikeudet vaaditaan** : EI TOISTAISEKSI

**Data** : `{}`

## Onnistunut Vastaus

**Ehto** : Tapahtuman poisto onnistui.

**Koodi** : `200 OK`

**Sisältö** : `{}`


## Huomautukset

* Oikeudet ja autentikointi lisätään, kun käyttäjät lisätään.