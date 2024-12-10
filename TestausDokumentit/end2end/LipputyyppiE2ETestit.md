# Lipputyyppi End-to-End Testit

## Lipputyypin luonti ja poisto

### Testin Tavoite
Tarkistaa, että käyttäjä voi onnistuneesti luoda lipputyypin järjestelmän läpi ja poistaa lipputyypin, mukaan lukien:
- Frontend-lomake
- Backend REST API
- Tietokannan päivitys

### Testin Vaiheet
1. **Luo Lipputyyppi**
    - Avaa sovellus selaimessa.
    - Kirjaudu tunnuksilla `admin/admin321`.
    - Navigoi kohtaan "Lipputyypit - Uusi lipputyyppi".
    - Avautuu ikkuna, jossa luodaan uusi lipputyyppi.
    - Syötä kohtaan "Nimi" Lapsi.
    - Syötä kohtaan "Kuvaus" Alennettu lapsilippu.
    - paina nappia "Luo lipputyyppi"
    - Avautuu uusi ikkuna, jossa pitäisi näkyä uusi lipputyyppi listattuna.

    **Poista lipputyyppi**
    - Pysy samassa ikkunassa, jossa näkyy kaikki lipputyypit tai navigoi etusivulta kohtaan "Lipputyypit - Kaikki lipputyypit".
    - Paina poista nappia Lapsilipun kohdalla.
    - Paina "Vahvista poisto" nappia.
    - Lapsilippu pitäisi poistua lipputyypit sivulta.

2. **Varmista API-vastaus**
   - Varmista esimerkiksi Developer Toolsilla selaimessa:
     - HTTP-status: `201 CREATED`
     - JSON-vastaus sisältää oikeat kentät:
       ```json
        {
            "lipputyyppiId":6,
            "lipputyyppiNimi":"Lapsi",
            "kuvaus":"Alennettu lapsilippu",           
        }
       ```
    **Poisto**
    - Varmista esimerkiksi Developer Toolsilla selaimessa:
     - HTTP-status: `204 No Content`
     - JSON-vastaus:
       ```json
        {}
       ```

3. **Tarkista tietokanta**
   - Aja Postmanilla GET request varmistaaksesi, että data tallentui tietokantaan. 
    Sovellus näyttää uuden lipputyypin id:n otsikossa, kun lipputyyppi luodaan:
    `/api/lipputyypit/{id}`

    **Poisto**
    - Aja Postmanilla GET request varmistaaksesi, että data poistui tietokannasta. 
    Sovellus ei näytä enää uutta lipputyyppiä, kun lipputyyppi poistetaan:
    `/api/lipputyypit`

4. **Tarkista näkyvyys sovelluksessa**
   - Siirry Hallintapaneeliin sovelluksen ylävalikosta.
   - Valitse "Lipputyypit - Kaikki lipputyypit"
   - Etsi lipputyyppi listasta.

    **Tai**
    - Valitse sovelluksen ylävalikosta "Lipputyypit - Kaikki lipputyypit".
    - Etsi lipputyyppi listasta.

    **Poisto**
    - Samat vaiheet kuin aiemmassa, mutta katsotaan, että lipputyyppiä ei ole enää listattuna lipputyypeissä.


### Testien Riippuvuudet
- Testitietokanta: MySQL/Mahdollisesti H2 testivaiheessa
- Palvelimet: 
  - Backend: Spring Boot
  - Frontend: React
- Testikäyttäjät: 
  - admin/admin321

### Virhetilanteet
- Jos jokin kenttä ei ole täytetty lipputyyppiä lisättäessä, antaa sovellus kehotuksen "Täytä tämä kenttä". 