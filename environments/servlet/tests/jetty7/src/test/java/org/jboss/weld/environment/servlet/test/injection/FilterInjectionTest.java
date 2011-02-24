package org.jboss.weld.environment.servlet.test.injection;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.api.Run;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.junit.runner.RunWith;
import static org.jboss.arquillian.api.RunModeType.AS_CLIENT;
import static org.jboss.weld.environment.servlet.test.util.JettyDeployments.JETTY_ENV;

@RunWith(Arquillian.class)
@Run(AS_CLIENT)
public class FilterInjectionTest extends FilterInjectionTestBase
{
   @Deployment
   public static WebArchive deployment()
   {
      return FilterInjectionTestBase.deployment().addWebResource(JETTY_ENV, "jetty-env.xml");
   }

}
