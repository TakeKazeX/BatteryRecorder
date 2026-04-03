# 历史统计缓存规则

## 1. 当前共享缓存入口

- 共享版本号定义在 `app/.../data/history/HistoryCacheVersions.kt`
- 缓存命名统一收敛在 `app/.../data/history/HistoryCacheNaming.kt`
- 当前三类缓存共用同一个版本号：
  - `app_stats`
  - `scene_stats`
  - `power_stats`

## 2. 什么时候必须提升版本

满足任一条件，就提升 `HISTORY_STATS_CACHE_VERSION`：

- 缓存文件内容结构变化
- cache key 组成变化
- 同一路径下缓存文件的命名约定变化
- 读取侧校验字段变化，导致旧缓存不再满足当前解析前提

不要只改某个目录名、后缀名或局部 key 逻辑而不升版本。

## 3. 当前项目约束

- `HistoryCacheNaming.kt` 是缓存命名唯一入口，不要把路径拼接散落回各个计算器。
- `RecordDetailPowerStatsComputer` 的缓存命中必须继续校验 `sourceLastModified` 与源文件 `lastModified()` 一致。
- 当前策略是不兼容时直接失效旧缓存，不做静默兼容或降级读取。
- 若只是纯计算逻辑变化，但缓存输入、payload、命中前提完全不变，可以不升版本；拿不准时优先升版本。

## 4. 典型影响面

常见涉及文件包括：

- `AppStatsComputer.kt`
- `SceneStatsComputer.kt`
- `RecordDetailPowerStatsComputer.kt`
- `HistoryCacheNaming.kt`
- `HistoryCacheVersions.kt`
- `HistoryRepository.kt`

改动前先确认本次缓存是否真的由这些入口持有，不要只凭文档猜。
