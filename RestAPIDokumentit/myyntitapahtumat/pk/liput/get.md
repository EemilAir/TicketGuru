# Näytä myyntitapahtumaan liittyvät liput

Näytä haetun myyntitapahtuman liput.

**URL** : `/api/myyntitapahtumat/{id}/liput`

**Metodi** : `GET`

**Query-parametrit:** 

`id` (pakollinen): Myyntitapahtuman yksilöivä arvo.

**Autentikointi vaaditaan** : EI TOISTAISEKSI

**Oikeudet vaaditaan** : EI TOISTAISEKSI

## Onnistunut vastaus

**Koodi** : `200 OK`

**Sisältöesimerkit**

Lista lippuja, jotka liittyvät myyntitapahtumaan, jonka id on 1.

```json
[
    {
        "lippuId": 1,
        "koodi": "d9b136a2-1ba9-4610-8d97-a46a486f2b32",
        "luontiaika": "2024-10-09T09:42:17.638421",
        "lipunTila": "AKTIIVINEN",
        "lipputyyppi": {
            "lipputyyppiId": 1,
            "lipputyyppiNimi": "Normaali",
            "kuvaus": "Normaali lippu"
        }
    },
    {
        "lippuId": 2,
        "koodi": "d9c6b2f8-026c-45af-afe0-68a4c05b91e3",
        "luontiaika": "2024-10-09T09:42:17.638421",
        "lipunTila": "AKTIIVINEN",
        "lipputyyppi": {
            "lipputyyppiId": 1,
            "lipputyyppiNimi": "Normaali",
            "kuvaus": "Normaali lippu"
        }
    },
    {
        "lippuId": 3,
        "koodi": "0a81b022-5418-41e3-a247-411377dd424b",
        "luontiaika": "2024-10-09T09:42:17.638936",
        "lipunTila": "AKTIIVINEN",
        "lipputyyppi": {
            "lipputyyppiId": 2,
            "lipputyyppiNimi": "VIP",
            "kuvaus": "VIP lippu"
        }
    }
]
```

## Epäonnistunut vastaus

**Koodi** : `404 NOT FOUND`

**Sisältöesimerkit**

Myyntitapahtuma, jonka id on 5 ja jolle ei ole tallennettu lippuja.

```json
{}
```