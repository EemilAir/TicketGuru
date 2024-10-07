# Luo myyntitapahtuma

Luo uusi myyntitapahtuma

**URL** : `/api/myyntitapahtumat/`

**Metodi** : `POST`

**Autentikointi vaaditaan** : EI TOISTAISEKSI

**Oikeudet vaaditaan** : EI TOISTAISEKSI

**Reunaehdot**

Myyntitapahtumalla on oltava kayttajaId

```json
{
    "kayttajaId": 2
}
```

## Onnistunut vastaus

**Ehto** : Myyntitapahtuman luominen onnistui

**Koodi** : `201 CREATED`

**Sisältöesimerkki**

Tapahtuma, jolle annetaan kayttajaId:ksi 2

```json
{
    "id": 5,
    "summa": 0.0,
    "maksupvm": "2024-10-07T05:45:35.2463842",
    "maksutapaId": null,
    "kayttajaId": 2
}
```

## Epäonnistunut vastaus

**Ehto** : Jos kayttajaId:tä ei ole annettu

```json
{
    "kayttajaId": ""
}
```

**Koodi** : `400 BAD REQUEST`

**Sisältö** : `kayttajaId vaaditaan`


### Tai

**Ehto** : Jos käyttäjää ei löydy annetulla kayttajaId:llä


```json
{
    "kayttajaId": 10 // Oletetaan ettei löydy käyttäjää, jonka id on 10
}
```

**Koodi** : `404 NOT FOUND`

**Sisältö** : `Käyttäjää ei löydy tietokannasta`