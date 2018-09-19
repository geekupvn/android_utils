package vn.geekup.geekupandroidsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.geekup.utils.DateUtils;
import vn.geekup.utils.ScreenUtils;
import vn.geekup.utils.StringUtils;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        StringUtils.init(this);
        // or
        ScreenUtils.init(this);
        // or
        DateUtils.init(this);
    }
}
