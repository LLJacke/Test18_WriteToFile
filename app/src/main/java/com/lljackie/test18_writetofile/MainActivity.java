package com.lljackie.test18_writetofile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final String FileName = "myfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_write = (Button) findViewById(R.id.bt_write);
        Button bt_read = (Button) findViewById(R.id.bt_read);

        bt_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out = null;
                try {
                    FileOutputStream fileOutputStream = openFileOutput(FileName, MODE_PRIVATE);
                    out = new BufferedOutputStream(fileOutputStream);
                    String output = "fire in the hole";
                    try {
                        out.write(output.getBytes(StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        bt_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in = null;
                try {
                    FileInputStream fileInputStream = openFileInput(FileName);
                    in = new BufferedInputStream(fileInputStream);
                    StringBuilder input = new StringBuilder("");
                    int c;
                    try {
                        while ((c= in.read()) != -1){
                            input.append((char)c);
                        }
                        Toast.makeText(MainActivity.this, input.toString(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
