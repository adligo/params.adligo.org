package org.adligo.models.params.shared;


public class TagMatcher {
	public static final String NO_PARENT_TAG_FOUND_FOR = "No parent tag found for ";
	private TagInfoMutant root = null;
	private TagInfoMutant currentParent = null;
	
	public boolean addHeader(Tag header) {
		if (root == null) {	
			root = new TagInfoMutant();
			currentParent = root;
			String name = header.getName();
			root.setTagName(name);
			root.setHeaderStart(header.getStart());
			root.setHeaderEnd(header.getEnd());
			root.setHasEnder(!header.isSelfEnding());
			
			
			if ("?xml".equals(name)) {
				root.setHasEnder(false);
				return true;
			}
		} else {
			if (header.isSelfEnding()) {
				TagInfoMutant child = new TagInfoMutant();
				currentParent.addChild(child);
				child.setTagName(header.getName());
				child.setHeaderStart(header.getStart());
				child.setHeaderEnd(header.getEnd());
				child.setHasEnder(false);
			} else {
				TagInfoMutant child = new TagInfoMutant();
				currentParent.addChild(child);
				currentParent = child;
				child.setTagName(header.getName());
				child.setHeaderStart(header.getStart());
				child.setHeaderEnd(header.getEnd());
				child.setHasEnder(true);
			}
		}
		return false;
	}
	
	public void addEnder(Tag ender) {
		if (currentParent == null) {
			return;
		}
		String tagName = ender.getName();
		findHeader(ender, tagName, currentParent);
	}

	private void findHeader(Tag ender, String tagName, TagInfoMutant parent) {
		String currentName = parent.getTagName();
		if (currentName.equals(tagName)) {
			parent.setEnderStart(ender.getStart());
			parent.setEnderEnd(ender.getEnd());
			currentParent = parent.getParent();
			if (currentParent == null) {
				currentParent = root;
			}
		} else {
			TagInfoMutant grandParent = parent.getParent();
			if (grandParent == null) {
				//throw new IllegalArgumentException(NO_PARENT_TAG_FOUND_FOR + ender);
				return;
			}
			findHeader(ender, tagName, grandParent);
		}
	}
	
	public TagInfo getRoot() {
		if (root == null) {
			return null;
		}
		if (root.hasEnder()) {
			if (root.getEnderEnd() == -1 || root.getEnderStart() == -1) {
				root.setHasEnder(false);
			}
		}
		return new TagInfo(root);
	}
}
