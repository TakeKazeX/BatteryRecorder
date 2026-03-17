package yangfentuozi.batteryrecorder.ui.dialog.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import yangfentuozi.batteryrecorder.shared.util.LoggerX
import yangfentuozi.batteryrecorder.ui.model.displayName
import yangfentuozi.batteryrecorder.ui.theme.AppShape

data class LogLevelDialogConfig(
    val currentValue: LoggerX.LogLevel,
    val onDismiss: () -> Unit,
    val onSave: (LoggerX.LogLevel) -> Unit,
    val onReset: () -> Unit
)

/**
 * 渲染日志级别选择弹窗。
 *
 * @param config 当前级别与确认、重置回调集合。
 * @return 无，直接渲染日志级别选择弹窗。
 */
@Composable
fun LogLevelDialog(
    config: LogLevelDialogConfig
) {
    var selectedLevel by remember(config.currentValue) { mutableStateOf(config.currentValue) }

    AlertDialog(
        onDismissRequest = config.onDismiss,
        title = { Text("日志级别") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                LoggerX.LogLevel.entries.forEach { level ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selectedLevel == level,
                                onClick = { selectedLevel = level }
                            )
                            .padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedLevel == level,
                            onClick = null
                        )
                        Text(
                            text = level.displayName,
                            modifier = Modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { config.onSave(selectedLevel) }) {
                Text("确定")
            }
        },
        dismissButton = {
            TextButton(onClick = config.onReset) {
                Text("重置")
            }
        },
        shape = AppShape.extraLarge
    )
}
