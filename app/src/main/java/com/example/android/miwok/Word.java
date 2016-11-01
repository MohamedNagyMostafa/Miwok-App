package com.example.android.miwok;

/**
 * Created by mohamed nagy on 8/22/2016.
 */
public class Word {

    private String englishWord;
    private String miwokWord;
    private int image;
    private int song;
    private static  final int NO_IMAGE = -1;

    public Word(String englishWord,String miwokWord,int song){
        this(englishWord,miwokWord,NO_IMAGE,song);
    }

    public Word(String englishWord,String miwokWord,int image,int song){
        this.englishWord = englishWord;
        this.miwokWord = miwokWord;
        this.image = image;
        this.song = song;
    }

    public String getEnglishWord(){ return englishWord;}

    public String getMiwokWord(){
        return miwokWord;
    }

    public int getImage(){  return image;}

    public int getSong(){ return song;}

    public boolean hasImage(){  return image != NO_IMAGE;}
}
