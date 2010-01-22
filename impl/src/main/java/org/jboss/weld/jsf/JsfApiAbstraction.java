/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.jsf;

import org.jboss.weld.bootstrap.api.Service;
import org.jboss.weld.resources.spi.ResourceLoader;
import org.jboss.weld.util.ApiAbstraction;
import org.jboss.weld.util.reflection.SecureReflections;

/**
 * Utility class for JSF related components, concepts etc. It can also
 * report on the compatibility of the current JSF implementation being used.
 * 
 * @author Pete Muir
 * @author Dan Allen
 */
public class JsfApiAbstraction extends ApiAbstraction implements Service
{
   // An UI component
   public final Class<?> UICOMPONENT_CLASS;
   
   // JSF FacesContext
   public final Class<?> FACES_CONTEXT;
   
   public final double MINIMUM_API_VERSION;
   
   public JsfApiAbstraction(ResourceLoader resourceLoader)
   {
      super(resourceLoader);
      this.UICOMPONENT_CLASS = classForName("javax.faces.component.UIComponent");
      this.FACES_CONTEXT = classForName("javax.faces.context.FacesContext");
      double version = 2.0;
      if (this.FACES_CONTEXT != null)
      {
         version = SecureReflections.isMethodExists(FACES_CONTEXT, "isPostback") ? 2.0 : 1.2;
      }
      MINIMUM_API_VERSION = version;
   }
   
   public boolean isApiVersionCompatibleWith(double version)
   {
      return MINIMUM_API_VERSION >= version;
   }
   
   public void cleanup() {}

}
