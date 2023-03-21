package br.com.fcpaiva.admin.application;

import br.com.fcpaiva.admin.domain.familia.Familia;

public abstract class UseCase<IN, OUT> {

   public abstract OUT execute(IN anIn);

}
