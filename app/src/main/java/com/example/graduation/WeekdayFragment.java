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
import com.naver.maps.map.internal.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;

public class WeekdayFragment extends Fragment {
    private FragmentManager fm;
    private FragmentTransaction ft;
    private ArrayList<edu> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private eduAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private eduAdapter.OnItemClickListener listener;
    String mond="",tued="",wedd="",thud="",fird="",satd="",sund="";
    String Week = "";
    String result = "", result2="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_weekday, container, false);

        CheckBox mon = v.findViewById(R.id.week_check_mon);
        CheckBox tue = v.findViewById(R.id.week_check_tue);
        CheckBox wed = v.findViewById(R.id.week_check_wed);
        CheckBox thu = v.findViewById(R.id.week_check_thu);
        CheckBox fri = v.findViewById(R.id.week_check_fri);
        CheckBox sat = v.findViewById(R.id.week_check_sat);
        CheckBox sun = v.findViewById(R.id.week_check_sun);
        mon.setOnClickListener(Weekday);
        tue.setOnClickListener(Weekday);
        wed.setOnClickListener(Weekday);
        thu.setOnClickListener(Weekday);
        fri.setOnClickListener(Weekday);
        sat.setOnClickListener(Weekday);
        sun.setOnClickListener(Weekday);

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

        recyclerView = v.findViewById(R.id.recycler_weekday);
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

    View.OnClickListener Weekday = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (((CheckBox) v).isChecked()) {
                switch (v.getId()) {
                    case R.id.week_check_mon:
                        mond += "???,";
                        break;
                    case R.id.week_check_tue:
                        tued += "???,";
                        break;
                    case R.id.week_check_wed:
                        wedd += "???,";
                        break;
                    case R.id.week_check_thu:
                        thud += "???,";
                        break;
                    case R.id.week_check_fri:
                        fird += "???,";
                        break;
                    case R.id.week_check_sat:
                        satd += "???,";
                        break;
                    case R.id.week_check_sun:
                        sund += "???,";
                        break;
                }
            } else {
                switch (v.getId()) {
                    case R.id.week_check_mon:
                        mond=mond.replace("???,", "");
                        break;
                    case R.id.week_check_tue:
                        tued=tued.replace("???,", "");
                        break;
                    case R.id.week_check_wed:
                        wedd=wedd.replace("???,", "");
                        break;
                    case R.id.week_check_thu:
                        thud=thud.replace("???,", "");
                        break;
                    case R.id.week_check_fri:
                        fird=fird.replace("???,", "");
                        break;
                    case R.id.week_check_sat:
                        satd=satd.replace("???,", "");
                        break;
                    case R.id.week_check_sun:
                        sund=sund.replace("???,", "");
                        break;

                }
            }
                Week=mond+tued+wedd+thud+fird+satd+sund;
            if(Week.length()>0) {
                Week = Week.substring(0, Week.length() - 1);
            }
            Toast.makeText(getContext(), Week, Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
                result2 = Week + "\uf8ff";
                databaseReference.orderByChild("week").startAt(Week).endAt(result2).addListenerForSingleValueEvent(new ValueEventListener() {
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
                        Week="";

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


