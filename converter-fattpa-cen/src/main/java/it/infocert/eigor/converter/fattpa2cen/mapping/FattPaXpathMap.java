package it.infocert.eigor.converter.fattpa2cen.mapping;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.springframework.util.MultiValueMap;

import java.util.*;

public class FattPaXpathMap {

    private Multimap<String, String> mapping = HashMultimap.create();

    private final List<String> italianPaths = new ArrayList<>(Arrays.asList(
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/Numero",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/Data",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/TipoDocumento",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/Divisa",
            "//FatturaElettronicaBody/DatiBeniServizi/DatiRiepilogo/EsigibilitaIVA",
            "//FatturaElettronicaBody/DatiPagamento/DettaglioPagamento/DataScadenzaPagamento",
            "//FatturaElettronicaBody/DatiGenerali/DatiContratto/CodiceCUP",
            "//FatturaElettronicaBody/DatiGenerali/DatiContratto/IdDocumento",
            "//FatturaElettronicaBody/DatiGenerali/DatiOrdineAcquisto/IdDocumento",
            "//FatturaElettronicaBody/DatiGenerali/DatiRicezione/IdDocumento",
            "//FatturaElettronicaBody/DatiGenerali/DatiDDT/NumeroDDT",
            "//FatturaElettronicaBody/DatiGenerali/DatiContratto/CodiceCIG",
            "//FatturaElettronicaHeader/CedentePrestatore/RiferimentoAmministrazione",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/Causale",
            "//FatturaElettronicaBody/DatiGenerali/DatiFattureCollegate",
            "//FatturaElettronicaBody/DatiGenerali/DatiFattureCollegate/IdDocumento",
            "//FatturaElettronicaBody/DatiGenerali/DatiFattureCollegate/Data",
            "//FatturaElettronicaHeader/CedentePrestatore",
            "//FatturaElettronicaHeader/CedentePrestatore/DatiAnagrafici/Anagrafica/Denominazione",
            "//FatturaElettronicaHeader/CedentePrestatore/DatiAnagrafici/CodiceFiscale",
            "//FatturaElettronicaHeader/CessionarioCommittente/DatiAnagrafici/Anagrafica/CodEORI",
            "//FatturaElettronicaHeader/CedentePrestatore/DatiAnagrafici/RegimeFiscale",
            "//FatturaElettronicaHeader/CedentePrestatore/DatiAnagrafici/iscrizioneREA/CapitaleSociale",
            "//FatturaElettronicaHeader/CedentePrestatore/Sede",
            "//FatturaElettronicaHeader/CedentePrestatore/Sede/Indirizzo",
            "//FatturaElettronicaHeader/CedentePrestatore/Sede/NumeroCivico",
            "//FatturaElettronicaHeader/CedentePrestatore/Sede/Comune",
            "//FatturaElettronicaHeader/CedentePrestatore/Sede/CAP",
            "//FatturaElettronicaHeader/CedentePrestatore/Sede/Provincia",
            "//FatturaElettronicaHeader/CedentePrestatore/Sede/Nazione",
            "//FatturaElettronicaHeader/CedentePrestatore/Contatti",
            "//FatturaElettronicaHeader/CedentePrestatore/DatiAnagrafici/Anagrafica/Nome",
            "//FatturaElettronicaHeader/CedentePrestatore/Contatti/Telefono",
            "//FatturaElettronicaHeader/CedentePrestatore/Contatti/Email",
            "//FatturaElettronicaHeader/FatturaCessionarioCommittente",
            "//FatturaElettronicaHeader/FatturaCessionarioCommittente/DatiAnagrafici/Anagrafica/Denominazione",
            "//FatturaElettronicaHeader/CessionarioCommittente/DatiAnagrafici/CodiceFiscale",
            "//FatturaElettronicaHeader/CessionarioCommittente/DatiAnagrafici/Anagrafica/CodEORI",
            "//FatturaElettronicaHeader/DatiTrasmissione/PECDestinatario",
            "//FatturaElettronicaHeader/CessionarioCommittente/Sede/Indirizzo",
            "//FatturaElettronicaHeader/CessionarioCommittente/Sede/NumeroCivico",
            "//FatturaElettronicaHeader/CessionarioCommittente/Sede/Comune",
            "//FatturaElettronicaHeader/CessionarioCommittente/Sede/CAP",
            "//FatturaElettronicaHeader/CessionarioCommittente/Sede/Provincia",
            "//FatturaElettronicaHeader/CessionarioCommittente/Sede/Nazione",
            "//FatturaElettronicaBody/DatiPagamento/DettaglioPagamento/Beneficiario",
            "//FatturaElettronicaHeader/RappresentanteFiscale",
            "//FatturaElettronicaHeader/RappresentanteFiscale/DatiAnagrafici/Anagrafica/Denominazione",
            "//FatturaElettronicaBody/DatiGenerali/DatiTrasporto",
            "//FatturaElettronicaBody/DatiGenerali/DatiTrasporto/DataOraConsegna",
            "//FatturaElettronicaBody/DatiGenerali/DatiTrasporto/IndirizzoResa",
            "//FatturaElettronicaBody/DatiGenerali/DatiTrasporto/IndirizzoResa/Indirizzo",
            "//FatturaElettronicaBody/DatiGenerali/DatiTrasporto/IndirizzoResa/NumeroCivico",
            "//FatturaElettronicaBody/DatiGenerali/DatiTrasporto/IndirizzoResa/Comune",
            "//FatturaElettronicaBody/DatiGenerali/DatiTrasporto/IndirizzoResa/CAP",
            "//FatturaElettronicaBody/DatiGenerali/DatiTrasporto/IndirizzoResa/Provincia",
            "//FatturaElettronicaBody/DatiGenerali/DatiTrasporto/IndirizzoResa/Nazione",
            "//FatturaElettronicaBody/DatiPagamento",
            "//FatturaElettronicaBody/DatiPagamento/DettaglioPagamento/ModalitaPagamento",
            "//FatturaElettronicaBody/DatiPagamento/DettaglioPagamento/CodicePagamento",
            "//FatturaElettronicaBody/DatiPagamento/DettaglioPagamento/IBAN",
            "//FatturaElettronicaBody/DatiPagamento/DettaglioPagamento/BIC",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/Natura",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/AliquotaIVA",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/Descrizione",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/Descrizione",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiRitenuta",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiRitenuta/ImportoRitenuta",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiRitenuta/AliquotaRitenuta",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiRitenuta/TipoRitenuta",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiRitenuta/CausalePagamento",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/Natura",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/AliquotaIVA",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/Descrizione",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/Descrizione",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiBollo",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiBollo/ImportoBollo",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiCassaPrevidenziale",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiCassaPrevidenziale/ImportoContributoCassa",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiCassaPrevidenziale/ImponibileCassa",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiCassaPrevidenziale/AlCassa",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiCassaPrevidenziale/Natura",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiCassaPrevidenziale/AliquotaIVA",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/DatiCassaPrevidenziale/TipoCassa",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/ImportoTotaleDocumento",
            "//FatturaElettronicaBody/DatiGenerali/DatiGeneraliDocumento/Arrotondamento",
            "//FatturaElettronicaBody/DatiPagamento/DettaglioPagamento/ImportoPagamento",
            "//FatturaElettronicaBody/DatiBeniServizi/DatiRiepilogo",
            "//FatturaElettronicaBody/DatiBeniServizi/DatiRiepilogo/ImponibileImporto",
            "//FatturaElettronicaBody/DatiBeniServizi/DatiRiepilogo/Imposta",
            "//FatturaElettronicaBody/DatiBeniServizi/DatiRiepilogo/Natura",
            "//FatturaElettronicaBody/DatiBeniServizi/DatiRiepilogo/AliquotaIVA",
            "//FatturaElettronicaBody/DatiBeniServizi/DatiRiepilogo/RiferimentoNormativo",
            "//FatturaElettronicaBody/Allegati",
            "//FatturaElettronicaBody/Allegati/NomeAttachment",
            "//FatturaElettronicaBody/Allegati/DescrizioneAttachment",
            "//FatturaElettronicaBody/Allegati/Attachment",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/NumeroLinea",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/Descrizione",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/Quantita",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/UnitaMisura",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/PrezzoTotale",
            "//FatturaElettronicaBody/DatiRiepilogo/DettaglioLinee/RiferimentoAmministrazione",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/DataInizioPeriodo",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/DataFinePeriodo",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/ScontoMaggiorazione",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/ScontoMaggiorazione/Importo",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/ScontoMaggiorazione/Percentuale",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/ScontoMaggiorazione",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/ScontoMaggiorazione/Importo",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/ScontoMaggiorazione/Percentuale",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/PrezzoUnitario",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/UnitaMisura",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/UnitaMisura",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/Natura",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/AliquotaIVA",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/CodiceArticolo",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/CodiceArticolo/CodiceValore",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/CodiceArticolo/CodiceTipo",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/AltriDatiGestionali",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/AltriDatiGestionali/TipoDato",
            "//FatturaElettronicaBody/DatiBeniServizi/DettaglioLinee/AltriDatiGestionali/RiferimentoTesto"
            ));

    private final List<String> invoicePaths = new ArrayList<>(Arrays.asList(
            "/BT0001",
            "/BT0002",
            "/BT0003",
            "/BT0005",
            "/BT0008",
            "/BT0009",
            "/BT0011",
            "/BT0012",
            "/BT0013",
            "/BT0015",
            "/BT0016",
            "/BT0017",
            "/BT0019",
            "/BG0001/BT0022",
            "/BG0003",
            "/BG0003/BT0025",
            "/BG0003/BT0026",
            "/BG0004",
            "/BG0004/BT0027",
            "/BG0004/BT0029",
            "/BG0004/BT0030",
            "/BG0004/BT0032",
            "/BG0004/BT0033",
            "/BG0004/BG0005",
            "/BG0004/BG0005/BT0035",
            "/BG0004/BG0005/BT0036",
            "/BG0004/BG0005/BT0037",
            "/BG0004/BG0005/BT0038",
            "/BG0004/BG0005/BT0039",
            "/BG0004/BG0005/BT0040",
            "/BG0004/BG0006",
            "/BG0004/BG0006/BT0041",
            "/BG0004/BG0006/BT0042",
            "/BG0004/BG0006/BT0043",
            "/BG0007",
            "/BG0007/BT0044",
            "/BG0007/BT0046",
            "/BG0007/BT0047",
            "/BG0007/BT0049",
            "/BG0007/BG0008/BT0050",
            "/BG0007/BG0008/BT0051",
            "/BG0007/BG0008/BT0052",
            "/BG0007/BG0008/BT0053",
            "/BG0007/BG0008/BT0054",
            "/BG0007/BG0008/BT0055",
            "/BG0010/BT0059",
            "/BG0011",
            "/BG0011/BT0062",
            "/BG0013",
            "/BG0013/BT0072",
            "/BG0015",
            "/BG0015/BT0075",
            "/BG0015/BT0076",
            "/BG0015/BT0077",
            "/BG0015/BT0078",
            "/BG0015/BT0079",
            "/BG0015/BT0080",
            "/BG0016",
            "/BG0016/BT0081",
            "/BG0016/BT0083",
            "/BG0016/BG0017/BT0084",
            "/BG0016/BG0017/BT0086",
            "/BG0020",
            "/BG0020/BT0095",
            "/BG0020/BT0096",
            "/BG0020/BT0097",
            "/BG0020/BT0098",
            "/BG0020",
            "/BG0020/BT0095",
            "/BG0020/BT0096",
            "/BG0020/BT0097",
            "/BG0020/BT0098",
            "/BG0021",
            "/BG0021/BT0102",
            "/BG0021/BT0103",
            "/BG0021/BT0104",
            "/BG0021/BT0105",
            "/BG0021",
            "/BG0021/BT0099",
            "/BG0021",
            "/BG0021/BT0099",
            "/BG0021/BT0100",
            "/BG0021/BT0101",
            "/BG0021/BT0102",
            "/BG0021/BT0103",
            "/BG0021/BT0105",
            "/BG0022/BT0112",
            "/BG0022/BT0114",
            "/BG0022/BT0115",
            "/BG0023",
            "/BG0023/BT0116",
            "/BG0023/BT0117",
            "/BG0023/BT0118",
            "/BG0023/BT0119",
            "/BG0023/BT0120",
            "/BG0024",
            "/BG0024/BT0122",
            "/BG0024/BT0123",
            "/BG0024/BT0125",
            "/BG0025",
            "/BG0025/BT0126",
            "/BG0025/BT0127",
            "/BG0025/BT0129",
            "/BG0025/BT0130",
            "/BG0025/BT0131",
            "/BG0025/BT0133",
            "/BG0025/BG0026/BT0134",
            "/BG0025/BG0026/BT0135",
            "/BG0025/BG0027",
            "/BG0025/BG0027/BT0136",
            "/BG0025/BG0027/BT0138",
            "/BG0025/BG0028",
            "/BG0025/BG0028/BT0141",
            "/BG0025/BG0028/BT0143",
            "/BG0025/BG0029/BT0146",
            "/BG0025/BG0029/BT0149",
            "/BG0025/BG0029/BT0150",
            "/BG0025/BG0030/BT0151",
            "/BG0025/BG0030/BT0152",
            "/BG0025/BG0031",
            "/BG0025/BG0031/BT0153",
            "/BG0025/BG0031/BT0154",
            "/BG0025/BG0031/BG0032",
            "/BG0025/BG0031/BG0032/BT0160",
            "/BG0025/BG0031/BG0032/BT0161"
            ));

    public Multimap<String, String> getMapping() {
        if (mapping.isEmpty()) {
            for (int i = 0; i < italianPaths.size(); i++) {
                mapping.put(invoicePaths.get(i), italianPaths.get(i));
            }
            return mapping;
        } else {
            return mapping;
        }
    }
}