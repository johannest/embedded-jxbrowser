package org.vaadin.test;

import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;

public class JxBrowserOptionsBuilder {
    private final Engine engine;
    private EngineOptions.Builder builder;
    private final String license = "USE_YOUR_OWN_TRIAL_OR_REAL_LICENSE_HERE";

    public JxBrowserOptionsBuilder() {
        builder = EngineOptions.newBuilder(RenderingMode.HARDWARE_ACCELERATED);
        builder.licenseKey(license);
        EngineOptions engineOptions = builder.build();
        engine = Engine.newInstance(engineOptions);
    }

    public Engine getEngine() {
        return engine;
    }
}
