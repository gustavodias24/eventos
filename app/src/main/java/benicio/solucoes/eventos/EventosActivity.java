package benicio.solucoes.eventos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaCas;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import benicio.solucoes.eventos.databinding.ActivityEventosBinding;

public class EventosActivity extends AppCompatActivity {

    private ActivityEventosBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityEventosBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        WindowUtils.configWindow(this);

        mainBinding.btnOpcoes.setOnClickListener(this::configShowPopUp);
    }


    @SuppressLint("NonConstantResourceId")
    private void configShowPopUp(View view) {
        PopupMenu popupMenu = new PopupMenu(EventosActivity.this, view);

        popupMenu.getMenuInflater().inflate(R.menu.menu_principal, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.espacoExpositor) { // Use getItemId() instead of getOrder()
                IntentIntegrator integrator = new IntentIntegrator(EventosActivity.this); // Use EventosActivity.this
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Scaneie um QR Code");
                integrator.setCameraId(0); // Use a câmera traseira
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                return true;
            } else {
                return false;
            }
        });

        popupMenu.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Sem Resultados!", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(this, LeitorQrCodeActivity.class);
                i.putExtra("infos", result.getContents());
                startActivity(i);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}