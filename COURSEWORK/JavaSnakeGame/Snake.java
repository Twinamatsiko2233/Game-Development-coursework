import java.awt.Point;
import java.util.ArrayList;

public class Snake {
    ArrayList<Point> body = new ArrayList<>();
    Point head;
    char dir = 'U';

    public Snake(int x, int y) {
        reset(x, y);
    }

    void move() {
        Point n = new Point(head);
        if(dir=='U') n.y--;
        if(dir=='D') n.y++;
        if(dir=='L') n.x--;
        if(dir=='R') n.x++;
        body.add(0,n);
        head = n;
        body.remove(body.size()-1);
    }

    void grow() {
        body.add(new Point(body.get(body.size()-1)));
    }

    boolean selfCollision() {
        for(int i=1;i<body.size();i++)
            if(head.equals(body.get(i))) return true;
        return false;
    }

    boolean wallCollision() {
        return head.x<0 || head.x>=GamePanel.GRID_WIDTH || head.y<0 || head.y>=GamePanel.GRID_HEIGHT;
    }

    void reset(int x, int y) {
        body.clear();
        body.add(new Point(x,y));
        body.add(new Point(x,y+1));
        body.add(new Point(x,y+2));
        head = body.get(0);
        dir = 'U';
    }
}
