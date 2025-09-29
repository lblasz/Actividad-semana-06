package com.example.actividadsemana06;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {

    Button btnAceptar;
    EditText editTextNombre;
    ImageView imageView;
    Button btnDescargar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnAceptar = findViewById(R.id.btnAceptar);
        editTextNombre = findViewById(R.id.editTextNombre);
        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_launcher_background);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stNombre = editTextNombre.getText().toString();
               Toast.makeText(Inicio.this, stNombre, Toast.LENGTH_SHORT).show();
            }
        });

        btnDescargar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = loadImageFromNetwork("https://www.citypng.com/photo/27110/hd-windows-11-logo-icon-transparent-background");
                        mImageView.post(new Runnable() {
                            @Override
                            public void run() {
                                mImageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();
            }

        });

    }
}
