package com.chrisdavid.herd;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

/** Helper class to inject user/log-in dependencies that would otherwise have
 * too much boilerplate and make Activities that use these less readable. */
public class UserAuthHelper {
    // Context MUST implement OnConnectionFailedListener
    public static GoogleApiClient getGoogleApiClient(Context context) {
        assert(context instanceof GoogleApiClient.OnConnectionFailedListener);

        // Set up Google API Client
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.web_client_id))
                .requestEmail()
                .build();
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(context.getApplicationContext())
                .enableAutoManage((FragmentActivity)context /* FragmentActivity */,
                        (GoogleApiClient.OnConnectionFailedListener)context /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        return mGoogleApiClient;
    }

}
