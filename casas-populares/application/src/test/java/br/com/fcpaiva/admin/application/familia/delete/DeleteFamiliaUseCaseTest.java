package br.com.fcpaiva.admin.application.familia.delete;

import br.com.fcpaiva.admin.application.UseCaseTest;
import br.com.fcpaiva.admin.domain.familia.Familia;
import br.com.fcpaiva.admin.domain.familia.FamiliaGateway;
import br.com.fcpaiva.admin.domain.familia.FamiliaId;
import br.com.fcpaiva.admin.domain.familia.dependentes.Dependentes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@Tag("unitTest")
public abstract class DeleteFamiliaUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteFamiliaUseCase useCase;

    @Mock
    private FamiliaGateway familiaGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(familiaGateway);
    }

    @Test
    public void givenAValidId_whenCallsDeleteCategory_shouldBeOK() {
        final var nomePai = "Pai01";
        final var nomeMae = "Mae01";
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
                Familia.novaFamilia(nomePai, nomeMae, renda,pontuacao,dependentes,ativo);
        final var expectedId = aCommand.getId();

        doNothing()
                .when(familiaGateway).deleteById(eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(familiaGateway, times(1)).deleteById(eq(expectedId));
    }

    @Test
    public void givenAInvalidId_whenCallsDeleteCategory_shouldBeOK() {
        final var expectedId = FamiliaId.from("123");

        doNothing()
                .when(familiaGateway).deleteById(eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(familiaGateway, times(1)).deleteById(eq(expectedId));
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
        final var nomePai = "Pai01";
        final var nomeMae = "Mae01";
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
                Familia.novaFamilia(nomePai, nomeMae, renda,pontuacao,dependentes,ativo);
        final var expectedId = aCommand.getId();

        doThrow(new IllegalStateException("Gateway error"))
                .when(familiaGateway).deleteById(eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        Mockito.verify(familiaGateway, times(1)).deleteById(eq(expectedId));
    }
}
