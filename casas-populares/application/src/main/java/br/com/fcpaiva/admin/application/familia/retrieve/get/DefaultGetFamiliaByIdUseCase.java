package br.com.fcpaiva.admin.application.familia.retrieve.get;

import br.com.fcpaiva.admin.domain.familia.Familia;
import br.com.fcpaiva.admin.domain.familia.FamiliaGateway;
import br.com.fcpaiva.admin.domain.exceptions.NotFoundException;
import br.com.fcpaiva.admin.domain.familia.FamiliaId;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetFamiliaByIdUseCase extends GetFamiliaByIdUseCase {

    private final FamiliaGateway familiaGateway;

    public DefaultGetFamiliaByIdUseCase(final FamiliaGateway familiaGateway) {
        this.familiaGateway = Objects.requireNonNull(familiaGateway);
    }

    @Override
    public FamiliaOutput execute(final String anIn) {
        final var anFamiliaID = FamiliaId.from(anIn);

        return this.familiaGateway.findById(anFamiliaID)
                .map(FamiliaOutput::from)
                .orElseThrow(notFound(anFamiliaID));
    }

    private Supplier<NotFoundException> notFound(final FamiliaId anId) {
        return () -> NotFoundException.with(Familia.class, anId);
    }
}
