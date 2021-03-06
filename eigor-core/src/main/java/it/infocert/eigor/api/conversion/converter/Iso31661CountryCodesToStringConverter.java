package it.infocert.eigor.api.conversion.converter;

import it.infocert.eigor.model.core.enums.Iso31661CountryCodes;

public class Iso31661CountryCodesToStringConverter extends ToStringTypeConverter<Iso31661CountryCodes> {

    public static TypeConverter<Iso31661CountryCodes, String> newConverter() {
        return new Iso31661CountryCodesToStringConverter();
    }

    private Iso31661CountryCodesToStringConverter() {
    }

    @Override
    public String convert(Iso31661CountryCodes iso31661CountryCodes) {
        return iso31661CountryCodes != null ? iso31661CountryCodes.getIso2charCode() : null;
    }

    @Override
    public Class<Iso31661CountryCodes> getSourceClass() {
        return Iso31661CountryCodes.class;
    }
}
