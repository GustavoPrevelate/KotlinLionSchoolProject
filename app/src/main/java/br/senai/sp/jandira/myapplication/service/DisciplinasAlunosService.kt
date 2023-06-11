package br.senai.sp.jandira.myapplication.service

import br.senai.sp.jandira.lionschool.model.ListaDisciplinas
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DisciplinasAlunosService {

    //Busca pelas disciplinas dos alunos com base na matricula
    @GET("alunos-disciplinas")
    fun getAlunoDisciplinas(@Query("matricula") matricula: String?): Call<ListaDisciplinas>
}