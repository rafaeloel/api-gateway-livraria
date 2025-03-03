package com.impacta.api.livraria.domain.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroResponse {

    private Long numero;
    private String titulo;
    private String edicao;
    private String isbn;
    private String categoria;
}
