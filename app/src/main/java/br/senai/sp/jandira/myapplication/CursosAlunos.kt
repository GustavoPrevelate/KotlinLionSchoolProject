package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.BottomShape
import br.senai.sp.jandira.lionschool.components.TopShape
import br.senai.sp.jandira.lionschool.model.ListaCursos

import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.myapplication.ui.theme.BlueLionSchool
import br.senai.sp.jandira.myapplication.ui.theme.YellowLionSchool

import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CursoAlunosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CursoAlunosScreen()

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CursoAlunosScreen() {

    val context = LocalContext.current

    var cursos by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Cursos>())
    }
    //Hamad para a API
    val call = RetrofitFactory().getCursoService().getCursos()

    call.enqueue(object : Callback<ListaCursos> {
        override fun onResponse(
            call: Call<ListaCursos>,
            response: Response<ListaCursos>
        ) {
            cursos = response.body()!!.cursos
        }

        override fun onFailure(call: Call<ListaCursos>, t: Throwable) {
            Log.i("ds2m","onFailure: $t")
        }

    })
    Log.i("ds2m"," $cursos")
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
            Spacer(modifier = Modifier.padding(25.dp))
            Column(
                modifier = Modifier
                    .height(400.dp)
                    .padding(top = 0.dp)
                    .background(Color.White, shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)),

            ) {

                LazyHorizontalGrid(
                    modifier = Modifier
                        .height(350.dp)
                        .width(450.dp)
                        .padding(top = 40.dp, bottom = 0.dp, start = 5.dp),
                    rows = GridCells.Adaptive(100.dp),
                    horizontalArrangement = Arrangement.spacedBy(80.dp),
                    verticalArrangement = Arrangement.spacedBy(26.dp),
                    content = {
                        items(cursos) {

                            Card(
                                modifier = Modifier
                                    .border(8.dp, color = YellowLionSchool, CircleShape)
                                    .height(150.dp)
                                    .width(400.dp)
                                    .clickable {
                                        val intent = Intent(context, AlunosActivity::class.java)
                                        intent.putExtra("sigla", it.sigla)
                                        intent.putExtra("titulo", it.nome)
                                        context.startActivity(intent)
                                    },

                                shape = CircleShape,
                                backgroundColor = BlueLionSchool,

                                ) {
                                Row(
                                    modifier = Modifier,
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .size(90.dp),
                                        model = it.icone,
                                        contentDescription = "Curso",


                                    )
                                    Spacer(modifier = Modifier.padding(25.dp))
                                    Text(
                                        text = it.sigla,
                                        fontSize = 56.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                    )

                                }
                            }

                        }
                    } )

            }
            Spacer(modifier = Modifier.padding(30.dp))
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(0.dp))
            {
                BottomShape()
            }
        }

    }
}