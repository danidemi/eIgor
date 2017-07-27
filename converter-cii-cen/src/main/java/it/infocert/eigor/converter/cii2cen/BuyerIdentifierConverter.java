package it.infocert.eigor.converter.cii2cen;

import it.infocert.eigor.api.ConversionResult;
import it.infocert.eigor.api.IConversionIssue;
import it.infocert.eigor.api.conversion.ConversionRegistry;
import it.infocert.eigor.model.core.model.BG0000Invoice;
import it.infocert.eigor.model.core.model.BT0046BuyerIdentifierAndSchemeIdentifier;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.reflections.Reflections;

import java.util.List;

/**
 * The Buyer Identifier Custom Converter
 */
public class BuyerIdentifierConverter extends CustomConverter {

    public BuyerIdentifierConverter(Reflections reflections, ConversionRegistry conversionRegistry) {
        super(reflections, conversionRegistry);
    }

    public ConversionResult<BG0000Invoice> toBT0046(Document document, BG0000Invoice invoice, List<IConversionIssue> errors) {
        BT0046BuyerIdentifierAndSchemeIdentifier bt0046 = null;

        Element rootElement = document.getRootElement();
        List<Namespace> namespacesInScope = rootElement.getNamespacesIntroduced();

        List<Element> buyerTradeParties = null;
        Element child = findNamespaceChild(rootElement, namespacesInScope, "SupplyChainTradeTransaction");

        if (child != null) {
            Element child1 = findNamespaceChild(child, namespacesInScope, "ApplicableHeaderTradeAgreement");

            if (child1 != null) {
                buyerTradeParties = findNamespaceChildren(child1, namespacesInScope, "BuyerTradeParty");

                for (Element elem : buyerTradeParties) {

                    Element id = findNamespaceChild(elem, namespacesInScope, "ID");
                    Element globalID = findNamespaceChild(elem, namespacesInScope, "GlobalID");

                    if (id != null && globalID != null) {

                        bt0046 = new BT0046BuyerIdentifierAndSchemeIdentifier(id.getText() + " " + globalID.getText());
                        invoice.getBG0007Buyer(0).getBT0046BuyerIdentifierAndSchemeIdentifier().add(bt0046);
                    }
                }
            }
        }
        return new ConversionResult<>(errors, invoice);
    }
}
