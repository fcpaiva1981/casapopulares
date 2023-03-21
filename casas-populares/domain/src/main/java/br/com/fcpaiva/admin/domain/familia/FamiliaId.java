package br.com.fcpaiva.admin.domain.familia;

import br.com.fcpaiva.admin.domain.Identifier;
import br.com.fcpaiva.admin.domain.utils.IdUtils;
import lombok.Getter;

import java.util.Objects;

@Getter
public class FamiliaId extends Identifier {

    private final String value;

    private FamiliaId(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static FamiliaId unique() {
        return FamiliaId.from(IdUtils.uuid());
    }

    public static FamiliaId from(final String anId) {
        return new FamiliaId(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FamiliaId that = (FamiliaId) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
