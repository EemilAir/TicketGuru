# Luo myyntitapahtuma

Luo uusi myyntitapahtuma, jossa on liput

**URL** : `/api/myyntitapahtumat/`

**Metodi** : `POST`

**Autentikointi vaaditaan** : EI TOISTAISEKSI

**Oikeudet vaaditaan** : EI TOISTAISEKSI

**Reunaehdot**

Myyntitapahtumalla on oltava maksutapaId, maksupvm, kayttajaId, ja liput joissa tapahtumaId, lipputyyppiId ja maara

```json
{
    "maksutapaId": 1,
    "maksupvm": "2024-10-01T12:00:00",
    "kayttajaId": 2,
    "liput": [
            {
            "tapahtumaId": 1,
            "lipputyyppiId": 1,
            "maara": 2
            },
            {
            "tapahtumaId": 1,
            "lipputyyppiId": 2,
            "maara": 1
        }
    ]
}
```

## Onnistunut vastaus

**Ehto** : Myyntitapahtuman ja lippujen luominen onnistui

**Koodi** : `201 CREATED`

**Sisältöesimerkki**

Myyntitapahtuma, jolle on annettu ylläolevan JSON:in tiedot.

```json
{
    "myyntitapahtumaId": 5,
    "summa": 100.0,
    "maksutapa": "Käteinen",
    "maksupvm": "2024-10-08T21:23:04.8335254",
    "kayttajaId": 2,
    "liput": [
        {
            "koodi": "4bce29f1-82fb-4500-9f1c-879009d78124",
            "tapahtumaId": 1,
            "lipputyyppi": "Normaali",
            "tila": "AKTIIVINEN"
        },
        {
            "koodi": "d7c32be0-c05d-45a2-808e-322ded6dffba",
            "tapahtumaId": 1,
            "lipputyyppi": "Normaali",
            "tila": "AKTIIVINEN"
        },
        {
            "koodi": "0df59b60-2768-4dae-994f-0771e5e4cef4",
            "tapahtumaId": 1,
            "lipputyyppi": "VIP",
            "tila": "AKTIIVINEN"
        }
    ]
}
```

## Epäonnistunut vastaus

**Ehto** : Jos lipputyyppi ei kuulu tapahtumaan.

```json
{
    "maksutapaId": 1,
    "maksupvm": "2024-10-01T12:00:00",
    "kayttajaId": 2,
    "liput": [
            {
            "tapahtumaId": 1,
            "lipputyyppiId": 3, //tapahtumassa on vain lipputyyppiId 1 ja 2
            "maara": 2
            },
            {
            "tapahtumaId": 1,
            "lipputyyppiId": 3,
            "maara": 1
        }
    ]
}
```

**Koodi** : `400 BAD REQUEST`

**Sisältö** : `Lipputyypin tulee löytyä tapahtumasta`


### Tai

**Ehto** : Jos käyttäjää ei löydy annetulla kayttajaId:llä


```json
{
    "kayttajaId": 10 // Oletetaan ettei löydy käyttäjää, jonka id on 10
}
```

**Koodi** : `404 NOT FOUND`

**Sisältö** : `Käyttäjää ei löydy tietokannasta`
