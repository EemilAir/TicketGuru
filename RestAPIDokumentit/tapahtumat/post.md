# Luo tapahtuma

Luo uusi tapahtuma

**URL** : `/api/tapahtumat/`

**Metodi** : `POST`

**Oikeudet vaaditaan** : ADMIN

**Reunaehdot**

Tapahtumalla on oltava nimi, kategoria, aloitus- ja lopetuspvm, katuosoite, LippujaJaljella, osoiteId sekä lipputyypit

```json
{
    "nimi": "Provinssi 2025",
    "kategoria": "Festivaali",
    "aloituspvm": "2025-06-25T12:00:00",
    "lopetuspvm": "2025-06-27T23:00:00",
    "katuosoite": "Törnäväntie 20",
    "lippujaJaljella": 2500,
    "osoiteId": 5,
    "lipputyypit": [
        {
            "id": 1,
            "hinta": 35.0
        },
        {
            "id": 2,
            "hinta": 70.0
        }
    ]
}
```

## Onnistunut vastaus

**Ehto** : Tapahtuman luominen onnistui

**Koodi** : `201 CREATED`

**Sisältöesimerkki**

```json
{
    "nimi": "Provinssi 2025",
    "kuvaus": null,
    "kategoria": "Festivaali",
    "aloituspvm": "2025-06-25T12:00",
    "lopetuspvm": "2025-06-27T23:00",
    "katuosoite": "Törnäväntie 20",
    "osoite": {
        "osoiteId": 5,
        "postinumero": "40100",
        "postitmp": "Jyväskylä"
    },
    "lippujaJaljella": 2500,
    "lipputyypit": [
        {
            "id": {
                "tapahtumaId": 8,
                "lipputyyppiId": 1
            },
            "nimi": "Normaali",
            "kuvaus": "Normaali lippu",
            "hinta": 35.0
        },
        {
            "id": {
                "tapahtumaId": 8,
                "lipputyyppiId": 2
            },
            "nimi": "VIP",
            "kuvaus": "VIP lippu",
            "hinta": 70.0
        }
    ]
}
```

## Epäonnistunut vastaus

**Ehto** : Jos kenttiä puuttuu

**Koodi** : `400 BAD REQUEST`

**Sisältöesimerkki**

Tapahtuma, josta puuttuu nimi

```json
{
    "viesti": "Validaatiovirhe. Tarkista syöteparametrit.",
    "aikaleima": "2024-11-12T14:15:39.8239007",
    "tilakoodi": 400,
    "tila": "Bad Request",
    "polku": "uri=/api/tapahtumat/",
    "virheet": {
        "nimi": "Tapahtuman nimi ei voi olla tyhjä"
    }
}
```