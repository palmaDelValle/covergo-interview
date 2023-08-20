package com.palmadelvalle.browserConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum BrowserType {
    FIREFOX, CHROME, OPERA, EDGE, IEXPLORER, SAFARI, CHROMIUM;

    public static BrowserType lookup(String browserName) throws InvalidBrowserException {
        for (BrowserType browser : BrowserType.values()) {
            if (browser.name().equalsIgnoreCase(browserName)) {
                return browser;
            }
        }
        throw new InvalidBrowserException(String.format("The selected device %s is not an applicable choice", browserName));
    }

    public static BrowserType getBrowser() {
        BrowserType browserType = BrowserType.CHROME;
        if (System.getProperty("browser") != null) {
            try {
                browserType = BrowserType.lookup(System.getProperty("browser"));
            } catch (Exception e) {
                log.warn("Provided device does not match options. Using Firefox instead. ", e);
            }
        }
        return browserType;
    }

    public static BrowserType getBrowser(String browserName) throws InvalidBrowserException {
        return lookup(browserName);
    }
}
