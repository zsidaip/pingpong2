package pong.nik.uniobuda.hu.pong;

public class Tile {
    public int x,y,x2,y2;//x,y
    public int height,width;//screen size
    public int ex,ey;//x,y tengely menti egységnyi pixelszám
    private int center;//y tengelymenti kozeppontja a teglalapnak
    private int w_size=9;//objektum szelesseg
    private int h_size=50;//objektum magassag

    public Tile(int x, int y, int height, int width, int ex, int ey){
        this.x = x; this.x2=this.x+w_size*ex;
        this.y = y; this.y2=this.y+h_size*ey;
        this.center=this.y+((this.y2-this.y/2));
        this.height = height;
        this.width = width;
        this.ex = ex;
        this.ey = ey;
    }

    public void Move(int direction,int speed){
        this.y+=direction*this.ey*speed;
        if(this.y<0)this.y=0;
        this.y2=this.y+(h_size*ey);

        if(this.y2>height) {
            this.y2 = height;
            this.y = height-(h_size*ey);
        }
    }
}
