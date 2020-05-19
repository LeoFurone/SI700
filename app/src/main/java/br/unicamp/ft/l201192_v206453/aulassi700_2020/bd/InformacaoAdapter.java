package br.unicamp.ft.l201192_v206453.aulassi700_2020.bd;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.unicamp.ft.l201192_v206453.aulassi700_2020.R;
import br.unicamp.ft.l201192_v206453.aulassi700_2020.models.Informacao;

public class InformacaoAdapter extends BaseAdapter {

    private Context context;
    private List<Informacao> lista;

    public InformacaoAdapter(Context context, List<Informacao> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Informacao getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;

        if (convertView == null ) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            v = inflater.inflate(R.layout.item_lista, null);
        } else {
            v = convertView;
        }

        Informacao i = getItem(position);

        TextView itemNome = (TextView) v.findViewById(R.id.nome_item);
        TextView itemEmail = (TextView) v.findViewById(R.id.email_item);

        itemNome.setText(i.getNome());
        itemEmail.setText(i.getEmail());

        return v;
    }
}
