/*
 * Copyright 2013 GiavaCms.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.giavacms.base.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE) // non ci risparmia una
// seconda query per calcolare il resto dei dati secondo me, e per contro rende
// "huge" la tabella PAGE
@Table(name = Page.TABLE_NAME)
@DiscriminatorColumn(name = "EXTENSION", discriminatorType = DiscriminatorType.STRING)
public class Page extends I18nSupport
// implements I18Nable, Serializable
{

   private static final long serialVersionUID = 1L;

   public static final String TABLE_NAME = "Page";

   // ------------------------------------------------------------------------

   boolean active = true;
   boolean clone = false;
   boolean extended = false;
   private String title;
   private String formerTitle;
   private String description;
   private TemplateImpl template;
   private Long templateId;
   private String extension;

   // ------------------------------------------------------------------------

   // transiente, per accumulare il risultato finale
   private String content;

   // ------------------------------------------------------------------------

   public Page()
   {
   }

   // ------------------------------------------------------------------------

   @ManyToOne(fetch = FetchType.LAZY, cascade = { /* CascadeType.PERSIST, */
            CascadeType.MERGE, CascadeType.DETACH })
   public TemplateImpl getTemplate()
   {
      if (template == null)
         this.template = new TemplateImpl();
      return template;
   }

   public void setTemplate(TemplateImpl template)
   {
      this.template = template;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
      if (this.formerTitle == null)
      {
         this.formerTitle = title;
      }
   }

   @Lob
   @Column(length = 1024)
   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public boolean isActive()
   {
      return active;
   }

   public void setActive(boolean active)
   {
      this.active = active;
   }

   public boolean isClone()
   {
      return clone;
   }

   public void setClone(boolean clone)
   {
      this.clone = clone;
   }

   public String getExtension()
   {
      return extension;
   }

   public void setExtension(String extension)
   {
      this.extension = extension;
   }

   // ------------------------------------------------------------------------

   @Transient
   public String getContent()
   {
      return content;
   }

   public void setContent(String content)
   {
      this.content = content;
   }

   // ------------------------------------------------------------------------

   @Override
   public boolean equals(Object o)
   {
      if (!(o instanceof Page))
         return false;
      Page p = (Page) o;
      return p.getId() == null ? false : p.getId().equals(this.getId());
   }

   @Override
   public String toString()
   {
      return "Page [id="
               + this.getId()
               + ", active="
               + active
               + ", title="
               + title
               + ", description="
               + description
               + ", templateImplId="
               + (templateId) + ", content=" + content
               + "]";
   }

   @Override
   public int hashCode()
   {
      return (this.getId() != null) ? this.getId().hashCode() : super
               .hashCode();
   }

   @Transient
   public boolean isExtended()
   {
      return extended;
   }

   public void setExtended(boolean extended)
   {
      this.extended = extended;
   }

   @Column(name = "template_id", insertable = false, updatable = false)
   public Long getTemplateId()
   {
      return templateId;
   }

   public void setTemplateId(Long templateId)
   {
      this.templateId = templateId;
   }

   @Column(name = "title", insertable = false, updatable = false)
   public String getFormerTitle()
   {
      return formerTitle;
   }

   public void setFormerTitle(String formerTitle)
   {
      this.formerTitle = formerTitle;
   }

}
