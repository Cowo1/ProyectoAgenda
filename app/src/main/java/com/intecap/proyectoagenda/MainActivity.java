package com.intecap.proyectoagenda;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

        private boolean dobleTap = false;
        private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        if (getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }

        SharedViewModel viewModel = new ViewModelProvider(this).get(SharedViewModel.class);








        BottomNavigationView navView = findViewById(R.id.bnvMenuNavegacionInferior);

        AppBarConfiguration abcBarraConfiguracion = new AppBarConfiguration.Builder(
                R.id.paginaCrearActividad,R.id.paginaActividades,R.id.paginaImportantes, R.id.paginaNotas, R.id.paginaActFinalizadas).build();
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fcvContenedorFragmentos);

        NavController nvcControladorNavegacion = navHostFragment.getNavController();

        nvcControladorNavegacion.setGraph(R.navigation.nav);

        NavigationUI.setupWithNavController(navView,nvcControladorNavegacion);

        FloatingActionButton fabBotonFlotante = findViewById(R.id.fabBotonFlotante);
        fabBotonFlotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Boton Flotante seleccionado", Snackbar.LENGTH_LONG).setAction("Deshacer Accion", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Accion realizada del snackbar", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                nvcControladorNavegacion.navigate(R.id.paginaCrearActividad);


            }
        });

        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.itmActividades)
                    nvcControladorNavegacion.navigate(R.id.paginaActividades);
                else if(item.getItemId() == R.id.itmImportantes)
                    nvcControladorNavegacion.navigate(R.id.paginaImportantes);
                else if (item.getItemId() == R.id.itmNotas) {
                    nvcControladorNavegacion.navigate(R.id.paginaNotas);

                }else if (item.getItemId() == R.id.itmActFinalizadas)
                    nvcControladorNavegacion.navigate(R.id.paginaActFinalizadas);
                return true;
            }
        });

        navView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {

            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.itmActividades) {
                    Toast.makeText(MainActivity.this, "Item Actividades Re-Seleccionado", Toast.LENGTH_LONG).show();
                } else if (item.getItemId() == R.id.itmImportantes) {
                    Toast.makeText(MainActivity.this, "Item Actividades Importantes Re-Seleccionado", Toast.LENGTH_LONG).show();
                } else if (item.getItemId() == R.id.itmNotas) {
                    Toast.makeText(MainActivity.this, "Item Notas Re-Seleccionado", Toast.LENGTH_LONG).show();
                } else if (item.getItemId() == R.id.itmActFinalizadas) {
                    Toast.makeText(MainActivity.this, "Item Actividades Finalizadas Re-Seleccionado", Toast.LENGTH_LONG).show();
                }
            }
        });






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(dobleTap){
                    finish();
                }
                dobleTap = true;
                Toast.makeText(MainActivity.this, "Presione nuevamente para salir de la app", Toast.LENGTH_SHORT).show();
                handler.postDelayed(()-> dobleTap = false, 2000);

            }
        });

    }


}