# Tapahtuma End-to-End Testit

## Tapahtuman luonti

### Testin Tavoite
Tarkistaa, että käyttäjä voi onnistuneesti luoda tapahtuman järjestelmän läpi, mukaan lukien:
- Frontend-lomake
- Backend REST API
- Tietokannan päivitys

### Testin Vaiheet
1. **Luo tapahtupa**
    - Avaa sovellus selaimessa.
    - Kirjaudu tunnuksilla `admin/admin321`.
    - Navigoi kohtaan "Tapahtumat - Uusi tapahtuma".
    - Uusi ikkuna avautuu tapahtuman luonnille. Täytä kentät seuraavasti:
        - Tapahtuman nimi: Tuska
        - Tapahtuman kuvaus: Metallimusiikkiin keskittyvä festivaali Helsingin Suvilahdessa.
        - Tapahtuman kategoria: Festivaali
        - Aloituspäivämäärä: 06/27/2025, 12:00 PM
        - Lopetuspäivämäärä: 06/29/2025, 11:59 PM
        - Katuosoite: Suvilahden tapahtumakenttä
        - Lippuja jäljellä: 50000
        - Osoite: 00540 Helsinki
        - Lipputyypit: 
            - Normaali
                - Hinta: 239
            - VIP
                - Hinta: 389
    - Avautuu uusi ikkuna, jossa tulisi näkyä uusi tapahtuma listattuna omana korttinaan.

2. **Varmista API-vastaus**
   - Varmista esimerkiksi Developer Toolsilla selaimessa:
     - HTTP-status: `201 CREATED`
     - JSON-vastaus sisältää oikeat kentät:
       ```json
        {
            "tapahtumaId":10,
            "nimi":"Tuska",
            "kuvaus":"Metallimusiikkiin keskittyvä festivaali Helsingin Suvilahdessa.",
            "kategoria":"Festivaali",
            "aloituspvm":"2025-06-27T12:00",
            "lopetuspvm":"2025-06-29T11:59",
            "katuosoite":"Suvilahden tapahtumakenttä",
            "osoite":
            {
                "osoiteId":1,"postinumero":"00540","postitmp":"Helsinki"
            },
                "lippujaJaljella":50000,
                "lipputyypit":
            [
                {
                    "id":
                        {"tapahtumaId":10,"lipputyyppiId":1},
                    "nimi":"Normaali",
                    "kuvaus":"Normaali lippu",
                    "hinta":239.0
                },
                {
                    "id":
                        {"tapahtumaId":10,"lipputyyppiId":2},
                    "nimi":"VIP",
                    "kuvaus":"VIP lippu",
                    "hinta":389.0
                }
            ]
        }
       ```

3. **Tarkista tietokanta**
   - Aja Postmanilla GET request varmistaaksesi, että data tallentui tietokantaan. 
    Sovellus näyttää uuden tapahtuman id:n otsikossa, kun tapahtuma luodaan:
    `/api/tapahtumat/{id}`

4. **Tarkista näkyvyys sovelluksessa**
   - Siirry Hallintapaneeliin sovelluksen ylävalikosta.
   - Valitse "Tapahtumat - Kaikki tapahtumat"
   - Etsi tapahtuma listasta tai hae nimellä hakukentän avulla.

    **Tai**
    - Valitse sovelluksen ylävalikosta "Tapahtumat - Kaikki tapahtumat".
    - Etsi tapahtuma listasta tai hae nimellä hakukentän avulla.


### Testien Riippuvuudet
- Testitietokanta: MySQL/Mahdollisesti H2 testivaiheessa
- Palvelimet: 
  - Backend: Spring Boot
  - Frontend: React
- Testikäyttäjät: 
  - admin/admin321

### Virhetilanteet
- tapahtuma-ikkunassa ei voi asettaa negatiivista arvoa lippujen määrälle eikä tapahtumaa voi tehdä ilman yhtään valittua lippua. 
    Virheilmoitusta ei tule, toimintoa ei saa suoritettua.

    
