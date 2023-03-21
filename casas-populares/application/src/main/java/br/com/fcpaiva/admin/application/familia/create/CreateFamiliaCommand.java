package br.com.fcpaiva.admin.application.familia.create;

import br.com.fcpaiva.admin.domain.familia.dependentes.Dependentes;

import java.time.Instant;
import java.util.List;

public record CreateFamiliaCommand( String nomePai,
                                   String nomeMae,
                                   Double  renda,
                                   int pontuacao,
                                   List<Dependentes> dependentesList,
                                   boolean isAtivo,
                                   Instant createdAt) {

    public static CreateFamiliaCommand with(final String aNomePai,
                                           final String aNomeMae,
                                           final Double aRenda,
                                           final int aPontuacao,
                                           final List<Dependentes> aDependentesList,
                                           final boolean aIsAtivo,
                                           final Instant aCreatedAt){

        return new CreateFamiliaCommand(aNomePai,aNomeMae,aRenda,aPontuacao,aDependentesList,aIsAtivo,aCreatedAt);
    }
}
