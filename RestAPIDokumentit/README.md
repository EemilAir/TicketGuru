# RESTAPIDokumentaatio

Nämä ovat TicketGuru projektin RestAPI:in dokumentaatiot. Täyttä URL:ää ei ole
vielä tarjolla, joten projektin testi versio toimii paikallisesti laitteelta 
portista 8080 'localhost:8080/'

## Avoimet Endpointit

Avoimet endpointit vaativat joko USER tai ADMIN oikeudet

## Tapahtumat

Endpointeilla voidaan hallita uusien tapahtumien tarkastelua, 
luomista, poistamista, sekä muokaamista.

* [Näytä tapahtumat](tapahtumat/get.md) : `GET /api/tapahtumat/`
* [Luo tapahtuma](tapahtumat/post.md) : `POST /api/tapahtumat/`
* [Näytä tapahtuma](tapahtumat/pk/get.md) : `GET /api/tapahtumat/id`
* [Muokkaa tapahtumaa](tapahtumat/pk/patch.md) : `PATCH /api/tapahtumat/id`
* [Poista tapahtuma](tapahtumat/pk/delete.md) : `DELETE /api/tapahtumat/id`

## Myyntitapahtumat

Endpointeilla voidaan hallita tapahtumien myyntejä ja niihin kuuluvia lippuja. 

* [Näytä kaikki myyntitapahtumat](myyntitapahtumat/get.md) : `GET /api/myyntitapahtumat/`
* [Luo uusi myyntitapahtuma](myyntitapahtumat/post.md) : `POST /api/myyntitapahtumat/`
* [Näytä myyntitapahtuma](myyntitapahtumat/pk/get.md) : `GET /api/myyntitapahtumat/id`
* [Poista myyntitapahtuma](myyntitapahtumat/pk/delete.md) : `DELETE /api/myyntitapahtumat/id`

* [Näytä myyntitapahtumaan kuuluvat liput](myyntitapahtumat/pk/myyntitapahtumanLiput/get.md) : `Get /api/myyntitapahtumat/id/liput`

## Liput

Endpointilla voidaan hakea lippu sen koodilla ja muokata lipun tilaa.

* [Näytä lippu koodin perusteella](liput/get.md) : `GET /api/liput?koodi={koodi}`
* [Muokkaa lipun tila](liput/patch.md) : `PATCH /api/liput/id`

## Lipputyyppit

Endpointilla voidaan hallita lipputyyppejä.

* [Näytä kaikki lipputyypit](lipputyypit/get.md) : `GET /api/lipputyypit/`
* [Luo uusi lipputyyppi](lipputyypit/post.md) : `POST /api/lipputyypit/`
* [Näytä lipputyyppi](lipputyypit/pk/get.md) : `GET /api/lipputyypit/id`
* [Muokkaa lipputyyppiä](lipputyypit/pk/patch.md) : `PATCH /api/lipputyypit/id`
* [Poista lipputyyppi](lipputyypit/pk/delete.md) : `DELETE /api/lipputyypit/id`

## Maksutavat

Endpointilla voidaan hakea kaikki maksutavat.

* [Näytä kaikki maksutavat](maksutavat/get.md) : `GET /api/maksutavat/`