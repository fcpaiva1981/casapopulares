package br.com.fcpaiva.admin.domain.familia;

import br.com.fcpaiva.admin.domain.AggregateRoot;
import br.com.fcpaiva.admin.domain.familia.dependentes.Dependentes;
import br.com.fcpaiva.admin.domain.utils.InstantUtils;
import br.com.fcpaiva.admin.domain.validation.ValidationHandler;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Objects;


@Getter
public class Familia  extends AggregateRoot<FamiliaId> implements Cloneable{

    private String nomePai;
    private String nomeMae;
    private Double renda;
    private int pontuacao;
    private List<Dependentes> dependentesList;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    private boolean isAtivo;

    private Familia(final FamiliaId id,
                    final String nomePai,
                    final String nomeMae,
                    final Double renda,
                    final int pontuacao,
                    final List<Dependentes> dependentesList,
                    final boolean isAtivo,
                    final Instant aCreatedAt,
                    final Instant aUpdateDate,
                    final Instant aDeleteDate) {
        super(id);
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
        final var id = FamiliaId.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isAtivo ? null : now;
        return new Familia(id, aNomePai, aNomeMae, aRenda, aPontuacao, aDependentesList, isAtivo, now, now, deletedAt);
    }

    public static Familia with(
            final FamiliaId aId,
            final String aNomePai,
            final String aNomeMae,
            final Double aRenda,
            final int aPontuacao,
            final List<Dependentes> aDependentesList,
            final boolean aIsAtivo,
            final Instant aCreatedAt,
            final Instant aUpdateDate,
            final Instant aDeleteDate) {
        return new Familia(
                aId,
                aNomePai,
                aNomeMae,
                aRenda,
                aPontuacao,
                aDependentesList,
                aIsAtivo,
                aCreatedAt,
                aUpdateDate,
                aDeleteDate);
    }

    public static Familia with(final Familia aFamilia) {
        return new Familia(
                aFamilia.getId(),
                aFamilia.getNomePai(),
                aFamilia.getNomeMae(),
                aFamilia.getRenda(),
                aFamilia.getPontuacao(),
                aFamilia.getDependentesList(),
                aFamilia.isAtivo(),
                aFamilia.getCreatedAt(),
                aFamilia.getUpdatedAt(),
                aFamilia.getDeletedAt());
    }

    public Familia activate() {
        this.deletedAt = null;
        this.isAtivo = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Familia deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }

        this.isAtivo = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Familia update(
            final String aNomePai,
            final String aNomeMae,
            final Double aRenda,
            final int aPontuacao,
            final List<Dependentes> aDependentesList,
            final boolean aIsAtivo
    ) {
        if (aIsAtivo) {
            activate();
        } else {
            deactivate();
        }
        this.nomePai = aNomePai;
        this.nomeMae = aNomeMae;
        this.renda = aRenda;
        this.pontuacao = aPontuacao;
        this.dependentesList = aDependentesList;
        this.updatedAt = Instant.now();
        return this;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new FamiliaValidator(this, handler).validate();
    }

    @Override
    public Familia clone() {
        try {
            return (Familia) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
