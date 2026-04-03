---
name: compose-edge-to-edge-screen
description: 处理 BatteryRecorder Compose 页面沉浸和 inset 相关改动的固定流程。用于新增或修改顶层 Screen、Scaffold、滚动内容区、底部手势区避让、全屏图表页等布局时，避免破坏现有 edge-to-edge 规则。
---

# compose-edge-to-edge-screen

1. 先读取 `references/edge-to-edge-rules.md`，确认当前页面级 inset 消费规则和特殊页面结构。
2. 先判断目标页面属于哪类：普通页面、带底部筛选/操作区的页面，还是类似记录详情的沉浸型页面。
3. 顶层 `Scaffold` 默认继续使用 `batteryRecorderScaffoldInsets()`；不要把底部手势区重新交回 `Scaffold` 一刀切处理。
4. 底部导航手势区由内容层按需追加 `navigationBarBottomPadding()` 或等价封装；优先保持现有页面风格一致。
5. 改布局前后都对照 `references/edge-to-edge-checklist.md`，检查顶部安全区、底部手势区、滚动容器高度和页面外层 margin 是否被破坏。
6. 记录详情这类沉浸页若需要铺满背景，优先沿用“外层铺背景，内层滚动内容按内容高度展开”的结构，不要直接把滚动列改成 `fillMaxSize()` 制造常驻底部空白。
7. Android 项目不要自行 build；完成后明确告知用户手动测试。
