package com.boss.kredit.penjaga;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFcmService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Cek jika ada perintah "lock" masuk
        if (remoteMessage.getData().containsKey("perintah")) {
            String aksi = remoteMessage.getData().get("perintah");
            
            if ("kunci_hp".equals(aksi)) {
                kunciPerangkat();
            } else if ("ambil_foto".equals(aksi)) {
                // Logika kamera akan kita tambahkan di langkah berikutnya
            }
        }
    }

    private void kunciPerangkat() {
        DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName adminName = new ComponentName(this, AdminReceiver.class);
        if (dpm.isAdminActive(adminName)) {
            dpm.lockNow(); // Perintah paksa kunci layar
        }
    }
        }
