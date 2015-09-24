
package javaminor.SOAPclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the javaminor.SOAPclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PayResponse_QNAME = new QName("http://service/", "payResponse");
    private final static QName _ValidateCardResponse_QNAME = new QName("http://service/", "validateCardResponse");
    private final static QName _ValidateCard_QNAME = new QName("http://service/", "validateCard");
    private final static QName _Pay_QNAME = new QName("http://service/", "pay");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: javaminor.SOAPclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidateCardResponse }
     * 
     */
    public ValidateCardResponse createValidateCardResponse() {
        return new ValidateCardResponse();
    }

    /**
     * Create an instance of {@link PayResponse }
     * 
     */
    public PayResponse createPayResponse() {
        return new PayResponse();
    }

    /**
     * Create an instance of {@link Pay }
     * 
     */
    public Pay createPay() {
        return new Pay();
    }

    /**
     * Create an instance of {@link ValidateCard }
     * 
     */
    public ValidateCard createValidateCard() {
        return new ValidateCard();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "payResponse")
    public JAXBElement<PayResponse> createPayResponse(PayResponse value) {
        return new JAXBElement<PayResponse>(_PayResponse_QNAME, PayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateCardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "validateCardResponse")
    public JAXBElement<ValidateCardResponse> createValidateCardResponse(ValidateCardResponse value) {
        return new JAXBElement<ValidateCardResponse>(_ValidateCardResponse_QNAME, ValidateCardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "validateCard")
    public JAXBElement<ValidateCard> createValidateCard(ValidateCard value) {
        return new JAXBElement<ValidateCard>(_ValidateCard_QNAME, ValidateCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pay }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "pay")
    public JAXBElement<Pay> createPay(Pay value) {
        return new JAXBElement<Pay>(_Pay_QNAME, Pay.class, null, value);
    }

}
