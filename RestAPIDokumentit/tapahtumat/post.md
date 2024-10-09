# Luo tapahtuma

Luo uusi tapahtuma

**URL** : `/api/tapahtumat/`

**Metodi** : `POST`

**Autentikointi vaaditaan** : EI

**Oikeudet vaaditaan** : Ei

**Reunaehdot**

Tapahtumalla on oltava id, nimi, aloitus- ja lopetuspvm, katuosoite sekä osoite id

```json
{
    "tapahtumaId": 4,
    "nimi": "Tapahtuma 4",
    "aloituspvm": "2024-11-01T19:33:59.697881",
    "lopetuspvm": "2024-11-02T19:33:59.697881",
    "katuosoite": "Osoite 4",
    "osoite": {
            "osoiteId": 4,
            "postinumero": "00400",
            "postitmp": "Turku"
        },
}
```

## Onnistunut vastaus

**Ehto** : Tapahtuman luominen onnistui

**Koodi** : `201 CREATED`

**Sisältöesimerkki**

Tapahtuma, jolle annetaan id:ksi 4 ja johon on täytetty kaikki kentät.

```json
{
    "tapahtumaId": 4,
    "nimi": "Tapahtuma 4",
    "kuvaus": "Kuvaus 4",
    "kategoria": "Kategoria 4",
    "aloituspvm": "2024-11-01T19:33:59.697881",
    "lopetuspvm": "2024-11-02T19:33:59.697881",
    "katuosoite": "Osoite 4",
    "lippujaJaljella": 400,
    "osoite": {
            "osoiteId": 4,
            "postinumero": "00400",
            "postitmp": "Turku"
        },
}
```




## Epäonnistunut vastaus

**Ehto** : Jos tapahtuma on jo olemassa tapahtumissa

**Koodi** : `409 CONFLICT`

**Sisältö** : `{}`



### Tai

**Ehto** : Jos kenttiä puuttuu

**Koodi** : `400 BAD REQUEST`

**Sisältöesimerkki**

Tapahtuma, josta puuttuu id

```json
{
    "nimi": "Tapahtuma 5",
    "kuvaus": "Kuvaus 5",
    "kategoria": "Kategoria 5",
    "aloituspvm": "2024-11-02T19:33:59.697881",
    "lopetuspvm": "2024-11-03T19:33:59.697881",
    "katuosoite": "Osoite 5",
    "lippujaJaljella": 500,
    "osoite": {
            "osoiteId": 5,
            "postinumero": "00500",
            "postitmp": "Kuopio"
        },
}
```