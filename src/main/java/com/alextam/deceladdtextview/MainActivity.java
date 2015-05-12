package com.alextam.deceladdtextview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DecalAddTextView tv_decal = (DecalAddTextView)findViewById(R.id.tv_decal);
        Button btn_plus = (Button)findViewById(R.id.btn_plus);
        Button btn_minus = (Button)findViewById(R.id.btn_minus);

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_decal.setValue(66250.288);
                tv_decal.startDecalPlus();
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_decal.setValue(66250.288);
                tv_decal.startDecalMinus();
            }
        });

    }

}
