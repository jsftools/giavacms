package org.giavacms.exhibition.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.giavacms.base.model.attachment.Image;

@Entity
@Table(name = "ExhibitionTestimonial")
@DiscriminatorValue(value = Testimonial.TYPE)
public class Testimonial extends Subject implements Serializable
{

   private static final long serialVersionUID = 1L;
   public static final String TYPE = "TES";

   private String artistName;
   private boolean active = true;
   private Image image;
   private Image newImage;

   public Testimonial()
   {
      super.setType(TYPE);
   }

   @Transient
   public String getNameSurname()
   {
      return (getName() == null ? "" : getName() + " ")
               + (getSurname() == null ? "" : getSurname());
   }

   public String getArtistName()
   {
      return artistName;
   }

   public void setArtistName(String artistName)
   {
      this.artistName = artistName;
   }

   public boolean isActive()
   {
      return active;
   }

   public void setActive(boolean active)
   {
      this.active = active;
   }

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "image_id", unique = true, nullable = true, insertable = true, updatable = true)
   public Image getImage()
   {
      return image;
   }

   public void setImage(Image image)
   {
      this.image = image;
   }

   @Transient
   public Image getNewImage()
   {
      if (newImage == null)
         this.newImage = new Image();
      return newImage;
   }

   public void setNewImage(Image newImage)
   {
      this.newImage = newImage;
   }

   @Override
   public String toString()
   {
      return "Testimonial []";
   }

}
