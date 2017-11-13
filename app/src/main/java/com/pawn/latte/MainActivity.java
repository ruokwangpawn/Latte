package com.pawn.latte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pawn.latte.ccfin_cheap_detail.CheapDetailActivity;
import com.pawn.latte.select_tab_view.MeituanSelectActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btMeituanSelect = (Button) findViewById(R.id.bt_meituan_select);
        btMeituanSelect.setOnClickListener(this);
        Button btCheapDetail = (Button) findViewById(R.id.bt_cheap_detail);
        btCheapDetail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_meituan_select:
                startActivity(new Intent(this, MeituanSelectActivity.class));
                break;
            case R.id.bt_cheap_detail:
                startActivity(new Intent(this, CheapDetailActivity.class));
                break;
        }
    }
}
