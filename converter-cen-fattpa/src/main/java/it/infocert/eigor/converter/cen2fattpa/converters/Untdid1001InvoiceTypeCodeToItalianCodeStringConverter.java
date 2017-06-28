package it.infocert.eigor.converter.cen2fattpa.converters;

import it.infocert.eigor.api.conversion.ToStringTypeConverter;
import it.infocert.eigor.api.conversion.TypeConverter;
import it.infocert.eigor.model.core.enums.Untdid1001InvoiceTypeCode;

public class Untdid1001InvoiceTypeCodeToItalianCodeStringConverter extends ToStringTypeConverter<Untdid1001InvoiceTypeCode> {
    @Override
    public String convert(Untdid1001InvoiceTypeCode untdid1001InvoiceTypeCode) {
        switch (untdid1001InvoiceTypeCode.getCode()) {
            case 380:
            case 389:
                return "TD01";
            case 381:
                return "TD04";
            case 383:
                return "TD05";
            case 386:
                return "TD02";
            default:
                return "";
        }
    }

    @Override
    public Class<Untdid1001InvoiceTypeCode> getSourceClass() {
        return Untdid1001InvoiceTypeCode.class;
    }
}