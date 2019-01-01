package moviescraper.doctord.model.dataitem;

public class OriginalTitle extends MovieDataItem {
	private String originalTitle;
	public static final OriginalTitle BLANK_ORIGINALTITLE = new OriginalTitle("");

	public String getOriginalTitle() {
		return originalTitle;
	}

	public OriginalTitle() {
		this.originalTitle = "";
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = sanitizeString(originalTitle);
	}

	@Override
	public String toString() {
		return "OriginalTitle [originalTitle=\"" + originalTitle + "\"" + dataItemSourceToString() + "]";
	}

	public OriginalTitle(String originalTitle) {
		setOriginalTitle(originalTitle);
	}

	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
