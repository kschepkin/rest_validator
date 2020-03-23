package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {

    /**
     * @param line
     * @param regular
     * @return match 0
     */
    public String matcher( String line, String regular ) {

        // Create a Pattern object
        Pattern r = Pattern.compile(regular);

        // Now create matcher object.
        Matcher m = r.matcher(line);

        if (m.find( )) {
            return  m.group(0);

        } else {
            return "No log found.";
        }
    }
}