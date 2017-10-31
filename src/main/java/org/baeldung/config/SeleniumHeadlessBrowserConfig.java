package org.baeldung.config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumHeadlessBrowserConfig extends SeleniumConfig {

    @Override
    public void openNewWindow() {
    	
        /*final Capabilities capabilities = DesiredCapabilities.htmlUnit();
        
        driver = new HtmlUnitDriver(capabilities) {
            @Override
            protected WebClient newWebClient(BrowserVersion version) {
                WebClient webClient = super.newWebClient(version);
                webClient.getOptions().setThrowExceptionOnScriptError(false);
                return webClient;
            }
        };*/
    	
    	DesiredCapabilities caps = new DesiredCapabilities();
    	caps.setJavascriptEnabled(true);                
    	caps.setCapability("takesScreenshot", true);  
    	if (Utils.TARGET_ENV_WINDOWS.equalsIgnoreCase(this.getTargetEnv())){
	    	caps.setCapability(
	    	                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
	    	                    "bin/"+ this.getTargetEnv() +"/phantomjs.exe"
	    	                    );  
    	}else {
    		caps.setCapability(
                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    "bin/"+ this.getTargetEnv() +"/phantomjs"
                    );  
    	}
    	
    	 driver = new  PhantomJSDriver(caps);

         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


}