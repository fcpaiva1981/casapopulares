package br.com.fcpaiva.admin.domain.familia.dependentes;

import lombok.Getter;

@Getter
public class Dependentes {
    private String nome;
    private int idade;
    private boolean isAptoReceber;

    private Dependentes(String nome, int idade, boolean aptoReceber) {
        this.nome = nome;
        this.idade = idade;
        this.isAptoReceber = aptoReceber;
    }

    public static Dependentes adicionarDependente(final String aNome,
                                                  final int aIdade,
                                                  final boolean aAptoReceber){
        return new Dependentes(aNome,aIdade,aAptoReceber);
    }

}
