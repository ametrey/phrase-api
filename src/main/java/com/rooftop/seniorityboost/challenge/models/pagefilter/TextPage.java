package com.rooftop.seniorityboost.challenge.models.pagefilter;

public class TextPage {
    private Integer page;
    private Integer rpp;
    private Integer chars;

    public TextPage() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRpp() {
        return rpp;
    }

    public void setRpp(int rpp) {
        this.rpp = rpp;
    }

    public Integer getChars() {
        return chars;
    }

    public void setChars(Integer chars) {
        this.chars = chars;
    }

    public Integer validatePageNumber(Integer page) {
        if (page <= 1) {
            return 0;
        }
        return page - 1;
    }

    public Integer validateRppNumber(Integer rpp) {
        if (rpp < 10) {
            return 10;
        }
        if (rpp > 100) {
            return 100;
        }
        return rpp;
    }

    public Integer validateChars(Integer chars) {
        if (chars < 2) {
            return 2;
        }
        return chars;
    }

}
