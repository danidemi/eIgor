package it.infocert.eigor.api;

/**
 * Find a {@link FromCenConversion conversion} that will convert
 * a @{@link it.infocert.eigor.model.core.model.BG0000Invoice CEN} invoice in another format.
 */
public interface FromCenConversionRepository {
    public FromCenConversion findConversionFromCen(String ubl);
}