package com.integro.rsgs.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.integro.rsgs.R;
import com.integro.rsgs.activities.MainActivity;
import com.integro.rsgs.model.News;
import com.integro.rsgs.model.Notification;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = "MyFirebaseMsgService";
    public static final String TYPE = "type";
    public static final String NEWS_KEY = "news";
    public static final String NOTIFICATION_KEY = "notifications";
    public static final String NOTIFICATION_CHANNEL_ID = "1";
    public static final String NOTIFICATION_CHANNEL_NAME = "sjbhs_notifications";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }
        sendNotification(remoteMessage.getData());
    }

    private void sendNotification(Map<String, String> data) {
        Log.d(TAG, "sendNotification data : " + data.toString());
        try {
            String body = data.get("body");
            Intent intent = null;
            String title = null;
            String img = null;
            String description = null;
            PendingIntent pendingIntent;
            NotificationCompat.Builder mBuilder;

            int type = Integer.parseInt(data.get(TYPE));
            Log.d("type", "bsk type : " + type);

            if (type == 1) {
                News newsItem = (News) new Gson().fromJson(body, News.class);
                title = newsItem.getTitle();
                img = newsItem.getImage();
                description = newsItem.getDescription();
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("data", "" + data);
                intent.putExtra(TYPE, NEWS_KEY);
            }
            if (type == 2) {
                Notification nItem = (Notification) new Gson().fromJson(body, Notification.class);
                title = nItem.getTitle();
                description = nItem.getDescription();
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("data", "" + data);
                intent.putExtra(TYPE, NOTIFICATION_KEY);
            }


            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importanceDefault = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importanceDefault);
                channel.setDescription("description");
                notificationManager.createNotificationChannel(channel);
            }
            intent.putExtra(TAG, true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setDefaults(android.app.Notification.DEFAULT_SOUND | android.app.Notification.DEFAULT_LIGHTS | android.app.Notification.DEFAULT_VIBRATE);
            mBuilder.setContentTitle(title);
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mBuilder.setContentText(description);
            mBuilder.setSmallIcon(R.drawable.download);
            mBuilder.setAutoCancel(true);
            mBuilder.setPriority(android.app.Notification.PRIORITY_HIGH);
            mBuilder.setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE);
            mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            mBuilder.setContentIntent(pendingIntent);
            //setting BigPicture in Notifications
            Bitmap bitmap_image = getBitmapFromURL(img);
            if (bitmap_image == null) {
                mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(description));
            } else {
                Log.d("IMAGE_GURU", "" + bitmap_image);
                NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle().bigPicture(bitmap_image);
                mBuilder.setStyle(style);
                mBuilder.setLargeIcon(bitmap_image);
            }

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(Integer.parseInt("001"), mBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}


