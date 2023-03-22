package br.com.fcpaiva.admin.application.familia.delete;

import br.com.fcpaiva.admin.domain.familia.FamiliaGateway;
import br.com.fcpaiva.admin.domain.familia.FamiliaId;

import java.util.Objects;

public class DefaultDeleteFamiliaUseCase  extends DeleteCategoryFamily {

    private final FamiliaGateway familiaGateway;


    public DefaultDeleteFamiliaUseCase(final FamiliaGateway familiaGateway) {
        this.familiaGateway = familiaGateway;
    }

    @Override
    public void execute(final String anIn) {
        this.familiaGateway.deleteById(FamiliaId.from(anIn));
    }
}