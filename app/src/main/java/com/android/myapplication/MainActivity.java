package com.android.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    final private static String RECORD_FILE = "/sdcard/recorded.mp4";

    ImageButton monolog_btn;
    ImageButton bubble_btn;
    ImageButton hashtag_btn;
    Button signal_btn;
    Button security_btn;
    Button help_btn;
    Button info_change_btn;
    ImageView profileImage;

    TextView nameView;
    TextView phoneNumView;
    TextView accountView;
    EditText edt_name;
    EditText edt_phoneNum;
    EditText edt_account;
    Button info_save_btn;
    ImageView info_pencil1;
    ImageView info_pencil2;
    ImageView info_pencil3;
    FloatingActionButton fab;

    MediaRecorder recorder;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setTitle(""); // 툴바 제목 설정


        monolog_btn = findViewById(R.id.monolog_btn);
        bubble_btn = findViewById(R.id.bubble_btn);
        hashtag_btn = findViewById(R.id.hashtag_btn);
        signal_btn = findViewById(R.id.signal_btn);
        security_btn = findViewById(R.id.security_btn);
        help_btn = findViewById(R.id.help_btn);
        info_change_btn = findViewById(R.id.info_change_btn);
        profileImage = findViewById(R.id.profileImage);


        nameView = findViewById(R.id.nameView);
        phoneNumView = findViewById(R.id.phoneNumView);
        accountView = findViewById(R.id.accountView);
        edt_name = findViewById(R.id.edt_name);
        edt_phoneNum = findViewById(R.id.edt_phoneNum);
        edt_account = findViewById(R.id.edt_account);
        info_save_btn = findViewById(R.id.info_save_btn);

        info_pencil1 = findViewById(R.id.info_pencil1);
        info_pencil2 = findViewById(R.id.info_pencil2);
        info_pencil3 = findViewById(R.id.info_pencil3);

        // SD카드
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, "recorded.mp4");
        filename = file.getAbsolutePath();
        Log.d("MainActivity", "저장할 파일 명: " + filename);

        // 녹음 기능
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permissionCheck();
                recordAudio();
                //Intent recordIntent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                //startActivity(recordIntent);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            }
        });

        // 마이페이지 - 회원정보 수정
        // 프로필 수정 버튼 눌렀을 때
        info_change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info_change_btn.setVisibility(View.INVISIBLE);
                info_save_btn.setVisibility(View.VISIBLE);

                info_pencil1.setVisibility(View.VISIBLE);
                info_pencil2.setVisibility(View.VISIBLE);
                info_pencil3.setVisibility(View.VISIBLE);

                nameView.setVisibility(View.INVISIBLE);
                phoneNumView.setVisibility(View.INVISIBLE);
                accountView.setVisibility(View.INVISIBLE);

                edt_name.setVisibility(View.VISIBLE);
                edt_phoneNum.setVisibility(View.VISIBLE);
                edt_account.setVisibility(View.VISIBLE);

                // 저장 버튼 눌렀을 때
                info_save_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "프로필 변경이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        info_save_btn.setVisibility(View.INVISIBLE);
                        info_change_btn.setVisibility(View.VISIBLE);

                        nameView.setText(edt_name.getText().toString() + "님");
                        phoneNumView.setText(edt_phoneNum.getText().toString());
                        accountView.setText(edt_account.getText().toString());

                        nameView.setVisibility(View.VISIBLE);
                        phoneNumView.setVisibility(View.VISIBLE);
                        accountView.setVisibility(View.VISIBLE);

                        edt_name.setVisibility(View.INVISIBLE);
                        edt_phoneNum.setVisibility(View.INVISIBLE);
                        edt_account.setVisibility(View.INVISIBLE);
                        info_pencil1.setVisibility(View.INVISIBLE);
                        info_pencil2.setVisibility(View.INVISIBLE);
                        info_pencil3.setVisibility(View.INVISIBLE);

                    }
                });
            }
        });

        // 마이페이지 - 내 독백 화면으로 이동
        monolog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyMonologActivity.class);
                startActivity(intent);
            }
        });

        // 마이페이지 - 내가 남긴 버블 화면으로 이동
        bubble_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyBubbleActivity.class);
                startActivity(intent);
            }
        });

        // 마이페이지 - 내 해시태그 화면으로 이동
        hashtag_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyHashTagActivity.class);
                startActivity(intent);
            }
        });

        // 마이페이지 - 알림 화면으로 이동
        signal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignalActivity.class);
                startActivity(intent);
            }
        });

        // 마이페이지 - 개인/보안 화면으로 이동
        security_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecurityActivity.class);
                startActivity(intent);
            }
        });

        // 마이페이지 - 고객센터/도움말 화면으로 이동
        help_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

    }

    public void permissionCheck() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED
        || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1);
        }
    }

    // 음성녹음
    private void recordAudio() {
        recorder = new MediaRecorder();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);

        } else {
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            recorder.setOutputFile(filename);

            try {
                recorder.prepare();
                recorder.start();
                Toast.makeText(getApplicationContext(), "녹음 시작했습니다.", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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
                Intent intent = new Intent(getApplicationContext(), MyMonologActivity.class);
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