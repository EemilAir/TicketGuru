# RESTAPIDokumentaatio

Nämä ovat TicketGuru projectin RestAPI:in documentaatiot. Täyttä URL:ää ei ole
vielä tarjolla, joten projektin testi versio toimii paikallisesti laitteelta 
portista 8080 'localhost:8080/'

## Avoimet Endpointit

Avoimet endpointit eivät vaadi authentikaatiota (avoimia tullaan siirtämään suljettuihin!)

## Tapahtumiin kuuluvat

Endpointeilla voidaan hallita uusien tapahtumien tarkastelua, 
luomista, poistamista, sekä muokaamista.

* [Näytä tapahtumat](tapahtumat/get.md) : `GET /api/tapahtumat/`
* [Luo tapahtuma](tapahtumat/post.md) : `POST /api/tapahtumat/`
* [Näytä tapahtuma](tapahtumat/pk/get.md) : `GET /api/tapahtumat/id`
* [Muuta tapahtumaa](tapahtumat/pk/put.md) : `PUT /api/tapahtumat/id`
* [Poista tapahtuma](tapahtumat/pk/delete.md) : `DELETE /api/tapahtumat/id`

## Myyntitapahtumiin kuuluvat

Myyntitapahtumilla voidaan luoda hallita tapahtumien myyntejä ja niihin kuuluvia lippuja. 

* [Näytä kaikki myyntitapahtumat](myyntitapahtumat/get.md) : `GET /api/myyntitapahtumat/`
* [Luo uusi myyntitapahtuma](myyntitapahtumat/post.md) : `POST /api/myyntitapahtumat/`
* [Näytä myyntitapahtuma](myyntitapahtumat/pk/get.md) : `GET /api/myyntitapahtumat/id`
* [Poista myyntitapahtuma](myyntitapahtumat/pk/delete.md) : `DELETE /api/myyntitapahtumat/`

## Lippuihin kuuluvat

* [Näytä myyntitapahtumaan kuuluvat liput](myyntitapahtumat/pk/liput/get.md) : `Get /api/myyntitapahtumat/id/liput`