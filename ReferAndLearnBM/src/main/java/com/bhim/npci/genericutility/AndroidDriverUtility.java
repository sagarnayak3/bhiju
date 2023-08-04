package com.bhim.npci.genericutility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.ScreenOrientation;
import io.appium.java_client.appmanagement.ApplicationState;
import static com.bhim.npci.genericutility.UtilityObjectClass.*;

/**
 * @author piyus
 * This class is used to perform all android driver related operations
 */
public class AndroidDriverUtility{

	
	/**
	 * @author piyus 
	 * This method is used to run the application in the background
	 * @param packageName
	 * @return
	 */
	public ApplicationState runAppInBackground(String packageName) {
		getDriver().runAppInBackground(Duration.ofSeconds(30));
		return getDriver().queryAppState(packageName);
	}

	/**
	 * @author piyus 
	 * This method is used to install the application and check
	 *         whether it is installed or not
	 * @param apkPath
	 * @param packageName
	 * @return
	 */
	public boolean installApp(String apkPath, String packageName) {
		getDriver().installApp(apkPath);
		return getDriver().isAppInstalled(packageName);
	}

	/**
	 * @author piyus 
	 * This method is used to uninstall the application
	 * @param packageName
	 * @return
	 */
	public boolean removeApp(String packageName) {
		getDriver().removeApp(packageName);
		return getDriver().isAppInstalled(packageName);
	}

	/**
	 * @author piyus 
	 * This method is used to change orientation of device to
	 *         landscape
	 */
	public void orientationLandScape() {
		ScreenOrientation s = getDriver().getOrientation();
		getDriver().rotate(s.LANDSCAPE);
	}

	/**
	 * @author piyus 
	 * This method is used to change orientation of device to portrait
	 */
	public void orientationPortrait() {
		ScreenOrientation s = getDriver().getOrientation();
		getDriver().rotate(s.PORTRAIT);
	}

	/**
	 * @author piyus 
	 * This method is used to open the notification tray
	 */
	public void openNotification() {
		getDriver().openNotifications();
	}

	/**
	 * @author piyus 
	 * This method is used to hide the keyboard
	 */
	public void hideKeyboard() {
		getDriver().hideKeyboard();
	}

	/**
	 * This method is used to check whether device is locked or not
	 * @author piyus
	 * @return
	 */
	public boolean deviceLockState() {
		return getDriver().isDeviceLocked();
	}

	/**
	 * @author piyus 
	 * This method is used to get all context id's
	 */
	public void getContexts() {
		Set<String> contextID = getDriver().getContextHandles();
		for (String string : contextID) {
			System.out.println(string);
		}
	}

	/**
	 * @author piyus 
	 * This method is used to switch context to web view
	 * @param contextId
	 */
	public void switchContext(String contextId) {
		getDriver().context(contextId);
	}
}
