package yangfentuozi.batteryrecorder.ui.model

import yangfentuozi.batteryrecorder.shared.util.LoggerX

val LoggerX.LogLevel.displayName: String
    get() = when (this) {
        LoggerX.LogLevel.Verbose -> "详细"
        LoggerX.LogLevel.Debug -> "调试"
        LoggerX.LogLevel.Info -> "信息"
        LoggerX.LogLevel.Warning -> "警告"
        LoggerX.LogLevel.Error -> "错误"
        LoggerX.LogLevel.Assert -> "断言"
        LoggerX.LogLevel.Disabled -> "关闭"
    }
