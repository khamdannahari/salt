package com.khamdan.nahari.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.khamdan.nahari.R
import com.khamdan.nahari.data.response.User

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val viewState by viewModel.state.collectAsState()
    Surface(Modifier.fillMaxSize()) {
        UserRow(portfolios = viewState.users)
    }
}

@Composable
fun UserRow(portfolios: List<User>) {
    Column {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            text = stringResource(id = R.string.home_title),
            style = MaterialTheme.typography.subtitle1
        )
        val lastIndex = portfolios.size - 1
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 8.dp)
        ) {
            itemsIndexed(portfolios) { index: Int, portfolio: User ->
                UserItem(portfolio = portfolio)
                if (index < lastIndex) Spacer(modifier = Modifier.width(24.dp))
            }
        }
    }
}

@Composable
fun UserItem(portfolio: User) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        AsyncImage(
            model = portfolio.avatar,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = portfolio.first_name.plus(" ").plus(portfolio.last_name),
            style = MaterialTheme.typography.body1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
        Text(
            text = portfolio.email,
            style = MaterialTheme.typography.body2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 2.dp)
                .fillMaxWidth()
        )
    }
}