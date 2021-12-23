import java.util.LinkedList;

public class Snake {
    //body
    public LinkedList<Node>body;
    private Direction direction= Direction.LEFT;
    //die or live
    public  boolean isLive=true;

    public Snake() {
        initSnake();
    }
    public void initSnake(){
        body=new LinkedList<>();
        body.add(new Node(10,10));
        body.add(new Node(11,10));
        body.add(new Node(12,10));
        body.add(new Node(13,10));
    }
    public void move(){

        //蛇头
        Node head=body.getFirst();
        if(isLive){

            switch (direction){
            case UP:{
                body.addFirst(new Node(head.getX(),head.getY()-1));
                break;}
            case DOWN:{
                body.addFirst(new Node(head.getX(),head.getY()+1));
                break;}
            case RIGHT:{
                body.addFirst(new Node(head.getX()+1,head.getY()));
                break;}
            case LEFT:{
                body.addFirst(new Node(head.getX()-1,head.getY()));
                break;}
        }
            body.removeLast();}
        //live or die
        head=body.getFirst();
        if(head.getX()<0||head.getY()<0||head.getX()>30||head.getY()>30)isLive=false;
        for(int i=1;i<body.size();i++){
            Node node=body.get(i);
            if(head.getX()== node.getX()&&head.getY()==node.getY())isLive=false;
        }

    }

    public LinkedList<Node> getBody() {
        return body;
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
//吃食物
    public void eat(Food food) {
        Node head=body.getFirst();
        switch (direction){
            case UP:{
                body.addFirst(new Node(head.getX(),head.getY()-1));
                break;}
            case DOWN:{
                body.addFirst(new Node(head.getX(),head.getY()+1));
                break;}
            case RIGHT:{
                body.addFirst(new Node(head.getX()+1,head.getY()));
                break;}
            case LEFT:{
                body.addFirst(new Node(head.getX()-1,head.getY()));
                break;}
        }
    }
}
