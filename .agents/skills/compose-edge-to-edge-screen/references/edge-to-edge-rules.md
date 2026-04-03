# Compose 沉浸页面规则

## 1. 当前统一入口

- `BaseActivity` 负责启用 edge-to-edge
- 页面级 `Scaffold` 统一通过 `batteryRecorderScaffoldInsets()` 只消费顶部和水平安全区
- 底部导航手势区不由 `Scaffold` 统一吃掉，而是交给页面内容层自己决定是否避让

## 2. 当前项目约束

- 普通页面保持：顶层 `Scaffold` 吃顶部/水平安全区，内容层按需要补底部手势区
- 页面外层 margin 默认按 `16.dp` 收敛；看到更大留白时先判断是不是组件内部排版
- 带底部筛选栏或操作区的页面，要单独处理导航栏底部 inset，不能直接照搬普通滚动页
- 记录详情页属于特殊结构：外层容器负责铺满沉浸背景，内层滚动内容按内容高度展开，避免手势区出现常驻大空白

## 3. 改动时不要做的事

- 不要把底部 `navigationBars` 重新塞回 `Scaffold` 的统一 `contentWindowInsets`
- 不要在多个组件里各自散落写一套 inset 规则
- 不要为了消掉空白，粗暴移除底部安全区，导致按钮或列表贴到手势区
- 不要未经确认就顺手统一重构所有页面的沉浸结构

## 4. 常见影响面

常见涉及文件包括：

- `app/.../ui/BaseActivity.kt`
- `app/.../utils/EdgeToEdgeInsets.kt`
- `app/.../ui/screens/home/HomeScreen.kt`
- `app/.../ui/screens/settings/SettingsScreen.kt`
- `app/.../ui/screens/history/HistoryListScreen.kt`
- `app/.../ui/screens/history/RecordDetailScreen.kt`
- `app/.../ui/screens/prediction/PredictionDetailScreen.kt`

改前先确认真实入口在页面本身、公共 inset 工具，还是某个底部栏组件。
