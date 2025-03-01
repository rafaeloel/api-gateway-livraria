package com.impacta.api.livraria.controller;

import com.impacta.api.livraria.domain.AutorDto;
import com.impacta.api.livraria.domain.LivroDto;
import com.impacta.api.livraria.exceptions.NotFoundException;
import com.impacta.api.livraria.services.LivrariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livraria/api/v1")
public class LivrariaController {

    @Autowired
    private LivrariaService livaLivrariaService;

    @GetMapping("/autor")
    public ResponseEntity<List<AutorDto>> findAllAutor() {

        return livaLivrariaService.findAllAutor();

    }

    @GetMapping("/autor/{numero}")
    public ResponseEntity<AutorDto> findAutorByNumero(
            @PathVariable Long numero) throws NotFoundException {

        return livaLivrariaService.findAutorByNumero(numero);

    }

    @GetMapping("/autor/{numero}/livro")
    public ResponseEntity<LivroDto> findLivroByNumero(
            @PathVariable Long numero) throws NotFoundException {

        return livaLivrariaService.findLivroByNumero(numero);

    }

    @PostMapping("/autor")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AutorDto> saveAutor(@RequestBody AutorDto autorDto) {

        return livaLivrariaService.salvaAutor(autorDto);

    }

    @PostMapping("/autor/{numero}/livro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LivroDto> saveLivro(@PathVariable Long numero, @RequestBody LivroDto livroDto) {

        return livaLivrariaService.salvarLivro(numero, livroDto);

    }

    @PatchMapping("/autor/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<AutorDto> updateAutor(@PathVariable Long id, @RequestBody AutorDto autorDto) {

        return livaLivrariaService.atualizaAutor(id, autorDto);

    }
}
