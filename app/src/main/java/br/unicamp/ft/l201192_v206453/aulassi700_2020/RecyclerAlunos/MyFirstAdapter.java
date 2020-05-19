package br.unicamp.ft.l201192_v206453.aulassi700_2020.RecyclerAlunos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.unicamp.ft.l201192_v206453.aulassi700_2020.R;

public class MyFirstAdapter extends RecyclerView.Adapter {

    private ArrayList<Aluno> alunos;
    public MyFirstAdapter(ArrayList alunos){
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_layout, parent, false);

        /*evento*/

        return new MyFirstViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyFirstViewHolder)holder).bind(alunos.get(position));
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    /*evento*/
    public interface FirstOnLongClickListener {void firstOnItemLongClick(String nome);}
    private FirstOnLongClickListener firstOnLongClickListener;
    public void setFirstOnLongClickListener(FirstOnLongClickListener str){this.firstOnLongClickListener = str;}


    class MyFirstViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView  nomeAluno;
        private TextView idadeAluno;
        private LinearLayout linear;

        public MyFirstViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            nomeAluno  = itemView.findViewById(R.id.nome);
            idadeAluno  = itemView.findViewById(R.id.idade);
            linear = itemView.findViewById(R.id.linear);


            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    if (firstOnLongClickListener != null){
                        alunos.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
//                    firstOnLongClickListener.firstOnItemLongClick(txt.getText().toString());
                    }
                    return true;
                }
            });

        }

        public void bind(Aluno aluno){
            imageView.setImageResource(aluno.getFoto());
            nomeAluno.setText(aluno.getNome());
            idadeAluno.setText(aluno.getCargo());
        }
    }
}

















