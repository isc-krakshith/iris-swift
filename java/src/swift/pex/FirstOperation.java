package swift.pex;

import com.intersystems.enslib.pex.*;

/**
 * Simple Business Operation implemented in Java.
 * Request: FirstMessage 
 * Response: FirstMessage containing the original value in uppercase 
 */
public class FirstOperation extends BusinessOperation {
    
    /** Sample property that can be set from IRIS Management Portal */
    public String CLIENTID;

    /** 
     * OnInit: initilize Business Operation 
     */
    public void OnInit() throws Exception {
        LOGINFO("FirstOperation:OnInit()");
        LOGINFO(String.format("CLIENTID: %s", CLIENTID));    
        return;
    }

    /**
     * OnTearDown: actions you want to do when stopping the Business Operation
     */
    public void OnTearDown() throws Exception {
        LOGINFO("FirstOperation:OnTearDown()");
        return;
    }

    /**
     * OnMessage: handle incoming request messages
     */
    public Object OnMessage(Object request) throws Exception {
        FirstMessage response = new FirstMessage();
        response.value = ((FirstMessage) request).value.toUpperCase();
        return response;
    }
}
