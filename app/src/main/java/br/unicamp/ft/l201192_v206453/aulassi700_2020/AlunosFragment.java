package br.unicamp.ft.l201192_v206453.aulassi700_2020;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.l201192_v206453.aulassi700_2020.RecyclerAlunos.Aluno;
import br.unicamp.ft.l201192_v206453.aulassi700_2020.RecyclerAlunos.MyFirstAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlunosFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyFirstAdapter myFirstAdapter;

    public AlunosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alunos, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        myFirstAdapter = new MyFirstAdapter(
                new ArrayList(Arrays.asList(Aluno.getAlunos(getContext())))
        );

        /* evento */
        MyFirstAdapter.FirstOnLongClickListener listener2 = new MyFirstAdapter.FirstOnLongClickListener(){
            @Override
            public void firstOnItemLongClick(String nomeClicado) {
                System.out.println(nomeClicado);
            }
        };
        myFirstAdapter.setFirstOnLongClickListener(listener2);
        /*fim do evento*/

        recyclerView.setAdapter(myFirstAdapter);

        return view;
    }
}