package com.example.sergei.mytrain.fragment;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sergei.mytrain.R;

/**
 * Created by sergei on 25.10.2015.
 */
public class MainFragment extends Fragment {
    public static final String TAG = "MainFragment";
    //static int count = 0;
    Button startBtn;
    boolean isGpsOn = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);



        startBtn = (Button) view.findViewById(R.id.startBtn);

        setTextBtn();
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isGpsOn){

//                    Toast.makeText(getActivity().getApplicationContext(), "Click isGpsOn", Toast.LENGTH_SHORT)
//                            .show();
                }else{
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
//                    Toast.makeText(getActivity().getApplicationContext(), "Click off", Toast.LENGTH_SHORT)
//                            .show();
                }
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {// TODO сделать пункты меню вместо заглушек
        switch (item.getItemId()){
            case R.id.settings :
                Toast.makeText(getActivity().getApplicationContext(),"Settings", Toast.LENGTH_SHORT)
                        .show();break;
            case R.id.delete : break;
            default: break;
        }
        return true;
    }

    private boolean checkedGps(){
        LocationManager locationManager = (LocationManager)getActivity()
                .getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
    private void setTextBtn(){
        isGpsOn = checkedGps();
        startBtn.setText(isGpsOn ? R.string.start_traning : R.string.engine_gps);
    }

    @Override
    public void onPause() {
        super.onPause();
        //count++;
        Log.d(TAG, "onPause: ");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " );
        setTextBtn();
    }
}
