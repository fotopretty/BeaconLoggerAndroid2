package org.altbeacon.beaconreference;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.RegionBootstrap;
import org.altbeacon.beacon.startup.BootstrapNotifier;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;

public class BeaconReferenceApplication extends Application
{
    private static final String TAG = "BeaconReferenceApp";
    private BackgroundPowerSaver backgroundPowerSaver;
    public static boolean deviceSupportsBluetooth = true;
    public static BeaconController beaconController = null;

    public void onCreate() {
        super.onCreate();

        //  Do the beacon management here.
        if (deviceSupportsBluetooth) {
            beaconController = new BeaconController(this);
            // simply constructing this class and holding a reference to it in your custom Application
            // class will automatically cause the BeaconLibrary to save battery whenever the application
            // is not visible.  This reduces bluetooth power usage by about 60%
            backgroundPowerSaver = new BackgroundPowerSaver(this);
            Log.i(TAG, "Power Saver is on: " + backgroundPowerSaver.toString());
        }
    }

}