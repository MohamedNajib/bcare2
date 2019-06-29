package com.emedia.bcare.Config;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.emedia.bcare.BuildConfig;
import com.emedia.bcare.R;
import com.emedia.bcare.cash.SharedUser;
import com.squareup.otto.Bus;

import java.util.Hashtable;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BCareApp extends android.app.Application {

    public static Context context ;
    public static boolean homeRegistered = false;
    static BCareApp instance;
    private Dialog spinner;
    public static Typeface typeFace_29ltbukrabold;
    private static Hashtable<String, Typeface> fontCache = new Hashtable<String, Typeface>();

    boolean isRefreshed = false;

    public static boolean HomeCreated = false;

    public static synchronized BCareApp getInstance() {
        if (context == null) {
            throw new IllegalArgumentException("Impossible to get the instance. This class must be initialized before");
        }
        if (instance == null) {
            instance = new BCareApp();
        }
        return instance;
    }


    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();

        this.CreateRegisterManagers();
        this.initializeInjector();
        this.initializeLeakDetection();


    }

    public Context getContext()
    {
        return this.context;
    }

    public void CreateRegisterManagers()
    {
        /** Categories Manager */
       //mCatsManager = new CategoriesManager(this, mBus);
       //mBus.register(mCatsManager);
       //
       //mBus.register(this);
    }


    public Typeface getTypeFace_29ltbukrabold() {
        if (typeFace_29ltbukrabold == null) {
            typeFace_29ltbukrabold = Typeface.createFromAsset(context.getAssets(), "fonts/Cocon_light.otf");
            return typeFace_29ltbukrabold;
        } else {
            return typeFace_29ltbukrabold;
        }
    }



    public void updateToken(String _token)
    {
        SharedUser.getSharedUser().setToken(_token);
    }


    public void makeToast(String message, Context _con)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) ((Activity)_con).getWindow().getDecorView().findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(message);

        Toast toast = new Toast(_con);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }



    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }




    private void initDialog(Activity view) {
        spinner = new Dialog(view);
        spinner.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        spinner.setContentView(R.layout.dialogue_progress);
        spinner.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        spinner.setTitle("");
        Display display = view.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        spinner.getWindow().setLayout(width, 2 * (height / 6));
    }

    public void showDialogue(Activity view) {
        initDialog(view);
        spinner.show();
    }

    public void hideDialogue() {
        spinner.hide();
    }


    public void showExitDialogue(final Activity act)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(act)
                .setIcon(R.drawable.exit)
                .setTitle("Exit?")
                .setMessage("Are you sure to Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                        homeIntent.addCategory( Intent.CATEGORY_HOME );
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        act.startActivity(homeIntent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }

    public static SharedLanguage getLanguage() {
        return SharedLanguage.getSharedLanguage();
    }

    //===========================================
    // New Code At Config
    //===========================================
    private void initializeInjector() {
        //this.applicationComponent = DaggerApplicationComponent.builder()
        //        .applicationModule(new ApplicationModule(this))
        //        .build();
    }

    //public ApplicationComponent getApplicationComponent() {
    //    return this.applicationComponent;
    //}

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            //LeakCanary.install(this);
        }
    }

    public boolean isLogged()
    {
        return true;
    }

    public boolean logout()
    {
        return true;
    }

    public void registerHomeBus()
    {
        homeRegistered = true;
    }
    public void unRegsiterHomeBus()
    {
        homeRegistered = false;
    }
    public boolean getHomeBus()
    {
        return homeRegistered;
    }


}
