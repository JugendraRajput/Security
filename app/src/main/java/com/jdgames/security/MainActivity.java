package com.jdgames.security;

import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.john.waveview.WaveView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BatteryManager batteryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        WaveView waveView = findViewById(R.id.batteryView);

        batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
        int batteryPercent = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        int lastIndex = 2;
        if (batteryPercent > 99) {
            lastIndex = 3;
        }
        String str = batteryPercent + "%";
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new RelativeSizeSpan(2f), 0, lastIndex, 0);

        textView.setText(spannableString);
        waveView.setProgress(batteryPercent);

        final int[] clickCount = {0};
        textView.setOnClickListener(view -> {
            if (clickCount[0] > 7) {
                Toast.makeText(MainActivity.this, "Developed by 'Jugendra'", Toast.LENGTH_SHORT).show();
                clickCount[0] = 0;
            } else {
                clickCount[0]++;
            }
        });

        int androidVersion = Build.VERSION.SDK_INT + 1;
        String androidName;
        switch (androidVersion) {
            case 21:
                androidName = "android 5\n21, Lollipop";
                break;
            case 22:
                androidName = "android 5\n22, Lollipop(MR1)";
                break;
            case 23:
                androidName = "android 6\n23, Marshmallow";
                break;
            case 24:
                androidName = "android 7\n24, Nougat";
                break;
            case 25:
                androidName = "android 7\n25, Nougat(MR1)";
                break;
            case 26:
                androidName = "android 8\n26, Oreo";
                break;
            case 27:
                androidName = "android 8\n27, Oreo(MR1)";
                break;
            case 28:
                androidName = "android 9\n28, Pie";
                break;
            case 29:
                androidName = "android 10\n29, Quince Tart";
                break;
            case 30:
                androidName = "android 11\n30, Red Velvet Cake";
                break;
            case 31:
                androidName = "android 12\n31, Snow Cone";
                break;
            case 32:
                androidName = "android 12\n32, Snow Cone(V2)";
                break;
            case 33:
                androidName = "android 13\n33, Tiramisu";
                break;
            default:
                androidName = "null";
                break;
        }

        ArrayList<Parse> arrayList = new ArrayList<>();
        arrayList.add(new Parse(R.drawable.cloud, "Storage", "Internal available storage", StorageManager.getAvailableInternalMemorySize() + "/" + StorageManager.getTotalInternalMemorySize()));
        arrayList.add(new Parse(R.drawable.ram, "RAM", "Available memory", StorageManager.getRAM(this)));
        arrayList.add(new Parse(R.drawable.apps, "Installed Apps", "Total installed apps", String.valueOf(StorageManager.getTotalInstalledApps(this))));
        arrayList.add(new Parse(R.drawable.account, "Android Version", "Operating System Version", androidName));

        MyListView listView = findViewById(R.id.listView);
        Adapter adapter = new Adapter(this, R.layout.list_view, arrayList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CheckChargingStatus();
    }

    public void CheckChargingStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            if (batteryManager.isCharging()) {
                findViewById(R.id.chargingSymbol).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.chargingSymbol).setVisibility(View.GONE);
            }
        } else {
            findViewById(R.id.chargingSymbol).setVisibility(View.GONE);
        }
    }
}