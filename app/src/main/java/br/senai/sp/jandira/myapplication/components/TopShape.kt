package br.senai.sp.jandira.lionschool.components

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.MeiosContatoAcitivity
import br.senai.sp.jandira.myapplication.Home
import br.senai.sp.jandira.myapplication.R
import br.senai.sp.jandira.myapplication.ui.theme.BlueLionSchool
import br.senai.sp.jandira.myapplication.ui.theme.YellowLionSchool


@Composable
fun TopShape() {
    val context = LocalContext.current

    Row(

    ){

        Card(
            modifier = Modifier
                .height(height = 120.dp)
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(context, Home::class.java)
                    context.startActivity(intent)
                },
            backgroundColor = Color(0xFF3347B0),
            shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
        ) {
            Row(
                modifier = Modifier.padding(0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically



            ) {
                Text(
                    text = "Home",
                    fontSize = 64.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp),
                    textAlign = TextAlign.Center,
                )
            }

        }
    }
}

@Preview
@Composable
fun TopFormPreview() {
    TopShape()
}