# Näytä lipputyypit

Näytä kaikki lipputyypit.

**URL** : `/api/lipputyypit/`

**Metodi** : `GET`

**Oikeudet vaaditaan** : ADMIN tai USER


## Onnistuneet vastaukset

**Ehto** : Käyttäjä hakee kaikki lipputyypit.

**Koodi** : `200 OK`

**Sisältöesimerkit** : 

Tässä esimerkissä käyttäjä näkee kaikki lipputyypit.

```json
[
    {
        "id": 1,
        "lipputyyppiNimi": "Normaali",
        "kuvaus": "Normaali lippu"
    },
    {
        "id": 2,
        "lipputyyppiNimi": "VIP",
        "kuvaus": "VIP lippu"
    },
    {
        "id": 3,
        "lipputyyppiNimi": "Opiskelija",
        "kuvaus": "Alennettu opiskelijalippu"
    },
    {
        "id": 4,
        "lipputyyppiNimi": "Perhelippu",
        "kuvaus": "Perhelippu 2 aikuista ja 2 lasta"
    },
    {
        "id": 5,
        "lipputyyppiNimi": "Eläkeläinen",
        "kuvaus": "Alennettu eläkeläislippu"
    }
]
```

