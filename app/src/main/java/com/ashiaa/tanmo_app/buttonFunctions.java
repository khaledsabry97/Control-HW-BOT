//package com.ashiaa.tanmo_app;
//
//import android.app.Activity;
//import android.media.MediaPlayer;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//
//public class buttonFunctions {
//
//
//
//    public Activity activityAttribute;
//
////    private final Context context;
//
//    public buttonFunctions(Activity _activity) {
//        this.activityAttribute = _activity;
////        this.context = context;
//    }
//
//    //on button find
//    final Button onButton = activityAttribute.findViewById(R.id.button_on);
//
//    //off button find
//    final Button offButton = activityAttribute.findViewById(R.id.button_off);
//
//    public void OnButtonFunction()
//    {
//        //on button listener
//        onButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View argOn) {
//                onButton.setEnabled(false);
//                offButton.setEnabled(true);
//                //do something
//
//                onSoundFunction();
//
//                //Toast message
//                Toast.makeText(activityAttribute, "الرجاء الإنتظار",
//                        Toast.LENGTH_LONG).show();
//
//                requestObject.onButtonFunction(argOn);
//                //debugger message
//                Log.d("tagv_click Button 1","On Button clicked");
//            }
//        });
//    }
//
//    public void OffButtonFunction()
//    {
//        //off button listener
//        offButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View argOff) {
//                offButton.setEnabled(false);
//                onButton.setEnabled(true);
//                //do something
//
//                offSoundFunction();
//
//                //Toast message
//                Toast.makeText(activityAttribute, "الرجاء الإنتظار",
//                        Toast.LENGTH_LONG).show();
//
//                requestObject.offButtonFunction(argOff);
//
//                //debugger message
//                Log.d("tagv_click Button 2","Off Button clicked");
//            }
//        });
//    }
//
//
//    //on sound function
//    public void onSoundFunction()
//    {
//        final MediaPlayer OnSound = MediaPlayer.create(activityAttribute,R.raw.on_sound );
//
//        OnSound.start();
//
//        OnSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                // TODO Auto-generated method stub
//                mp.reset();
//                mp.release();
//
//            }
//        });
//
//    }
//
//
//    //on sound function
//    public void offSoundFunction()
//    {
//        final MediaPlayer OffSound = MediaPlayer.create(activityAttribute,R.raw.off_sound );
//
//        OffSound.start();
//
//        OffSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                // TODO Auto-generated method stub
//                Toast.makeText(activityAttribute, "sound Finished", Toast.LENGTH_SHORT).show();
//                mp.reset();
//                mp.release();
//
//            }
//        });
//
//    }
//
//
//
//    //create object of http request class
//    requestFunctions requestObject=new requestFunctions(this.activityAttribute);
//}
