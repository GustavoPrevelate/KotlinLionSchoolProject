package br.senai.sp.jandira.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.CursoAlunosActivity
import br.senai.sp.jandira.myapplication.ui.theme.BlueLionSchool
import br.senai.sp.jandira.myapplication.ui.theme.YellowLionSchool
import br.senai.sp.jandira.lionschool.components.BottomShape


class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            HomeScreen()

        }
    }
}

@Composable
fun HomeScreen() {

    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .fillMaxHeight()) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp), horizontalArrangement = Arrangement.End
            ) {



            }
            Box() {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Row(modifier = Modifier
                        .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top

                    ) {
                        Image(
                            modifier = Modifier.size(250.dp),
                            painter = painterResource(id = R.drawable.logo__grande),
                            contentDescription = "Logo_grande"
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 25.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 0.dp)
                    ) {
                        Text(
                            text = "Sobre Lion School",
                            fontSize = 32.sp,
                            color = BlueLionSchool,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "A Lion School é uma escola prestigiada, onde você pode escolher um curso para se profissionalizar, Torne-se já um(a) aluno(a)! ",
                            fontSize = 24.sp,
                            color = BlueLionSchool,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp, start = 30.dp, end = 40.dp),
                            textAlign = TextAlign.Center,

                            )

                    }
                    Spacer(modifier = Modifier.padding(bottom = 20.dp))
                    Button(
                        onClick = {
                            val intent = Intent(context, CursoAlunosActivity::class.java)
                            context.startActivity(intent)},
                        modifier = Modifier
                            .width(300.dp)
                            .padding(vertical = 0.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(YellowLionSchool),
                        border = BorderStroke(width = 6.dp, color = BlueLionSchool)
                    ) {
                        Text(
                            text = "Cursos disponíveis",
                            fontSize = 24.sp,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }


                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom
            ) {

                BottomShape()

            }
        }


    }

}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}