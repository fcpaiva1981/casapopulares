package br.com.fcpaiva.admin.application.familia.create;


import br.com.fcpaiva.admin.domain.familia.Familia;
import br.com.fcpaiva.admin.domain.familia.FamiliaGateway;
import br.com.fcpaiva.admin.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateFamiliaUseCase extends CreateFamiliaUseCase {

    private final FamiliaGateway familiaGateway;

    public DefaultCreateFamiliaUseCase(final FamiliaGateway familiaGateway) {
        this.familiaGateway = Objects.requireNonNull(familiaGateway);
    }
    @Override
    public Either<Notification, CreateFamiliaOutput> execute(final CreateFamiliaCommand aCommand) {
        final var nomePai = aCommand.nomePai();
        final var nomeMae = aCommand.nomeMae();
        final var renda = aCommand.renda();
        final var pontuacao = aCommand.pontuacao();
        final var dependentes = aCommand.dependentesList();
        final var ativo = aCommand.isAtivo();

        final var notification = Notification.create();

        final var aFamilia = Familia.novaFamilia(nomePai, nomeMae, renda, pontuacao, dependentes, ativo);
        aFamilia.validate(notification);

        return notification.hasError() ? Left(notification) : create(aCommand);
    }

    private Either<Notification, CreateFamiliaOutput> create(final Familia aFamilia) {
        return Try(() -> this.familiaGateway.create(aFamilia))
                .toEither()
                .bimap(Notification::create, CreateFamiliaOutput::from);
    }
}
