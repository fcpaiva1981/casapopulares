package br.com.fcpaiva.admin.domain.familia;

import br.com.fcpaiva.admin.domain.Identifier;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class FamiliaId extends Identifier {

    private final String value;

    private FamiliaId(String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static FamiliaId unique(){
        return  FamiliaId.from(UUID.randomUUID());
    }

    public static FamiliaId from(final String anId){
        return new FamiliaId(anId);
    }

    public static FamiliaId from(final UUID anId){
        return new FamiliaId(anId.toString().toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        final FamiliaId that = (FamiliaId) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
