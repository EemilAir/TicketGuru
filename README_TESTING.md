# Ohjeet MySQL testaukseen
Tässä ohjeet miten saatte itse sovelluksen pyörimään MySQL-tietokannan kanssa

## Edellytykset

- asenna [MySQL 8.0.40](https://dev.mysql.com/downloads/installer/)
    - Asentakaa suoraan vaan kaikki mitä se ehdottaa (server, workbench jne.)

## Ympäristömuuttujat
- Pitää manuaalisesti laittaa winukan ympäristömuuttujiin
    - win-key -> haku: "environment variables" tms. -> klikkaa environment variables  -> syötä uudet muuttujat System Variable (ei user) kohtaan
- Ympäristömuuttujat (env varit)
    - MYSQL_ROOT_USER=<tietokantakäyttäjän_nimi> (defaulttina: root, mut kattokaa mikä se on teillä MySQL:ssä)
    - MYSQL_ROOT_PASSWORD=<tietokannan_salasana> (saadaan MySQL asentamisen yhteydessä)

## Tietokannan luominen
- Avatkaa MySQL Workbench
- tietokannan pitäs pyöriä localhost:3306
    - portinkin vois heittää ympäristömuuttujaks, mutta ei käynyt aikasemmin mielessä :D 
- Luokaa uus "Schema" eli tietokanta nimellä "ticketguru"




