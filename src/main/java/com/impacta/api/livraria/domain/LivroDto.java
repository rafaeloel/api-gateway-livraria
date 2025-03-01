package com.impacta.api.livraria.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroDto {

    private Long id;
    private Long numero;
    private String titulo;
    private String edicao;
    private String isbn;
    private String categoria;

}
