package javaminor.main;

import javaminor.logic.ScanItemRepository;
import javaminor.util.Populator;
import lombok.Getter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by alex on 9/23/15.
 */
public class ServletMain implements ServletContextListener {

    @Getter
    private static final Populator populator = new Populator();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        populator.populate();
        ScanItemRepository.setScanItems(populator.getAllScanItems());
        System.err.println("asdasd");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
