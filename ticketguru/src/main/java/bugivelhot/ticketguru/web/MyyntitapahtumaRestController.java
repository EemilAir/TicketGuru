package bugivelhot.ticketguru.web;

import org.springframework.web.bind.annotation.RestController;

import bugivelhot.ticketguru.dto.MyyntitapahtumaDTO;
import bugivelhot.ticketguru.model.Myyntitapahtuma;
import bugivelhot.ticketguru.service.MyyntitapahtumaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/myyntitapahtumat/")
public class MyyntitapahtumaRestController {

    @Autowired
    private MyyntitapahtumaService myyntitapahtumaService;

    @PostMapping
    public ResponseEntity<Myyntitapahtuma> luoMyyntitapahtuma(@RequestBody MyyntitapahtumaDTO myyntitapahtumaDTO) {
        Myyntitapahtuma myyntitapahtuma = myyntitapahtumaService.luoJaTallennaMyyntitapahtuma(myyntitapahtumaDTO);
        return ResponseEntity.ok(myyntitapahtuma);
    }

}
