package javaminor.main;

import javaminor.SOAPclient.PaymentImpl;
import javaminor.SOAPclient.PaymentImplService;
import javaminor.data.access.DataHandler;
import javaminor.data.object.ScanItemHandler;
import javaminor.logic.ScanItemRepository;
import javaminor.util.Populator;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by alex on 9/23/15.
 */
public class ServletMain implements ServletContextListener {
    private static Logger logger = LogManager.getLogger(ServletMain.class.getName());

    @Getter
    private static final Populator populator = new Populator();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.error("Starting initialization.");

        // populate with data
        populator.populate();

        // assign the populated data to the repository
        //ScanItemRepository.setScanItems(populator.getAllScanItems());
        ScanItemRepository.setScanItems(new ScanItemHandler(new DataHandler()).getAllScanItems());

        logger.error("Finished initializing.");
        logger.error("Checking payment validation.");

        PaymentImplService service = new PaymentImplService();
        PaymentImpl port = service.getPaymentImplPort();
        try {
            logger.error(port.validateCard("123456789", 100));
            logger.error("Done checking payment validation.");
        } catch (Exception e){
            logger.error("Payment validation failed.");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug("Context destroyed.");
    }
}
