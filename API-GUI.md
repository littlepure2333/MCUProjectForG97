# views包 -- 视图和前端逻辑

## views.FormatCheck -- 格式检查(static)
* views.FormatCheck.isID()
* views.FormatCheck.isName()
* views.FormatCheck.isAddress()

## views.components.GotoButton -- 前往下一界面的按钮
* GotoButton button = new GotoButton(text, nextPanel)

## 进入views.BorrowAndReturnPanel的条件
* State.setCurrentStation 设置目标站点（选择站点）
* Windows.stationView(mode) 选择站点界面的模式