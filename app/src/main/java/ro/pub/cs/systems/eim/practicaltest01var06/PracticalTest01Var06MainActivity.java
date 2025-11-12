package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

//    private int aVotes = 0;
//    private int bVotes = 0;
//    private int cVotes = 0;
//    private int dVotes = 0;

    final public static String TAG                  = "activitylifecycle";
    private int[] votes = new int[4];

    private int leadingVotes = 0;

    private int leadingOptionIndex = -1;

    private Button a,b,c,d, reset, viewReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_var06_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        a = findViewById(R.id.aButton);
        b = findViewById(R.id.bButton);
        c = findViewById(R.id.cButton);
        d = findViewById(R.id.dButton);
        reset = findViewById(R.id.resetVotes);
        viewReport = findViewById(R.id.viewReport);


        ButtonClickListener listener = new ButtonClickListener();

        a.setOnClickListener(listener);
        b.setOnClickListener(listener);
        c.setOnClickListener(listener);
        d.setOnClickListener(listener);
        reset.setOnClickListener(listener);
        viewReport.setOnClickListener(listener);

        if(savedInstanceState != null)
        {
            Log.d(TAG, "onCreate() method was invoked without a previous state");
        }
        else
        {
            Log.d(TAG, "onCreate() method was invoked with a previous state");
        }
    }

    private class ButtonClickListener implements View.OnClickListener
    {
        TextView totalVotesCount = findViewById(R.id.totalVotes);
        TextView leadingOption = findViewById(R.id.leadingOption);

        int totalVotes = 0;
        @Override
        public void onClick(View v) {
            int id = v.getId();

            if(id == R.id.aButton)
            {
                votes[0]++;
            }
            else if(id == R.id.bButton)
            {
                votes[1]++;
            }
            else if (id == R.id.cButton)
            {
                votes[2]++;
            }
            else if (id == R.id.dButton)
            {
                votes[3]++;
            }
            else if (id == R.id.resetVotes)
            {
                votes[0] = 0;
                votes[1] = 0;
                votes[2] = 0;
                votes[3] = 0;
                leadingOption.setText("Leading Option: N/A");
            }
            else if (id == R.id.viewReport) {
                Intent intent = new Intent(PracticalTest01Var06MainActivity.this,
                        PracticalTest01Var06Secondary_activity.class);
                intent.putExtra("ro.pub.cs.systems.eim.PracticalTest01Var06Secondary_activity.Votes", votes);
                intent.putExtra("ro.pub.cs.systems.eim.PracticalTest01Var06Secondary_activity.Leading", leadingOptionIndex);
                startActivityForResult(intent, 2024);  // codul de request
            }
            if(id != R.id.resetVotes)
            {
                for (int i = 0; i < votes.length; i++)
                {
                    if(votes[i] > leadingVotes)
                    {
                        leadingVotes = votes[i];
                        leadingOptionIndex = i;
                    }
                }
                leadingOption.setText("Leading Option: " + (leadingOptionIndex + 1));
            }

            totalVotesCount.setText("Total votes: " + (votes[0] + votes[1] + votes[2] + votes[3]));

            Log.d(TAG, "A button votes: " + votes[0]);
            Log.d(TAG, "B button votes: " + votes[1]);
            Log.d(TAG, "C button votes: " + votes[2]);
            Log.d(TAG, "D button votes: " + votes[3]);


        }
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d(TAG, "onRestart() method was invoked");
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d(TAG, "onStart() method was invoked");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume() method was invoked");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause() method was invoked");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG, "onStop() method was invoked");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy() method was invoked");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        Log.d(TAG, "onSaveInstanceState() method was invoked");
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putIntArray("votes", votes);
        savedInstanceState.putInt("leadingVotes", leadingVotes);
        savedInstanceState.putInt("leadingOptionIndex", leadingOptionIndex);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        Log.d(TAG, "onRestoreInstanceState() method was invoked");
        super.onRestoreInstanceState(savedInstanceState);

        votes = savedInstanceState.getIntArray("votes");
        leadingVotes = savedInstanceState.getInt("leadingVotes");
        leadingOptionIndex = savedInstanceState.getInt("leadingOptionIndex");

        TextView totalVotesCount = findViewById(R.id.totalVotes);
        TextView leadingOption = findViewById(R.id.leadingOption);

        totalVotesCount.setText("Total votes: " + (votes[0] + votes[1] + votes[2] + votes[3]));

        for (int i = 0; i < votes.length; i++)
        {
            if(votes[i] > leadingVotes)
            {
                leadingVotes = votes[i];
                leadingOptionIndex = i;
            }
        }
        leadingOption.setText("Leading Option: " + (leadingOptionIndex + 1));

    }

}