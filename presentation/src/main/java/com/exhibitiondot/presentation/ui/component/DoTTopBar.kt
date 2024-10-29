package com.exhibitiondot.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.exhibitiondot.presentation.R
import com.exhibitiondot.presentation.ui.theme.screenPadding

@Composable
fun DoTTopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    onBack: (() -> Unit)? = null,
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 50.dp,
                bottom = screenPadding,
                start = screenPadding,
                end = screenPadding
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        onBack?.let {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = it),
                painter = painterResource(R.drawable.ic_back),
                tint = MaterialTheme.colorScheme.surfaceContainerHigh,
                contentDescription = "back-icon"
            )
            DoTSpacer(size = 14)
        }
        title?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}