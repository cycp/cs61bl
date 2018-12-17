/**
 * Created by changyeonclarapark on 7/12/16.
 */
public class QuadTree {
    private QTreeNode root;

    public QuadTree(double ulLat, double ulLon, double lrLat, double lrLon) {
        root = new QTreeNode("root", ulLat, ulLon, lrLat, lrLon, 0);
        root.UL = new QTreeNode("1", root.ulLat, root.ulLon, (root.ulLat + root.lrLat)/2, (root.ulLon + root.ulLon)/2, 1);
        root.UR = new QTreeNode("2", root.ulLat, (root.ulLon + root.lrLon)/2, (root.ulLat + root.lrLat)/2, root.lrLon, 1);
        root.LL = new QTreeNode("3", (root.ulLat + root.lrLat)/2, root.ulLon, (root.ulLat + root.lrLat)/2, root.lrLon, 1);
        root.LR = new QTreeNode("4", (root.ulLat + root.lrLat)/2, (root.ulLon + root.lrLon)/2, root.lrLat, root.lrLon, 1);
        root.fillTree();
    }

    //public void intersectionQuery(//query window)

//getULTile, returns Node
    //getULTile (root)

}
