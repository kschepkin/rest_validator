package controllers;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorsController {
    /**
     * @param e
     * @return stacktrace
     */
    public String getStacktrace(Exception e){
        e.printStackTrace();
        System.out.println("");
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
