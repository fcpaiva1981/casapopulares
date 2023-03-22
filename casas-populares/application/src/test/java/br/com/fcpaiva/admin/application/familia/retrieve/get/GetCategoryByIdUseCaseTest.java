package br.com.fcpaiva.admin.application.familia.retrieve.get;


import br.com.fcpaiva.admin.application.UseCaseTest;
import br.com.fcpaiva.admin.application.familia.create.DefaultCreateFamiliaUseCase;
import br.com.fcpaiva.admin.domain.exceptions.NotFoundException;
import br.com.fcpaiva.admin.domain.familia.Familia;
import br.com.fcpaiva.admin.domain.familia.FamiliaId;
import br.com.fcpaiva.admin.domain.familia.dependentes.Dependentes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class GetCategoryByIdUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateFamiliaUseCase useCase;

    @Mock
    private br.com.fcpaiva.admin.domain.familia.FamiliaGateway familiaGateway;


    @Override
    protected List<Object> getMocks() {
        return List.of(familiaGateway);
    }

    @Test
    public void givenAValidId_whenCallsGetCategory_shouldReturnCategory() {
        final var expectedName = "Pai01";
        final var expectedMae = "A categoria mais assistida";
        final var expectedIsActive = false;
        final var renda = 899.99;
        final var pontuacao = 8;
        final var dependentes = new ArrayList<Dependentes>();
        final var ativo = true;

        dependentes.add(Dependentes.adicionarDependente("Filho01", 8, true));
        dependentes.add(Dependentes.adicionarDependente("Filho02", 12, true));
        dependentes.add(Dependentes.adicionarDependente("Filho03", 14, true));
        dependentes.add(Dependentes.adicionarDependente("Filho04", 15, true));
        dependentes.add(Dependentes.adicionarDependente("Filho05", 19, false));

        final var aCommand =
                Familia.novaFamilia(expectedName, expectedName, renda,pontuacao,dependentes,ativo);

        final var expectedId = aCommand.getId();

        when(familiaGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(aCommand.clone()));

        final var actualCategory = useCase.execute(expectedId.getValue());

        Assertions.assertEquals(expectedId, actualCategory.id());
        Assertions.assertEquals(expectedName, actualCategory.name());
        Assertions.assertEquals(expectedDescription, actualCategory.description());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(aCommand.getCreatedAt(), actualCategory.createdAt());
        Assertions.assertEquals(aCommand.getUpdatedAt(), actualCategory.updatedAt());
        Assertions.assertEquals(aCommand.getDeletedAt(), actualCategory.deletedAt());
    }

    @Test
    public void givenAInvalidId_whenCallsGetCategory_shouldReturnNotFound() {
        final var expectedErrorMessage = "Category with ID 123 was not found";
        final var expectedId = FamiliaId.from("123");

        when(familiaGateway.findById(eq(expectedId)))
                .thenReturn(Optional.empty());

        final var actualException = Assertions.assertThrows(
                NotFoundException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
        final var expectedErrorMessage = "Gateway error";
        final var expectedId = FamiliaId.from("123");

        when(familiaGateway.findById(eq(expectedId)))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
