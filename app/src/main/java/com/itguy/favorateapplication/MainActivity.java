package com.itguy.favorateapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
//import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "https://google.co.kr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.settingSideNavBar();

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClientClass());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode==KeyEvent.KEYCODE_BACK)&&(webView.canGoBack())){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    /***
     *  -> 사이드 네브바 세팅
     *   - 클릭 아이콘 설정
     *   - 아이템 클릭 이벤트 설정
     */
    public void settingSideNavBar()
    {
        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 사이드 메뉴를 오픈하기위한 아이콘 추가
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_48);

        // 사이드 네브바 구현
        DrawerLayout drawLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawLayout,
                toolbar,
                R.string.open,
                R.string.closed
        );

        // 사이드 네브바 클릭 리스너
        drawLayout.addDrawerListener(actionBarDrawerToggle);

        // -> 사이드 네브바 아이템 클릭 이벤트 설정
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.menu_item1){
                    Toast.makeText(getApplicationContext(), "메뉴아이템 1 선택", Toast.LENGTH_SHORT).show();
                }else if(id == R.id.menu_item2){
                    Toast.makeText(getApplicationContext(), "메뉴아이템 2 선택", Toast.LENGTH_SHORT).show();
                }else if(id == R.id.menu_item3){
                    Toast.makeText(getApplicationContext(), "메뉴아이템 3 선택", Toast.LENGTH_SHORT).show();
                }else if(id == R.id.menu_item4){
                    Toast.makeText(getApplicationContext(), "메뉴아이템 4 선택", Toast.LENGTH_SHORT).show();
                }


                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }


    /***
     *  -> 뒤로가기시, 사이드 네브바 닫는 기능
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}