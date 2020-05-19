package br.unicamp.ft.l201192_v206453.aulassi700_2020.RecyclerAlunos;

import android.content.Context;
import android.content.res.TypedArray;

import br.unicamp.ft.l201192_v206453.aulassi700_2020.R;

public class Aluno {
    private String nome;
    private int foto;
    private String cargo;

    public Aluno(String nome, String cargo, int foto) {
        this.nome = nome;
        this.cargo = cargo;
        this.foto = foto;
    }

    public String getNome(){return nome;}
    public int getFoto(){return foto;}
    public String getCargo(){return cargo;}

    public static Aluno[] getAlunos(Context context){
        if (context != null){
            String[] infos   = context.getResources().getStringArray(R.array.nomes);
            TypedArray fotos = context.getResources().obtainTypedArray(R.array.fotos);

            Aluno[] alunos = new Aluno[infos.length];
            for (int i = 0; i < alunos.length; i++){
                String[] info = infos[i].split(",");
                alunos[i]= new Aluno(
                        info[0],
                        info[1],
                        fotos.getResourceId(i, 0)
                );
            }
            fotos.recycle();
            return alunos;
        }
        return null;
    }

}
