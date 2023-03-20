package br.com.fcpaiva.admin.domain.familia;

import br.com.fcpaiva.admin.domain.validation.Error;
import br.com.fcpaiva.admin.domain.validation.ValidationHandler;
import br.com.fcpaiva.admin.domain.validation.Validator;

public class FamiliaValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;
    private final  Familia familia;
    protected FamiliaValidator(final Familia aFamilia, final ValidationHandler aHandler) {
        super(aHandler);
        this.familia = aFamilia;
    }

    @Override
    public void validate() {
        checkDadosObrigatorios();
    }

    private void checkDadosObrigatorios(){
        final var nomeMae = this.familia.getNomeMae();
        if(nomeMae == null){
            this.validationHandler().append(new Error("O nome da mãe é obrigatório"));
            return;
        }

        if(nomeMae.isBlank()){
            this.validationHandler().append(new Error("O nome da mãe é obrigatório"));
            return;
        }

        final int length = nomeMae.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'nome mãe' deve ter entre 3 e 255 caracteres"));
            return;
        }
    }
}
