# Näytä maksutavat

Näytä kaikki maksutavat.

**URL** : `/api/maksutavat/`

**Metodi** : `GET`

**Oikeudet vaaditaan** : ADMIN tai USER


## Onnistuneet vastaukset

**Ehto** : Käyttäjä hakee kaikki maksutavat.

**Koodi** : `200 OK`

**Sisältöesimerkit** : 

Tässä esimerkissä käyttäjä näkee kaikki maksutavat.

```json
[
    {
        "maksutapaId": 3,
        "maksutapaNimi": "Credit",
        "myyntitapahtumat": null
    },
    {
        "maksutapaId": 2,
        "maksutapaNimi": "Debit",
        "myyntitapahtumat": null
    },
    {
        "maksutapaId": 1,
        "maksutapaNimi": "Käteinen",
        "myyntitapahtumat": null
    },
    {
        "maksutapaId": 4,
        "maksutapaNimi": "MobilePay",
        "myyntitapahtumat": null
    },
    {
        "maksutapaId": 6,
        "maksutapaNimi": "Ovella",
        "myyntitapahtumat": null
    },
    {
        "maksutapaId": 5,
        "maksutapaNimi": "PayPal",
        "myyntitapahtumat": null
    }
]
```

