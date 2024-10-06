# Näytä myyntitapahtuma

Nayta haetun myyntitapahtuman tiedot.

**URL** : `/api/myyntitapahtumat/{id}`

**Metodi** : `GET`

**Quaery-parametrit:** 

`id` (pakollinen): Myyntitapahtuman yksilöivä arvo.

**Autentikointi vaaditaan** : EI TOISTAISEKSI

**Oikeudet vaaditaan** : EI TOISTAISEKSI

## Onnistunut vastaus

**Koodi** : `200 OK`

**Sisältöesimerkit**

Myyntitapahtuma, jonka id on 2 ja jolle on tallennettu vaaditut tiedot.

```json
{
    "myyntitapahtumaId": 2,
    "summa": 30.0,
    "maksupvm": "2024-10-06T17:06:25.424821",
    "maksutapa": {
        "maksutapaId": 2,
        "maksutapa": "Debit"
    },
    "kayttaja": {
        "kayttajaId": 2,
        "kayttajanimi": "myyja1",
        "sposti": "myyja1@ticketguru.fi",
        "salasanaHash": "salasana",
        "kayttajarooli": "MYYJA"
    }
}
```

## Epäonnistunut vastaus

**Koodi** : `404 NOT FOUND`

**Sisältöesimerkit**

Myyntitapahtuma, jonka id on 5 ja jolle ei ole tallennettu tietoja.

```json
{}
```

## Huomautukset

Autentikointi tullaan lisäämään tulevaisuudessa.

