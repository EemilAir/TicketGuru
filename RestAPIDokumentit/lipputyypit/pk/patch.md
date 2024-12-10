# Päivitä lipputyypin osaa

Muokkaa yksittäistä lipputyyppiä.

**URL** : `/api/lipputyypit/{id}`

**Metodi** : `PATCH`

**Oikeudet vaaditaan** : ADMIN

**Sisältöesimerkit**

```json
{
    "kuvaus": "Perhelippu 2 aikuista ja 3 lasta"
}
```

## Onnistuneet Vastaukset

**Ehto** : lipputyypin muokkaus onnistui

**Koodi** : `200 OK`

**Sisältöesimerkki** :
Lipputyyppi 4, jonka kuvaus muutetaan "Perhelippu 2 aikuista ja 2 lasta" -> "Perhelippu 2 aikuista ja 3 lasta". 

Ennen muokkausta:

```json
{
    "id": 4,
    "lipputyyppiNimi": "Perhelippu",
    "kuvaus": "Perhelippu 2 aikuista ja 2 lasta"
},
```

Muokkauksen jälkeen:

```json
{
    "id": 4,
    "lipputyyppiNimi": "Perhelippu",
    "kuvaus": "Perhelippu 2 aikuista ja 3 lasta"
}
```

## Epäonnistunut vastaus

**Ehto** : Lipputyyppiä ei löydy.

**Koodi** : `404 NOT FOUND`

**Sisältöesimerkit** : 


```json
{
    "viesti": "Lipputyyppiä ei löydy ID:llä 123",
    "aikaleima": "2024-11-26T23:28:33.0815258",
    "tilakoodi": 404,
    "tila": "Not Found",
    "polku": "uri=/api/lipputyypit/123",
    "virheet": {}
}
```