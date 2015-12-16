AutoInstall
--------
对于一些特殊的Android设备，在adb install过程中会出现安装确认界面，AutoInstall可以帮助自动完成点击步骤，达到无人值守自动安装的目的。

# 实现方法
AutoInstall注册为Android的辅助功能服务（AccessibilityService），监听package为com.android.packageinstaller的事件，在必要的时候自动点击安装确认。
AccessibilityService参见http://developer.android.com/reference/android/accessibilityservice/AccessibilityService.html

# API要求
api_level >= 16 (Android 4.1)

# 用法
安装后在系统设置的辅助功能里面开启，例如：
a. 设置 -> 无障碍/辅助功能 -> 服务 -> AutoInstall -> 开启 -> 确定
b. 设置 -> 其它高级设置 -> 辅助功能 -> 服务 -> AutoInstall -> 开启 -> 确定

# 注意
AutoInstall也实现了手动点击apk文件安装时的自动完成功能，请谨慎开启此服务。AutoInstall存在安全风险，未明用途者不建议安装。安装即默认同意后果和损失自行承担。
