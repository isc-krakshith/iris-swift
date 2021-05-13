package swift.pex;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.intersystems.enslib.pex.*;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.Tag;
import com.prowidesoftware.swift.model.field.Field;

//import java.io.IOException;
import java.util.Locale;

/**
 * Simple Business Service implemented in Java.
 * Sends a FirstMessage to TargetConfigName
 */
public class FirstService extends BusinessService {
    
    /** TargetConfigName: production element name where message will be sent to */
    public String TargetConfigName;

    /** 
     * OnInit: initilize Business Service 
     */
    public void OnInit() throws Exception {
        LOGINFO("FirstService:OnInit()");
        
        // check init settings
        if (TargetConfigName == null) {
            LOGWARNING("Missing required TargetConfigName");
        } else {
            LOGINFO("TargetConfigname=" + TargetConfigName);
        }
        
        return;
    }

    /** 
     * OnProcessInput: process input from Inbound Adapter
     */
    public Object OnProcessInput(Object messageInput) throws Exception {

        // create a new FirstMessage request
        FirstMessage myRequest = new FirstMessage();
        
        // assign some content to the FirstMessage value
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 

        Locale locale = Locale.getDefault();
        SwiftMessage sm = SwiftMessage.parse("{1:F01BACOARB1A0B20000000000}{2:I103ADRBNL21XXXXU2}{3:{108:FOOB3926BE868XXX}}{4:\n" +
                ":20:REFERENCE\n" +
                ":23B:CRED\n" +
                ":32A:180730USD1234567,89\n" +
                ":50A:/12345678901234567890\n" +
                "CFIMHKH1XXX\n" +
                ":59:/12345678901234567890\n" +
                "JOE DOE\n" +
                "MyStreet 1234\n" +
                ":71A:OUR\n" +
                "-}{5:{CHK:3916EF336FF7}}");

        /*
         * With single value per field
         */
        myRequest.value = "Sender: " + sm.getSender() + " | " + "Receiver: " + sm.getReceiver() + " | ";

        for (Tag tag : sm.getBlock4().getTags()) {
            Field field = tag.asField();
            myRequest.value = myRequest.value + Field.getLabel(field.getName(), "103", null, locale)  + " | ";
            myRequest.value = myRequest.value + field.getValueDisplay(locale) + " | ";
        }

        /*
         * With values per component
         */
        for (Tag tag : sm.getBlock4().getTags()) {
            Field field = tag.asField();
            myRequest.value = myRequest.value + " | " + Field.getLabel(field.getName(), "103", null, locale) + " | ";
            for (int component = 1; component <= field.componentsSize(); component++) {
                if (field.getComponent(component) != null) {
                    myRequest.value = myRequest.value + " | " + field.getComponentLabel(component) + ": ";
                    myRequest.value = myRequest.value + " | " +  field.getValueDisplay(component, locale) +  " | ";
                }
            }
        }

        
        myRequest.value = myRequest.value + "Sent time: " + dtf.format(now);

        // send FirstMessage to TargetConfigName using a 20s timeout:
        FirstMessage myResponse = (FirstMessage) SendRequestSync(TargetConfigName, myRequest, 20);

        return null;
    }

    
}