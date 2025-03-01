package com.impacta.api.livraria.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorDto {

    private Long id;
    private Long numero;
    private String nome;
    private LivroDto livro;
}
