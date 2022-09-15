package com.jdgames.security;

import static android.content.Context.ACTIVITY_SERVICE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.util.List;

public class StorageManager {

    public static String getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        long result = availableBlocks * blockSize;
        if (result >= 1024) {
            result /= 1024;
            if (result >= 1024) {
                result /= 1024;
                if (result >= 1024) {
                    result /= 1024;
                }
            }
        }
        return String.valueOf(result);
    }

    public static String getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        return formatSize(totalBlocks * blockSize);
    }

    public static String formatSize(long size) {
        String suffix = null;

        if (size >= 1024) {
            suffix = "KB";
            size /= 1024;
            if (size >= 1024) {
                suffix = " MB";
                size /= 1024;
                if (size >= 1024) {
                    suffix = " GB";
                    size /= 1024;
                }
            }
        }

        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

        int commaOffset = resultBuffer.length() - 3;
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',');
            commaOffset -= 3;
        }

        if (suffix != null) resultBuffer.append(suffix);
        return resultBuffer.toString();
    }

    public static String getRAM(Activity activity) {
        ActivityManager actManager = (ActivityManager) activity.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        long totalMemory = memInfo.totalMem;
        if (totalMemory >= 1024) {
            totalMemory /= 1024;
            if (totalMemory >= 1024) {
                totalMemory /= 1024;
                if (totalMemory >= 1024) {
                    totalMemory /= 1024;
                }
            }
        }

        long availableMemory = memInfo.availMem;
        if (availableMemory >= 1024) {
            availableMemory /= 1024;
            if (availableMemory >= 1024) {
                availableMemory /= 1024;
                if (availableMemory >= 1024) {
                    availableMemory /= 1024;
                }
            }
        }
        return availableMemory + "/" + totalMemory + " GB";
    }

    @SuppressLint("QueryPermissionsNeeded")
    public static int getTotalInstalledApps(Activity activity) {
        int count = 0;
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.R) {
            List<PackageInfo> packageInfoList = activity.getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < packageInfoList.size(); i++) {
                count++;
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            List<ResolveInfo> pkgAppsList = activity.getPackageManager().queryIntentActivities(intent, 0);
            for (ResolveInfo ignored : pkgAppsList) {
                count++;
            }
        }
        return count;
    }
}
