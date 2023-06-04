package com.test.selenium.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

import static com.test.selenium.config.DriverType.CHROME;
import static com.test.selenium.config.DriverType.valueOf;

public class DriverFactory {

    private static final Logger LOG = (Logger) LogManager.getLogger(DriverFactory.class);
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");

    private RemoteWebDriver driver;
    private final DriverType selectedDriverType;

    public DriverFactory() {
        DriverType driverType = CHROME;
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        try {
            driverType = valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            LOG.warn("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            LOG.warn("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    public RemoteWebDriver getDriver() throws Exception {
        if (null == driver) {
            instantiateWebDriver(selectedDriverType);
        }

        return driver;
    }

    public RemoteWebDriver getStoredDriver() {
        return driver;
    }

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    private void instantiateWebDriver(DriverType driverType) throws MalformedURLException {
        LOG.info("Local Operating System: " + operatingSystem);
        LOG.info("Local Architecture: " + systemArchitecture);
        LOG.info("Selected Browser: " + selectedDriverType);

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        driver = driverType.getWebDriverObject(desiredCapabilities);
    }
}

