package br.com.fcpaiva.admin.application.familia.retrieve.list;

import br.com.fcpaiva.admin.domain.familia.FamiliaId;
import br.com.fcpaiva.admin.domain.familia.Familia;
import br.com.fcpaiva.admin.domain.familia.dependentes.Dependentes;


import java.time.Instant;
import java.util.List;

public record FamiliaListOutput(
        FamiliaId id,
        String nomePai,
        String nomeMae,
        Double renda,
        int pontuacao,
        List<Dependentes> dependentesList,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static FamiliaListOutput from(final Familia aFamilia) {
        return new FamiliaListOutput(
                aFamilia.getId(),
                aFamilia.getNomePai(),
                aFamilia.getNomeMae(),
                aFamilia.getRenda(),
                aFamilia.getPontuacao(),
                aFamilia.getDependentesList(),
                aFamilia.getCreatedAt(),
                aFamilia.getUpdatedAt(),
                aFamilia.getDeletedAt()
        );
    }
}
