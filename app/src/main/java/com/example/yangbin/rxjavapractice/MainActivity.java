package com.example.yangbin.rxjavapractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yangbin.rxjavapractice.Entity.TransformWord;
import com.example.yangbin.rxjavapractice.Utils.RetrofitUtil;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "time";
    private TextView mText, mTextChange;
    private EditText mEditText;
    RetrofitUtil.ApiService mApiService;
    private Observable<TransformWord> observable;
    private EditText mEditTextQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mApiService = RetrofitUtil.getApiService();
        editeChangeQuery();


        RxView.clicks(findViewById(R.id.btn_test))
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Object, ObservableSource<TransformWord>>() {
                    @Override
                    public ObservableSource<TransformWord> apply(Object o) throws Exception {
                        return mApiService.getTrasform(getMap(mEditText.getText().toString().trim())) .subscribeOn(Schedulers.io());
                    }
                })
                .map(new Function<TransformWord, String>() {
                    @Override
                    public String apply(TransformWord transformWord) throws Exception {
                        String string = "";
                        if (transformWord.getStatus() == 0) {
                            string = transformWord.getContent().getWord_mean().toString();
                        }
                        if (transformWord.getStatus() == 1) {
                            string = transformWord.getContent().getOut();
                        }
                        return string;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mText.setText(s);
                    }
                });

    }

    private void editeChangeQuery() {
        RxTextView.textChanges(mEditTextQuery)
                .debounce(1000, TimeUnit.MILLISECONDS)
                .flatMap(new Function<CharSequence, ObservableSource<TransformWord>>() {
                    @Override
                    public ObservableSource<TransformWord> apply(CharSequence charSequence) throws Exception {
                        return mApiService.getTrasform(getMap(charSequence.toString()));
                    }
                })
                .map(new Function<TransformWord, String>() {
                    @Override
                    public String apply(TransformWord transformWord) throws Exception {
                        String string = "";
                        if (transformWord.getStatus() == 0) {
                            string = transformWord.getContent().getWord_mean().toString();
                        }
                        if (transformWord.getStatus() == 1) {
                            string = transformWord.getContent().getOut();
                        }
                        return string;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        mTextChange.setText(charSequence.toString());
                    }
                });
    }


    /**
     * 初始化控件
     */
    private void initView() {
        mText = findViewById(R.id.tv_test);
        mTextChange = findViewById(R.id.tv_test1);
        mEditText = findViewById(R.id.etv_test);
        mEditTextQuery = findViewById(R.id.etv_query);

    }


    /**
     * 按钮点击事件
     *
     * @param
     */
   /* public void click(View view) {
        observable = mApiService.getTrasform(getMap(mEditText.getText().toString().trim()));
        justTransform(observable);

    }*/
    private void transformtwice(Observable<TransformWord> observable) {
        observable.subscribeOn(Schedulers.io())
                .flatMap(new Function<TransformWord, ObservableSource<TransformWord>>() {
                    @Override
                    public ObservableSource<TransformWord> apply(TransformWord transformWord) throws Exception {
                        Log.d(TAG, "apply: " + System.currentTimeMillis());
                        return mApiService.getTrasform(getMap(transformWord.getContent().getOut()));
                    }
                }).map(new Function<TransformWord, String>() {
            @Override
            public String apply(TransformWord transformWord) throws Exception {
                String string = "";
                if (transformWord.getStatus() == 0) {
                    string = transformWord.getContent().getWord_mean().toString();
                }
                if (transformWord.getStatus() == 1) {
                    string = transformWord.getContent().getOut();
                }
                return string;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mText.setText(s);
                    }
                });
    }

    private void justTransform(Observable<TransformWord> observable)   {
        observable.throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .map(new Function<TransformWord, String>() {
                    @Override
                    public String apply(TransformWord transformWord) throws Exception {
                        Log.d(TAG, "apply: " + System.currentTimeMillis());
                        String mean = "";
                        if (transformWord.getStatus() == 1) {
                            mean = transformWord.getContent().getOut();
                        } else if (transformWord.getStatus() == 0) {
                            mean = transformWord.getContent().getWord_mean().toString();
                        }
                        return mean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mText.setText(s);
                    }
                });
    }

    private Map<String, String> getMap(String strig) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "fy");
        map.put("f", "auto");
        map.put("t", "auto");
        map.put("w", strig);
        return map;
    }

    public void click1(View view) {
        observable = mApiService.getTrasform(getMap(mEditText.getText().toString().trim()));
        transformtwice(observable);
    }

}
