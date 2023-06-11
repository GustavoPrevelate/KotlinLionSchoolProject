package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.myapplication.service.AlunosService
import br.senai.sp.jandira.myapplication.service.DisciplinasAlunosService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val URL_BASE = "https://api-lion-school-gustavo.cyclic.app/v1/lion-school/"

    private val retrofitFactory = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCursoService(): CursoService{
        return retrofitFactory.create(CursoService::class.java)
    }

    fun getAlunosService(): AlunosService{
        return retrofitFactory.create(AlunosService::class.java)
    }

    fun getAlunosDisciplinasService(): DisciplinasAlunosService{
        return retrofitFactory.create(DisciplinasAlunosService::class.java)
    }
}