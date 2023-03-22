package br.com.fcpaiva.admin.application.familia.retrieve.get;


import br.com.fcpaiva.admin.domain.familia.Familia;
import br.com.fcpaiva.admin.domain.familia.FamiliaId;
import br.com.fcpaiva.admin.domain.familia.dependentes.Dependentes;

import java.time.Instant;
import java.util.List;

public record FamiliaOutput(

        FamiliaId id,
         String nomePai,
         String nomeMae,
         Double renda,
         int pontuacao,
         List<Dependentes>dependentesList,
         Instant createdAt,
         Instant updatedAt,
         Instant deletedAt
) {

    public static FamiliaOutput from(final Familia aFamilia) {
        return new FamiliaOutput(
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
