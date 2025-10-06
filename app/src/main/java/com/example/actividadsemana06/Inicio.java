package com.example.actividadsemana06;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Inicio extends AppCompatActivity {

    Button btnAceptar;
    EditText editTextNombre;
    ImageView imageView;
    Button btnDescargar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // Inicialización de todos los Views
        btnAceptar = findViewById(R.id.btnAceptar);
        editTextNombre = findViewById(R.id.editTextNombre);
        imageView = findViewById(R.id.imageView);
        btnDescargar = findViewById(R.id.btnDescargar);

        imageView.setImageResource(R.drawable.ic_launcher_background);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stNombre = editTextNombre.getText().toString();
               Toast.makeText(Inicio.this, stNombre, Toast.LENGTH_SHORT).show();
            }
        });

        // Listener de clic para el botón de descarga
        btnDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String imageUrl = "https://www.citypng.com/photo/27110/hd-windows-11-logo-icon-transparent-background";
                        final Bitmap bitmap = loadImageFromNetwork(imageUrl);

                        // Para actualizar la UI, vuelve al hilo principal
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                if (bitmap != null) {
                                    imageView.setImageBitmap(bitmap);
                                    Toast.makeText(Inicio.this, "Imagen descargada ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Inicio.this, "Error al descargar ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }

    // Método para descargar una imagen desde una URL
    private Bitmap loadImageFromNetwork(String urlString) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            inputStream = connection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
