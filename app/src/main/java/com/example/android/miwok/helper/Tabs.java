package com.example.android.miwok.helper;

/**
 * Created by mohamednagy on 4/12/2017.
 */
public enum Tabs {
    PHRASES(Constants.PHRASES_LABEL, Constants.PHRASES),
    NUMBERS(Constants.NUMBERS_LABEL, Constants.NUMBERS),
    COLORS(Constants.COLORS_LABEL, Constants.COLORS),
    FAMILY(Constants.FAMILY_LABEL, Constants.FAMILY);

    public String m_label;
    public int m_index;

    Tabs(String label, int index){
        m_label = label;
        m_index = index;
    }

}
