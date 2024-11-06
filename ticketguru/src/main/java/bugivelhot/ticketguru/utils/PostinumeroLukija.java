package bugivelhot.ticketguru.utils;

import bugivelhot.ticketguru.model.Osoite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PostinumeroLukija {

    public List<Osoite> luePostitiedot(InputStream inputStream) {
        List<Osoite> osoitteet = new ArrayList<>();
        String line;

        // Luetaan tiedosto ja käsitellään sen sisältö
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            br.readLine(); // Ohitetaan CSV-tiedoston ensimmäinen rivi, joka sisältää otsikot

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String postinumero = values[0];
                String postitmp = values[1];

                // luodaan uusi osoite ja tallennetaan se palautettavaan listaan
                Osoite osoite = new Osoite(postinumero, postitmp);
                osoitteet.add(osoite);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return osoitteet;
    }
}