package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer song;

    private MediaPlayer.OnCompletionListener listenerCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();

            Toast.makeText(getActivity(),"Object is released",Toast.LENGTH_SHORT).show();
        }
    };

    private AudioManager.OnAudioFocusChangeListener listenerFocus = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if(i == AudioManager.AUDIOFOCUS_LOSS)
                releaseMediaPlayer();
            else if(i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
                song.pause();
            else if(i == AudioManager.AUDIOFOCUS_GAIN)
                song.start();
            else if(i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                song.pause();
        }
    };

    private AudioManager manager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Modfiy within fragment ...
        // adding view from the list ...
        View rootView = inflater.inflate(R.layout.word_list,container,false);
        // Fragment has not systemService .. so we call object of it's Activity " Number Activity"
        // by getActivity() method
        manager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("one","lutti",
                R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","otiiko",
                R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",
                R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyisa",
                R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","massokka",
                R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","temmokka",
                R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kenekaku",
                R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","kawinta",
                R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","wo'e",
                R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","na'aacha",
                R.drawable.number_ten,R.raw.number_ten));

        // this refers to Fragment and Fragment is not valid context
        // so we use getActivity ...
        WordAdopter adopter =
                new WordAdopter(getActivity(), words,R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adopter);
        /** add audio with another way **/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                int result = manager.requestAudioFocus(listenerFocus,
                        AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    // getActivity .. too
                    song = new MediaPlayer().create(getActivity(),words.get(i).getSong());
                    song.start();
                    song.setOnCompletionListener(listenerCompletion);
                    Toast.makeText(getActivity(),"done"+" "+song.getAudioSessionId(),Toast.LENGTH_SHORT).show();

                }
            }
        });

        return rootView;
    }
    private void releaseMediaPlayer(){
        if(song != null)
            song.release();
        song = null;
        manager.abandonAudioFocus(listenerFocus);
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
