package com.belajar.spotifykw.startedscreen

import android.media.tv.TvContract.Channels.Logo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.belajar.spotifykw.R
import com.belajar.spotifykw.routes.Routes


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Previeasadww() {

}

@Composable
fun GetStartedLayout(
    navController: NavController
) {
   Box(
       modifier = Modifier
           .fillMaxWidth()
           .fillMaxHeight()
   ) {
       Image(painter = painterResource(id = R.drawable.bg_started)
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
                   horizontalAlignment = CenterHorizontally
               ) {
                   LogoSpotify()
                   Spacer(modifier = Modifier.weight(1F))
                   StartedText(navController)
               }
           }

       }
   }
}

@Composable
fun LogoSpotify() {
    Image(painter = painterResource(id = R.drawable.logo_spotify)
        , contentDescription = null)
}

@Composable
fun StartedText(
    navController: NavController
) {
    Column (
        horizontalAlignment = CenterHorizontally
            ) {
        Text(text = stringResource(id = R.string.started_text),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.surface)
        Spacer(modifier = Modifier.height(21.dp))
        Text(text = stringResource(id = R.string.lorem),
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary)
        Spacer(modifier = Modifier.height(37.dp))
        Button(onClick = { navController.navigate(Routes.ChooseMode.routes)},
                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
                shape = RoundedCornerShape(30.dp)
        ) {
            Text(text = "Get Started",
                style = MaterialTheme.typography.h1,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(start = 40.dp,end = 40.dp, top = 20.dp, bottom = 20.dp))
        }
    }
}