package br.com.fcpaiva.admin.domain.familia;

import br.com.fcpaiva.admin.domain.familia.Familia;
import br.com.fcpaiva.admin.domain.familia.dependentes.Dependentes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class FamiliaTest {
    @Test
    public void giveAvalidParam_whenCallNewFamilia_thInstantiateAFamilia() {
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

        final var familia = Familia.novaFamilia(nomePai, nomeMae, renda, pontuacao, dependentes, ativo);
        Assertions.assertNotNull(familia);
        Assertions.assertNotNull(familia.getId());
        Assertions.assertNotNull(familia.getNomePai());
        Assertions.assertNotNull(familia.getNomeMae());
        Assertions.assertNotNull(familia.getRenda());
        Assertions.assertNotNull(familia.getId());
        Assertions.assertNotNull(familia.getPontuacao());
        Assertions.assertNotNull(familia.getDependentesList());
        Assertions.assertNotNull(familia.getCreatedAt());
        Assertions.assertNotNull(familia.getUpdatedAt());
        Assertions.assertNull(familia.getDeletedAt());

    }
}
