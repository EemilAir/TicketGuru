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

**Ehto** : Tapahtuman luominen onnistui.

**Koodi** : `201 CREATED`

**Sisältöesimerkki**

```json
{
    "nimi": "Provinssi 2025",
    "kuvaus": "Provinssi 2025 -festivaali",
    "kategoria": "Musiikki",
    "aloituspvm": "2024-12-01T18:00:00",
    "lopetuspvm": "2024-12-01T22:00:00",
    "katuosoite": "Törnäväntie 20",
    "osoiteId": 3,
    "lippujaJaljella": 200,
    "lipputyypit": [
        {
            "id": 1,
            "hinta": 50.0
        },
        {
            "id": 2,
            "hinta": 100.0
        }
    ]
}

```

## Epäonnistunut vastaus

**Ehto** : Jos vaadittuja parametreja puuttuu.

**Koodi** : `400 BAD REQUEST`

**Sisältöesimerkki**

Tapahtuma, josta puuttuu parametri nimi.

```json
{
    "viesti": "Validaatiovirhe. Tarkista syöteparametrit.",
    "aikaleima": "2024-11-23T22:20:29.3080732",
    "tilakoodi": 400,
    "tila": "Bad Request",
    "polku": "uri=/api/tapahtumat/",
    "virheet": {
        "nimi": "Tapahtuman nimi ei voi olla tyhjä"
    }
}
```