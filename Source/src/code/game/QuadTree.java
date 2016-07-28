package code.game;

import java.util.ArrayList;
import yansuen.game.GameObject;

/**
 * @author voki
 */
public class QuadTree {

    private final int maxObjects = 3;
    private final int maxLevel = 5;

    //QuadTree node bounds
    float x;
    float y;
    float height;
    float width;

    private int level;
    private ArrayList<GameObject> objects;
    private QuadTree[] nodes;

    public QuadTree(int level, float x, float y, float width, float height) {

        this.level = level;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        objects = new ArrayList<>();
        nodes = new QuadTree[4];
    }

    /*
    deletes Quadtree
     */
    public void clear() {

        objects.clear();

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].clear();
                nodes[i] = null;
            }
        }
    }

    /*
    splits Quadtree into 4 parts
     */
    private void split() {
        float subHeight =  height/ 2;
        float subWidth =  width/ 2;
        
        nodes[0] = new QuadTree(level + 1, x, y, subWidth, subHeight);//left upper rectangle
        nodes[1] = new QuadTree(level + 1, x + subWidth, y, subWidth, subHeight);//right upper rectangle
        nodes[2] = new QuadTree(level + 1, x, y + subHeight, subWidth, subHeight);//left lower rectangle
        nodes[3] = new QuadTree(level + 1, x + subWidth, y + subHeight, subWidth, subHeight);//right lower rectangle
    }

    private int getObjectIndex(GameObject object) {
        int index = -1;
        float horizontalMiddle = y + height / 2;
        float verticalMiddle = x + width / 2;
        
        float objectY = object.getData().getY();
        float objectX = object.getData().getX();
        float objectHeight = object.getData().getHeight();
        float objectWidth = object.getData().getWidth();
                
                
        boolean topRectangle = objectY + objectHeight < horizontalMiddle && objectY < horizontalMiddle;
        boolean bottomRectangle = objectY > horizontalMiddle;

        if (objectX < verticalMiddle && objectX + objectWidth < verticalMiddle) {
            if (topRectangle) {
                index = 0;
            } else if (bottomRectangle) {
                index = 2;
            }
        } else if (objectX > verticalMiddle) {
            if (topRectangle) {
                index = 1;
            } else if (bottomRectangle) {
                index = 3;
            }
        }
        return index;
    }

    public void insert(GameObject object) {
        if (nodes[0] != null) {
            int index = getObjectIndex(object);
            if (index != -1) {
                nodes[index].insert(object);
                return;
            }
        }
        objects.add(object);

        if (objects.size() >= maxObjects && level < maxLevel) {
            if (nodes[0] == null) {
                split();
            }
            int i = 0;
            while (i < objects.size()) {
                int index = getObjectIndex(objects.get(i));
                if (index != -1) {
                    nodes[index].insert(objects.remove(i));
                } else {
                    i++;
                }
            }
        }
    }

    public ArrayList<GameObject> retrieve(ArrayList<GameObject> list, GameObject object) {
        int index = getObjectIndex(object);
        if (index != -1 && nodes[0] != null) {
            nodes[index].retrieve(list, object);
        }
        list.addAll(objects);

        return list;
    }

    public void printTree() {
        System.out.println(this);
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].printTree();
            }
        }

    }

    @Override
    public String toString() {
        return "QuadTree{" + "\tmaxObj=" + maxObjects + ",\tmaxLvl=" + maxLevel
                + ",\tx=" + x + ",\ty=" + y + ",\tw=" + width + ",\th=" + height + ",\tlvl=" + level + ",\tobj=" + objects.size() + '}';
    }

}
