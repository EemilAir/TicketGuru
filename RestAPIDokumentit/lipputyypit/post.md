# Luo lipputyyppi

Luo uusi lipputyyppi.

**URL** : `/api/lipputyypit/`

**Metodi** : `POST`

**Oikeudet vaaditaan** : ADMIN

**Reunaehdot**

Lipputyypillä on oltava nimi ja kuvaus.

```json
{
  "lipputyyppiNimi": "Peruslippu",
  "kuvaus": "Normaali pääsylippu tapahtumaan",
}

```

## Onnistunut vastaus

**Ehto** : Lipputyypin luominen onnistui.

**Koodi** : `201 CREATED`

**Sisältöesimerkit**

```json
{

  "lipputyyppiNimi": "Peruslippu",
  "kuvaus": "Normaali pääsylippu tapahtumaan",
}


```

## Epäonnistunut vastaus

**Ehto** : Jos vaadittuja parametreja puuttuu.

**Koodi** : `400 BAD REQUEST`

**Sisältöesimerkit**

Lipputyyppi, josta puuttuu parametri, tässä esimerkissä lipputyyppiNimi.

```json
{
    "viesti": "Lipputyypin nimi ei voi olla tyhjä",
    "aikaleima": "2024-11-26T23:13:59.1553649",
    "tilakoodi": 400,
    "tila": "Bad Request",
    "polku": "uri=/api/lipputyypit/",
    "virheet": {}
}
```