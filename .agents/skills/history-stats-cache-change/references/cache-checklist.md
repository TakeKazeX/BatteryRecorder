# 历史统计缓存改动检查清单

## 实施前

- 确认本次改动命中的缓存类型：`app_stats`、`scene_stats`、`power_stats`
- 确认是 payload、key、命名还是命中校验变化
- 搜索相关缓存 key/文件名，确认没有并行入口

## 实施中

- 需要升版本时，统一修改 `HISTORY_STATS_CACHE_VERSION`
- 保持缓存命名继续走 `HistoryCacheNaming.kt`
- 若命中规则变化，检查读缓存与写缓存两侧是否同步
- 若改 `power_stats`，确认 `sourceLastModified` 校验仍然成立

## 实施后

- 搜索 `HISTORY_STATS_CACHE_VERSION`、缓存目录名、相关 key，确认没有只改半条链路
- 检查 `AppStatsComputer`、`SceneStatsComputer`、`RecordDetailPowerStatsComputer` 是否仍对同一版本语义达成一致
- 确认没有引入双版本兼容、静默回退或吞错逻辑
- 若缓存规则真实现状发生变化，同步更新 `AGENTS.md`
- 不要自行 build Android 项目；改完后提示用户手动测试
