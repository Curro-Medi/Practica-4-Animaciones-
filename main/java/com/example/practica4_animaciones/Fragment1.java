package com.example.practica4_animaciones;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Fragment1 extends Fragment implements View.OnClickListener {

    ImageView imaaaa ;
    Button btnEmpezar ;
    Button btnReset ;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SoundPool soundPool;
    GridLayout grid;
    int ultIMpulsada = 0;
    private String mParam1;
    private String mParam2;
    HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
    int aciertos = 0;
    int id = 0;

    public Fragment1() {
        // Required empty public constructor

    }


    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_1, container, false);

    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState){
        btnEmpezar = (Button) getView().findViewById(R.id.btnEmpezar);
        btnReset = (Button) getView().findViewById(R.id.btnResetear);
        btnEmpezar.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        inicializarSoundPool();
        imaaaa = (ImageView) getView().findViewById(R.id.IBtn1);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnEmpezar) {
            btnEmpezar.setEnabled(false);
            btnReset.setEnabled(true);
            empezarPartida();

        }else if(v.getId()==R.id.btnResetear){
            btnEmpezar.setEnabled(true);
            btnReset.setEnabled(false);
            acabarPartida();

        }else{

            comprobarImagen(v.getId());
        }
    }



    public void empezarPartida(){
        asignarAnimales();
    }

    public void asignarAnimales(){

        ArrayList<Integer> imagenes = new ArrayList<Integer>();
        imagenes.add(R.drawable.ciervo);
        imagenes.add(R.drawable.ciervo);
        imagenes.add(R.drawable.conejo);
        imagenes.add(R.drawable.conejo);
        imagenes.add(R.drawable.erizo);
        imagenes.add(R.drawable.erizo);
        imagenes.add(R.drawable.oso);
        imagenes.add(R.drawable.oso);
        imagenes.add(R.drawable.pajaro);
        imagenes.add(R.drawable.pajaro);
        imagenes.add(R.drawable.zorro);
        imagenes.add(R.drawable.zorro);

        Collections.shuffle(imagenes);

        int ciervo = 0;
        int conejo = 0;
        int erizo = 0;
        int oso = 0;
        int pajaro = 0;
        int zorro = 0;

        for(int i = 0; i<imagenes.size();i++){
            id = getResources().getIdentifier("IBtn" + (i+1), "id", getContext().getPackageName());
            ImageView ima = (ImageView) getView().findViewById(id);
            ima.setImageResource(imagenes.get(i));


            switch (imagenes.get(i)){
                case R.drawable.ciervo:

                    if (ciervo==0){
                        ciervo = id;

                    }else{
                        //guardar en el hashmap key=id; value = ciervo      key = ciervo; value = id
                        hashMap.put(ciervo,id);
                        hashMap.put(id,ciervo);
                    }
                    break;

                case R.drawable.conejo:

                        if (conejo==0){
                            conejo = id;

                        }else{
                            hashMap.put(conejo,id);
                            hashMap.put(id,conejo);
                        }
                    break;

                case R.drawable.erizo:

                    if (erizo==0){
                        erizo = id;

                    }else{
                        hashMap.put(erizo,id);
                        hashMap.put(id,erizo);
                    }
                    break;

                case R.drawable.oso:

                    if (oso==0){
                        oso = id;

                    }else{
                        hashMap.put(oso,id);
                        hashMap.put(id,oso);
                    }
                    break;

                case R.drawable.pajaro:

                    if (pajaro==0){
                        pajaro = id;

                    }else{
                        hashMap.put(pajaro,id);
                        hashMap.put(id,pajaro);
                    }
                    break;


                case R.drawable.zorro:

                    if (zorro==0){
                        zorro = id;

                    }else{
                        hashMap.put(zorro,id);
                        hashMap.put(id,zorro);
                    }
                    break;

                default:
                    break;
            }

        }



    }

    public void comprobarImagen(int id){

        if(ultIMpulsada == 0){
            //almacenar id en variable de entorno   reproducimos sonido     cambiamos color de fondo
            ultIMpulsada=id;
            inicializarSoundPool();
            MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.mario_coin);
            mediaPlayer.start();

            ImageView ima = (ImageView) getView().findViewById(id);
            ima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ima.setBackgroundColor(ResourcesCompat.getColor(getContext().getResources(), R.color.cambia, null));
                }
            });


        }else { //ya se pulso imagen antes
            if (ultIMpulsada == hashMap.get(id)){
                aciertos ++;

                MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.mario_bros_woo_hoo);
                mediaPlayer.start();

                ImageView ima = (ImageView) getView().findViewById(id);
                ima.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ima.setBackgroundColor(ResourcesCompat.getColor(getContext().getResources(), R.color.cambia, null));
                    }
                });

        //reproducimos sonido       ponemos color       inutilizamos la imageview       sumar 1 en una variable contador (aciertos)
            }else{
                ImageView ima = (ImageView) getView().findViewById(id);
                MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.mario_bros_firework);
                mediaPlayer.start();
                ima.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ima.setBackgroundColor(ResourcesCompat.getColor(getContext().getResources(), R.color.colorPrimaryDark, null));
                    }
                });
            //cambiar fondo anterior    reproducir sonido
            }

        }

    }

    public void acabarPartida(){

        aciertos = 0;

        for(int i = 0; i<12;i++) {
            id = getResources().getIdentifier("IBtn" + (i + 1), "id", getContext().getPackageName());
            ImageView ima = (ImageView) getView().findViewById(id);
            ima.setBackgroundColor(ResourcesCompat.getColor(getContext().getResources(), R.color.blanco, null));
            ima.setImageResource(0);

        }
        
        //recorrer hashmap
            //eliminamos source --> setimagebitmap(null)
            //cambiamos fondo
            //reseteamos contador

        MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.mario_bros_1_up);
        mediaPlayer.start();
    }

    public void inicializarSoundPool(){

        if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes .Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(3) // número máximo de streams
                    .setAudioAttributes(audioAttributes) // atributos de audio previamente definidos
                    .build();
        }
            // Para versiones inferiores a Lollipop
        else {soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
            // 3-> número máximo de reproducciones simultáneas
            // AudioManager.STREAM_MUSIC -> Tipo de Stream de audio
            // 0 -> Actualmente no tiene efecto. 0 para el de por defecto.
        }
            //comprobar version e inicializar
            //cargar los archivos de audio

    }



}