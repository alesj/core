package org.jboss.weld.tests.contexts.request.rmi;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.api.Run;
import org.jboss.arquillian.api.RunModeType;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.BeanArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.weld.tests.category.Integration;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@Category(Integration.class)
@RunWith(Arquillian.class)
@Run(RunModeType.AS_CLIENT)
public class RmiRequestScopeTest
{
    @Deployment
    public static Archive createDeployment()
    {
        return ShrinkWrap.create(BeanArchive.class, "test.jar")
                .intercept(BridgeInterceptor.class)
                .addClasses(
                      Bridge.class,
                      BridgeBean.class,
                      Config.class,
                      Manager.class,
                      My.class,
                      BridgeAnnotation.class,
                      BridgeInterceptor.class
                );
    }

    private static Bridge getBridge()
    {
        try
        {
            Object serverObject = new InitialContext().lookup("BridgeBean/remote");
            return (Bridge) serverObject;
        }
        catch (NamingException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRmiRequestScopeActive()
    {
        System.out.println(getBridge().doSomething());
    }
}
