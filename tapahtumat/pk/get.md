# Näytä tapahtuma

Nayta haetun tapahtuman tiedot.

**URL** : `/api/tapahtumat/`

**Metodi** : `GET`

**Autentikointi vaaditaan** : EI TOISTAISEKSI

**Oikeudet vaaditaan** : EI TOISTAISEKSI

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
    "aloituspvm": "2024-10-03T19:21:48.312169",
    "lopetuspvm": "2024-10-04T19:21:48.312169",
    "katuosoite": "Katuosoite3",
    "lippujaJaljella": 300,
    "osoite": {
        "osoiteId": 3,
        "postinumero": "00300",
        "postitmp": "Vantaa",
        "lipunmyyntipisteet": []
    },
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

Autentikointi tullaan lisäämään tulevaisuudessa.

