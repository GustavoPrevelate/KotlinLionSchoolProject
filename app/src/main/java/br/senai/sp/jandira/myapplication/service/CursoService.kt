package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.ListaCursos
import br.senai.sp.jandira.lionschool.model.ListaAlunos
import br.senai.sp.jandira.lionschool.model.ListaDisciplinas
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CursoService {

    //Busca pelo curso
    @GET("cursos")
    fun getCursos(): Call<ListaCursos>

}