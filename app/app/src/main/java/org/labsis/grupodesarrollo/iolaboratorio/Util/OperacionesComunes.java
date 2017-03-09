package org.labsis.grupodesarrollo.iolaboratorio.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ignacio on 31/07/15.
 */
public class OperacionesComunes {



    /**
     * Check if the device is connected to a network. In the case that the user
     * has more than one network enable, such as wifi and data (3g, 4g), it
     * checks the active network connectivity state.
     *
     * @param context
     *
     * @return boolean true if the active network is connected, false otherwise
     */
    public static boolean isConnectivityAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivity.getActiveNetworkInfo();

        return (info != null) ? info.isConnected() : false;
    }

}
