package br.com.fcpaiva.admin.application.familia.retrieve.list;

import br.com.fcpaiva.admin.application.UseCase;
import br.com.fcpaiva.admin.application.familia.create.CreateFamiliaCommand;
import br.com.fcpaiva.admin.application.familia.create.CreateFamiliaUseCase;
import br.com.fcpaiva.admin.domain.validation.handler.Notification;
import io.vavr.control.Either;
public abstract class ListFamiliaUseCase
        extends UseCase<CreateFamiliaCommand, Either<Notification, CreateFamiliaUseCase>>{

}
