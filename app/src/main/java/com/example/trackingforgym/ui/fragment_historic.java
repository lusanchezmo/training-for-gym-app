package com.example.trackingforgym.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackingforgym.R;
import com.example.trackingforgym.data.RutineHistoric;
import com.example.trackingforgym.databinding.FragmentHistoricBinding;

import java.util.Date;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_historic#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_historic extends Fragment {
    RecyclerView recycler;
    ConstraintLayout contenedorCalendario;
    private FragmentHistoricBinding binding;
    View root;
    //private HomeViewModel homeViewModel;
    Adaptador_Historic_Layout adapter;
    //RutineHistoric[] rutinas;
    MCalendarView calendarView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHistoricBinding.inflate(inflater, container, false);
        //root = binding.getRoot();
        //setElements();

        return inflater.inflate(R.layout.fragment_historic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        root =view;
        setElements();


        System.out.println("cambadio helo");
    }

    public void setElements(){

        contenedorCalendario= (ConstraintLayout) root.findViewById(R.id.contenedorCalendario);
        Button btnMosCalendar = (Button) root.findViewById(R.id.btnVerCalendario);
        btnMosCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarCalendario(view);
            }
        });
        calendarView = (MCalendarView) root.findViewById(R.id.calendar);
        calendarView.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                Toast.makeText(getContext(),  date.getYear()+"/"+date.getMonthString()+"/"+date.getDayString(), Toast.LENGTH_LONG).show();
            }
        });

        setRecycler();
    }
    public void mostrarCalendario(View view){
        /*Calendar cal = Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day =cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMont){
                System.out.println("holasd");
            }
        },day,month,year);
        datePicker.show();*/
        if(contenedorCalendario.getVisibility()==View.VISIBLE)
            contenedorCalendario.setVisibility(View.GONE);
        else
            contenedorCalendario.setVisibility(View.VISIBLE);
    }

    public fragment_historic() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static fragment_historic newInstance(String param1, String param2) {
        fragment_historic fragment = new fragment_historic();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void setRecycler(){

        recycler = (RecyclerView) root.findViewById(R.id.recyclerHistorialRutinas);
        recycler.setLayoutManager(new LinearLayoutManager(root.getContext(),LinearLayoutManager.VERTICAL, false));

        RutineHistoric[] rutinas={
                new RutineHistoric("#E91E63", "Pierna", new Date(2020,10,18)),
                new RutineHistoric("#F44336", "tren Sup", new Date(2021,1,6)),
                new RutineHistoric("#03A9F4", "tren inf", new Date(2022,2,10)),
                new RutineHistoric("#009688", "Pecho y tricep", new Date(2020,10,16)),
                new RutineHistoric("#673AB7", "Gluteo", new Date(2022,10,16)),
                new RutineHistoric("#E91E63", "Pierna", new Date(2028,7,26)),
                new RutineHistoric("#F44336", "tren Sup", new Date(2021,10,14)),
                new RutineHistoric("#03A9F4", "tren inf", new Date(2019,5,6)),
                new RutineHistoric("#009688", "Pecho y tricep", new Date(2018,10,9)),
                new RutineHistoric("#009688", "Pecho y tricep", new Date(2022,5,16)),
                new RutineHistoric("#673AB7", "Gluteo", new Date(2020,10,17))
        };

        adapter=new Adaptador_Historic_Layout(rutinas);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirHistoricEspecifico(adapter.getDatos(recycler.getChildAdapterPosition(view)));
            }
        });
        recycler.setAdapter(adapter);

        for(RutineHistoric i:rutinas){
            calendarView.markDate(i.getFecha().getYear(), i.getFecha().getMonth(), i.getFecha().getDay());
        }
    }

    public void abrirHistoricEspecifico(String str){
        System.out.println(str);
        Toast toast = Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT);
        toast.show();
    }
}