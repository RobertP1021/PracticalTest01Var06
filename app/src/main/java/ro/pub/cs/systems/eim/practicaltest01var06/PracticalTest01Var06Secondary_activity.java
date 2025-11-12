package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var06Secondary_activity extends AppCompatActivity {

    private Button exportData, close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_var06_secondary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ro.pub.cs.systems.eim.PracticalTest01Var06Secondary_activity.Votes")) {
            int[] votes = intent.getIntArrayExtra("ro.pub.cs.systems.eim.PracticalTest01Var06Secondary_activity.Votes");
            int leadingVotes = intent.getIntExtra("ro.pub.cs.systems.eim.PracticalTest01Var06Secondary_activity.Leading", 0);
            TextView votesA = findViewById(R.id.optionA);
            TextView votesB = findViewById(R.id.optionB);
            TextView votesC = findViewById(R.id.optionC);
            TextView votesD = findViewById(R.id.optionD);
            TextView leadingOption = findViewById(R.id.leadingVotes);

            votesA.setText("Option A: " + votes[0] + " votes");
            votesB.setText("Option B: " + votes[1] + " votes");
            votesC.setText("Option C: " + votes[2] + " votes");
            votesD.setText("Option D: " + votes[3] + " votes");
            leadingOption.setText("Leading option has " + leadingVotes + 1 + " votes");
        }

        exportData = findViewById(R.id.exportButton);
        close = findViewById(R.id.closeButton);

        ButtonClickListener listener = new ButtonClickListener();

        exportData.setOnClickListener(listener);
        close.setOnClickListener(listener);



    }

    private class ButtonClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            int id = v.getId();

            if(id == R.id.closeButton)
            {
                setResult(RESULT_CANCELED, null);
                finish();
            }
            else if(id == R.id.exportButton)
            {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                Toast.makeText(PracticalTest01Var06Secondary_activity.this,
                        "Data exported successfully",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}