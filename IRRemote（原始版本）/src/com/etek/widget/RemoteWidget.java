package com.etek.widget;



import com.etek.irremote.R;
import com.etek.main.StartActivity;
import com.ircode.IRCode;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.hardware.ConsumerIrManager;
import android.util.Log;
import android.widget.RemoteViews;
public class RemoteWidget extends AppWidgetProvider
{
	ConsumerIrManager cim;
	String TAG = "RemoteWidget";
	private static final String REMOTE_ACTION_POWER = "com.irda.widget.remoteActionPower";
	private static final String REMOTE_ACTION_VOLUP = "com.irda.widget.remoteActionVolup";
	String data = "38028,343,172,21,65,21,65,21,65,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,22,21,65,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,65,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,65,21,65,21,65,21,65,21,1673,343,86,21,3732";

	@Override
	public void onReceive(Context context, Intent intent) {
		
		String action = intent.getAction();
		cim = (ConsumerIrManager)context.getSystemService("consumer_ir");
		if (REMOTE_ACTION_POWER.equals(action) || REMOTE_ACTION_VOLUP.equals(action)) {
			
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
			
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_remote);
			
			Log.v(TAG, action);
			IRCode code = new IRCode(data);
			sendCmd(code.getFrequency(),code.getDatas());
			manager.partiallyUpdateAppWidget(appWidgetId, views);
		}
		
		super.onReceive(context, intent);
	}
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds){
		 final int n = appWidgetIds.length;

	        for (int i = 0; i < n; i++) {
	        	 int appWidgetId = appWidgetIds[i];
		 Intent intent = new Intent(context, StartActivity.class);
         PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

         RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_remote);
         views.setOnClickPendingIntent(R.id.w_power, pendingIntent);
         views.setOnClickPendingIntent(R.id.w_chup, generatePendingIntent(context, REMOTE_ACTION_POWER, appWidgetId));
         views.setOnClickPendingIntent(R.id.w_chdown, generatePendingIntent(context, REMOTE_ACTION_VOLUP, appWidgetId));
         appWidgetManager.updateAppWidget(appWidgetId, views);
	        }
	}
	
	private PendingIntent generatePendingIntent(Context context, String action, int appWidgetId) {
		Intent switchIntent = new Intent(context, RemoteWidget.class);
        switchIntent.setAction(action);
        switchIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        return PendingIntent.getBroadcast(context, 0, switchIntent, PendingIntent.FLAG_UPDATE_CURRENT); 
	}
	 @SuppressLint("NewApi") private void sendCmd(int freq, int[] datas)
	    {
	     
	        try
	        {
	          cim.transmit(freq, datas);
	            
	        }
	        catch (Exception localException)
	        {
	          localException.printStackTrace();
	        }
	      
	    }
}