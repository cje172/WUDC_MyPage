package com.android.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MyBubbleActivity extends MainActivity {

    ImageButton bubble1;
    ImageButton bubble2;
    ImageButton bubble3;
    ImageButton bubble4;
    ImageButton bubble5;
    ImageButton bubble6;
    ImageButton bubble7;
    ImageButton bubble8;
    ImageButton bubble9;
    ImageButton bubble10;
    ImageButton bubble11;
    ImageButton bubble12;
    ImageButton bubble13;
    ImageButton bubble14;
    ImageButton bubble15;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bubble);

        bubble1 = findViewById(R.id.bubble1);
        bubble2 = findViewById(R.id.bubble2);
        bubble3 = findViewById(R.id.bubble3);
        bubble4 = findViewById(R.id.bubble4);
        bubble5 = findViewById(R.id.bubble5);
        bubble6 = findViewById(R.id.bubble6);
        bubble7 = findViewById(R.id.bubble7);
        bubble8 = findViewById(R.id.bubble8);
        bubble9 = findViewById(R.id.bubble9);
        bubble10 = findViewById(R.id.bubble10);
        bubble11 = findViewById(R.id.bubble11);
        bubble12 = findViewById(R.id.bubble12);
        bubble13 = findViewById(R.id.bubble13);
        bubble14 = findViewById(R.id.bubble14);
        bubble15 = findViewById(R.id.bubble15);

        bubble1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setTitle("내가 남긴 버블"); // 툴바 제목 설정
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                // 액티비티 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                // 위 문장에서 MyMonolog.class 대신 뒤로 돌아갈 화면 입력해야함
                startActivity(intent);
                return true;
            }
            case R.id.notice: {

            }
        }
        return super.onOptionsItemSelected(item);
    }

}