package com.palmadelvalle.browserConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum BrowserType {
    FIREFOX, CHROME, OPERA, EDGE, IEXPLORER, SAFARI, CHROMIUM;

    /**
     * This method retunrs the value defined in the Enum looking by his name.
     * @param browserName Browser name to look for.
     * @return BrowserType of the browser.
     * @throws InvalidBrowserException Throws an exception if the browser name is not found between the enum values.
     */
    public static BrowserType lookup(String browserName) throws InvalidBrowserException {
        for (BrowserType browser : BrowserType.values()) {
            if (browser.name().equalsIgnoreCase(browserName)) {
                return browser;
            }
        }
        throw new InvalidBrowserException(String.format("The selected device %s is not an applicable choice", browserName));
    }

    /**
     * This method read from properties to know which browser should be instantiated.
     * @return BrowserType Enum.
     */
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
}
