package org.jboss.weld.tests.contexts.request.rmi;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BridgeBean implements Bridge {
    @Inject @My private Config config;

    @BridgeAnnotation
    public String doSomething() {
        System.err.println(">>>>>>>>>>> Bridge.doSomething.");
        return config.toString();
    }
}
