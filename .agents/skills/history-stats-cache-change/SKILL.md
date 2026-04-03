---
name: history-stats-cache-change
description: 处理 BatteryRecorder 历史统计缓存相关改动的固定流程。用于修改 AppStats、SceneStats、记录详情 power stats 的缓存 payload、cache key、命名目录、失效规则或版本号时，避免只改半条缓存链路。
---

# history-stats-cache-change

1. 先读取 `references/cache-rules.md`，确认当前共享缓存版本、命名入口和适用范围。
2. 开始改动前，先判断本次变化属于哪一类：缓存 payload 结构变化、cache key 组成变化、命名目录变化，还是只改计算逻辑但缓存输入输出保持不变。
3. 只要 `app_stats`、`scene_stats`、`power_stats` 任一缓存格式或 key 组成变化，就统一提升共享版本；不要只改单个缓存目录名蒙混过关。
4. 实施前后对照 `references/cache-checklist.md`，补齐版本、命名、读取校验与调用方一致性检查。
5. 不要新增静默兼容层或双写双读过渡逻辑；当前项目规则是显式失效旧缓存，让问题暴露。
6. 只改与本次缓存链路直接相关的文件，不顺手重构历史仓库或预测链路。
7. Android 项目不要自行 build；完成后明确告知用户手动测试。
