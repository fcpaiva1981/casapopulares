package br.com.fcpaiva.admin.domain.familia;

public record FamiliaSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}
