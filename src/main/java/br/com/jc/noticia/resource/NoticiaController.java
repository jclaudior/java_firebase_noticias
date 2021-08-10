package br.com.jc.noticia.resource;

import br.com.jc.noticia.model.Noticia;
import br.com.jc.noticia.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/noticias")
public class NoticiaController {

    @Autowired
    NoticiaService noticiaService;

    @PostMapping
    public ResponseEntity<String> salvarNoticia(@RequestBody Noticia noticia){
        try{
            return ResponseEntity.ok().body(noticiaService.salvarNoticia(noticia));
        }catch (Exception e){
            return  ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarNoticias (){
        try{
            return ResponseEntity.ok().body(noticiaService.buscarNoticias());
        }catch (Exception e){
            return  ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping(path="/titulo/{titulo}")
    public ResponseEntity<?> buscarNoticia (@PathVariable String titulo){
        try{
            return ResponseEntity.ok().body(noticiaService.buscarNoticia(titulo));
        }catch (Exception e){
            return  ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> atualizarNoticia(@RequestBody Noticia noticia){
        try{
            return ResponseEntity.ok().body(noticiaService.atualizarNoticia(noticia));
        }catch (Exception e){
            return  ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletarNoticia (@RequestBody Noticia noticia){
        try{
            return ResponseEntity.ok().body(noticiaService.deletarNoticia(noticia));
        }catch (Exception e){
            return  ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
