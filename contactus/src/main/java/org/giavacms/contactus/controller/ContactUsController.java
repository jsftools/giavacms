/*
 * Copyright 2013 GiavaCms.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.giavacms.contactus.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.giavacms.common.annotation.BackPage;
import org.giavacms.common.annotation.EditPage;
import org.giavacms.common.annotation.ListPage;
import org.giavacms.common.annotation.OwnRepository;
import org.giavacms.common.annotation.ViewPage;
import org.giavacms.common.controller.AbstractLazyController;
import org.giavacms.contactus.model.ContactUs;
import org.giavacms.contactus.repository.ContactUsRepository;

@Named
@SessionScoped
public class ContactUsController extends AbstractLazyController<ContactUs>
{

   private static final long serialVersionUID = 1L;

   // --------------------------------------------------------
   @BackPage
   public static String BACK = "/private/administration.xhtml";
   @ViewPage
   @ListPage
   @EditPage
   public static String LIST = "/private/contactus/contacts/list.xhtml";

   // --------------------------------------------------------

   @Inject
   @OwnRepository(ContactUsRepository.class)
   ContactUsRepository contactUsRepository;

   // --------------------------------------------------------

   public ContactUsController()
   {
   }

}
