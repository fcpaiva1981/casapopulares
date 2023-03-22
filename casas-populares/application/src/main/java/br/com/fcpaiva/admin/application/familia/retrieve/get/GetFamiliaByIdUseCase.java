package br.com.fcpaiva.admin.application.familia.retrieve.get;

import br.com.fcpaiva.admin.application.UseCase;

public abstract class GetFamiliaByIdUseCase
        extends UseCase<String, FamiliaOutput> {
    public abstract FamiliaOutput execute(String anIn);
}
