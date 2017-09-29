package it.infocert.eigor.converter.cen2ubl;

import it.infocert.eigor.api.CustomMapping;
import it.infocert.eigor.api.conversion.JavaLocalDateToStringConverter;
import it.infocert.eigor.model.core.enums.Iso31661CountryCodes;
import it.infocert.eigor.model.core.enums.Untdid2005DateTimePeriodQualifiers;
import it.infocert.eigor.model.core.model.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DeliveryOrInvoicePeriodConverter implements CustomMapping<Document> {
    private static final Logger log = LoggerFactory.getLogger(DeliveryOrInvoicePeriodConverter.class);

    @Override
    public void map(BG0000Invoice cenInvoice, Document document, List errors) {

        JavaLocalDateToStringConverter dateConverter = new JavaLocalDateToStringConverter();

        Element root = document.getRootElement();
        if (root != null) {
            if (!cenInvoice.getBG0013DeliveryInformation().isEmpty()) {
                BG0013DeliveryInformation bg0013 = cenInvoice.getBG0013DeliveryInformation(0);
                if (!bg0013.getBG0014InvoicingPeriod().isEmpty()) {
                    BG0014InvoicingPeriod bg0014 = bg0013.getBG0014InvoicingPeriod(0);

                    Element invoicePeriod = root.getChild("InvoicePeriod");
                    if (invoicePeriod == null) {
                        invoicePeriod = new Element("InvoicePeriod");
                        root.addContent(invoicePeriod);
                    }

                    if (!bg0014.getBT0073InvoicingPeriodStartDate().isEmpty()) {
                        BT0073InvoicingPeriodStartDate bt0073 = bg0014.getBT0073InvoicingPeriodStartDate(0);
                        if (bt0073 != null) {
                            Element startDate = new Element("StartDate");
                            startDate.addContent(dateConverter.convert(bt0073.getValue()));
                            invoicePeriod.addContent(startDate);
                        }
                    }

                    if (!bg0014.getBT0074InvoicingPeriodEndDate().isEmpty()) {
                        BT0074InvoicingPeriodEndDate bt0074 = bg0014.getBT0074InvoicingPeriodEndDate(0);
                        if (bt0074 != null) {
                            Element endDate = new Element("EndDate");
                            endDate.addContent(dateConverter.convert(bt0074.getValue()));
                            invoicePeriod.addContent(endDate);
                        }
                    }

                    if (!cenInvoice.getBT0008ValueAddedTaxPointDateCode().isEmpty()) {
                        BT0008ValueAddedTaxPointDateCode bt0008 = cenInvoice.getBT0008ValueAddedTaxPointDateCode(0);
                        if (bt0008 != null) {
                            Element descriptionCode = new Element("DescriptionCode");
                            Untdid2005DateTimePeriodQualifiers code = bt0008.getValue();
                            descriptionCode.addContent(code.name());
                            invoicePeriod.addContent(descriptionCode);
                        }
                    }
                }
            }
        }
    }
}