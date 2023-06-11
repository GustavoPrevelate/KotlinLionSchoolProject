package br.senai.sp.jandira.lionschool


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.TopShape
import br.senai.sp.jandira.myapplication.R
import br.senai.sp.jandira.myapplication.ui.theme.BlueLionSchool


class MeiosContatoAcitivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MeiosContatoScreen()

        }
    }
}

@Composable
fun MeiosContatoScreen() {



        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Row(
                    modifier = Modifier.padding(0.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    TopShape()

                }
                Spacer(modifier = Modifier.padding(bottom = 20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 0.dp)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .height(height = 150.dp)
                            .width(350.dp),

                        backgroundColor = BlueLionSchool,
                        shape = RoundedCornerShape( 50.dp)
                    ) {
                        Text(
                            text = "(11) 4774-4700",
                            fontSize = 24.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 10.dp),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "lionschool@gmail.com",
                            fontSize = 24.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 50.dp),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Rua tcc, 211,  Centro, Jandira",
                            fontSize = 24.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 90.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.padding(bottom = 20.dp))
                    Card(
                        modifier = Modifier
                            .height(height = 150.dp)
                            .width(350.dp),
                        backgroundColor = BlueLionSchool,
                        shape = RoundedCornerShape( 50.dp)
                    ) {
                        Text(
                            text = "Copyright © 2022 | \n Gustavo Prevelate",
                            fontSize = 24.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.padding(bottom = 20.dp))

                    Card(
                        modifier = Modifier
                            .height(height = 150.dp)
                            .width(350.dp),
                        backgroundColor = BlueLionSchool,
                        shape = RoundedCornerShape( 50.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(0.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                modifier = Modifier.size(64.dp),
                                painter = painterResource(id = R.drawable.youtube),
                                contentDescription = "youtube")
                            Spacer(modifier = Modifier.padding(start = 15.dp))
                            Image(
                                modifier = Modifier.size(64.dp),
                                painter = painterResource(id = R.drawable.twitter),
                                contentDescription = "twitter")
                            Spacer(modifier = Modifier.padding(start = 15.dp))
                            Image(
                                modifier = Modifier.size(64.dp),
                                painter = painterResource(id = R.drawable.instagram),
                                contentDescription = "instagram")
                            Spacer(modifier = Modifier.padding(start = 15.dp))
                            Image(
                                modifier = Modifier.size(64.dp),
                                painter = painterResource(id = R.drawable.facebook),
                                contentDescription = "facebook")
                        }


                    }
                    Spacer(modifier = Modifier.padding(bottom = 20.dp))
                }

            }



        }

}

@Preview(showSystemUi = true)
@Composable
fun MeiosContatoScreenPreview() {
    MeiosContatoScreen()
}