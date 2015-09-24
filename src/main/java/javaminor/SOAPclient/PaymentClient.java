package javaminor.SOAPclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alex on 9/24/15.
 */
public class PaymentClient {

    protected static Logger logger = LogManager.getLogger(PaymentClient.class.getName());

    public boolean checkValidity(String identifier, double amount){
        try{
            PaymentImplService service = new PaymentImplService();
            logger.info("Connecting to the service");
            PaymentImpl port = service.getPaymentImplPort();
            logger.info("Returning service value");
            return port.validateCard(identifier, amount);
        }catch(Exception e){
            e.printStackTrace();
            logger.info("Could not reach validation service");
            return false;
        }
    }
}
