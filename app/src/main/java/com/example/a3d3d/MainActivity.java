package com.example.a3d3d;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.dyman.easyshow3d.ModelFactory;
import com.dyman.easyshow3d.bean.ModelObject;
import com.dyman.easyshow3d.imp.ModelLoaderListener;
import com.dyman.easyshow3d.view.Show3dsMd2View;
import com.dyman.easyshow3d.view.ShowModelView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static android.opengl.GLES10.glClearColor;

public class MainActivity extends AppCompatActivity {
    private static final int FILE_SELECT_CODE = 0;
    String res="小黄人.stl";


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShowModelView showModelView=findViewById(R.id.showModelView);

        Toolbar toolbar=findViewById(R.id.tb);
        final Button button1=findViewById(R.id.bt1);//飞机
        Button button2=findViewById(R.id.bt2);//小黄人
        Button button3=findViewById(R.id.bt3);//茶壶
        Button button4=findViewById(R.id.bt4);//茶壶

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res="Banshee.obj";
                load();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res="小黄人.stl";
                load();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res="茶壶.obj";
                load();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res="Banshee2.obj";
                load();
            }
        });

        createFolder(getApplicationContext().getExternalFilesDir(""), "3d");






    }

    public void createFolder(File path, String name) {
        File Folder = new File(path + "/" + name);
        if (!Folder.exists())//判断文件夹是否存在，不存在则创建文件夹，已经存在则跳过
        {
            Folder.mkdir();//创建文件夹
        } else {
            Log.i("", "文件夹已存在");
        }
    }
    public void load(){
        //打开文件输入流
        try {
            File file = new File(getApplicationContext().getExternalFilesDir("3d") + "/"+res);
            FileInputStream inputStream = new FileInputStream(file);
            //  File file = getApplicationContext().getFilesDir();
            ModelFactory.decodeFile(MainActivity.this, file.getPath(), new ModelLoaderListener() {
                @Override
                public void loadedUpdate(float progress) {
                    //Log.i(TAG, "模型解析进度： " + progress);

                }


                @Override
                public void loadedFinish(ModelObject modelObject) {
                    if (modelObject != null) {
                        //  解析完成，显示模型
                        ShowModelView showModelView=findViewById(R.id.showModelView);

                        showModelView.setModelObject(modelObject);


                    }
                }

                @Override
                public void loaderCancel() {
                }

                @Override
                public void loaderFailure() {
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

