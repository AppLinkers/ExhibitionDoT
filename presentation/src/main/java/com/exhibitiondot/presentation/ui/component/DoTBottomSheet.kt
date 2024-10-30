package com.exhibitiondot.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.exhibitiondot.domain.model.Filter
import com.exhibitiondot.presentation.R
import com.exhibitiondot.presentation.ui.screen.main.home.HomeUiState
import com.exhibitiondot.presentation.ui.theme.screenPadding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DoTBottomSheet(
    showSheet: Boolean,
    containerColor: Color = MaterialTheme.colorScheme.background,
    sheetState: SheetState,
    windowInsets: WindowInsets = WindowInsets(top = 0.dp),
    properties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties(),
    onDismissRequest: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit)
) {
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            containerColor = containerColor,
            sheetState = sheetState,
            windowInsets = windowInsets,
            dragHandle = null,
            properties = properties,
            content = content
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeFilterSheet(
    title: String,
    scope: CoroutineScope,
    filerList: List<Filter>,
    selectedFilter: Filter? = null,
    selectedFilterList: List<Filter> = emptyList(),
    onSelectFilter: (Filter) -> Unit,
    onSelectAll: () -> Unit,
    onApplyFilter: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    val showSheet = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    DoTBottomSheet(
        showSheet = true,
        sheetState = showSheet,
        onDismissRequest = onDismissRequest
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            text = title,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Column(
            modifier = Modifier.padding(all = screenPadding)
        ) {
            FilterSelectScreen(
                filterList = filerList,
                selectedFilter = selectedFilter,
                selectedFilterList = selectedFilterList,
                onSelectFilter = onSelectFilter,
                onSelectAll = onSelectAll
            )
            DoTSpacer(size = 50)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeSheetButton(
                    text = stringResource(R.string.close),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.surfaceContainerLow,
                    borderColor = MaterialTheme.colorScheme.onSurface,
                    onClick = {
                        scope.launch { showSheet.hide() }
                            .invokeOnCompletion { onDismissRequest() }
                    }
                )
                DoTSpacer(size = 10)
                HomeSheetButton(
                    text = stringResource(R.string.apply),
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    contentColor = MaterialTheme.colorScheme.background,
                    onClick = {
                        scope.launch {
                            onApplyFilter()
                            showSheet.hide()
                        }.invokeOnCompletion { onDismissRequest() }
                    }
                )
            }
        }
    }
}