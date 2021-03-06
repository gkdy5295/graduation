package com.example.graduation;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class CategoryFragment extends Fragment {
    private FragmentManager fm;
    private FragmentTransaction ft;
    private ArrayList<edu> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private eduAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private eduAdapter.OnItemClickListener listener;
    String it="", art="", music="", cook="", health="", certificate="", humanity="", etc="";
    String cate="",result="",result2="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.frag_catrgory,container,false);

        CheckBox cate_it = v.findViewById(R.id.cate_it);
        CheckBox cate_art = v.findViewById(R.id.cate_art);
        CheckBox cate_music = v.findViewById(R.id.cate_music);
        CheckBox cate_cook = v.findViewById(R.id.cate_cook);
        CheckBox cate_health = v.findViewById(R.id.cate_health);
        CheckBox cate_certificate = v.findViewById(R.id.cate_certificate);
        CheckBox cate_humanity = v.findViewById(R.id.cate_humanity);
        CheckBox cate_etc = v.findViewById(R.id.cate_etc);

        cate_it.setOnClickListener(category);
        cate_art.setOnClickListener(category);
        cate_music.setOnClickListener(category);
        cate_cook.setOnClickListener(category);
        cate_health.setOnClickListener(category);
        cate_certificate.setOnClickListener(category);
        cate_humanity.setOnClickListener(category);
        cate_etc.setOnClickListener(category);

        eduAdapter.OnItemClickListener listener= new eduAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
//                eduAdapter.eduViewHolder viewHolder = (eduAdapter.eduViewHolder) recyclerView.findViewHolderForAdapterPosition(pos);
                Bundle bundle = new Bundle();
                bundle.putString("table_name",arrayList.get(pos).getName());
                bundle.putString("table_outlook",arrayList.get(pos).getOutlook());
                bundle.putString("table_institution",arrayList.get(pos).getInstitution());
                bundle.putString("table_place",arrayList.get(pos).getPlace());
                bundle.putString("table_week",arrayList.get(pos).getWeek());
                bundle.putString("table_time",arrayList.get(pos).getTime());
                bundle.putString("table_teacher",arrayList.get(pos).getTeacher());
                bundle.putString("table_edu_person", String.valueOf(arrayList.get(pos).getEdu_person()));
                bundle.putString("table_apply_start",arrayList.get(pos).getApply_start());
                bundle.putString("table_apply_end",arrayList.get(pos).getApply_end());
                bundle.putString("table_edu_start",arrayList.get(pos).getEdu_start());
                bundle.putString("table_edu_end",arrayList.get(pos).getEdu_end());
                bundle.putString("table_target",arrayList.get(pos).getTarget());
                bundle.putString("table_fee",arrayList.get(pos).getFee());
                bundle.putString("table_teacher_info",arrayList.get(pos).getTeacher_info());
                bundle.putString("table_apply_url",arrayList.get(pos).getApply_url());
                bundle.putString("table_cate",arrayList.get(pos).getCategory());



                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                eduTableFragment eduTableFragment = new eduTableFragment();
                eduTableFragment.setArguments(bundle);
                transaction.replace(R.id.frame_container, eduTableFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        };

        recyclerView = v.findViewById(R.id.recycler_category);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        database = FirebaseDatabase.getInstance(); // ?????????????????? ?????????????????? ??????
        databaseReference = database.getReference("edu"); // DB ????????? ??????=

        adapter = new eduAdapter(arrayList, getContext(), listener);
        recyclerView.setAdapter(adapter);

        return v;

    }

    View.OnClickListener category = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (((CheckBox) v).isChecked()) {
                switch (v.getId()) {
                    case R.id.cate_it:
                        it += "IT,";
                        break;
                    case R.id.cate_art:
                        art += "??????,";
                        break;
                    case R.id.cate_music:
                        music += "??????,";
                        break;
                    case R.id.cate_cook:
                        cook += "??????,";
                        break;
                    case R.id.cate_health:
                        health += "??????,";
                        break;
                    case R.id.cate_certificate:
                        certificate += "?????????,";
                        break;
                    case R.id.cate_humanity:
                        humanity += "?????????,";
                        break;
                    case R.id.cate_etc:
                        etc += "??????,";
                        break;
                }
            } else {
                switch (v.getId()) {
                    case R.id.cate_it:
                        it = it.replace("IT,", "");
                        break;
                    case R.id.cate_art:
                        art = art.replace("??????,", "");
                        break;
                    case R.id.cate_music:
                        music = music.replace("??????,", "");
                        break;
                    case R.id.cate_cook:
                        cook = cook.replace("??????,", "");
                        break;
                    case R.id.cate_health:
                        health = health.replace("??????,", "");
                        break;
                    case R.id.cate_certificate:
                        certificate = certificate.replace("?????????,", "");
                        break;
                    case R.id.cate_humanity:
                        humanity = humanity.replace("?????????,", "");
                        break;
                    case R.id.cate_etc:
                        etc = etc.replace("??????,", "");
                        break;
                }
            }
            cate=it+health+etc+art+cook+music+humanity+certificate;
            if(cate.length()>0) {
                cate = cate.substring(0, cate.length() - 1);
            }
            Toast.makeText(getContext(), cate, Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
            result2 = cate + "\uf8ff";
            databaseReference.orderByChild("category").startAt(cate).endAt(result2).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // ?????????????????? ????????????????????? ???????????? ???????????? ???
                    arrayList.clear(); // ?????? ?????????????????? ?????????????????? ?????????
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // ??????????????? ????????? List??? ????????????
                        edu edu = snapshot.getValue(edu.class); // ??????????????? User ????????? ???????????? ?????????.
                        arrayList.add(edu); // ?????? ??????????????? ?????????????????? ?????? ????????????????????? ?????? ??????
                    }
                    Collections.reverse(arrayList);// ????????? ?????? ??? ?????????????????? ????????? ???
                    adapter.notifyDataSetChanged();
                    cate="";
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // ????????? ??????????????? ?????? ?????? ???
                    Log.e("HomeFragment", String.valueOf(databaseError.toException())); // ????????? ??????
                }
            });

        }
    };

}
