import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Button {
	public Rectangle place;
	private boolean isHovered = false;
	public String text;
	public Color defaultColor = Color.WHITE, hoverColor = Color.BLACK;
	private Event clickEvent;
	
	public Button(Rectangle p, String txt, Color dc, Color hc, Event ev){
		place = new Rectangle(p);
		text = new String(txt);
		defaultColor = dc;
		hoverColor = hc;
		clickEvent = ev;
	}
	
	public void draw(Graphics g){
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(text, g);
        int x = (place.x + (place.width/2) - (int) (r.getWidth()) / 2);
        int y = (place.y + (place.height/2) - (int)(r.getHeight()/2) + fm.getAscent()); 
        g.setColor(Color.BLACK);
        g.drawString(text, x, y);
        if(isHovered)g.setColor(hoverColor);
        else g.setColor(defaultColor);
        g.drawRect(place.x, place.y, place.width, place.height);
	}
	
	public void ifClick(MouseEvent e){
		if(place.contains(e.getPoint())){
			clickEvent.func();
		}
	}
	
	public void checkHover(MouseEvent e){
		if(place.contains(e.getPoint())){
			isHovered = true;
		}else isHovered = false;
	}
	
	static interface Event{
		public void func();
	}
	
}
