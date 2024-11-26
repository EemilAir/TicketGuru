# Päivitä lipun tila

Päivitä lipun tila käytetyksi tai käytetyn peruutus.

**URL** : `/api/liput/{id}`

**Metodi** : `PATCH`

**Oikeudet vaaditaan** : ADMIN tai USER

**Sisältöesimerkit**

```json
{
    "tila": 0
}
```

## Onnistuneet Vastaukset

**Ehto** : Lipun tila muuttui

**Koodi** : `200 OK`

**Sisältöesimerkit** :
Lippu, jonka tila arvoksi on muutettu 0

Ennen muokkausta:
```json
{
    "koodi": "0b2e3362-d3cd-42ca-93ed-8e45a70d1140",
    "tapahtumaId": 1,
    "lipputyyppi": "Normaali",
    "tila": 1,
    "kayttoaika": null
}
```

Muokkauksen jälkeen:
```json
{
    "koodi": "0b2e3362-d3cd-42ca-93ed-8e45a70d1140",
    "tapahtumaId": 1,
    "lipputyyppi": "Normaali",
    "tila": 0,
    "kayttoaika": "2024-11-12T15:21:45.6731703"
}
```

## Epäonnistunut vastaus

**Ehto** : Lipun tilaksi on annettu virheellinen arvo.

**Koodi** : `400 BAD REQUEST`

**Sisältöesimerkit** : 

Lipun tila arvoksi on annettu 2

```json
{
    "viesti": "Virheellinen tila: 2. Tilan tulee olla muodossa 0 tai 1",
    "aikaleima": "2024-11-12T15:22:33.1635709",
    "tilakoodi": 400,
    "tila": "Bad Request",
    "polku": "uri=/api/liput/1",
    "virheet": {}
}
```
### Tai

**Ehto** : Jos lippua ei löydy.

**Koodi** : `404 NOT FOUND`

**Sisältöesimerkit** :

Kun yritetään hakea lippua id:llä 9999.

```json
{
    "viesti": "Lippua ei löytynyt ID:llä 9999",
    "aikaleima": "2024-11-26T23:33:01.1406661",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/liput/9999",
    "virheet": {}
}
```