package br.com.fcpaiva.admin.application.familia.retrieve.list;
import br.com.fcpaiva.admin.domain.familia.FamiliaGateway;

import br.com.fcpaiva.admin.domain.pagination.Pagination;
import br.com.fcpaiva.admin.domain.pagination.SearchQuery;


import java.util.Objects;

public class DefaultListFamiliaUseCase extends ListFamiliaUseCase {

    private final FamiliaGateway familiaGateway;

    public DefaultListFamiliaUseCase(final FamiliaGateway categoryGateway) {
        this.familiaGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<FamiliaListOutput> execute(final SearchQuery aQuery) {
        return this.familiaGateway.findAll(aQuery)
                .map(FamiliaListOutput::from);
    }
}
