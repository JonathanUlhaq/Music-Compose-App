package com.belajar.spotifykw.startedscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.belajar.spotifykw.R
import com.belajar.spotifykw.routes.Routes

@Composable
fun ChoseModeLayout(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(painter = painterResource(id = R.drawable.bg_choose)
            , contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop)

        Scaffold(
            backgroundColor = Color.Transparent
        ) {
            Box (
                modifier = Modifier
                    .padding(it)
            ){
                Column (
                    Modifier
                        .padding(start = 37.dp, end = 37.dp, top = 37.dp, bottom = 69.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LogoSpotify()
                    Spacer(modifier = Modifier.weight(1F))
                    ChooseModeItem(navController)
                }
            }

        }
    }
}

@Composable
fun ChooseModeItem(
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(CenterHorizontally)
    ) {
        Text(text = "Choose Mode",
            style = MaterialTheme.typography.h1,
            fontSize = 22.sp,
            color = MaterialTheme.colors.surface
        )
        Spacer(modifier = Modifier
            .height(30.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
        ) {
                IconButton(onClick = { /*TODO*/ }) {
                   Column {
                       Card(
                           backgroundColor = MaterialTheme.colors.onBackground,
                           shape = CircleShape,
                           elevation = 0.dp
                       ) {
                           Icon(painter = painterResource(id = R.drawable.moon),
                               contentDescription = null,
                               modifier = Modifier.padding(20.dp)
                                   .size(24.dp),
                                tint = MaterialTheme.colors.onSurface)
                       }
                       Spacer(modifier = Modifier.height(15.dp))
                       
                       Text(text = "Dark Mode",
                            style = MaterialTheme.typography.h1,
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 16.sp)
                   }
                }

            Spacer(modifier = Modifier.width(70.dp))

            IconButton(onClick = { /*TODO*/ }) {
                Column {
                    Card(
                        backgroundColor = MaterialTheme.colors.onBackground,
                        shape = CircleShape,
                        elevation = 0.dp
                    ) {
                        Icon(painter = painterResource(id = R.drawable.sun),
                            contentDescription = null,
                            modifier = Modifier.padding(20.dp)
                                .size(24.dp),
                            tint = MaterialTheme.colors.onSurface)
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(text = "Light Mode",
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 16.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = { navController.navigate(Routes.Home.routes)},
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(text = "Continue",
                style = MaterialTheme.typography.h1,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(start = 40.dp,end = 40.dp, top = 20.dp, bottom = 20.dp))
        }
    }
}