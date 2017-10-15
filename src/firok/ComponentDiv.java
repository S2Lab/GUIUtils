package firok;

import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Point;

public class ComponentDiv
{
	private int order;
	String name;
	private HashMap<String,Component> components;
	private int x,y; // 块整体位置
	
	public ComponentDiv()
	{
		order=0;
		name="default name";
		components=new HashMap<String,Component>();
	}
	public ComponentDiv(String nameIn)
	{
		order=0;
		name=nameIn;
		components=new HashMap<String,Component>();
	}
	
	// 为div添加一个组件
	public void registerComponent(String nameIn,JComponent jcomponentIn)
	{
		registerComponent(nameIn,jcomponentIn,0,0);
	}
	public void registerComponent(String nameIn,JComponent jcomponentIn,int xIn,int yIn)
	{
		jcomponentIn.setLocation(this.x+xIn, this.y+yIn);
		
		Component temp_component=new Component(jcomponentIn,xIn,yIn);
		components.put(nameIn, temp_component);
		order++;
	}
	public void registerComponent(JComponent jcomponentIn)
	{
		registerComponent(getNextDefaultComponentName(),jcomponentIn,0,0);
	}
	public void registerComponent(JComponent jcomponentIn,int xIn,int yIn)
	{
		registerComponent(getNextDefaultComponentName(),jcomponentIn,xIn,yIn);
	}
	
	public void addDivToPanel(JPanel panelIn)
	{
		for(Component temp_component:components.values())
		{
			panelIn.add(temp_component.component);
		}
	}
	
	public boolean hasComponent(String nameIn)
	{
		return components.containsKey(nameIn);
	}
	public JComponent getComponent(String nameIn)
	{
		return components.get(nameIn).getComponent();
	}
	
	// 位置相关操作
	public int getDivPosX()
	{
		return x;
	}
	public int getDivPosY()
	{
		return y;
	}
	public int getComponentPosX(String nameIn)
	{
		return 0;
	}
	public int getComponentPosY(String nameIn)
	{
		return 0;
	}
	
	public void setDivPosX(int xIn)
	{
		setDivPos(xIn,0);
	}
	public void setDivPosY(int yIn)
	{
		setDivPos(0,yIn);
	}
	public void setDivPos(int xIn,int yIn)
	{
		int old_x=getDivPosX();
		int old_y=getDivPosY();
		
		moveDivPos(xIn-old_x,yIn-old_y);
	}
	public void setComponentPosX(int xIn)
	{
		setComponentPos(xIn,0);
	}
	public void setComponentPosY(int yIn)
	{
		setComponentPos(0,yIn);
	}
	public void setComponentPos(int xIn,int yIn)
	{
		System.out.println("没有实现这个功能");
	}
	
	public void moveDivPosX(int xIn)
	{
		moveDivPos(xIn,0);
	}
	public void moveDivPosY(int yIn)
	{
		moveDivPos(0,yIn);
	}
	public void moveDivPos(int xIn,int yIn)
	{
		x+=xIn;
		y+=yIn;
		
		for(Component component:components.values())
		{
			component.movePos(xIn, yIn);
		}
	}
	public void moveComponentPosX(String componentNameIn,int xIn)
	{
		moveComponentPos(componentNameIn,xIn,0);
	}
	public void moveComponentPosY(String componentNameIn,int yIn)
	{
		moveComponentPos(componentNameIn,0,yIn);
	}
	public void moveComponentPos(String componentNameIn,int xIn,int yIn)
	{
		;
	}
	
	public void hideDiv()
	{
		for(Component temp_component:components.values())
		{
			temp_component.hide();
		}
	}
	public void showDiv()
	{
		for(Component temp_component:components.values())
		{
			temp_component.show();
		}
	}
	public void setDivEnable(boolean enableIn)
	{
		for(Component temp_component:components.values())
		{
			temp_component.setEnable(enableIn);;
		}
	}
	public void setComponentEnable(String componentNameIn,boolean enableIn)
	{
		JComponent component=getComponent(componentNameIn);
		if(component!=null)
			component.setEnabled(enableIn);
	}
	
	public void setDivFont(Font fontIn)
	{
		for(Component temp_component:components.values())
		{
			temp_component.getComponent().setFont(fontIn);
		}
	}
	public void setComponentFont(String componentNameIn,Font fontIn)
	{
		JComponent component=getComponent(componentNameIn);
		if(component!=null)
			component.setFont(fontIn);
	}
	
	protected class Component
	{
		private JComponent component; // 组件主体
		private int x,y; // 组件相对位置
		
		public Component(JComponent componentIn,int xIn,int yIn)
		{
			component=componentIn;
			x=xIn;
			y=yIn;
		}
		
		public JComponent getComponent()
		{
			return component;
		}
		
		public void setPos(int xIn,int yIn) // 设定偏移量
		{
			int motion_x=x-xIn;
			int motion_y=y-yIn;
			
			x=xIn;
			y=yIn;
			
			movePos(motion_x,motion_y);
		}
		public int getPosX() // 获取偏移量x
		{
			return x;
		}
		public int getPosY() // 获取偏移量y
		{
			return y;
		}
		public void setActualPos(int xIn,int yIn) // 设定实际位置
		{
			Point loc=component.getLocation();
			
			int motion_x=loc.x-xIn;
			int motion_y=loc.y-yIn;
			
			movePos(motion_x,motion_y);
		}
		public void setActualPosX(int xIn)
		{
			setActualPos(xIn,0);
		}
		public void setActualPosY(int yIn)
		{
			setActualPos(0,yIn);
		}
		public int getActualPosX() // 获取实际位置x
		{
			return component.getX();
		}
		public int getActualPosY() // 获取实际位置y
		{
			return component.getY();
		}
		
		public void movePos(int xIn,int yIn)
		{
			Point loc=component.getLocation();
			
			x+=xIn;
			y+=yIn;
			
			loc.x+=xIn;
			loc.y+=yIn;
			
			component.setLocation(loc);
		}
		public void movePosX(int xIn)
		{
			movePos(xIn,0);
		}
		public void movePosY(int yIn)
		{
			movePos(0,yIn);
		}
		
		// 可见性
		public void show()
		{
			component.setVisible(true);
		}
		public void hide()
		{
			component.setVisible(false);
		}
		public void setVisible(boolean visibleIn)
		{
			component.setVisible(visibleIn);
		}
		
		public void setEnable(boolean enableIn)
		{
			component.setEnabled(enableIn);
		}
	}
	
	private String getNextDefaultComponentName()
	{
		return GUIU.defaultComponentName+order;
	}
}
