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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LivrariaService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    public AutorDto salvaAutor(AutorDto autorDto) {

        Autor autor = Autor.builder()
                .nome(autorDto.getNome())
                .numero(this.generateRandomLong())
                .build();
        Autor resultado = autorRepository.save(autor);

        return LivrariaMapper.INSTANCE.autorToDto(resultado);

    }

    public LivroDto salvarLivro(Long numero, LivroDto livroDto) {

        this.findAutorByNumero(numero);

        Livro livro = Livro.builder()
                .numero(numero)
                .titulo(livroDto.getTitulo())
                .edicao(livroDto.getEdicao())
                .isbn(livroDto.getIsbn())
                .categoria(livroDto.getCategoria())
                .build();
        Livro resultado = livroRepository.save(livro);

        return LivrariaMapper.INSTANCE.livroToDto(resultado);

    }


    public List<AutorDto> findAllAutor() {

        return LivrariaMapper.INSTANCE.listAutorToListDto(autorRepository.findAll());

    }

    public AutorDto findAutorByNumero(Long numero) throws NotFoundException {

        Autor autor = autorRepository.findByNumero(numero)
                .orElseThrow(() -> new NotFoundException("Não foi possivel localizar Autor"));

        return LivrariaMapper.INSTANCE.autorToDto(autor);

    }

    public List<LivroDto> findLivroByNumero(Long numero) throws NotFoundException {

        List<Livro> livros = livroRepository.findAllByNumero(numero);

        if (livros.isEmpty()) {
            throw new NotFoundException("Não foi possivel localizar livro cadastrado para o Autor " + numero);
        }

        return LivrariaMapper.INSTANCE.listLivroToDtoList(livros.stream().toList());

    }

    public AutorDto atualizaAutor(Long id, AutorDto autorDto) {

        return autorRepository.findById(id)
                .map(autor -> {
                    autor.setNome(autorDto.getNome());
                    autorRepository.save(autor);
                    return LivrariaMapper.INSTANCE.autorToDto(autor);
                })
                .orElseThrow(() -> new NotFoundException("Não foi possivel realizar atualização"));

    }

    private Long generateRandomLong() {

        String uuid = String.valueOf(UUID.randomUUID().getMostSignificantBits()).replaceAll("[^0-9]", "").substring(0, 5);
        return Long.parseLong(uuid);

    }

}
