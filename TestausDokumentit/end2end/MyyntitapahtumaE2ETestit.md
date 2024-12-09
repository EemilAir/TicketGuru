# Myyntitapahtuma End-to-End Testit

## Myyntitapahtuman luonti

### Testin Tavoite
Tarkistaa, että käyttäjä voi onnistuneesti luoda myyntitapahtuman järjestelmän läpi, mukaan lukien:
- Frontend-lomake
- Backend REST API
- Tietokannan päivitys

### Testin Vaiheet
1. **Luo Myyntitapahtuma**
    - Avaa sovellus selaimessa.
    - Kirjaudu tunnuksilla `admin/admin321`.
    - Navigoi kohtaan "Tapahtumat - Kaikki tapahtumat".
    - Valitse "Myy lippuja" Ruisrock 2025 -tapahtuman kohdalta.
    - Valitse myytävät liput avautuvassa ikkunassa:
        - Normaali - 30 €: 1
        - Vip - 60 €: 1
    - Valitse "Lisää ostoskoriin", jonka jälkeen "Ostoskorissa olevat liput" -kohdan alle tulee näkyviin myydyt lipu.
    - Valitse maksutavaksi "Debit".
    - Lähetä lomake klikkaamalla "Vahvista myynti" -painiketta.
    - Avautuu uusi ikkuna, jossa tulisi näkyä seuraavat tiedot:
        - Otsikko: Myyntitapahtuma *ID*
        - Summa: 90 €
        - Maksutapa: Debit
        - Maksupäivämäärä: Ajankohta, jolloin myyntitapahtuma on vahvistettu, muodossa: *viikonpäivä, DD.MM.YYYY HH:SS*
        - Käyttäjä ID: 2
        - Tapahtuma ID: 2
        - Erilliset kuvat lipuista, jotka sisältävät QR-koodit.

2. **Varmista API-vastaus**
   - Varmista esimerkiksi Developer Toolsilla selaimessa:
     - HTTP-status: `201 CREATED`
     - JSON-vastaus sisältää oikeat kentät:
       ```json
        {
        "maksutapaId":"2",
        "kayttajaId":2,
        "liput":[
            {
            "lipputyyppiId":1,"tapahtumaId":2,"maara":1,"nimi":"Normaali","hinta":30
            },
            {
            "lipputyyppiId":2,"tapahtumaId":2,"maara":1,"nimi":"VIP","hinta":60
            }
        ],
        "tapahtumaId":2
        }
       ```

3. **Tarkista tietokanta**
   - Aja Postmanilla GET request varmistaaksesi, että data tallentui tietokantaan. 
    Sovellus näyttää uuden myyntitapahtuman id:n otsikossa, kun myyntitapahtuma luodaan:
    `/api/myyntitapahtumat/{id}`

4. **Tarkista näkyvyys sovelluksessa**
   - Siirry Hallintapaneeliin sovelluksen ylävalikosta.
   - Kirjoita "Myyntitapahtumat"-kohdan hakukenttään uuden myyntitapahtuman id.

    **Tai**
    - Valitse sovelluksen ylävalikosta "Myyntitapahtumat" ja kirjoita avautuvaan hakukenttään uuden myyntitapahtuman id.

### Testien Riippuvuudet
- Testitietokanta: MySQL/Mahdollisesti H2 testivaiheessa
- Palvelimet: 
  - Backend: Spring Boot
  - Frontend: React
- Testikäyttäjät: 
  - admin/admin321

### Virhetilanteet
- Myyntitapahtuma-ikkunassa ei voi asettaa negatiivista arvoa lippujen määrälle eikä myyntitapahtumaa voi tehdä ilman yhtään valittua lippua. 
    Virheilmoitusta ei tule, toimintoa ei saa suoritettua.
