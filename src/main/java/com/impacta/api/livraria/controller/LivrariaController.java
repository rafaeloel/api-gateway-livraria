package com.impacta.api.livraria.controller;

import com.impacta.api.livraria.domain.AutorDto;
import com.impacta.api.livraria.domain.LivroDto;
import com.impacta.api.livraria.domain.responses.AutorResponse;
import com.impacta.api.livraria.domain.responses.LivroResponse;
import com.impacta.api.livraria.exceptions.NotFoundException;
import com.impacta.api.livraria.mapper.ResponseMapper;
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
    public ResponseEntity<List<AutorResponse>> findAllAutor() {

        List<AutorDto> response = livaLivrariaService.findAllAutor();
        return new ResponseEntity<>(ResponseMapper.INSTANCE.listAutorToResponse(response), HttpStatus.OK);

    }

    @GetMapping("/autor/{numero}")
    public ResponseEntity<AutorResponse> findAutorByNumero(
            @PathVariable Long numero) throws NotFoundException {

        AutorDto response = livaLivrariaService.findAutorByNumero(numero);
        return new ResponseEntity<>(ResponseMapper.INSTANCE.autorToResponse(response), HttpStatus.OK);

    }

    @GetMapping("/autor/{numero}/livro")
    public ResponseEntity<List<LivroResponse>> findLivroByNumero(
            @PathVariable Long numero) throws NotFoundException {

        List<LivroDto> response = livaLivrariaService.findLivroByNumero(numero);
        return new ResponseEntity<>(ResponseMapper.INSTANCE.listLivroToResponse(response), HttpStatus.OK);

    }

    @PostMapping("/autor")
    public ResponseEntity<AutorResponse> saveAutor(@RequestBody AutorDto autorDto) {

        AutorDto response = livaLivrariaService.salvaAutor(autorDto);
        return new ResponseEntity<>(ResponseMapper.INSTANCE.autorToResponse(response), HttpStatus.CREATED);

    }

    @PostMapping("/autor/{numero}/livro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LivroResponse> saveLivro(@PathVariable Long numero, @RequestBody LivroDto livroDto) {

        LivroDto response = livaLivrariaService.salvarLivro(numero, livroDto);
        return new ResponseEntity<>(ResponseMapper.INSTANCE.livroToResponse(response), HttpStatus.CREATED);

    }

    @PatchMapping("/autor/{id}")
    public ResponseEntity<AutorResponse> updateAutor(@PathVariable Long id, @RequestBody AutorDto autorDto) {

        AutorDto response = livaLivrariaService.atualizaAutor(id, autorDto);
        return new ResponseEntity<>(ResponseMapper.INSTANCE.autorToResponse(response), HttpStatus.NO_CONTENT);

    }
}
