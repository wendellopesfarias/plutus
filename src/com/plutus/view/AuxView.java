package com.plutus.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.plutus.model.Asset;
 
@ManagedBean(name="auxView")
@ViewScoped
public class AuxView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = -8124096211344166470L;
	private TreeNode root;
	private TreeNode selectedNode;
	private List<Asset> assets;
	private Asset selectedAsset;
     
    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode("Node 0", root);
        TreeNode node1 = new DefaultTreeNode("Node 1", root);
         
        TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
        TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);
         
        TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
         
        node1.getChildren().add(new DefaultTreeNode("Node 1.1"));
        node00.getChildren().add(new DefaultTreeNode("Node 0.0.0"));
        node00.getChildren().add(new DefaultTreeNode("Node 0.0.1"));
        node01.getChildren().add(new DefaultTreeNode("Node 0.1.0"));
        node10.getChildren().add(new DefaultTreeNode("Node 1.0.0"));
        root.getChildren().add(new DefaultTreeNode("Node 2"));
        
        assets = new ArrayList<>();
        
        for (int i = 0; i < 20; i++) {
        		Asset a = new Asset();
			a.setId(i+"");
			a.setTicker("A"+i);
        		a.setAsset_name("Asset Name"+i);
        		assets.add(a);
		}
        
    }
    
    
 
    public TreeNode getRoot() {
        return root;
    }

	public List<Asset> getAssets() {
		return assets;
	}
	
	public TreeNode getSelectedNode() {
		System.out.println(""+selectedNode);
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}

	public Asset getSelectedAsset() {
		return selectedAsset;
	}

	public void setSelectedAsset(Asset selectedAsset) {
		this.selectedAsset = selectedAsset;
	}
    
 
}
