package com.jianghw.app.list;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jianghw on 18-3-2.
 */

public class SimpleListActivity extends ListActivity {

    private static final String TAG_JX = "log_jx";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String path = intent.getStringExtra("com.jianghw.app.list.Path");
        if (path == null) path = "";
        Log.i(TAG_JX, this.getPackageName() + "===>" + path);

        List<Map<String, Object>> listData = getListData(path);
        String[] from = null;
        int[] to = null;
        SimpleAdapter adapter = new SimpleAdapter(this, listData, android.R.layout.simple_list_item_1, from, to);
        setListAdapter(adapter);
    }

    private List<Map<String, Object>> getListData(String path) {
        //manifest中配置好的
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_SAMPLE_CODE);//category_sample_code

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);
        if (list == null) list = new ArrayList<>();

        String[] prefixPath;
        String prefixWithSlash = path;
        if (TextUtils.isEmpty(path)) {
            prefixPath = null;
        } else {
            prefixPath = path.split("/");
            prefixWithSlash = path + "/";
        }

        Map<String, Boolean> entries = new HashMap<>();
        int len=list.size();
        for(int i = 0; i<len; i++) {
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null ? labelSeq.toString() : info.activityInfo.name;

            if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {
                String[] labelPath = label.split("/");
                String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];
            }
        }

        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
