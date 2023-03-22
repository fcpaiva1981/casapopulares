package br.com.fcpaiva.admin.application.familia.create;

import br.com.fcpaiva.admin.domain.familia.Familia;

public record CreateFamiliaOutput(String id)
{
    public static CreateFamiliaOutput from(final String anId) {
        return new CreateFamiliaOutput(anId);
    }

    public static CreateFamiliaOutput from(final Familia aFamilia) {
        return new CreateFamiliaOutput(aFamilia.getId().getValue());
    }
}
