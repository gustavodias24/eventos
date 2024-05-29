package benicio.solucoes.eventos;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import benicio.solucoes.eventos.databinding.ActivityLeitorQrCodeBinding;

public class LeitorQrCodeActivity extends AppCompatActivity {

    private ActivityLeitorQrCodeBinding mainBinding;

    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityLeitorQrCodeBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        WindowUtils.configWindow(this);

        b = getIntent().getExtras();

        assert b != null;
        mainBinding.textInfos.setText(b.getString("infos", ""));

        mainBinding.btnVoltar.setOnClickListener(v -> finish());
    }
}