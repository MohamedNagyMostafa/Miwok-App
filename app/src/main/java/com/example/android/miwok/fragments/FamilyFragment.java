package com.example.android.miwok.fragments;


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

import com.example.android.miwok.R;
import com.example.android.miwok.words.Word;
import com.example.android.miwok.words.WordAdopter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

    private MediaPlayer song;
    private MediaPlayer.OnCompletionListener listenerCompletion;
    private AudioManager.OnAudioFocusChangeListener listenerFocus;
    private AudioManager manager;


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list,container,false);

        listenerCompletion = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(mediaPlayer != null)
                    mediaPlayer.release();
                mediaPlayer = null;

                Toast.makeText(getActivity(),"Object is released",Toast.LENGTH_SHORT).show();
            }
        };

        listenerFocus = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int i) {
                if(i == AudioManager.AUDIOFOCUS_LOSS)
                    releaseMediaPlayer();
//                else if(i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
//                    song.pause();
//                else if(i == AudioManager.AUDIOFOCUS_GAIN)
//                    song.start();
                else if(i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                    song.setVolume(0.3f,0.3f);
            }
        };

        // getSystemService : to get the states of System in mobile
        // To get Focus in the correct time
        manager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("father","әpә",
                R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother","әṭa",
                R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son","angsi",
                R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter","tune",
                R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother","taachi",
                R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother","chalitti",
                R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister","teṭe",
                R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister","kolliti",
                R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother","ama",
                R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather","paapa",
                R.drawable.family_grandfather,R.raw.family_grandfather));

        // To recycle list_item ... and save memory
        WordAdopter adopter =
                new WordAdopter(getActivity(), words,R.color.category_family);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adopter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                // request to if we can focus audio to us audio sound
                int result = manager.requestAudioFocus(listenerFocus,
                        AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                // Listener returns two values
                // AUDIOFOCUS_REQUEST_GRANTED , AUDIOFOCUS_REQUEST_FAILED
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    song = new MediaPlayer().create(getActivity(),words.get(i).getSong());
                    // Setup OnCompletionListener to release and stop the media player
                    // once the sound has finished ..
                    song.setOnCompletionListener(listenerCompletion);

                    song.start();
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
