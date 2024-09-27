# Nayta tapahtuma

Nayta haetun tapahtuman tiedot.

**URL** : `/api/tapahtumat/`

**Metodi** : `GET`

**Autentikointi vaaditaan** : EI

**Oikeudet vaaditaan** : Ei

## Onnistunut vastaus

**Koodi** : `200 OK`

**Sisältöesimerkit**

Tapahtuma, jonka id on 3 ja jolle on tallennettu tiedot.

```json
{
    "tapahtumaId": 3,
    "nimi": "Tapahtuma 3",
    "kuvaus": "Kuvaus 3",
    "kategoria": "Kategoria 3",
    "aloituspvm": "2024-10-02T19:33:59.697881",
    "lopetuspvm": "2024-10-03T19:33:59.697881",
    "katuosoite": "Osoite 3",
    "lippujaJaljella": 300,
    "osoite": null,
    "liput": [],
    "tapahtumanLipputyypit": []
}
```




## Epäonnistunut vastaus

**Koodi** : `404 NOT FOUND`

**Sisältöesimerkit**

Tapahtuma, jonka id on 5 ja jolle on tallennettu pakolliset tiedot nimi, alkupäivämäärä, loppupäivämäärä, sekä osoitetiedot.

```json
{}
```

## Huomautukset


