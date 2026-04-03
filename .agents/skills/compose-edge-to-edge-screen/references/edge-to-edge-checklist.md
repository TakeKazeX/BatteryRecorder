# Compose 沉浸页面改动检查清单

## 实施前

- 确认目标页面是否为顶层 Screen
- 确认页面底部是否存在按钮区、筛选栏、图表控制区或其他需要避让手势区的内容
- 确认当前页面是否已经使用 `batteryRecorderScaffoldInsets()`

## 实施中

- 顶层 `Scaffold` 继续只消费顶部和水平安全区
- 内容层按实际需要追加底部导航栏 padding
- 若页面可滚动，确认滚动容器不会因 `fillMaxSize()` 等结构把底部手势区渲染成永久空白
- 若页面有固定底部区域，确认其 inset 处理与滚动内容不冲突

## 实施后

- 检查顶部状态栏区域是否被内容遮挡
- 检查底部手势区上方是否出现多余常驻空白
- 检查按钮、筛选栏、列表尾项在手势区附近是否可正常点击和阅读
- 搜索 `batteryRecorderScaffoldInsets`、`navigationBarBottomPadding`，确认没有新散落入口
- 若沉浸规则真实现状发生变化，同步更新 `AGENTS.md`
- 不要自行 build Android 项目；改完后提示用户手动测试
