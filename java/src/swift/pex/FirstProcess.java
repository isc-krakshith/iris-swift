package swift.pex;

import com.intersystems.enslib.pex.*;

/**
 * Simple Business Process implemented in Java.
 * Receives a request and sends it to TargetConfigName.
 */
public class FirstProcess extends BusinessProcess {
    
    /** TargetConfigName: production element name where message will be sent to */
    public String TargetConfigName;

    /** Timeout when sending a message to TargetConfigName  */
    public String Timeout = "PT10S";
    
    /**
     * OnInit: Business Process initialization
     */
    public void OnInit() throws Exception {
        LOGINFO("FirstProcess:OnInit()");
        
        // check settings
        if (TargetConfigName == null) {
            LOGWARNING("Missing TargetConfigName");
        } else {
            LOGINFO("TargetConfigname=" + TargetConfigName);
        }
        
        return;
    }

    /**
     * OnRequest: handle the initiating incoming message
     */
    public Object OnRequest(Object request) throws Exception {
        LOGINFO("OnRequest");
        SendRequestAsync(TargetConfigName, (Message)request, true); // ResponseRequired=true
        SetTimer(Timeout, "HasTimedOut");
        return null;
    }
    
    /**
     * OnResponse: handle received responses
     */
    public Object OnResponse(Object request, java.lang.Object response, Object callRequest, Object callResponse, String completionKey) throws Exception {
        LOGINFO("OnResponse, CompletionKey=" + completionKey);
        if (completionKey != "HasTimedOut") {
            response = (FirstMessage) callResponse;
        }
        LOGINFO("Response:" + response.toString());
        return response;
    }
    
    /**
     * OnComplete: this is called after receiving responses for all requests sent to targets
     */
    public Object OnComplete(java.lang.Object request, java.lang.Object response) throws java.lang.Exception {
        LOGINFO("OnComplete");
        return response;
    }

}