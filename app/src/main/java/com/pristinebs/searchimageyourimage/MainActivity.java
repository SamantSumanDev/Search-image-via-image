package com.pristinebs.searchimageyourimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout SearchView, resultView;
    TextInputEditText edtSearchImage, edtSearchUrl;
    ImageView imgSearch, imgeSearch2,imgClose;

    RecyclerView imgeRecycler, VisualImgeRecycler;
    ImgRecylerAdapter imgadapter;
    VisualImageRecyclerAdapter VisualImgAdapter;
    LinearLayoutManager manager;

    AppCompatImageView ImgUser;

    TextView tvTitle, tvTitile2, tvSubtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        initView();

        edtSearchImage.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    imgSearch.setEnabled(false);
                    String imgUrl = edtSearchImage.getText().toString().trim();
                    getImage(imgUrl);
                    return true;
                }
                return false;
            }
        });


        edtSearchImage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                imgClose.setVisibility(View.VISIBLE);
                imgeSearch2.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        edtSearchUrl.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    imgSearch.setEnabled(false);
                    String imgUrl = edtSearchUrl.getText().toString().trim();
                    getImage(imgUrl);
                    return true;
                }
                return false;
            }
        });

        edtSearchUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                imgClose.setVisibility(View.VISIBLE);
                imgeSearch2.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearchUrl.setText("");
                imgClose.setVisibility(View.GONE);
                imgeSearch2.setVisibility(View.VISIBLE);
            }
        });



    }

    private void getImage(String imgUrl) {
        String engine = "google_lens";
        String api_key = "genereted api key";
        ApiInterface apiInterface = ApiUtils.getData();
        Call<ImgSearchModel> call = apiInterface.getImage(engine, imgUrl, api_key);
        call.enqueue(new Callback<ImgSearchModel>() {
            @Override

            public void onResponse(Call<ImgSearchModel> call, Response<ImgSearchModel> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Responeded", Toast.LENGTH_SHORT).show();
                    ImgSearchModel imgSearchModelList = new ImgSearchModel();
                    imgSearchModelList = response.body();
                    ArrayList<ImgSearchModel.Image> imageArrayList = new ArrayList<>();
                    ArrayList<ImgSearchModel.VisualMatch> VisualImageArrayList = new ArrayList<>();

                    imageArrayList = imgSearchModelList.knowledge_graph.get(0).images;
                    VisualImageArrayList = imgSearchModelList.visual_matches;
                    SearchView.setVisibility(View.GONE);
                    resultView.setVisibility(View.VISIBLE);

                    edtSearchUrl.setText(imgUrl);
                    tvTitle.setText(imgSearchModelList.knowledge_graph.get(0).title);
                    tvTitile2.setText(imgSearchModelList.knowledge_graph.get(0).title);
                    tvSubtitle.setText(imgSearchModelList.knowledge_graph.get(0).subtitle);

                    Picasso.get().load(imageArrayList.get(0).link)
                            .into(ImgUser);


                    manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true);
                    imgeRecycler.setLayoutManager(manager);
                    manager.setStackFromEnd(true);
                    manager.setSmoothScrollbarEnabled(true);

                    imgadapter = new ImgRecylerAdapter(getApplicationContext(), imageArrayList);
                    imgeRecycler.setHasFixedSize(true);
                    imgeRecycler.setAdapter(imgadapter);

                    LinearLayoutManager vmanager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,true);
                    VisualImgeRecycler.setLayoutManager(vmanager);
                    vmanager.setStackFromEnd(true);
                    VisualImgAdapter = new VisualImageRecyclerAdapter(getApplicationContext(), VisualImageArrayList);
                    VisualImgeRecycler.setHasFixedSize(true);
                    VisualImgeRecycler.setAdapter(VisualImgAdapter);

                    imgSearch.setEnabled(true);
                    if (imgeSearch2!=null){
                        imgeSearch2.setVisibility(View.GONE);
                        imgClose.setVisibility(View.VISIBLE);
                    }




                }
            }

            @Override
            public void onFailure(Call<ImgSearchModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Errror: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                imgSearch.setEnabled(true);
                Log.e("error", t.getMessage());

            }
        });
    }

    private void initView() {
        SearchView = findViewById(R.id.SearchView);
        resultView = findViewById(R.id.resultView);
        edtSearchImage = findViewById(R.id.edtSearchImage);
        edtSearchUrl = findViewById(R.id.edtSearchUrl);
        imgSearch = findViewById(R.id.imgSearch);
        imgeSearch2 = findViewById(R.id.imgeSearch2);
        imgClose = findViewById(R.id.imgClose);
        ImgUser = findViewById(R.id.ImgUser);

        tvTitle = findViewById(R.id.tvTitle);
        tvTitile2 = findViewById(R.id.tvTitile2);
        tvSubtitle = findViewById(R.id.tvSubtitle);

        imgeRecycler = findViewById(R.id.imgeRecycler);
        VisualImgeRecycler = findViewById(R.id.VisualImgeRecycler);
    }
}
