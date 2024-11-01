package com.example.lab_5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private void showToast() {
        // Step 1: 初始化 Toast 物件
        Toast toast = new Toast(MainActivity.this);
        // Step 2: 設定 Toast 在畫面中的顯示位置 (頂端，x偏移量為0，y偏移量為50)
        toast.setGravity(Gravity.TOP, 0, 50);
        // Step 3: 設定 Toast 顯示的持續時間 (短暫)
        toast.setDuration(Toast.LENGTH_SHORT);
        // Step 4: 取得自定義的 layout
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup)findViewById(R.id.custom_toast_root));
        // Step 5: 將自定義的 layout 設置給 Toast
        toast.setView(layout);
        // Step 6: 顯示 Toast
        toast.show();
    }

    private void showListDialog() {
        // 建立一個字串陣列，用來存放列表中的項目
        final String[] list = {"message1", "message2", "message3", "message4", "message5"};
        // 建立一個 AlertDialog.Builder 物件，並設定上下文
        AlertDialog.Builder dialog_list = new AlertDialog.Builder(MainActivity.this);
        // 設定對話框的標題
        dialog_list.setTitle("使用LIST呈現");
        // 設定對話框的列表項目，並指定點擊事件的監聽器
        dialog_list.setItems(list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 當使用者點擊列表中的某一項時，會觸發這個方法
                // i 代表被點擊項目的索引值
                // 顯示一個 Toast，顯示使用者所選的項目
                Toast.makeText(MainActivity.this,
                        "你選的是" + list[i], Toast.LENGTH_SHORT).show();
            }
        });
        // 顯示對話框
        dialog_list.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button); // 取得 Button 元件
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 建立 AlertDialog.Builder 物件
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("請選擇功能");
                dialog.setMessage("請根據下方按鈕選擇要顯示的物件");

                // 設定取消按鈕
                dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "dialog關閉", Toast.LENGTH_SHORT).show();
                    }
                });

                // 設定自定義Toast按鈕
                dialog.setNegativeButton("自定義Toast", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast(); // 執行顯示自定義 Toast 的方法
                    }
                });

                // 設定顯示列表按鈕
                dialog.setPositiveButton("顯示list", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showListDialog(); // 執行顯示列表的對話框的方法
                    }
                });
                dialog.show(); // 顯示對話框
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}