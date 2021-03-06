package it.infocert.eigor.api.conversion.converter;

import it.infocert.eigor.model.core.datatypes.Identifier;

public class IdentifierToStringConverter extends ToStringTypeConverter<Identifier> {

    public static TypeConverter<Identifier, String> newConverter(){
        return new IdentifierToStringConverter();
    }

    private IdentifierToStringConverter() {
    }

    @Override
    public String convert(Identifier identifier) {
        return identifier.toString();
    }

    @Override
    public Class<Identifier> getSourceClass() {
        return Identifier.class;
    }
}
