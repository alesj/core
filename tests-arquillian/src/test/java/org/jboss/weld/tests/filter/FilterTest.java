/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.weld.tests.filter;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.api.Run;
import org.jboss.arquillian.api.RunModeType;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.weld.tests.category.Integration;
import org.jboss.weld.tests.jsp.JspTest;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
@Category(Integration.class)
@RunWith(Arquillian.class)
@Run(RunModeType.AS_CLIENT)
public class FilterTest
{
   @Deployment
   public static Archive<?> deployment()
   {
      return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(FilterTest.class.getPackage())
            .addWebResource(FilterTest.class.getPackage(), "web.xml", "web.xml")
            .addWebResource(JspTest.class.getPackage(), "faces-config.xml", "faces-config.xml")
            .addResource(JspTest.class.getPackage(), "index.jsp", "index.jsp")
            .addResource(JspTest.class.getPackage(), "home.jspx", "home.jspx")
            .addWebResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void testFilter() throws Exception
   {
      WebClient client = new WebClient();
      client.setThrowExceptionOnFailingStatusCode(false);
      Page page = client.getPage(getPath("index.jsp"));
      Assert.assertEquals(200, page.getWebResponse().getStatusCode());
   }

   protected String getPath(String page)
   {
      // TODO: this should be moved out and be handled by Arquillian
      return "http://localhost:8080/test/" + page;
   }
}
