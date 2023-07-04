package com.example.privaseev2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.Manifest;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView osBuildTextView;
    private  TextView outputTextView ;

    int PERMISSION_ALL = 123;
    String[] PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputTextView = findViewById(R.id.outputTextView);



        // Initialize osBuildTextView after setContentView()
        osBuildTextView = findViewById(R.id.osBuild);

        osBuildTextView.setText(findBuild());
        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        outputTextView.append("\n Permissions: "+hasPermissions(this, PERMISSIONS));
    }

    private String findBuild() {
        // If the CPU_ABI field is not available, use SUPPORTED_ABIS as fallback
        // Get the supported ABIs
        String[] supportedABIs = Build.SUPPORTED_ABIS;
        // Use the first supported ABI as the architecture
        String architecture = (supportedABIs != null && supportedABIs.length > 0) ? supportedABIs[0] : null;

        return architecture;
    }


}
