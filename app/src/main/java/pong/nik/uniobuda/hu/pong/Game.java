package pong.nik.uniobuda.hu.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by VIP on 2016.04.20..
 */
public class Game extends View {
    private Rect ore;
    private Paint black;
    private Paint white;
    Coord ball;
    Tile prec1,prec2;
    private boolean b_init;
    private int ball_x_direction,ball_y_direction;

    public Game(Context context) {
        super(context);
        ore=new Rect();
        black=new Paint();
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);

        white=new Paint();
        white.setColor(Color.WHITE);
        white.setStyle(Paint.Style.FILL);
        b_init=false;
        ball_x_direction=1;
        ball_y_direction=1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!b_init){
            b_init=true;
            init(getWidth(),getHeight());
        }
        //clear view
        this.ore.set(0,0, canvas.getWidth(), canvas.getHeight());
        canvas.drawRect(this.ore, this.black);
        //draw ball
        canvas.drawCircle(ball.x, ball.y, 10,this.white);

        //recalculate ball pos
        if (ball.x+10>getWidth()){
            ball_x_direction=-1;
        }else if(ball.x-10<0){
            ball_x_direction=1;
        }
        if (ball.y+10>getHeight()){
            ball_y_direction=-1;
        }else if(ball.y-10<0){
            ball_y_direction=1;
        }

        ball.x+=5*ball_x_direction;
        ball.y+=7*ball_y_direction;

        //draw player1
        canvas.drawRect(prec1.x, prec1.y, prec1.x2, prec1.y2, this.white);
        //draw player2
        canvas.drawRect(prec2.x,prec2.y,prec2.x2, prec2.y2,this.white);

        invalidate();
    }
    private void init(int w, int h){
        ball=new Coord(20,20);
        int ex=1; int ey=1;
        prec1=new Tile(w-10,h/2,h,w,ex,ey);
        prec2=new Tile(1,h/2,h,w,ex,ey);
    }
}
