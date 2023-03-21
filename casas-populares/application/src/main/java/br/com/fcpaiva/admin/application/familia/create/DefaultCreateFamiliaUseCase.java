package br.com.fcpaiva.admin.application.familia.create;

import br.com.fcpaiva.admin.application.UseCase;
import br.com.fcpaiva.admin.domain.familia.Familia;
import br.com.fcpaiva.admin.domain.familia.FamilyGateway;
import br.com.fcpaiva.admin.domain.validation.handler.ThrowsValidationHandler;

import java.util.ArrayList;

public  class DefaultCreateFamiliaUseCase extends UseCase<CreateFamiliaCommand, CreateFamiliaOutput> {

    private final FamilyGateway familyGateway;

    public DefaultCreateFamiliaUseCase(FamilyGateway familyGateway) {
        this.familyGateway = familyGateway;
    }

    @Override
    public CreateFamiliaOutput execute(final CreateFamiliaCommand aCommand){

        final var aFamilia  = Familia.novaFamilia(aCommand.nomePai(),aCommand.nomeMae(),aCommand.renda(),aCommand.pontuacao(),aCommand.dependentesList(),aCommand.isAtivo());
        aFamilia.validate(new ThrowsValidationHandler());
        return CreateFamiliaOutput.from(this.familyGateway.create(aFamilia));
    }

}
