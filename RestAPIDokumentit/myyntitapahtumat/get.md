# Näytä myyntitapahtumat

Näytä kaikkien myyntitapahtumien tiedot tai suodata tapahtumia summan, maksutavan tai käyttäjän perusteella.

**URL** : `/api/myyntitapahtumat/`

**Metodi** : `GET`

**Oikeudet vaaditaan** : ADMIN tai USER

## Query-parametrit:
* `summa` (valinnainen): Suodatus myyntitapahtuman summan mukaan (esim `?summa=25.0`).
* `kayttajanimi` (valinnainen): Suodatus myyntitapahtuman käyttäjänimen mukaan eli myyntitapahtuman toteuttaneen käyttäjän mukaan (esim `?kayttajanimi=myyja1`).
* `maksutapa` (valinnainen): Suodatus maksutavan mukaan (esim. `?maksutapa=Käteinen`).
* `summa`- ja `maksutapa`-paremetreilla voi hakea, kuinka suuria maksutapahtumia on tehty tietyillä maksutavoilla (esim `?summa=15.0&maksutapa=Credit`).

**Esimerkit:**
* Hae myyntitapahtumat summan perusteella: `/api/myyntitapahtumat/?summa=30.0`
* Hae myyntitapahtumat käyttäjänimen (eli myyjän) perusteella: `/api/myyntitapahtumat/?kayttajanimi=myyja1`
* Hae myyntitapahtumat maksutavan perusteella: `/api/myyntitapahtumat/?maksutapa=Credit`
* Hae myyntitapahtumat summan ja maksutavan perusteella `/api/myyntitapahtumat/?summa=30.0&maksutapa=Credit`

## Onnistuneet vastaukset

**Ehto** : Käyttäjä näkee yhden tai useamman myyntitapahtuman.

**Koodi** : `200 OK`

**Sisältö** : Tässä esimerkissä käyttäjälle näytetään neljä eri myyntitapahtumaa.

```json

{
    "myyntitapahtumaId": 1,
    "summa": 100.0,
    "maksutapa": "Käteinen",
    "maksupvm": "2024-11-12T12:20:44.083611",
    "kayttajaId": 2,
    "liput": [
        {
            "koodi": "70742224-d917-4760-b2ee-d47ddbbd9a9a",
            "tapahtumaId": 1,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "006cc0a2-679a-40c4-be52-59ca00564c05",
            "tapahtumaId": 1,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "e2a58359-a1a6-44c6-8002-880aec272020",
            "tapahtumaId": 1,
            "lipputyyppi": "VIP",
            "tila": 1,
            "kayttoaika": null
        }
    ]
},
{
    "myyntitapahtumaId": 2,
    "summa": 210.0,
    "maksutapa": "Debit",
    "maksupvm": "2024-11-12T12:20:44.211686",
    "kayttajaId": 3,
    "liput": [
        {
            "koodi": "fe12f5a7-77d9-4905-9cea-730188dda16c",
            "tapahtumaId": 2,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "c76c0485-1c9f-4bbd-9ae9-10a3187d3935",
            "tapahtumaId": 2,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "f771c685-756a-4e69-aec9-1de7a5ea8915",
            "tapahtumaId": 2,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "5f0a50b3-17db-406b-a519-4457edd05bf5",
            "tapahtumaId": 2,
            "lipputyyppi": "VIP",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "c52626a1-6496-4989-bf6f-657aedda90c6",
            "tapahtumaId": 2,
            "lipputyyppi": "VIP",
            "tila": 1,
            "kayttoaika": null
        }
    ]
},
{
    "myyntitapahtumaId": 3,
    "summa": 140.0,
    "maksutapa": "Käteinen",
    "maksupvm": "2024-11-12T12:20:44.428043",
    "kayttajaId": 4,
    "liput": [
        {
            "koodi": "907fdc7c-e36c-4a4a-aabe-41932e79a807",
            "tapahtumaId": 3,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "6b118c67-00a9-4c9e-9980-fed8c3069bee",
            "tapahtumaId": 3,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "d6ea93a9-c8a5-484a-8286-9d087a7f9a2e",
            "tapahtumaId": 3,
            "lipputyyppi": "VIP",
            "tila": 1,
            "kayttoaika": null
        }
    ]
},
{
    "myyntitapahtumaId": 4,
    "summa": 115.0,
    "maksutapa": "MobilePay",
    "maksupvm": "2024-11-12T12:20:44.485409",
    "kayttajaId": 5,
    "liput": [
        {
            "koodi": "a1c6f400-5764-4ead-8c4f-0347879e9636",
            "tapahtumaId": 4,
            "lipputyyppi": "Normaali",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "e3e921bf-f932-4897-968a-f845192dba53",
            "tapahtumaId": 4,
            "lipputyyppi": "Opiskelija",
            "tila": 1,
            "kayttoaika": null
        },
        {
            "koodi": "463e8b8e-be5e-49e4-990f-489f724895b7",
            "tapahtumaId": 4,
            "lipputyyppi": "VIP",
            "tila": 1,
            "kayttoaika": null
        }
    ]
}


```

## Virhevastaukset

**Ehto**: Käyttäjä ei näe yhtään myyntitapahtumaa, joka vastaa annettuja suodatuskriteerejä.

**Koodi** : `404 NOT FOUND`

**Sisältö** : {

```json
{
    "viesti": "Myyntitapahtumia ei löytynyt",
    "aikaleima": "2024-11-12T12:57:36.3371407",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/myyntitapahtumat/",
    "virheet": {}
}
```
}

### Tai

**Ehto**: Virheellinen pyyntö (esim. virheelliset query-parametrit, kuten `api/myyntitapahtumat/summa=d`).

**Koodi** : `400 Bad Request`

**Sisältö** : {

```json
{
    "viesti": "Virheellinen arvo 'summa=d' parametrille 'id'. Odotettu tyyppi on 'Long'",
    "aikaleima": "2024-11-23T21:28:36.5744102",
    "tilakoodi": 400,
    "tila": "Bad Request",
    "polku": "uri=/api/myyntitapahtumat/summa=d",
    "virheet": {}
}
```
}