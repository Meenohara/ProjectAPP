package com.example.meena.dailyyoga;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Meena on 29-05-2016.
 */
public class DisplayInfo extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayinfo);
        String string=null;
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            string = extras.getString("STRING_I_NEED");
        }

        try{
            BufferedReader fileReader;
            fileReader = new BufferedReader(
                    new InputStreamReader(getAssets().open(string))) ;
            StringBuilder strBuilder = new StringBuilder() ;
            String line;

            while ((line = fileReader.readLine()) != null) {
                strBuilder.append(line);
                strBuilder.append("\n");
            }

            fileReader.close();
            strBuilder.trimToSize();
            String contentsOfFile = strBuilder.toString();
            System.out.println(contentsOfFile);

            TextView intp = (TextView)findViewById(R.id.interpre);
            intp.setText(contentsOfFile);

        }catch(IOException e){
            // Should never happen!
            throw new RuntimeException(e);
        }

    }

}

