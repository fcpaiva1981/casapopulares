package br.com.fcpaiva.admin.domain.familia;


import br.com.fcpaiva.admin.domain.familia.dependentes.Dependentes;
import br.com.fcpaiva.admin.domain.utils.InstantUtils;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Familia {

    private String id;
    private String nomePai;
    private String nomeMae;
    private Double renda;
    private int pontuacao;
    private List<Dependentes> dependentesList;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    private boolean isAtivo;

    private Familia(final String id,
                    final String nomePai,
                    final String nomeMae,
                    final Double renda,
                    final int pontuacao,
                    final List<Dependentes> dependentesList,
                    final boolean isAtivo,
                    final Instant aCreatedAt,
                    final Instant aUpdateDate,
                    final Instant aDeleteDate) {
        this.id = id;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.renda = renda;
        this.pontuacao = pontuacao;
        this.dependentesList = dependentesList;
        this.isAtivo = isAtivo;
        this.createdAt = Objects.requireNonNull(aCreatedAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdateDate, "'updatedAt' should not be null");
        this.deletedAt = aDeleteDate;
    }

    public static Familia novaFamilia(final String aNomePai,
                                      final String aNomeMae,
                                      final Double aRenda,
                                      final int aPontuacao,
                                      final List<Dependentes> aDependentesList,
                                      final boolean isAtivo) {
        final var id = UUID.randomUUID().toString();
        final var now = InstantUtils.now();
        final var deletedAt = isAtivo ? null : now;
        return new Familia(id, aNomePai, aNomeMae, aRenda, aPontuacao, aDependentesList, isAtivo, now, now, deletedAt);
    }
}
