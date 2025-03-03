package com.impacta.api.livraria.domain.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorResponse {

    private Long numero;
    private String nome;
}
