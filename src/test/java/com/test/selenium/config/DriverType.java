package com.test.selenium.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

public enum DriverType implements DriverSetup {
    CHROME {
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            HashMap<String, Object> chromePreferences = new HashMap<>();
            chromePreferences.put("profile.password_manager_enabled", false);

            ChromeOptions options = new ChromeOptions();
            options.merge(capabilities);
            options.setHeadless(HEADLESS);
            options.addArguments("--no-default-browser-check");
            options.addArguments("--remote-allow-origins=*");
            options.setExperimentalOption("prefs", chromePreferences);

            return new ChromeDriver(options);
        }
    };

    public final static boolean HEADLESS = Boolean.getBoolean("headless");

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
