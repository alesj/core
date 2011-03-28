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
package org.jboss.weld.util.bytecode;

import java.util.Arrays;

/**
 * Method name + params key.
 * 
 * @author Ales Justin
 */
public class MethodNameParamsKey
{
   private MethodInformation mi;
   private String key;

   public MethodNameParamsKey(MethodInformation mi)
   {
      if (mi == null)
         throw new IllegalArgumentException("Null method info");
      this.mi = mi;
      this.key = mi.getName() + Arrays.toString(mi.getParameterTypes());
   }

   public MethodInformation getMethodInfo()
   {
      return mi;
   }

   @Override
   public int hashCode()
   {
      return key.hashCode();
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof MethodNameParamsKey == false)
         return false;

      MethodNameParamsKey mnpk = (MethodNameParamsKey) obj;
      return key.equals(mnpk.key);
   }

   @Override
   public String toString()
   {
      return key;
   }
}
