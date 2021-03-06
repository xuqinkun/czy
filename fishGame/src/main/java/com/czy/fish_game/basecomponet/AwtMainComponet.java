package com.czy.fish_game.basecomponet;

import com.czy.fish_game.mainsurface.MainSurface;
import com.czy.fish_game.manager.CannonManager;
import com.czy.fish_game.manager.GameInitManager;
import com.czy.fish_game.manager.LayoutManager;
import com.czy.fish_game.model.GamingInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AwtMainComponet{
	public static void main(String[] args) throws Exception  {
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		JFrame frame = new JFrame();
        GamingInfo.getGamingInfo().setGaming(true);
        GamingInfo.getGamingInfo().setScreenWidth(2000);
    	GamingInfo.getGamingInfo().setScreenHeight(1000);
    	frame.setSize(GamingInfo.getGamingInfo().getScreenWidth(), GamingInfo.getGamingInfo().getScreenHeight());
    	frame.setUndecorated(true); // 去掉窗口的装饰 
//    	frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定的窗口装饰风格 
//		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		MainSurface pane = new MainSurface();
		GamingInfo.getGamingInfo().setSurface(pane);
		frame.setContentPane(pane);
//		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(GameInitManager.getGameInitManager().isIniting()){
					return ;
				}
				//屏幕被触摸
				//先看布局管理器是否有相应
				if(!LayoutManager.getLayoutManager().onClick(e.getX(), e.getY())){
					//发射子弹
					CannonManager.getCannonManager().shot(e.getX(),  e.getY());
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
//		frame.pack();
		pane.action();
		/* 创建一个线程来异步初始化游戏内容：使用游戏初始化管理器初始化游戏*/
    	new Thread(()->GameInitManager.getGameInitManager().init()).start();
	}

}
