package com.pawn.latte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pawn.latte.ccfin_cheap_detail.CheapDetailActivity;
import com.pawn.latte.select_tab_view.MeituanSelectActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    private List<User> getJsonData(String url) {

        List<User> userList = new ArrayList<>();
        try {
            String jsonString = readStream(new URL(url).openStream());
            JSONObject jsonObject;
            User user;
            jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                user = new User();
                user.name = jsonObject.getString("name");
                userList.add(user);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private String readStream(InputStream is) {
        InputStreamReader isr;
        String result = "";
        String line = "";
        try {
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    class User {

        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
