package org.log;

import org.reporter.ExtentManager;

public class Logger {
    public static void logstep(String msg ){
        System.out.println(msg);
        ExtentManager.logStep(msg);
    }
}
