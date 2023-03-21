package br.com.fcpaiva.admin.application.familia.create;

import br.com.fcpaiva.admin.application.UseCaseTest;
import br.com.fcpaiva.admin.domain.familia.FamilyGateway;
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
import static org.mockito.Mockito.when;

public class CreateFamiliaUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateFamiliaUseCase useCase;

    @Mock
    private FamilyGateway familyGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(familyGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateCategory_shoudReturnCategoryId(){
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

        final var aCommand = CreateFamiliaCommand.with(nomePai,nomeMae,renda,pontuacao,dependentes,ativo, Instant.now());

        final FamilyGateway familyGateway = Mockito.mock(FamilyGateway.class);
        when(familyGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        when(familyGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(familyGateway, Mockito.times(1))
                .create(Mockito.argThat((aFamily -> Objects.equals(nomePai, aFamily.getNomePai())
                        && Objects.equals(nomeMae, aFamily.getNomeMae())
                        && Objects.nonNull(aFamily.getId())
                        && Objects.nonNull(aFamily.getRenda())
                        && Objects.nonNull(aFamily.getPontuacao())
                        && Objects.nonNull(aFamily.getDependentesList().size())
                        && Objects.nonNull(aFamily.isAtivo()))));
    }
}
