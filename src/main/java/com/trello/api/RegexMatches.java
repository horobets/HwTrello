package com.trello.api;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {

    public static List<String> getRegexMatches(String text, String regex) {
        List<String> matches = new ArrayList<>();

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);

        while (m.find()) {
            matches.add(m.group());
        }
        return matches;
    }
}
