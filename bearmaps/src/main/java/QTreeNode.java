/**
 * Created by changyeonclarapark on 7/12/16.
 */
public class QTreeNode {
    public double ulLat;
    public double ulLon;
    public double lrLat;
    public double lrLon;

    public String tileName;
    public int depth;

    public QTreeNode UL;
    public QTreeNode UR;
    public QTreeNode LL;
    public QTreeNode LR;

    public QTreeNode(String tileName, double ulLat, double ulLon, double lrLat, double lrLon, int depth) {
        this.tileName = tileName;
        this.ulLat = ulLat;
        this.ulLon = ulLon;
        this.lrLat = lrLat;
        this.lrLon = lrLon;
        this.depth = depth;
    }

    public void fillTree() {
        if(this.depth > 7) {
            return;
        } else {
            this.UL = new QTreeNode(this.tileName + "1", this.ulLat, this.ulLon, (this.ulLat + this.lrLat)/2,
                    (this.ulLon + this.ulLon)/2, this.depth + 1);
            this.UL.fillTree();

            //etc
            this.UR.fillTree();
            this.LL.fillTree();
            this.LR.fillTree();
        }
    }

    //contains method that takes in point
}

