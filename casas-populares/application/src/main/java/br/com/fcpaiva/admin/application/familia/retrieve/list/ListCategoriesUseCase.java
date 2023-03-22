package br.com.fcpaiva.admin.application.familia.retrieve.list;

import br.com.fcpaiva.admin.domain.pagination.Pagination;
import br.com.fcpaiva.admin.domain.pagination.SearchQuery;
import com.fullcycle.admin.catalogo.application.UseCase;
import com.fullcycle.admin.catalogo.domain.pagination.Pagination;
import com.fullcycle.admin.catalogo.domain.pagination.SearchQuery;

public abstract class ListCategoriesUseCase
        extends UseCase<SearchQuery, Pagination<FamiliaListOutput>> {
    public abstract Pagination<FamiliaListOutput> execute(SearchQuery aQuery);
}
