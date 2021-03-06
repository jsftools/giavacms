/*
 * Copyright 2013 GiavaCms.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.giavacms.exhibition.controller.request;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.giavacms.common.annotation.HttpParam;
import org.giavacms.common.annotation.OwnRepository;
import org.giavacms.common.controller.AbstractRequestController;
import org.giavacms.common.model.Search;
import org.giavacms.exhibition.model.Museum;
import org.giavacms.exhibition.model.Participant;
import org.giavacms.exhibition.repository.MuseumRepository;
import org.giavacms.exhibition.service.ParticipantService;

@Named
@RequestScoped
public class MuseumRequestController extends AbstractRequestController<Museum>
         implements Serializable
{

   private static final long serialVersionUID = 1L;

   public static final String ID_PARAM = "museum";
   public static final String CURRENT_PAGE_PARAM = "start";

   @Inject
   @HttpParam("q")
   String search;

   @Inject
   @HttpParam(ID_PARAM)
   String id;

   @Inject
   @HttpParam(CURRENT_PAGE_PARAM)
   String start;

   @Inject
   @HttpParam("exhibition")
   String exhibition;

   Search<Participant> customSearch = new Search<Participant>(Participant.class);

   @Override
   protected void initSearch()
   {
      customSearch.getObj().getSubject().setType(Museum.TYPE);
      customSearch.getObj().getExhibition().setId(exhibition);
      customSearch.getObj().getSubject().setSurname(search);
      super.initSearch();
   }

   @Inject
   @OwnRepository(MuseumRepository.class)
   MuseumRepository museumRepository;

   @Inject
   ParticipantService participantService;

   @SuppressWarnings("unchecked")
   @Override
   public List<Museum> loadPage(int startRow, int pageSize)
   {
      return (List<Museum>) participantService.getList(customSearch, startRow, pageSize);
   }

   @Override
   public int totalSize()
   {
      return participantService.getListSize(customSearch);
   }

   @Override
   public String getIdParam()
   {
      return ID_PARAM;
   }

   @Override
   public String getCurrentPageParam()
   {
      return CURRENT_PAGE_PARAM;
   }

   public boolean isScheda()
   {
      return getElement() != null && getElement().getId() != null;
   }

}
