# Päivitä Nykyinen Tapahtuma

Muokkaa yksittäisen tapahtuman.

**URL** : `/api/tapahtumat/id`

**Metodi** : `PUT`

**Autentikointi vaaditaan** : EI TOISTAISEKSI

**Oikeudet vaaditaan** : EI TOISTAISEKSI

**Reunaehdot**

Tapahtumalla on oltava nimi, kuvaus, kategoria aloitus- ja lopetuspvm, katuosoite, lippujaJaljella, osoite (osoiteId, postinumero ja postitmp), liput ja tapahtumanLipputyypit.

**Data esimerkki**

```json
{
    "nimi": "Tapahtuma 1",
    "kuvaus": "Kuvaus 1",
    "kategoria": "Kategoria 1",
    "aloituspvm": "2024-10-02T20:26:52.207407",
    "lopetuspvm": "2024-10-03T20:26:52.207407",
    "katuosoite": "Katuosoite1",
    "lippujaJaljella": 100,
    "osoite": {
        "osoiteId": 1,
        "postinumero": "00100",
        "postitmp": "Helsinki"
    },
    "liput": [],
    "tapahtumanLipputyypit": []
}
```

## Onnistuneet Vastaukset

**Ehto** : Tapahtuman muokkaus onnistui

**Koodi** : `200 OK`

**Sisältöesimerkki** :
Tapahtuma 1, jonka tietoja on päättetty muokkaa. Muokatussa tapahtumassa on päivitetyt tiedot aiemmin olleesseen "Tapahtuma 1" nähden.

Ennen muokkausta:
```json
{
    "nimi": "Tapahtuma 1",
    "kuvaus": "Kuvaus 1",
    "kategoria": "Kategoria 1",
    "aloituspvm": "2024-10-02T20:26:52.207407",
    "lopetuspvm": "2024-10-03T20:26:52.207407",
    "katuosoite": "Katuosoite1",
    "lippujaJaljella": 100,
    "osoite": {
        "osoiteId": 1,
        "postinumero": "00100",
        "postitmp": "Helsinki"
    },
    "liput": [],
    "tapahtumanLipputyypit": []
}
```

Muokkauksen jälkeen:
```json
{
    "nimi": "Muokattu tapahtuma 1",
    "kuvaus": "Muokattu kuvaus 1",
    "kategoria": "Muokattu kategoria 1",
    "aloituspvm": "2024-10-02T20:26:52.207407",
    "lopetuspvm": "2024-10-03T20:26:52.207407",
    "katuosoite": "Muokkausosoite",
    "lippujaJaljella": 500,
    "osoite": {
        "osoiteId": 2,
        "postinumero": "00200",
        "postitmp": "Espoo"
    },
    "liput": [],
    "tapahtumanLipputyypit": []
}
```