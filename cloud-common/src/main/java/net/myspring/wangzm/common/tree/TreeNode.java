package net.myspring.wangzm.common.tree;

import com.google.common.collect.Lists;

import java.util.List;

public class TreeNode {
	private String id;
	private String label;
	private List<TreeNode> children = Lists.newArrayList();

	public TreeNode(){}

	public TreeNode(String id, String label){
		this.id = id;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
}
