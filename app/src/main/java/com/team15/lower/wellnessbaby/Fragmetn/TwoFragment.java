package com.team15.lower.wellnessbaby.Fragmetn;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.team15.lower.wellnessbaby.DBhelper.SQLHelper;
import com.team15.lower.wellnessbaby.Model.Vaccine;
import com.team15.lower.wellnessbaby.R;

/**
 * Created by AyeNyeinThu on 27. 10. 2016.
 */
public class TwoFragment extends Fragment {

    Spinner spinner;
    Button see;

    SQLHelper sqlHelper;
    SQLiteDatabase sqLiteDatabase;
    TextView age_des;
    TextView disease;
    TextView medicine;
    TextView note;
    RelativeLayout mama;


    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlHelper = new SQLHelper(getContext(), "healthpart");
        sqLiteDatabase = sqlHelper.getDb();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tpw, container, false);

        spinner = (Spinner) view.findViewById(R.id.age_spinner);
        see = (Button) view.findViewById(R.id.calculate_medicine);
        mama=(RelativeLayout)view.findViewById(R.id.mama);

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.age_group, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final AlertDialog.Builder builder3 = new AlertDialog.Builder(getContext());
        // Get the layout inflater
        inflater = getActivity().getLayoutInflater();
        final View dialogview = inflater.inflate(R.layout.dialogcustomlayout, null);
        builder3.setView(dialogview);
        builder3.setPositiveButton("ထြက္မည္.........", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mama,new TwoFragment()).commit();
            }
        }).setCancelable(false);
        age_des = (TextView) dialogview.findViewById(R.id.age_des);
        disease = (TextView) dialogview.findViewById(R.id.disease_description);
        medicine = (TextView) dialogview.findViewById(R.id.medicine_description);
        note=(TextView)dialogview.findViewById(R.id.note);


        see = (Button) view.findViewById(R.id.calculate_medicine);

        final LayoutInflater finalInflater = inflater;
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index_month = spinner.getSelectedItemPosition();
                String[] age_selected=getResources().getStringArray(R.array.age_group);

                Vaccine va=getAlert(index_month+1);
                age_des.setText(va.getVaccine_age());
                disease.setText(va.getDisease_name());
                medicine.setText(va.getVaccine_name());
                note.setText(va.getVaccine_total_count());
                builder3.show();
            }
        });
        return view;
    }

    private Vaccine getAlert(int id){

        Vaccine vaccine=new Vaccine();
        String query="select * from vaccine where vaccine_id='"+id+"'";
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        Log.i("aung naing tun",cursor.getCount()+"");
        if(cursor.moveToFirst()){
            do{
                vaccine.setVaccine_id(Integer.parseInt(cursor.getString(0)));
                vaccine.setVaccine_age(cursor.getString(1));
                vaccine.setVaccine_name(cursor.getString(2));
                vaccine.setDisease_name(cursor.getString(3));
                vaccine.setVaccine_total_count(cursor.getString(4));
            }while (cursor.moveToNext());
        }
        return vaccine;
    }

}
