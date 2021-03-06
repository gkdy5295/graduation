package com.example.graduation;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class LocationFragment extends Fragment {
    private Spinner spinner1, spinner2;
    ArrayAdapter<CharSequence> adspin1, adspin2;
    String choice_do="";
    String choice_se="";
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ArrayList<edu> arrayList,filteredList;
    private eduAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private eduAdapter.OnItemClickListener listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.frag_location,container,false);
        spinner1 = v.findViewById(R.id.spinner_loca);
        spinner2 =v.findViewById(R.id.spinner_orgn);

        recyclerView = v.findViewById(R.id.recycler_location);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        database = FirebaseDatabase.getInstance(); // ?????????????????? ?????????????????? ??????
        databaseReference = database.getReference("edu"); // DB ????????? ??????=


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.chungju, android.R.layout.simple_spinner_dropdown_item);
                        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adspin2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch(position){
                                    case 0:
                                        choice_se="??????";
                                        choice_do="??????"+"\uf8ff";
                                        basest();
                                        break;
                                    case 1:
                                        choice_se="??????????????????????????????????????????";
                                        baseon();
                                        break;
                                    case 2:
                                        choice_se="?????????????????????????????????";
                                        baseon();
                                        break;
                                    case 3:
                                        choice_se="???????????????????????????";
                                        baseon();
                                        break;
                                    case 4:
                                        choice_se="?????????????????????????????????";
                                        baseon();
                                        break;
                                    case 5:
                                        choice_se="???????????????";
                                        baseon();
                                        break;
                                    case 6:
                                        choice_se="???????????????????????????";
                                        baseon();
                                        break;
                                    case 7:
                                        choice_se="?????????????????????";
                                        baseon();
                                        break;
                                    case 8:
                                        choice_se="???????????????????????????";
                                        baseon();
                                        break;
                                    case 9:
                                        choice_se="????????????????????????";
                                        baseon();
                                        break;
                                    case 10:
                                        choice_se="??????????????????";
                                        baseon();
                                        break;
                                    case 11:
                                        choice_se="???????????????????????????";
                                        baseon();
                                        break;
                                    case 12:
                                        choice_se="?????????????????????(??????)";
                                        baseon();
                                        break;
                                    case 13:
                                        choice_se="?????????????????????(??????)";
                                        baseon();
                                        break;
                                    case 14:
                                        choice_se="????????????????????????????????????????????????";
                                        baseon();
                                        break;
                                    case 15:
                                        choice_se="????????????.?????????????????????????????????????????????";
                                        baseon();
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 1:
                        adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.cheongju, android.R.layout.simple_spinner_dropdown_item);
                        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adspin2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch(position) {
                                    case 0:
                                        choice_se = "??????";
                                        choice_do = "??????" + "\uf8ff";
                                        basest();
                                        break;
                                    case 1:
                                        choice_se = "?????????????????????-??????";
                                        baseon();
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 2:
                        adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.jecheon, android.R.layout.simple_spinner_dropdown_item);
                        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adspin2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch(position) {
                                    case 0:
                                        choice_se = "??????";
                                        choice_do = "??????" + "\uf8ff";
                                        basest();
                                        break;
                                    case 1:
                                        choice_se = "?????????????????????";
                                        baseon();
                                        break;
                                    case 2:
                                        choice_se = "????????????????????????";
                                        baseon();
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 3:
                        adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.boeun, android.R.layout.simple_spinner_dropdown_item);
                        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adspin2);
                        break;
                    case 4:
                        adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.okcheon, android.R.layout.simple_spinner_dropdown_item);
                        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adspin2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch(position) {
                                    case 0:
                                        choice_se = "??????";
                                        choice_do = "??????" + "\uf8ff";
                                        basest();
                                        break;
                                    case 1:
                                        choice_se = "?????????????????????";
                                        baseon();
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 5:
                        adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.yeongdong, android.R.layout.simple_spinner_dropdown_item);
                        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adspin2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch(position) {
                                    case 0:
                                        choice_se = "??????";
                                        choice_do = "??????" + "\uf8ff";
                                        basest();
                                        break;
                                    case 1:
                                        choice_se = "????????????";
                                        baseon();
                                        break;
                                    case 2:
                                        choice_se = "????????????????????????????????????????????????";
                                        baseon();
                                        break;
                                    case 3:
                                        choice_se = "??????????????????";
                                        baseon();
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 6:
                        adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.jeungpyeong, android.R.layout.simple_spinner_dropdown_item);
                        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adspin2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch(position) {
                                    case 0:
                                        choice_se = "??????";
                                        choice_do = "??????" + "\uf8ff";
                                        basest();
                                        break;
                                    case 1:
                                        choice_se = "????????????????????????";
                                        baseon();
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 7:
                        adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.jincheon, android.R.layout.simple_spinner_dropdown_item);
                        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adspin2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch(position) {
                                    case 0:
                                        choice_se = "??????";
                                        choice_do = "??????" + "\uf8ff";
                                        basest();
                                        break;
                                    case 1:
                                        choice_se = "?????????????????????";
                                        baseon();
                                        break;
                                    case 2:
                                        choice_se = "?????????????????????????????????";
                                        baseon();
                                        break;
                                    case 3:
                                        choice_se = "???????????????????????????";
                                        baseon();
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 8:
                        adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.goesan, android.R.layout.simple_spinner_dropdown_item);
                        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adspin2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch(position) {
                                    case 0:
                                        choice_se = "??????";
                                        choice_do = "??????" + "\uf8ff";
                                        basest();
                                        break;
                                    case 1:
                                        choice_se = "????????????????????????";
                                        baseon();
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 9:
                        adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.eumseong, android.R.layout.simple_spinner_dropdown_item);
                        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adspin2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch(position) {
                                    case 0:
                                        choice_se = "??????";
                                        choice_do = "??????" + "\uf8ff";
                                        basest();
                                        break;
                                    case 1:
                                        choice_se = "????????????????????????";
                                        baseon();
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 10:
                        adspin2 = ArrayAdapter.createFromResource(getActivity(), R.array.danyang, android.R.layout.simple_spinner_dropdown_item);
                        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adspin2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch(position) {
                                    case 0:
                                        choice_se = "??????";
                                        choice_do = "??????" + "\uf8ff";
                                        basest();
                                        break;
                                    case 1:
                                        choice_se = "???????????????????????????";
                                        baseon();
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        adapter = new eduAdapter(arrayList, getContext(), listener);
        recyclerView.setAdapter(adapter);
        return v;



    }

    public void baseon(){
        databaseReference.orderByChild("institution").equalTo(choice_se).addListenerForSingleValueEvent(new ValueEventListener() {
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // ????????? ??????????????? ?????? ?????? ???
                Log.e("HomeFragment", String.valueOf(databaseError.toException())); // ????????? ??????
            }
        });
    }

    public void basest(){
        databaseReference.orderByChild("institution").startAt(choice_se).endAt(choice_do).addListenerForSingleValueEvent(new ValueEventListener() {
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // ????????? ??????????????? ?????? ?????? ???
                Log.e("HomeFragment", String.valueOf(databaseError.toException())); // ????????? ??????
            }
        });
    }

}
