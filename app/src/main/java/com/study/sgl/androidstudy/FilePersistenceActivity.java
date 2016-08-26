package com.study.sgl.androidstudy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.study.sgl.tools.base.BaseActivity;
import com.study.sgl.tools.util.StringUtil;
import com.study.sgl.tools.util.T;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FilePersistenceActivity extends BaseActivity {

    private EditText name;
    private static final String FILE_NAME = "data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_persistence);

        name = (EditText) findViewById(R.id.edit_name);

        String tempString = load();
        if (!StringUtil.isEmpty(tempString)) {
            name.setText(tempString);
            name.setSelection(tempString.length());
            T.s(this, "从文件中获取到了!");
        }
    }

    /**
     * 从文件中读取
     */
    private String load() {
        FileInputStream fileInputStream;
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileInputStream = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 保存到文件
     */
    public void save(View view) {

        FileOutputStream fileOutputStream;
        BufferedWriter writer = null;

        try {
            fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            writer.write(name.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 保存到 SharedPreferences
     */
    public void saveToSP(View view) {
        SharedPreferences sp = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        sp.edit().putString("name", name.getText().toString()).apply();
    }

    /**
     * 从 SharedPreferences 读取
     */
    public void readFromSP(View view) {
        SharedPreferences sp = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        T.s(this, sp.getString("name","null"));
    }
}
