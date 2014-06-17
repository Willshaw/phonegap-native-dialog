package com.willshawmedia.phonegap;

import java.util.ArrayList;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;

import android.widget.Toast;

import android.content.Context;
import android.content.DialogInterface;

public class NativeDialog extends CordovaPlugin {


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ( action.equals( "create" ) ) {
            JSONObject config = args.getJSONObject( 0 ); 
            this.create( config, callbackContext );
            return true;
        }
        return false;
    }

    private void create( final JSONObject config, final CallbackContext callbackContext ) {
        
        final JSONObject result = new JSONObject();
        
        Runnable runnable = new Runnable() {
        
            public void run() {        
        
                try {
                
                    JSONArray items = config.getJSONArray( "items" );
                    String dialog_title = (String)config.get( "title" );

                    // convert items to normal list
                    int len = items.length();

                    final String[] item_list = new String[ len ];     

                    for( int i = 0; i < len; i++ ) { 
                        item_list[ i ] = items.getString( i );
                    } 

                    AlertDialog.Builder builder = new AlertDialog.Builder( cordova.getActivity() );
                    builder.setTitle( dialog_title );
                    builder.setItems( 
                        item_list, 
                        new DialogInterface.OnClickListener() {
                            public void onClick( DialogInterface dialog, int item ) {
                                try {
                                    result.put( "buttonIndex", item );
                                    callbackContext.success( result );
                                } catch ( JSONException e ) {
                                    //
                                }
                            }
                        }
                    );
                    AlertDialog alert = builder.create();
                    alert.show();
                } catch ( Exception e ) {
                    //
                }
            }
        };

        cordova.getActivity().runOnUiThread(runnable);
    }
}
