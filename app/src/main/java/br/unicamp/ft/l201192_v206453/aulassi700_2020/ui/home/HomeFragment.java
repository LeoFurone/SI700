package br.unicamp.ft.l201192_v206453.aulassi700_2020.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ft.l201192_v206453.aulassi700_2020.R;
import br.unicamp.ft.l201192_v206453.aulassi700_2020.bd.InformacaoAdapter;
import br.unicamp.ft.l201192_v206453.aulassi700_2020.models.Informacao;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    InformacaoAdapter informacaoAdapter;
    List<Informacao> lista;
    private ListView listViewInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        listViewInfo = (ListView) root.findViewById(R.id.listView);

     lista = new ArrayList<Informacao>();
     informacaoAdapter = new InformacaoAdapter(getContext(),lista);

     listViewInfo.setAdapter(informacaoAdapter);

     listaInformacao();

        return root;
    }

    private void listaInformacao() {
        String url = "https://aaatu.000webhostapp.com/read.php";

        Ion.with(getContext())
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        for(int j = 0 ; j < result.size() ; j++ ) {

                            JsonObject obj = result.get(j).getAsJsonObject();

                            Informacao i = new Informacao();

                            i.setNome(obj.get("nome").getAsString());
                            i.setEmail(obj.get("email").getAsString());

                            lista.add(i);
                        }
                        informacaoAdapter.notifyDataSetChanged();
                    }
                });

    }
}
