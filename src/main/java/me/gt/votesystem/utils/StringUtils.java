package me.gt.votesystem.utils;

import org.apache.commons.lang3.StringEscapeUtils;

public class StringUtils {

    public static String escapeHtml(String input) {
        return StringEscapeUtils.escapeHtml4(input);
    }
}
