package fi.academy.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class MaaKontrolleri {

    @Autowired
    MaaDao maadao;

    @GetMapping("/api/maat")
    public List<Maa> haeKaikkiMaat(@RequestParam(name = "country", required = false) String hakusana) {
        List<Maa> palautettavat = maadao.haeKaikkiMaat(hakusana);
        return palautettavat;
    }

    @GetMapping(value = "/api/kaupungit")
    public List<Kaupunki> haeKaikkiKaupungit() {
        List<Kaupunki> palautettavat = maadao.haeKaikkiKaupungit();
        return palautettavat;
    }

    @DeleteMapping(value = "api/maat/{code}")
    public ResponseEntity<?> poistaMaa(@PathVariable String code) {
            Boolean poistettu = maadao.poistaMaa(code);
            if (poistettu != null) {
return ResponseEntity.ok(poistettu);
            } else {
return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(String.format("Koodia %s ei ole olemassa. Poistaminen epäonnistui", code));
            }
    }

    @PostMapping(value = "api/maat")
    public ResponseEntity<?> lisaaMaa(@RequestBody Maa m) {
int id = maadao.lisaaMaa(m);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();

    }
}
