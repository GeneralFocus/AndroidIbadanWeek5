package com.example.android.powerreceiver;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

            PackageManager mPackageManager = null;
             ComponentName mReceiverComponentName = null;

    private	static	final	String	ACTION_CUSTOM_BROADCAST	=
            "com.example.android.powerreceiver.ACTION_CUSTOM_BROADCAST";

    private	CustomReceiver	mReceiver	=	new	CustomReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReceiverComponentName	=	new ComponentName(this,	CustomReceiver.class);
        mPackageManager	=	getPackageManager();

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver,	new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    @Override
    protected	void	onStart()	{
        super.onStart();
        mPackageManager.setComponentEnabledSetting
                (mReceiverComponentName,	PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
    }
    @Override
    protected	void	onStop()	{
        super.onStop();
        mPackageManager.setComponentEnabledSetting
                (mReceiverComponentName,	PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
    }

    public void sendCustomBroadcast(View view) {

        // create	a	new Intent,	with custom	action	string	as	the argument.
                Intent	customBroadcastIntent	=	new	Intent(ACTION_CUSTOM_BROADCAST);
        // Send	the	broadcast	using	the	LocalBroadcastManager class:
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);

    }

    @Override
    protected	void	onDestroy()	{
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
