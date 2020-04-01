package com.example.pro_language;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

import androidx.annotation.Nullable;

public class LanguageActivity extends Activity {
    private Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        //在Activity中设置语言
//        //获取资源对象
//        Resources resources=getResources();
//        //获取设置对象
//        Configuration configuration= resources.getConfiguration();
//        //获取屏幕参数
//        DisplayMetrics display=resources.getDisplayMetrics();
//        //设置语言
//        configuration.locale=Locale.CHINA;
//        //configuration.locale=Locale.ENGLISH;
//        //configuration.locale=Locale.TAIWAN;
//        resources.updateConfiguration(configuration,display);
        setContentView(R.layout.t_language);
        mContext=LanguageActivity.this;

        Button button=findViewById(R.id.btn_open);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        Button button1=findViewById(R.id.btn_open_single);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingleChoiceDialog();
            }
        });

        Button button2=findViewById(R.id.btn_open_multi);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMultiChoiceDialog();
            }
        });
    }
    //创建open Dialog,
    public void openDialog(){
        //AlertDialog.Builder:构建弹窗
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //设置title
        builder.setTitle("提示");
        //设置提示信息
        builder.setMessage("是否退出");
        //设置确定按钮
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //关闭Acitivity
                LanguageActivity.this.finish();
                //关闭dialog
                //dialog.dismiss();
            }
        });
        //设置取消按钮
        builder.setNegativeButton("否",null);
        //创建弹窗
        AlertDialog alertDialog=builder.create();
        //显示弹窗
        alertDialog.show();
    }

    //单选项弹窗
    public void openSingleChoiceDialog(){
        String[] choices=new String[]{"选项1","选项2","选项3"};
        //创建弹窗
        //setSingleChoiceItems方法，设置单选项，checkedItem：设置默认选择Item;
        AlertDialog.Builder builder= new AlertDialog.Builder(this)
                .setTitle("单选")
                .setSingleChoiceItems(choices, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext,"选择"+which,Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确认",null);
        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }

    //多选框弹窗
    public void openMultiChoiceDialog(){
        final String[] choices=new String[]{"选项1","选项2","选项3"};
        final boolean[] checkeds=new boolean[]{false,false,false};
        //创建弹窗
        //setSingleChoiceItems方法，设置单选项，checkedItem：设置默认选择Item;
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("多选");
        builder.setMultiChoiceItems(choices, checkeds, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkeds[which]=isChecked;
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text="";
                for (int i=0;i<choices.length;i++){
                    if (checkeds[i]){
                        text +=choices[i];
                    }
                }
                Toast.makeText(mContext,text,Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }
}
