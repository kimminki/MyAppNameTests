package com.uia.example.skp;


import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

@SuppressWarnings("unused")
public class UiAutomatorTest extends UiAutomatorTestCase{   

	UiWatcher watcher;

	public void turnOn() throws UiObjectNotFoundException, RemoteException {

		 	//Check ScreenOn
		  if(!getUiDevice().isScreenOn())
		  {
			  getUiDevice().wakeUp();
			  getUiDevice().swipe(100,206,620,1121,2);
			  System.out.println("Device Wakes up");
		  }
		  else
		  {
			  System.out.println("Device already wakes up");
		  }
		  return;
	}
	
	public void ready() throws UiObjectNotFoundException, RemoteException {   

		  getUiDevice().pressHome();
		  System.out.println("press Home");

		//Simulate a short press on the HOME button
	      UiObject allAppsButton = new UiObject(new UiSelector()
	         .description("앱스"));
	      
	      allAppsButton.clickAndWaitForNewWindow();
	      
	      System.out.println("Click Apps");

	      UiObject appsTab = new UiObject(new UiSelector()
	         .text("앱스"));
	      
	      // Simulate a click to enter the Apps tab.
	      appsTab.click();
		  System.out.println("Click AppTab");
		  
		  return;
	}
	
	public void start(String nameOfApp, String logoOfApp) throws UiObjectNotFoundException{
		//scroll control
		UiScrollable scrollScreen = new UiScrollable(new UiSelector().scrollable(true));

		//시간 측정 변수
		long timeStart = 0;
		long timeEnd = 0;
       
		UiObject appToCheck = new UiObject(new UiSelector()
		  .description(nameOfApp));

		//hoppin이 현재화면에 존재하는지 찾는다.      
		while(!appToCheck.exists())
		{
	    	System.out.println("Finding"+nameOfApp+"..");
	        scrollScreen.setAsHorizontalList();
	    	scrollScreen.scrollToEnd(1,3);
	     }
	            
	      //hoppin을 발견하면 시간측정
	     if(appToCheck.exists())
	     {         
	        System.out.println(nameOfApp+"is founded..");
	    
	        
        //Click Hoppin
        appToCheck.click();
        //시간측정 시작
        timeStart = System.currentTimeMillis();

        if(new UiObject(new UiSelector().description(logoOfApp)).exists())
        {
        	//시간측정 종료
        	timeEnd = System.currentTimeMillis();
        }
	         
	         System.out.println("loading time for "+nameOfApp+" is "+(timeEnd - timeStart)/1000f+"sec");     
	      }
	     return;
	}

	public void end() throws UiObjectNotFoundException, RemoteException {   
	     
		
	     getUiDevice().pressHome();     
	     System.out.println("Pressed Home");

	     getUiDevice().pressRecentApps();
	     UiObject waitUi = new UiObject(new UiSelector());
	     waitUi.waitUntilGone(2000);
	     System.out.println("pressRecentApps");
	     UiScrollable appViews = new UiScrollable(new UiSelector()
         .scrollable(true));
	     appViews.setAsHorizontalList();

	     UiSelector tresh = new UiSelector().clickable(getUiDevice().click(610, 1250));

	     
	     return;
	}
	
	public void testDemo() throws UiObjectNotFoundException, RemoteException {   
	  	
		turnOn();
	//	ready();
	//	start("hoppin","global_navigation_bar_title_img_center");
	//	start("T store","global_navigation_bar_title_img_center");
	//	getUiDevice().registerWatcher(getName(), watcher);
		end();
		System.out.println("Test Finished! Byebye");
		return;
  }   
}

