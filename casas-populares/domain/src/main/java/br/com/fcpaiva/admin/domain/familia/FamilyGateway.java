package br.com.fcpaiva.admin.domain.familia;

import br.com.fcpaiva.admin.domain.pagination.Pagination;

import java.util.Optional;

public interface FamilyGateway {

    Familia create(Familia aFamilia);
    void deleteById(FamiliaId anId);
    Optional<Familia> familiaById(FamiliaId anId);
    Familia update(Familia aFamilia);
    Pagination<Familia> findAll(FamiliaSearchQuery aQuery);
}
