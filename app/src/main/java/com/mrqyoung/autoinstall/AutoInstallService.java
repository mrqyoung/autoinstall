package com.mrqyoung.autoinstall;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class AutoInstallService extends AccessibilityService {

    private static final String TAG = "AutoInstallService";
    private static String PACKAGE_INSTALLER = "com.android.packageinstaller";

    public AutoInstallService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //log(event.toString());
        if (event.getSource() == null) {
            log("<null> event source");
            return;
        }
        int eventType = event.getEventType();
        if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
                && event.getPackageName().equals(PACKAGE_INSTALLER)) {
            boolean r = performInstallation(event);
            log("Action Perform: " + r);
        }

    }

    @Override
    public void onInterrupt() {
        log("AutoInstallServiceInterrupted");
    }

    private void log(String s) {
        Log.d(TAG, s);
    }

    private boolean performInstallation(AccessibilityEvent event) {
        List<AccessibilityNodeInfo> nodeInfoList;
        String[] labels = new String[]{"确定", "安装", "下一步", "完成"};
        for (String label : labels) {
            nodeInfoList = event.getSource().findAccessibilityNodeInfosByText(label);
            if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
                boolean performed = performClick(nodeInfoList);
                if (performed) return true;
            }
        }
        return false;
    }

    private boolean performClick(List<AccessibilityNodeInfo> nodeInfoList) {
        for (AccessibilityNodeInfo node : nodeInfoList) {
            if (node.isClickable() && node.isEnabled()) {
                return node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
        return false;
    }

}
