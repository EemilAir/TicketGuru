# Näytä lippu koodin perusteella

Näytä lippu lipun koodin perusteella.

**URL** : `/api/liput?koodi={koodi}`

**Metodi** : `GET`

**Query-parametrit:** 

`koodi` (pakollinen): esim: 0b2e3362-d3cd-42ca-93ed-8e45a70d1140.

## Onnistunut vastaus

**Ehto** : Lippu löytyy annetulla koodilla

**Koodi** : `200 OK`

**Sisältö** :

Lipun koodiin on annettu seuraava arvo "0b2e3362-d3cd-42ca-93ed-8e45a70d1140"

Vastaus:

```json
{
    "lippuId": 1,
    "koodi": "0b2e3362-d3cd-42ca-93ed-8e45a70d1140",
    "tila": 1,
    "kayttoaika": null,
    "lipputyyppiId": 1,
    "lipputyyppiNimi": "Normaali",
    "tapahtumaId": 1,
    "tapahtumaNimi": "Tuska Festival 2025"
}
```

## Epäonnistunut vastaus

**Ehto** : Annetulla koodilla ei ole vastaavaa lippua

**Koodi** : `404 NOT FOUND`

**Sisältö**

```json
{
    "viesti": "Lippua ei löytynyt koodilla: 0b2e3362-d3cd-42ca-93ed-8e45a73fuh3",
    "aikaleima": "2024-11-12T15:03:51.3898268",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/liput",
    "virheet": {}
}
```