package com.impacta.api.livraria.mapper;

import com.impacta.api.livraria.domain.AutorDto;
import com.impacta.api.livraria.domain.LivroDto;
import com.impacta.api.livraria.domain.responses.AutorResponse;
import com.impacta.api.livraria.domain.responses.LivroResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResponseMapper {

    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    AutorResponse autorToResponse(AutorDto dto);

    LivroResponse livroToResponse(LivroDto dto);

    List<AutorResponse> listAutorToResponse(List<AutorDto> dto);

    List<LivroResponse> listLivroToResponse(List<LivroDto> dto);


}
