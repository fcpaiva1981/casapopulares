package br.com.fcpaiva.admin.application.familia.create;

import br.com.fcpaiva.admin.application.UseCase;
import br.com.fcpaiva.admin.domain.validation.handler.Notification;
import io.vavr.control.Either;


public abstract class CreateFamiliaUseCase extends UseCase<CreateFamiliaCommand, Either<Notification, CreateFamiliaOutput>> {

}
