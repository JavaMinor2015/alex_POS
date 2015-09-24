package javaminor.main;

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
        logger.debug("Starting initialization.");

        // populate with data
        populator.populate();

        // assign the populated data to the repository
        ScanItemRepository.setScanItems(populator.getAllScanItems());

        logger.debug("Finished initializing.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug("Context destroyed.");
    }
}
