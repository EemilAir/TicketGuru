# Myyntitapahtuman poistaminen

Poistaa yksittäisen myyntitapahtuman

**URL** : `/api/myyntitapahtumat/:pk/`

**URL parametrit** : `pk=[integer]`, missä `pk` on myyntitapahtuman ID tietokannassa.

**Metodi** : `DELETE`

**Autentikointi vaaditaan** : EI TOISTAISEKSI

**Oikeudet vaaditaan** : EI TOISTAISEKSI

**Data** : `{}`

## Onnistunut Vastaus

**Ehto** : Jos myyntitapahtuma on olemassa.

**Koodi** : `204 NO CONTENT`

**Sisältö** : `{}`

## Epäonnistunut vastaus

**Ehto** : Myyntitapahtumaa ei ole annetulla id:llä

**Koodi** : `404 NOT FOUND`

**Sisältö** : `{}`

## Huomautukset

* Oikeudet ja autentikointi lisätään myöhemmin