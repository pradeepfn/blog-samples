package org.wso2.simplefrontend.core;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.wso2.feature.frontend.authentication.AuthenticationAdminStub;


public class AuthenticationAdminServiceClient {
     private static AuthenticationAdminStub authenticationAdminStub;

    public static void init(String backEndServerURL) throws AxisFault {

        authenticationAdminStub = new AuthenticationAdminStub(backEndServerURL);
        ServiceClient client = authenticationAdminStub._getServiceClient();
        Options options = client.getOptions();
        options.setManageSession(true);
    }

    public static String login( String hostName, String userName, String password) throws Exception {

        authenticationAdminStub.login(userName, password, hostName);
        ServiceContext serviceContext = authenticationAdminStub.
                _getServiceClient().getLastOperationContext().getServiceContext();
        String sessionCookie = (String) serviceContext.getProperty(HTTPConstants.COOKIE_STRING);
        return sessionCookie;
    }

    public static void setSystemProperties(String keyStorePath,String keyStoreType, String keyStorePassword ){

        System.setProperty("javax.net.ssl.trustStore", keyStorePath);
        System.setProperty("javax.net.ssl.trustStorePassword", keyStorePassword);
        System.setProperty("javax.net.ssl.trustStoreType", keyStoreType);

    }

   


}
