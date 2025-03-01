package com.impacta.api.livraria.services;

import com.impacta.api.livraria.domain.AutorDto;
import com.impacta.api.livraria.domain.LivroDto;
import com.impacta.api.livraria.exceptions.NotFoundException;
import com.impacta.api.livraria.mapper.LivrariaMapper;
import com.impacta.api.livraria.repository.AutorRepository;
import com.impacta.api.livraria.repository.LivroRepository;
import com.impacta.api.livraria.repository.entity.Autor;
import com.impacta.api.livraria.repository.entity.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LivrariaService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    public ResponseEntity<AutorDto> salvaAutor(AutorDto autorDto) {

        Autor autor = Autor.builder()
                .nome(autorDto.getNome())
                .numero(this.generateRandomLong())
                .build();
        Autor resultado = autorRepository.save(autor);

        return ResponseEntity.ok(LivrariaMapper.INSTANCE.autorToDto(resultado));

    }

    public ResponseEntity<LivroDto> salvarLivro(Long numero, LivroDto livroDto) {

        this.findAutorByNumero(numero);

        Livro livro = Livro.builder()
                .numero(numero)
                .titulo(livroDto.getTitulo())
                .edicao(livroDto.getEdicao())
                .isbn(livroDto.getIsbn())
                .categoria(livroDto.getCategoria())
                .build();
        Livro resultado = livroRepository.save(livro);

        return ResponseEntity.ok(LivrariaMapper.INSTANCE.livroToDto(resultado));

    }


    public ResponseEntity<List<AutorDto>> findAllAutor() {

        List<AutorDto> dto = LivrariaMapper.INSTANCE.listAutorToListDto(autorRepository.findAll());

        return ResponseEntity.ok(dto);

    }

    public ResponseEntity<AutorDto> findAutorByNumero(Long numero) throws NotFoundException {

        Autor autor = autorRepository.findByNumero(numero)
                .orElseThrow(() -> new NotFoundException("Não foi possivel localizar Autor"));

        return ResponseEntity.ok(LivrariaMapper.INSTANCE.autorToDto(autor));

    }

    public ResponseEntity<LivroDto> findLivroByNumero(Long numero) throws NotFoundException {

        Livro livro = livroRepository.findByNumero(numero)
                .orElseThrow(() -> new NotFoundException("Não foi possivel localizar Autor"));

        return ResponseEntity.ok(LivrariaMapper.INSTANCE.livroToDto(livro));

    }

    public ResponseEntity<AutorDto> atualizaAutor(Long id, AutorDto autorDto) {

        return autorRepository.findById(id)
                .map(autor -> {
                    autor.setNome(autorDto.getNome());
                    autorRepository.save(autor);
                    return ResponseEntity.ok(LivrariaMapper.INSTANCE.autorToDto(autor));
                })
                .orElse(ResponseEntity.notFound().build());

    }

    private Long generateRandomLong() {

        String uuid = String.valueOf(UUID.randomUUID().getMostSignificantBits()).replaceAll("[^0-9]", "").substring(0, 5);
        return Long.parseLong(uuid);

    }

}
