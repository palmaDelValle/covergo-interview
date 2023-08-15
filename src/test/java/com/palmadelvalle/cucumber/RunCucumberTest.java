package com.palmadelvalle.cucumber;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com.palmadelvalle.cucumber")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "features")
public class RunCucumberTest {
}
