package br.com.fcpaiva.admin.domain.familia;

import br.com.fcpaiva.admin.domain.pagination.Pagination;
import br.com.fcpaiva.admin.domain.pagination.SearchQuery;

import java.util.Optional;

public interface FamiliaGateway {
    Familia create(Familia aFamilia);
    void deleteById(FamiliaId anId);
    Optional<Familia> findById(FamiliaId anId);
    Familia update(Familia aFamilia);
    Pagination<Familia> findAll(SearchQuery aQuery);
}
