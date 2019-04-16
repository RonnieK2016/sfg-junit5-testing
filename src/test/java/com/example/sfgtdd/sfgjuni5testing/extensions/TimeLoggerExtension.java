package com.example.sfgtdd.sfgjuni5testing.extensions;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.awt.*;

public class TimeLoggerExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final String START_TIME_TAG = "START_TIME";

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {

        System.out.println("Test took: " + (System.currentTimeMillis() - (Long)getStore(context).get(START_TIME_TAG)));
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        getStore(context).put(START_TIME_TAG, System.currentTimeMillis());
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
    }
}
