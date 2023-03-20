package br.com.fcpaiva.admin.domain.familia;

import br.com.fcpaiva.admin.domain.familia.dependentes.Dependentes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DependentesTest {
    @Test
    public void giveAvalidParam_whenCallNewFamilia_thInstantiateADependente() {
        final var nome = "Filho01";
        final var idade = 10;
        final var aptoReceber = true;

        final var dependente  = Dependentes.adicionarDependente(nome,idade, aptoReceber);
        Assertions.assertNotNull(dependente);
        Assertions.assertNotNull(dependente.getNome());
        Assertions.assertNotNull(dependente.getIdade());
        Assertions.assertNotNull(dependente.isAptoReceber());
    }
}
