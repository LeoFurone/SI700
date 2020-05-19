package br.unicamp.ft.l201192_v206453.aulassi700_2020;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import static androidx.core.content.ContextCompat.getSystemService;


/**
 * A simple {@link Fragment} subclass.
 */
public class AutorFragment extends Fragment {
    private EditText editNome, editEmail;
    private CheckBox checkDiretoria, checkAtleta, checkSocio, checkAluno;
    private RadioButton radioTorcida;
    private Button btnSend;


    public AutorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_autor, container, false);

        editNome = (EditText) view.findViewById(R.id.edit_user);
        editEmail = (EditText) view.findViewById(R.id.edit_email);

        checkDiretoria = (CheckBox) view.findViewById(R.id.diretoria);
        checkAtleta = (CheckBox) view.findViewById(R.id.atleta);
        checkSocio = (CheckBox) view.findViewById(R.id.socio);
        checkAluno = (CheckBox) view.findViewById(R.id.aluno);

        radioTorcida = (RadioButton) view.findViewById(R.id.termos);

        btnSend = (Button) view.findViewById(R.id.btn_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nome = editNome.getText().toString();
                String email = editEmail.getText().toString();
                String diretoria = String.valueOf(checkDiretoria.isChecked());
                String atleta = String.valueOf(checkAtleta.isChecked());
                String socio = String.valueOf(checkSocio.isChecked());
                String aluno = String.valueOf(checkAluno.isChecked());

                String torcida = String.valueOf(radioTorcida.isChecked());


                if(!nome.isEmpty() && !email.isEmpty()){
                    Ion.with(getContext())
                            .load("https://aaatu.000webhostapp.com/create.php")
                            .setBodyParameter("nome", nome)
                            .setBodyParameter("email", email)
                            .setBodyParameter("diretoria", diretoria)
                            .setBodyParameter("atleta", atleta)
                            .setBodyParameter("socio", socio)
                            .setBodyParameter("aluno", aluno)
                            .setBodyParameter("torcida", torcida)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    if(result.get("CREATE").getAsString().equals("OK")){
                                        Toast.makeText(getActivity(),"Sucesso!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(),"Erro ao gravar no banco",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    changeKeyboardState(v);
                    NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
                    navController.navigate(R.id.nav_home);

                } else {
                    Toast.makeText(getActivity(),"Name e e-mail obrigatórios!", Toast.LENGTH_SHORT).show();

                }
            }

        });



        // Inflate the layout for this fragment
        return view;
    }

    public void changeKeyboardState(View view){
        if (view != null) {
            Context context = getContext();
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}