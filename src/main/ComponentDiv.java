package main;

import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Point;

public class ComponentDiv
{
	private int order;
	String name;
	private Map<String,Component> components;
	private int x,y; // 块整体位置
	
	public ComponentDiv()
	{
		order=0;
		name="default name";
	}
	public ComponentDiv(String nameIn)
	{
		order=0;
		name=nameIn;
	}
	
	// 为div添加一个组件
	protected void registerComponent(String nameIn,JComponent jcomponentIn)
	{
		registerComponent(nameIn,jcomponentIn,0,0);
	}
	protected void registerComponent(String nameIn,JComponent jcomponentIn,int xIn,int yIn)
	{
		jcomponentIn.setLocation(this.x+xIn, this.y+yIn);
		
		Component temp_component=new Component(jcomponentIn,xIn,yIn);
		components.put(nameIn, temp_component);
		order++;
	}
	protected void registerComponent(JComponent jcomponentIn)
	{
		registerComponent(getNextDefaultComponentName(),jcomponentIn,0,0);
	}
	protected void registerComponent(JComponent jcomponentIn,int xIn,int yIn)
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
	public long getDivPosX()
	{
		return x;
	}
	public long getDivPosY()
	{
		return y;
	}
	public long getComponentPosX(String nameIn)
	{
		return 0;
	}
	public long getComponentPosY(String nameIn)
	{
		return 0;
	}
	
	public void setDivPosX(int xIn)
	{
		;
	}
	public void setDivPosY(int yIn)
	{
		;
	}
	public void setDivPos(int xIn,int yIn)
	{
		;
	}
	public void setComponentPosX(int xIn)
	{
		;
	}
	public void setComponentPosY(int yIn)
	{
		;
	}
	public void setComponentPos(int xIn,int yIn)
	{
		;
	}
	
	public void moveDivPosX(int xIn)
	{
		;
	}
	public void moveDivPosY(int yIn)
	{
		;
	}
	public void moveDivPos(int xIn,int yIn)
	{
		;
	}
	public void moveComponentPosX(int xIn)
	{
		;
	}
	public void moveComponentPosY(int yIn)
	{
		;
	}
	public void moveComponentPos(int xIn,int yIn)
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
		;
	}
	public void setComponentEnable(String componentNameIn,boolean enableIn)
	{
		;
	}
	
	public void setDivFont(Font fontIn)
	{
		for(Component temp_component:components.values())
		{
			temp_component.getComponent().setFont(fontIn);
		}
	}
	public void setComponentFont(Font fontIn)
	{
		;
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
	}
	
	private String getNextDefaultComponentName()
	{
		return SGUIU.defaultComponentName+order;
	}
}
