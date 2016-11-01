package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mohamed nagy on 8/22/2016.
 */
public class WordAdopter extends ArrayAdapter<Word> {

    private int backgroundColor;
    private MediaPlayer song;

    // Constructor ..
    public WordAdopter(Context context,
                       ArrayList<Word> objects,
                       int backgroundColor) {
        super(context, 0, objects);
        this.backgroundColor = backgroundColor;
    }


    // Method which get View which will recycle list_item ..
    // Used To Add and modify some features ...
    // @params convertView : view which will recycle to each item in list
    // .. In this case(list_item)
    // @params position : position of The item List which Recycle ... position of Object that will created in list
    // .. In this case(Word's Objects position)
    // @params parent : view which the list will Appear in it
    // .. In this case (word_list)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get object from list ...
        Word ob = getItem(position);

        View listViewRecycle = convertView;
        if(listViewRecycle == null){
            /// inflate used to convert xml code to View
            listViewRecycle = LayoutInflater.from(
                    getContext()).inflate(R.layout.list_item,parent,false);
        }
        TextView  englishWord = (TextView)
                listViewRecycle.findViewById(R.id.default_text_view);
        final TextView  miwordWord = (TextView)
                listViewRecycle.findViewById(R.id.miwork_text_view);
        ImageView imageSrc = (ImageView)
                listViewRecycle.findViewById(R.id.imageSrc);
        LinearLayout linearLayout= (LinearLayout)
                listViewRecycle.findViewById(R.id.layout);
        ListView listView = (ListView)
                listViewRecycle.findViewById(R.id.list);

        linearLayout.setBackgroundColor(
                ContextCompat.getColor(getContext(),backgroundColor));
        englishWord.setText(ob.getEnglishWord());
        miwordWord.setText(ob.getMiwokWord());
//
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getItem(position).getSong().start();
//            }
//        });
        if(ob.hasImage()) {
            imageSrc.setImageResource(ob.getImage());
            // Used for PhrasesActivity that have not image within it ..
            imageSrc.setVisibility(ImageView.VISIBLE);
        }
        else
            imageSrc.setVisibility(ImageView.GONE);


        return listViewRecycle;
    }
}
