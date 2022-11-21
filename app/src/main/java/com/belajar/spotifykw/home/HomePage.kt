package com.belajar.spotifykw.home

import android.content.Context
import android.media.MediaPlayer
import android.widget.Space
import androidx.compose.animation.*
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.belajar.spotifykw.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@Composable
fun HomeLayout() {



    val scrollState = rememberScrollState()
    val mediaPlayer = MediaPlayer.create(LocalContext.current,R.raw.cidro)
    var show by remember {
        mutableStateOf(true)
    }
    var current:Int? by remember {
        mutableStateOf(null)
    }
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {

                TopBar()

        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(start = 15.dp)
            ) {
                item {
                    BannerSong()
                    Spacer(modifier = Modifier
                        .height(30.dp))
                    Categories()
                    Spacer(modifier = Modifier
                        .height(30.dp))
                    ArtistCard()
                    Spacer(modifier = Modifier
                        .height(35.dp))
                    PlayList {
                        show = current == it
                        when(current) {
                            null -> {
                                mediaPlayer.start()
                                current = it}
                            else -> {
                                mediaPlayer.pause()
                                current = null}
                        }


                    }
                }
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = !show,
                enter = expandVertically(expandFrom = Alignment.Bottom, animationSpec = tween(700)),
                exit = shrinkVertically(shrinkTowards = Alignment.Bottom, animationSpec = tween(700))
            ) {
                MusicPlay(mediaPlayer)

            }
        }
    )



}


@Composable
fun MusicPlay(
    mediaPlayer:MediaPlayer
) {
    Card (
        backgroundColor = MaterialTheme.colors.background,
        elevation = 18.dp
            ) {
        Column (
            modifier = Modifier
                .padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.Center
                ) {
            Row(
                modifier = Modifier
                    .padding( start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.billy),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(42.dp))
                Spacer(modifier = Modifier
                    .width(12.dp))
                Column{
                    Text(text = "Cidro",
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 16.sp)

                    Text(text = "Billie Ellish",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 12.sp)
                }

                IconButton(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(End)) {
                    Icon(painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }

           var poss = mediaPlayer.currentPosition.toFloat()
            var position by remember { mutableStateOf(0F) }
            var interaction = remember {
                 MutableInteractionSource()
            }
            var duration = mediaPlayer.duration.toFloat()
            val draged by interaction.collectIsDraggedAsState()
            if (draged) {

                position
            } else {
                position = poss
            }

            Slider(
                modifier = Modifier
                    .padding(12.dp)
                    .height(3.dp),
                value = position ,
                onValueChange = {position = it + 10F },
                valueRange = 0f..duration ,
                interactionSource = interaction
            )

        }
    }
}

@Composable
fun PlayList(
    playMusic:(Int)->Unit
) {
    Column (

    ) {
        PlaylistTitle()
        Spacer(modifier = Modifier.height(23.dp))
        PlayListColumn() {
            playMusic.invoke(it)
        }
    }

}

@Composable
fun PlayListColumn(
    playMusic:(Int)->Unit
) {

    val lagu = listOf(
        "Tanjung Mas Ninggal Janji",
        "Cidro",
        "Sewu Kutho",
        "Teteg Ati",
        "Korban Janji"
    )

    val penyanyi = listOf(
        "Billie Elish",
        "Drake",
        "John Mayer",
        "Axl Rose",
        "Denny Pargoy"
    )

    val durasi = listOf(
        "5:53",
        "5:00",
        "3:43",
        "2:10",
        "6:10"
    )

    Column {
        for (i in 0..durasi.size - 2) {
            PlayListItem(judul = lagu[i], penyanyi =penyanyi[i] , durasi = durasi[i]) {
                playMusic.invoke(i)
            }

        }
    }
}

@Composable
fun PlayListItem(
    judul: String,
    penyanyi: String,
    durasi:String,
    playMusic:()->Unit
) {
    var show by remember {
        mutableStateOf(true)
    }
    val icons by animateIntAsState(targetValue = if (!show) R.drawable.pause else R.drawable.ic_play )
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 20.dp)
            .clickable { playMusic.invoke()
                show = !show}
            ) {
        IconButton(onClick = { }) {
            Card(
                shape = CircleShape,
                backgroundColor = MaterialTheme.colors.onSecondary,
                elevation = 5.dp) {
                Icon(painter = painterResource(id = icons),
                    contentDescription = null,
                    tint = MaterialTheme.colors.surface,
                    modifier = Modifier
                        .padding(8.dp))
            }
        }
        Spacer(modifier = Modifier.width(23.dp))
        
        Column {
            Text(text = judul,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface,
                fontSize = 16.sp,
                modifier = Modifier.width(100.dp))
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = penyanyi,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                fontSize = 12.sp,
                modifier = Modifier.width(100.dp))
        }
        Spacer(modifier = Modifier.width(50.dp))
        Text(text = durasi,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface,
            fontSize = 15.sp)
        Spacer(modifier = Modifier.width(50.dp))
        Icon(painter = painterResource(id = R.drawable.ic_heart)
            , contentDescription = null,
            tint = MaterialTheme.colors.onSecondary)
    }
}

@Composable
fun PlaylistTitle() {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(end = 34.dp)
            ) {
                Text(text = "Playlist",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 20.sp)

        Spacer(modifier = Modifier
            .weight(1F))
        
        Text(text = "See More",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface,
            fontSize = 12.sp)
        
    }
}

@Composable
fun Categories() {
    val categories = listOf(
        "News",
        "Video",
        "Artists",
        "Podcast",
        "Stand Up",
        "Roasting"
    )

    var show by remember {
        mutableStateOf(false)
    }

    var current:Int? by remember {
        mutableStateOf(null)
    }

    LazyRow(content = {
        itemsIndexed(categories) {
            index,item ->
            show = current == index
            CategoriesItem(category = item, show = show) {
                current = index
            }
        }
    })
}

@Composable
fun CategoriesItem(
    category:String,
    show:Boolean,
    getCategories:() -> Unit
) {
    val color by animateColorAsState(targetValue = if (show) MaterialTheme.colors.onSurface else MaterialTheme.colors.secondary)

    Column (
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            ) {
        Text(text = category,
            style = MaterialTheme.typography.h1,
            color = color,
            fontSize = 16.sp,
            modifier = Modifier
                .clickable { getCategories.invoke() })
    Spacer(modifier = Modifier.height(5.dp))
        AnimatedVisibility(visible = show) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 3.5.dp, bottomEnd = 3.5.dp))
                .background(MaterialTheme.colors.primary)
                .width(25.dp)
                .height(3.dp))
        }

    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerSong() {
    HorizontalPager(count = 3) {
        BannerSongItem()
    }
}

@Composable
fun ArtistCard() {
    val artistImage = listOf(
        R.drawable.billy,
        R.drawable.khalid,
        R.drawable.orgil,
        R.drawable.billy
    )

    val namaArtist = listOf(
        "Billie Eilish",
        "Drake",
        "Sumiem",
        "Nunung"
    )
    val lagu = listOf(
        "Sewu Kutho",
        "Tanjung Mas Ninggal Janji",
        "Teteg Ati",
        "Klebus"
    )
    
    LazyRow(content = {
        items(artistImage.size) {
            ArtistCardItem(gambar = artistImage[it],
                penyanyi = namaArtist[it],
                judul = lagu[it ])
        }
    })
}

@Composable
fun ArtistCardItem(
    gambar:Int,
    judul:String,
    penyanyi:String
) {
    Column (
        Modifier.padding(start = 20.dp,end = 15.dp)
            ) {
        Box {
            Card(
                shape = RoundedCornerShape(50.dp),
                backgroundColor = Color.Transparent
            ) {
                Image(painter = painterResource(id = gambar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(185.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .offset(x = 125.dp, y = 155.dp)) {
                Card(
                    shape = CircleShape,
                    backgroundColor = MaterialTheme.colors.onSecondary,
                    elevation = 5.dp) {
                        Icon(painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = null,
                            tint = MaterialTheme.colors.surface,
                            modifier = Modifier
                                .padding(8.dp))
                }
            }
        }
        
        Spacer(modifier = Modifier.height(13.dp))
        Text(text = judul,
            style = MaterialTheme.typography.h1,
            fontSize = 16.sp,
            color = MaterialTheme.colors.onSurface)
        Spacer(modifier = Modifier.height(3.dp))
        Text(text = penyanyi,
            style = MaterialTheme.typography.body1,
            fontSize = 14.sp,
            color = MaterialTheme.colors.onSurface)
    }
}

@Composable
fun BannerSongItem() {
    Box (
        Modifier.padding(start = 15.dp, end = 15.dp)
            ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .offset(y = 33.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colors.primary)
            )

        Image(painter = painterResource(id = R.drawable.billie_home),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 15.dp)
                .offset(y = -2.dp)
                .fillMaxWidth()
                .wrapContentWidth(End)
                .size(155.dp),
            contentScale = ContentScale.FillWidth)

        Column (
            modifier = Modifier
                .offset(y = 46.dp,x = 20.dp)
                ) {
                Text(text = "New Single",
                    style = MaterialTheme.typography.h1,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onSurface)
            Spacer(modifier = Modifier.height(2.dp))

            Text(text = "Ojo Dibandingke",
                style = MaterialTheme.typography.h1,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .width(120.dp))
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = "Billie Eilish",
                style = MaterialTheme.typography.h1,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSurface)

        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(CenterHorizontally)
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface)
            }
        Spacer(modifier = Modifier.width(80.dp))
        
        Image(painter = painterResource(id = R.drawable.logo_spotify), 
            contentDescription = null,
            modifier = Modifier.size(100.dp))

        Spacer(modifier = Modifier.width(80.dp))

        Icon(painter = painterResource(id = R.drawable.menu),
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface)
    }
}