package br.com.fcpaiva.admin.application.familia.create;


import br.com.fcpaiva.admin.application.UseCaseTest;
import br.com.fcpaiva.admin.domain.familia.dependentes.Dependentes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CreateFamiliaUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateFamiliaUseCase useCase;

    @Mock
    private br.com.fcpaiva.admin.domain.familia.FamiliaGateway familiaGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(familiaGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateFamilia_shouldReturnFamiliaId() {
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
                CreateFamiliaCommand.with(expectedName, expectedName, renda,pontuacao,dependentes,ativo, Instant.now());

        when(familiaGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(familiaGateway, times(1)).create(argThat(aFamilia ->
                Objects.equals(expectedName, aFamilia.getNomePai())
                        && Objects.equals(expectedMae, aFamilia.getNomeMae())
                        && Objects.equals(expectedIsActive, aFamilia.isAtivo())
                        && Objects.nonNull(aFamilia.getId())
                        && Objects.nonNull(aFamilia.getCreatedAt())
                        && Objects.nonNull(aFamilia.getUpdatedAt())
                        && Objects.isNull(aFamilia.getDeletedAt())
        ));
    }


    @Test
    public void givenAInvalidName_whenCallsCreateFamilia_thenShouldReturnDomainException() {
        final String expectedName = null;
        final var expectedMae = "A categoria mais assistida";
        final var expectedIsActive = false;
        final var renda = 899.99;
        final var pontuacao = 8;
        final var dependentes = new ArrayList<Dependentes>();
        final var ativo = true;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        dependentes.add(Dependentes.adicionarDependente("Filho01", 8, true));
        dependentes.add(Dependentes.adicionarDependente("Filho02", 12, true));
        dependentes.add(Dependentes.adicionarDependente("Filho03", 14, true));
        dependentes.add(Dependentes.adicionarDependente("Filho04", 15, true));
        dependentes.add(Dependentes.adicionarDependente("Filho05", 19, false));
        final var aCommand =
                CreateFamiliaCommand.with(expectedName, expectedName, renda,pontuacao,dependentes,ativo, Instant.now());


        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(familiaGateway, times(0)).create(any());
    }

    @Test
    public void givenAValidCommandWithInactiveFamilia_whenCallsCreateFamilia_shouldReturnInactiveFamiliaId() {
        final String expectedName = null;
        final var expectedMae = "A categoria mais assistida";
        final var expectedIsActive = false;
        final var renda = 899.99;
        final var pontuacao = 8;
        final var dependentes = new ArrayList<Dependentes>();
        final var ativo = false;

        dependentes.add(Dependentes.adicionarDependente("Filho01", 8, true));
        dependentes.add(Dependentes.adicionarDependente("Filho02", 12, true));
        dependentes.add(Dependentes.adicionarDependente("Filho03", 14, true));
        dependentes.add(Dependentes.adicionarDependente("Filho04", 15, true));
        dependentes.add(Dependentes.adicionarDependente("Filho05", 19, false));
        final var aCommand =
                CreateFamiliaCommand.with(expectedName, expectedName, renda,pontuacao,dependentes,ativo, Instant.now());


        when(familiaGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(familiaGateway, times(1)).create(argThat(aFamilia ->
                Objects.equals(expectedName, aFamilia.getNomePai())
                        && Objects.equals(expectedMae, aFamilia.getNomeMae())
                        && Objects.equals(expectedIsActive, aFamilia.isAtivo())
                        && Objects.nonNull(aFamilia.getId())
                        && Objects.nonNull(aFamilia.getCreatedAt())
                        && Objects.nonNull(aFamilia.getUpdatedAt())
                        && Objects.nonNull(aFamilia.getDeletedAt())
        ));
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAException() {
        final String expectedName = null;
        final var expectedMae = "A categoria mais assistida";
        final var expectedIsActive = false;
        final var renda = 899.99;
        final var pontuacao = 8;
        final var dependentes = new ArrayList<Dependentes>();
        final var ativo = true;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Gateway error";

        dependentes.add(Dependentes.adicionarDependente("Filho01", 8, true));
        dependentes.add(Dependentes.adicionarDependente("Filho02", 12, true));
        dependentes.add(Dependentes.adicionarDependente("Filho03", 14, true));
        dependentes.add(Dependentes.adicionarDependente("Filho04", 15, true));
        dependentes.add(Dependentes.adicionarDependente("Filho05", 19, false));
        final var aCommand =
                CreateFamiliaCommand.with(expectedName, expectedName, renda,pontuacao,dependentes,ativo, Instant.now());

        when(familiaGateway.create(any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(familiaGateway, times(1)).create(argThat(aFamilia ->
                Objects.equals(expectedName, aFamilia.getNomePai())
                        && Objects.equals(expectedMae, aFamilia.getNomeMae())
                        && Objects.equals(expectedIsActive, aFamilia.isAtivo())
                        && Objects.nonNull(aFamilia.getId())
                        && Objects.nonNull(aFamilia.getCreatedAt())
                        && Objects.nonNull(aFamilia.getUpdatedAt())
                        && Objects.isNull(aFamilia.getDeletedAt())
        ));
    }
}
