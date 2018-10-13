package com.example.android.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

    /**
     * Broadcast Receiver implementation that delivers a custom Toast
     * message when it receives any of the registered broadcasts
     */
    public class CustomReceiver extends BroadcastReceiver {

        //String constant that defines the custom Broadcast Action
        static final String ACTION_CUSTOM_BROADCAST =
                "com.example.android.powerreceiver.ACTION_CUSTOM_BROADCAST";


        //Empty constructor
        public CustomReceiver() {
        }

        @Override
    public void onReceive(Context context, Intent intent) {
        // This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String	intentAction	=	intent.getAction();
        // Initialize string that equal to null
        String	toastMessage = null;

        switch	(intentAction){
            case	ACTION_CUSTOM_BROADCAST:
                toastMessage	=	context.getString(R.string.custom_broadcast_toast);
                break;
            case Intent.ACTION_POWER_CONNECTED:
                toastMessage = context.getString(R.string.connected);
                break;
            case	Intent.ACTION_POWER_DISCONNECTED:
                toastMessage = context.getString(R.string.disconnected);
                break;
        }

        Toast.makeText(context,	toastMessage,	Toast.LENGTH_SHORT).show();
    }
}
