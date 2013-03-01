package org.giavacms.richcontent.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.giavacms.base.common.util.HtmlUtils;
import org.giavacms.base.model.Page;
import org.giavacms.base.model.attachment.Document;
import org.giavacms.base.model.attachment.Image;
import org.giavacms.richcontent.model.type.RichContentType;

@Entity
@DiscriminatorValue(value = RichContent.EXTENSION)
public class RichContent extends Page {

	private static final long serialVersionUID = 1L;
	public static final String EXTENSION = "RichContent";

	private String preview;
	private String content;
	private String author;
	private Date date;
	private RichContentType richContentType;
	private List<Document> documents;
	private List<Image> images;
	private boolean highlight;

	public RichContent() {
		super();
		super.setExtension(EXTENSION);
	}

	@Transient
	public String getContentN() {
		return HtmlUtils.normalizeHtml(this.content);
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "RichContent_Document", joinColumns = @JoinColumn(name = "RichContent_id"), inverseJoinColumns = @JoinColumn(name = "documents_id"))
	public List<Document> getDocuments() {
		if (this.documents == null)
			this.documents = new ArrayList<Document>();
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void addDocument(Document document) {
		getDocuments().add(document);
	}

	@Transient
	public int getDocSize() {
		return getDocuments().size();
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "RichContent_Image", joinColumns = @JoinColumn(name = "RichContent_id"), inverseJoinColumns = @JoinColumn(name = "images_id"))
	public List<Image> getImages() {
		if (this.images == null)
			this.images = new ArrayList<Image>();
		return images;
	}

	@Transient
	public Image getImage() {
		if (getImages() != null && getImages().size() > 0)
			return getImages().get(0);
		return null;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void addImage(Image image) {
		getImages().add(image);
	}

	@Transient
	public int getImgSize() {
		return getImages().size();
	}

	@ManyToOne
	public RichContentType getRichContentType() {
		if (richContentType == null)
			richContentType = new RichContentType();
		return richContentType;
	}

	public void setRichContentType(RichContentType richContentType) {
		this.richContentType = richContentType;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	@Lob
	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "RichContent [id=" + super.getId() + ", active="
				+ super.isActive() + ", title=" + super.getTitle()
				+ ", preview=" + preview + ", content=" + content + ", author="
				+ author + ", date=" + date + ", richContentType="
				+ richContentType.getName() + ", highlight=" + highlight + "]";
	}

}
