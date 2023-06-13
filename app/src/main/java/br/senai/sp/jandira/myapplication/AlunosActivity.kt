package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.BottomShape
import br.senai.sp.jandira.lionschool.components.TopShape
import br.senai.sp.jandira.lionschool.model.ListaAlunos
import br.senai.sp.jandira.lionschool.service.RetrofitFactory

import br.senai.sp.jandira.myapplication.ui.theme.BlueLionSchool
import br.senai.sp.jandira.myapplication.ui.theme.YellowLionSchool
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AlunosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                AlunosScreen(intent.extras?.getString("sigla"),intent.extras?.getString("titulo"))

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlunosScreen(curso : String?,titulo : String?) {

    val context = LocalContext.current

    var alunos by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Alunos>())
    }
    //Hamad para a API
    val call = RetrofitFactory().getAlunosService().getAlunosCurso(curso)

    call.enqueue(object : Callback<ListaAlunos> {
        override fun onResponse(
            call: Call<ListaAlunos>,
            response: Response<ListaAlunos>
        ) {
            alunos = response.body()!!.alunos
        }

        override fun onFailure(call: Call<ListaAlunos>, t: Throwable) {
            Log.i("DS2M","onFailure: $t")
        }

    })
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(51,71,176)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TopShape()

            }
            Column(
                modifier = Modifier
                    .width(500.dp)
                    .height(500.dp)
                    .background(Color.White, shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.padding(27.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(9.dp))
                    var color by remember { mutableStateOf(Color.White) }
                    var colorText by remember { mutableStateOf(Color(51,71,176)) }
                    var isEnable by remember{ mutableStateOf( false)}
                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp),
                        onClick = {
                            if(!isEnable){
                                color = Color(51,71,176)
                                colorText = Color.White
                                isEnable = true
                            }else{
                                color = Color.White
                                colorText = Color(51,71,176)
                                isEnable = false
                            }
                            Log.i("ds2m","onFailure: ${isEnable}")

                            val call = RetrofitFactory().getAlunosService().getAlunosCursoStatus(curso,"Cursando")

                            call.enqueue(object : Callback<ListaAlunos> {
                                override fun onResponse(
                                    call: Call<ListaAlunos>,
                                    response: Response<ListaAlunos>
                                ) {
                                    alunos = response.body()!!.alunos
                                }

                                override fun onFailure(call: Call<ListaAlunos>, t: Throwable) {
                                    Log.i("ds2m","onFailure: $t")
                                }

                            })
                        },
                        border = BorderStroke(4.dp, BlueLionSchool),
                        backgroundColor = color,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 35.dp, top = 10.dp),
                            color = colorText,
                            text = "CURSANDO",
                            fontWeight = FontWeight.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(9.dp))
                    var colorFinalizado by remember { mutableStateOf(Color.White) }
                    var colorTextFinalizado by remember { mutableStateOf(YellowLionSchool) }
                    var isEnableFinalizado by remember{ mutableStateOf( false)}
                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp),
                        onClick = {
                            if(!isEnableFinalizado){
                                colorFinalizado = YellowLionSchool
                                colorTextFinalizado = Color.White
                                isEnableFinalizado = true
                            }else{
                                colorFinalizado = Color.White
                                colorTextFinalizado = YellowLionSchool
                                isEnableFinalizado = false
                            }

                            val call = RetrofitFactory().getAlunosService().getAlunosCursoStatus(curso,"Finalizado")
                            call.enqueue(object : Callback<ListaAlunos> {
                                override fun onResponse(
                                    call: Call<ListaAlunos>,
                                    response: Response<ListaAlunos>
                                ) {
                                    alunos = response.body()!!.alunos
                                }

                                override fun onFailure(call: Call<ListaAlunos>, t: Throwable) {
                                    Log.i("ds2m","onFailure: $t")
                                }

                            })

                        },
                        backgroundColor = colorFinalizado ,
                        border = BorderStroke(4.dp, YellowLionSchool),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 35.dp, top = 10.dp),
                            color = colorTextFinalizado,
                            text = "FINALIZADO",
                            fontWeight = FontWeight.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(9.dp))

                }
                LazyColumn(
                    content = {
                        items(alunos){

                            Card(
                                onClick = {
                                    val intent = Intent(context, AlunosDisciplinasActivity::class.java)
                                    intent.putExtra("matricula", it.matricula)
                                    intent.putExtra("foto", it.foto)
                                    intent.putExtra("nome", it.nome)
                                    context.startActivity(intent)
                                },
                                modifier = Modifier
                                    .height(150.dp)
                                    .fillMaxWidth()
                                    .padding(20.dp, 0.dp)
                                ,
                                shape = RoundedCornerShape(0.dp),
                                backgroundColor = if (it.status == "Cursando") BlueLionSchool else YellowLionSchool,

                                ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    AsyncImage(
                                        modifier = Modifier.size(150.dp),
                                        model = it.foto,
                                        contentDescription = "Estudante"
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column() {
                                        Text(
                                            modifier = Modifier.width(180.dp),
                                            text = it.nome.uppercase(),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Black,
                                            color = Color.White,
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Row() {
                                            Text(
                                                modifier = Modifier.width(100.dp),
                                                text = "Matricula:".uppercase(),
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Black,
                                                color = Color.White,
                                            )
                                            Text(
                                                modifier = Modifier.width(90.dp),
                                                text = it.matricula.uppercase(),
                                                fontSize = 14.sp,
                                                color = Color.White,
                                            )
                                        }
                                        Row() {
                                            Text(
                                                modifier = Modifier.width(100.dp),
                                                text = "Sexo:".uppercase(),
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Black,
                                                color = Color.White,
                                            )
                                            Text(
                                                modifier = Modifier.width(90.dp),
                                                text = it.sexo.uppercase(),
                                                fontSize = 14.sp,
                                                color = Color.White,
                                            )
                                        }

                                    }

                                }
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }
                    }
                )

            }
            Spacer(modifier = Modifier.height(10.dp))
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
