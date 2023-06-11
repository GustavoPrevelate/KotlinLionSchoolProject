package br.senai.sp.jandira.myapplication.service

import br.senai.sp.jandira.lionschool.model.ListaAlunos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlunosService {

    //

    //Busca de alunos com Curso, Status e Ano sendo parametros
    @GET("alunos")
    fun getAlunosCursoStatusAno(
        @Query("curso") curso: String?,
        @Query("status") status: String?,
        @Query("ano") ano: String?
    ): Call<ListaAlunos>

    //Busca de alunos com Curso e Status sendo parametros
    @GET("alunos")
    fun getAlunosCursoStatus(
        @Query("curso") curso: String?,
        @Query("status") status: String?
    ): Call<ListaAlunos>

    //Busca de alunos com Curso sendo parametro
    @GET("alunos")
    fun getAlunosCurso(@Query("curso") curso: String?): Call<ListaAlunos>
}