package com.rooftop.seniorityboost.challenge.models.request;

public class TextRequest {

    public String text;
    public Integer chars;

    public Integer validateCharsNumber(String str, Integer chars) {

        if (chars > str.length()) {
            return str.length();
        }
        if (chars < 2) {
            return 2;
        }
        return chars;
    }

}
