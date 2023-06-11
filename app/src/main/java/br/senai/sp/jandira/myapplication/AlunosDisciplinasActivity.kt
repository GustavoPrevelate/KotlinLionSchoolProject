package br.senai.sp.jandira.lionschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.BottomShape
import br.senai.sp.jandira.lionschool.components.TopShape
import br.senai.sp.jandira.lionschool.model.ListaDisciplinas
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.myapplication.ui.theme.BlueLionSchool
import br.senai.sp.jandira.myapplication.ui.theme.GrayLionSchool
import br.senai.sp.jandira.myapplication.ui.theme.RedLionSchool
import br.senai.sp.jandira.myapplication.ui.theme.YellowLionSchool
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlunosDisciplinasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlunosDisciplinasScreen(intent.extras?.getString("nome"),intent.extras?.getString("foto"),intent.extras?.getString("matricula"))
        }
    }
}
@Composable
fun AlunosDisciplinasScreen(nome :String?,foto :String?,matricula : String?) {

    var materias by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Disciplinas>())
    }
    //Hamad para a API
    val call = RetrofitFactory().getAlunosDisciplinasService().getAlunoDisciplinas(matricula)

    call.enqueue(object : Callback<ListaDisciplinas> {
        override fun onResponse(
            call: Call<ListaDisciplinas>,
            response: Response<ListaDisciplinas>

        ) {
            Log.i("ds2m","$response")
            materias = response.body()!!.disciplinas

        }

        override fun onFailure(call: Call<ListaDisciplinas>, t: Throwable) {
            Log.i("ds2m","onFailure: $t")
        }

    })
    Log.i("ds2m","$materias")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(51,71,176)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment =Alignment.CenterHorizontally,
            ) {

                TopShape()
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier
                    .background(RedLionSchool, shape = RoundedCornerShape(20.dp, 50.dp, 20.dp, 50.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        modifier = Modifier.size(120.dp),
                        model = foto,
                        contentDescription = "logo",

                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Column() {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = nome.toString(),
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = matricula.toString(),
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )

                    }

                }
            }
            Column(
                modifier = Modifier
                    .width(450.dp)
                    .height(350.dp)
                    .background(Color.White, shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    text = "Desempenho",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    color = YellowLionSchool
                )
                LazyColumn(
                    content = {
                        items(materias) {
                            Column() {
                                if (it.media.toInt() >= 70) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {

                                        Text(
                                            modifier = Modifier
                                                .padding(start = 70.dp, bottom = 10.dp),
                                            text = it.nome,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Black,
                                            color = BlueLionSchool
                                        )

                                    }
                                    Box(
                                        modifier = Modifier
                                            .width(450.dp)
                                            .height(30.dp)
                                            .padding(30.dp, 0.dp)
                                            .background(GrayLionSchool)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth((it.media.toFloat() * 0.01).toFloat())
                                                .height(35.dp)
                                                .background(BlueLionSchool)
                                        ) {

                                        }
                                    }



                                } else if (it.media.toInt() in 50..69) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {

                                        Text(
                                            modifier = Modifier
                                                .padding(start = 70.dp, bottom = 10.dp),
                                            text = it.nome,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Black,
                                            color = YellowLionSchool
                                        )

                                    }
                                    Box(
                                        modifier = Modifier
                                            .width(450.dp)
                                            .height(30.dp)
                                            .padding(30.dp, 0.dp)
                                            .background(GrayLionSchool)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth((it.media.toFloat() * 0.01).toFloat())
                                                .height(35.dp)
                                                .background(YellowLionSchool)
                                        ) {

                                        }
                                    }

                                } else{
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {

                                        Text(
                                            modifier = Modifier
                                                .padding(start = 70.dp, bottom = 10.dp),
                                            text = it.nome,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Black,
                                            color = RedLionSchool
                                        )

                                    }
                                    Box(
                                        modifier = Modifier
                                            .width(450.dp)
                                            .height(30.dp)
                                            .padding(30.dp, 0.dp)
                                            .background(GrayLionSchool)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth((it.media.toFloat() * 0.01).toFloat())
                                                .height(35.dp)
                                                .background(RedLionSchool)
                                        ) {

                                        }
                                    }

                                }

                                Spacer(modifier = Modifier.height(20.dp))
                            }

                        }
                    }

                )


            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomShape()

            }
        }


    }
}