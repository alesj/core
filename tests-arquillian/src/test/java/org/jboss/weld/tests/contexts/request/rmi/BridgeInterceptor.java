package org.jboss.weld.tests.contexts.request.rmi;

import java.io.Serializable;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@BridgeAnnotation
public class BridgeInterceptor implements Serializable
{
   @AroundInvoke
   public Object invoke(InvocationContext context) throws Exception
   {
      System.out.println(">>>>> BEFORE");
      try
      {
         return context.proceed();
      }
      finally
      {
         System.out.println(">>>>> AFTER");
      }
   }
}
