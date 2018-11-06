package es.iessaladillo.pedrojoya.pr04.ui.avatar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr04.R;
import es.iessaladillo.pedrojoya.pr04.data.local.Database;
import es.iessaladillo.pedrojoya.pr04.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr04.utils.ResourcesUtils;

public class AvatarActivity extends AppCompatActivity {

    public static final String EXTRA_AVATAR = "EXTRA_AVATAR";

    private Avatar avatar;
    private ImageView imgAvatar1;
    private ImageView imgAvatar2;
    private ImageView imgAvatar3;
    private ImageView imgAvatar4;
    private ImageView imgAvatar5;
    private ImageView imgAvatar6;
    private TextView lblAvatar1;
    private TextView lblAvatar2;
    private TextView lblAvatar3;
    private TextView lblAvatar4;
    private TextView lblAvatar5;
    private TextView lblAvatar6;
    private final Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        obtainData(getIntent());
        initViews();
        setTransparent();
    }

    private void initViews() {
        imgAvatar1 = ActivityCompat.requireViewById(this, R.id.imgAvatar1);
        imgAvatar2 = ActivityCompat.requireViewById(this, R.id.imgAvatar2);
        imgAvatar3 = ActivityCompat.requireViewById(this, R.id.imgAvatar3);
        imgAvatar4 = ActivityCompat.requireViewById(this, R.id.imgAvatar4);
        imgAvatar5 = ActivityCompat.requireViewById(this, R.id.imgAvatar5);
        imgAvatar6 = ActivityCompat.requireViewById(this, R.id.imgAvatar6);
        lblAvatar1 = ActivityCompat.requireViewById(this, R.id.lblAvatar1);
        lblAvatar2 = ActivityCompat.requireViewById(this, R.id.lblAvatar2);
        lblAvatar3 = ActivityCompat.requireViewById(this, R.id.lblAvatar3);
        lblAvatar4 = ActivityCompat.requireViewById(this, R.id.lblAvatar4);
        lblAvatar5 = ActivityCompat.requireViewById(this, R.id.lblAvatar5);
        lblAvatar6 = ActivityCompat.requireViewById(this, R.id.lblAvatar6);

        // EN ESTE CASO NO VEO LA VENTAJA DE HACER UN LISTENER ÚNICO, YA QUE AL FINAL
        // EN EL MÉTODO setAvatar() TIENES UN SWITCH PARA VER QUÉ AVATAR SE DEBE ENVIAR
        // DEPENDIENDO DE SOBRE QUÉ VISTA SE HA PULSADO.
        /*onClickListener*/
        View.OnClickListener clickListener = v -> {
            buildResult(v);
            finish();
        };

        imgAvatar1.setOnClickListener(clickListener);
        imgAvatar2.setOnClickListener(clickListener);
        imgAvatar3.setOnClickListener(clickListener);
        imgAvatar4.setOnClickListener(clickListener);
        imgAvatar5.setOnClickListener(clickListener);
        imgAvatar6.setOnClickListener(clickListener);
        lblAvatar1.setOnClickListener(clickListener);
        lblAvatar2.setOnClickListener(clickListener);
        lblAvatar3.setOnClickListener(clickListener);
        lblAvatar4.setOnClickListener(clickListener);
        lblAvatar5.setOnClickListener(clickListener);
        lblAvatar6.setOnClickListener(clickListener);

    }

    private void buildResult(View v) {
        setAvatar(v);
        Intent intent = new Intent();
        intent.putExtra(EXTRA_AVATAR, avatar);
        this.setResult(RESULT_OK, intent);
    }

    private void setTransparent() {
        if (avatar.getImageResId() == database.queryAvatar(1).getImageResId()) {
            selectImageView(imgAvatar1);
        } else if (avatar.getImageResId() == database.queryAvatar(2).getImageResId()) {
            selectImageView(imgAvatar2);
        } else if (avatar.getImageResId() == database.queryAvatar(3).getImageResId()) {
            selectImageView(imgAvatar3);
        } else if (avatar.getImageResId() == database.queryAvatar(4).getImageResId()) {
            selectImageView(imgAvatar4);
        } else if (avatar.getImageResId() == database.queryAvatar(5).getImageResId()) {
            selectImageView(imgAvatar5);
        } else if (avatar.getImageResId() == database.queryAvatar(6).getImageResId()) {
            selectImageView(imgAvatar6);
        }
    }

    private void setAvatar(View v) {
        Database database = Database.getInstance();
        long num = 0;

        switch (v.getId()) {
            case R.id.imgAvatar1:
                num = 1;
                break;
            case R.id.imgAvatar2:
                num = 2;
                break;
            case R.id.imgAvatar3:
                num = 3;
                break;
            case R.id.imgAvatar4:
                num = 4;
                break;
            case R.id.imgAvatar5:
                num = 5;
                break;
            case R.id.imgAvatar6:
                num = 6;
                break;
            case R.id.lblAvatar1:
                num = 1;
                break;
            case R.id.lblAvatar2:
                num = 2;
                break;
            case R.id.lblAvatar3:
                num = 3;
                break;
            case R.id.lblAvatar4:
                num = 4;
                break;
            case R.id.lblAvatar5:
                num = 5;
                break;
            case R.id.lblAvatar6:
                num = 6;
                break;
        }
        avatar = database.queryAvatar(num);
    }

    private void obtainData(Intent intent) {
        if (intent != null && intent.hasExtra(EXTRA_AVATAR)) {
            avatar = intent.getParcelableExtra(EXTRA_AVATAR);
        }
    }

    // DO NO TOUCH
    private void selectImageView(ImageView imageView) {
        imageView.setAlpha(ResourcesUtils.getFloat(this, R.dimen.selected_image_alpha));
    }

    // DO NOT TOUCH
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static void startForResult(Activity activity, int requestCode, Avatar avatar) {
        Intent intent = new Intent(activity, AvatarActivity.class);
        intent.putExtra(EXTRA_AVATAR, avatar);
        activity.startActivityForResult(intent, requestCode);
    }
}
