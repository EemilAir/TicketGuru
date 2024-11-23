# Luo myyntitapahtuma

Luo uusi myyntitapahtuma, joka sisältää ostetut liput listana.

**URL** : `/api/myyntitapahtumat/`

**Metodi** : `POST`

**Oikeudet vaaditaan** : ADMIN tai USER

**Reunaehdot**

Myyntitapahtumalla on oltava maksutapaId, maksupvm, kayttajaId, ja liput joissa tapahtumaId, lipputyyppiId ja maara

```json
{
    "maksutapaId": 1,
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
    "myyntitapahtumaId": 8,
    "summa": 100.0,
    "maksutapa": "Käteinen",
    "maksupvm": "2024-11-12T13:15:58.6666105",
    "kayttajaId": 2,
    "liput": [
        {
            "koodi": "cb682c31-a674-4d1a-bb35-3db31ba6b842",
            "tapahtumaId": 1,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "f9dc965d-09e7-4567-8b44-0f81b7e62417",
            "tapahtumaId": 1,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "a685d8ca-6f3d-4029-b4fc-d8d4e6875f74",
            "tapahtumaId": 1,
            "lipputyyppi": "VIP",
            "tila": 1,
            "kayttoaika": null
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

**Sisältö** : {

```json
{
    "viesti": "Lipputyyppi ei kuulu tapahtumaan",
    "aikaleima": "2024-11-12T13:20:38.9804115",
    "tilakoodi": 400,
    "tila": "Bad Request",
    "polku": "uri=/api/myyntitapahtumat/",
    "virheet": {}
}
```
}

### Tai

**Ehto** : Jos käyttäjää ei löydy annetulla kayttajaId:llä


```json
{
    "maksutapaId": 1,
    "maksupvm": "2024-10-01T12:00:00",
    "kayttajaId": 10,
    "liput": [
            {
            "tapahtumaId": 1,
            "lipputyyppiId": 2,
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

**Koodi** : `404 NOT FOUND`

**Sisältö** : 

```json
{
    "viesti": "Käyttäjää ei löydy",
    "aikaleima": "2024-11-12T13:24:11.7406636",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/myyntitapahtumat/",
    "virheet": {}
}
```

### Tai

**Ehto** : Jos tapahtumaa ei löydy annetulla tapahtumaId:llä


```json
{
    "maksutapaId": 1,
    "maksupvm": "2024-10-01T12:00:00",
    "kayttajaId": 1,
    "liput": [
            {
            "tapahtumaId": 999,
            "lipputyyppiId": 2,
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

**Koodi** : `404 NOT FOUND`

**Sisältö** : 

```json
{
    "viesti": "Tapahtumaa ei löydy",
    "aikaleima": "2024-11-23T21:35:54.9947078",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/myyntitapahtumat/",
    "virheet": {}
}
```

### Tai

**Ehto** : Jos maksutapaa ei löydy annetulla maksutapaId:llä


```json
{
    "maksutapaId": 99,
    "kayttajaId": 1,
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

**Koodi** : `404 NOT FOUND`

**Sisältö** : {

```json
{
    "viesti": "Maksutapaa ei löydy",
    "aikaleima": "2024-11-23T21:47:20.9895224",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/myyntitapahtumat/",
    "virheet": {}
}
```
}
