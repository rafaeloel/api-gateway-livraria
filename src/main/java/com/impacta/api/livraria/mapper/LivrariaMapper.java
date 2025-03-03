package com.impacta.api.livraria.mapper;

import com.impacta.api.livraria.domain.AutorDto;
import com.impacta.api.livraria.domain.LivroDto;
import com.impacta.api.livraria.repository.entity.Autor;
import com.impacta.api.livraria.repository.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LivrariaMapper {

    LivrariaMapper INSTANCE = Mappers.getMapper(LivrariaMapper.class);

    Autor dtoToAutor(AutorDto dto);

    AutorDto autorToDto(Autor autor);

    List<Autor> listDtoToAutor(List<AutorDto> listDto);

    List<AutorDto> listAutorToListDto(List<Autor> autorList);

    Livro dtoToLivro(LivroDto dto);

    LivroDto livroToDto(Livro livro);

    List<LivroDto> listLivroToDtoList(List<Livro> livro);

}
