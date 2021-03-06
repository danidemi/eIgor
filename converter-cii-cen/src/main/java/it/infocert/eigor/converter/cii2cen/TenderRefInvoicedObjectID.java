package it.infocert.eigor.converter.cii2cen;

import it.infocert.eigor.api.ConversionResult;
import it.infocert.eigor.api.CustomConverterUtils;
import it.infocert.eigor.api.CustomMapping;
import it.infocert.eigor.api.IConversionIssue;
import it.infocert.eigor.api.errors.ErrorCode;
import it.infocert.eigor.model.core.datatypes.Identifier;
import it.infocert.eigor.model.core.model.BG0000Invoice;
import it.infocert.eigor.model.core.model.BT0017TenderOrLotReference;
import it.infocert.eigor.model.core.model.BT0018InvoicedObjectIdentifierAndSchemeIdentifier;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.List;

/**
 * The TenderRefInvoicedObjectID Converter
 */
public class TenderRefInvoicedObjectID extends CustomConverterUtils implements CustomMapping<Document> {

    public ConversionResult<BG0000Invoice> toBT0017_18(Document document, BG0000Invoice invoice, List<IConversionIssue> errors) {

        Element rootElement = document.getRootElement();
        List<Namespace> namespacesInScope = rootElement.getNamespacesIntroduced();

        Element supplyChainTradeTransaction = findNamespaceChild(rootElement, namespacesInScope, "SupplyChainTradeTransaction");
        if (supplyChainTradeTransaction != null) {
            Element applicableHeaderTradeAgreement = findNamespaceChild(supplyChainTradeTransaction, namespacesInScope, "ApplicableHeaderTradeAgreement");
            if (applicableHeaderTradeAgreement != null) {
                List<Element> additionalReferencedDocument = findNamespaceChildren(applicableHeaderTradeAgreement, namespacesInScope, "AdditionalReferencedDocument");

                for (Element elemAddRefDoc : additionalReferencedDocument) {

                    Element issuerAssignedID = findNamespaceChild(elemAddRefDoc, namespacesInScope, "IssuerAssignedID");
                    Element typeCode = findNamespaceChild(elemAddRefDoc, namespacesInScope, "TypeCode");
                    Element referenceTypeCode = findNamespaceChild(elemAddRefDoc, namespacesInScope, "ReferenceTypeCode");

                    BT0018InvoicedObjectIdentifierAndSchemeIdentifier bt0018;
                    if (issuerAssignedID != null && typeCode != null) {
                        if (typeCode.getText().equals("50")) {
                            BT0017TenderOrLotReference bt0017 = new BT0017TenderOrLotReference(issuerAssignedID.getText());
                            invoice.getBT0017TenderOrLotReference().add(bt0017);
                        }
                        if (typeCode.getText().equals("130")) {
                            if (referenceTypeCode != null) {
                                bt0018 = new BT0018InvoicedObjectIdentifierAndSchemeIdentifier(new Identifier(referenceTypeCode.getText(), issuerAssignedID.getText()));
                            } else {
                                bt0018 = new BT0018InvoicedObjectIdentifierAndSchemeIdentifier(new Identifier(issuerAssignedID.getText()));
                            }
                            invoice.getBT0018InvoicedObjectIdentifierAndSchemeIdentifier().add(bt0018);
                        }
                    }
                }
            }
        }
        return new ConversionResult<>(errors, invoice);
    }

    @Override
    public void map(BG0000Invoice cenInvoice, Document document, List<IConversionIssue> errors, ErrorCode.Location callingLocation) {
        toBT0017_18(document, cenInvoice, errors);
    }
}